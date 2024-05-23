package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Model;
import model.Order;
import model.User;

import java.util.Optional;

public class OrderConfirmationViewModel
{
  private Model model;
  private ObservableList<SimpleOrderView> orders;
  private ObjectProperty<SimpleOrderView> selectedObject;
  private ViewState viewState;

  public OrderConfirmationViewModel(Model model, ViewState viewState){
    this.model = model;
    this.viewState = viewState;
    this.orders = FXCollections.observableArrayList();

    this.selectedObject = new SimpleObjectProperty<>();


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
          viewState.getSelectedOrder().orderIDProperty().get(), viewState.getUser()));
      loadFromModel();
    }
  }
}
