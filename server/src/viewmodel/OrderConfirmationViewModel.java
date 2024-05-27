package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Model;
import model.Order;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Optional;

public class OrderConfirmationViewModel implements PropertyChangeListener,
    UnnamedPropertyChangeSubject
{
  private Model model;
  private PropertyChangeSupport property;
  private ObservableList<SimpleOrderView> orders;
  private ObjectProperty<SimpleOrderView> selectedObject;
  private ViewState viewState;

  public OrderConfirmationViewModel(Model model, ViewState viewState){
    this.model = model;
    this.viewState = viewState;
    this.orders = FXCollections.observableArrayList();

    this.selectedObject = new SimpleObjectProperty<>();
    this.model.addListener(this);
    property = new PropertyChangeSupport(this);


  }

  public void loadFromModel(){
    orders.clear();
    Order[] userOrders = model.getAllOrders(model.getUserByUsername(viewState.getUser().getUsername()));
    System.out.println(userOrders.length);
    for (Order order : userOrders){
      SimpleOrderView simpleOrderView = new SimpleOrderView(order);
      orders.add(simpleOrderView);
    }
  }

  public void setOrders(ObservableList<SimpleOrderView> property){
    property.setAll(orders);
  }
  public void bindOrders(ObservableList<SimpleOrderView> propery)
  {
    orders.addListener((ListChangeListener<? super SimpleOrderView>) c -> {
      propery.setAll(orders);
    });
  }
  public void setSelected(){
    selectedObject.set(viewState.getSelectedOrder());
  }
  public ViewState getViewState(){
    return viewState;
  }
  private boolean confirmation(){
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Are you sure you wish to delete the order: " + selectedObject.get().orderIDProperty().get() + "?");
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }
  public void cancelOrderPressed(){
    if (confirmation())
    {

      model.cancelOrder(model.getOrderByID(
          viewState.getSelectedOrder().orderIDProperty().get(), model.getUserByUsername(viewState.getUser().getUsername())), viewState.getUser());
      viewState.setUser(model.getUserByUsername(viewState.getUser().getUsername()));
      loadFromModel();
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() ->{
    if (evt.getPropertyName().equals("addOrder" + viewState.getUser().getUsername()) || evt.getPropertyName().equals("removeOrder" + viewState.getUser().getUsername())){
      loadFromModel();
    }else if (evt.getPropertyName().equals("fatalError")){
      property.firePropertyChange(evt.getPropertyName(),null,evt.getNewValue());
    }});
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
property.removePropertyChangeListener(listener);
  }
}
