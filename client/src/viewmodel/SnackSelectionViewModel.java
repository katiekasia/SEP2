package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import model.Model;
import model.Order;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SnackSelectionViewModel implements PropertyChangeListener,
    UnnamedPropertyChangeSubject
{
private Model model;
private ViewState viewState;
private double total;
private StringProperty totalPrice;
private PropertyChangeSupport property;

private StringProperty candyPrice;
private StringProperty nachosPrice;
private StringProperty oreoPrice;
private StringProperty popcornPrice;
private StringProperty peanutsPrice;
private StringProperty colaPrice;
private StringProperty pepsiPrice;
private StringProperty fantaPrice;
private StringProperty tuborgPrice;
private StringProperty redbullPrice;

private StringProperty candiesAmount;
private StringProperty nachosAmount;
private StringProperty oreoAmount;
private StringProperty popcornAmount;
private StringProperty peanutsAmount;
private StringProperty colaAmount;
private StringProperty pepsiAmount;
private StringProperty fantaAmount;
private StringProperty tuborgAmount;
private StringProperty redbullAmount;
private String size;
private boolean isCurrent;



  public SnackSelectionViewModel(Model model, ViewState viewState) {
    this.model = model;
    this.viewState = viewState;

    total= 0;
    totalPrice = new SimpleStringProperty();
    size = "";
isCurrent = false;
    property = new PropertyChangeSupport(this);
    this.model.addListener(this);

    candyPrice = new SimpleStringProperty();
    nachosPrice = new SimpleStringProperty();
    oreoPrice = new SimpleStringProperty();
    popcornPrice = new SimpleStringProperty();
    peanutsPrice = new SimpleStringProperty();
    colaPrice = new SimpleStringProperty();
    pepsiPrice = new SimpleStringProperty();
    fantaPrice = new SimpleStringProperty();
    tuborgPrice = new SimpleStringProperty();
    redbullPrice = new SimpleStringProperty();

    candiesAmount = new SimpleStringProperty();
    nachosAmount = new SimpleStringProperty();
    oreoAmount = new SimpleStringProperty();
    popcornAmount = new SimpleStringProperty();
    peanutsAmount = new SimpleStringProperty();
    colaAmount = new SimpleStringProperty();
    pepsiAmount = new SimpleStringProperty();
    fantaAmount = new SimpleStringProperty();
    tuborgAmount = new SimpleStringProperty();
    redbullAmount = new SimpleStringProperty();
  }
  public void reset(){
    candyPrice.set("");
    nachosPrice.set("");
    oreoPrice.set("");
    popcornPrice.set("");
    peanutsPrice.set("");
    colaPrice.set("");
    pepsiPrice.set("");
    fantaPrice.set("");
    tuborgPrice.set("");
    redbullPrice.set("");

    candiesAmount.set("");
    nachosAmount.set("");
    oreoAmount.set("");
    popcornAmount.set("");
    peanutsAmount.set("");
    colaAmount.set("");
    pepsiAmount.set("");
    fantaAmount.set("");
    tuborgAmount.set("");
    redbullAmount.set("");

    totalPrice.set("");
    total = 0;

  }

  public void setCurrent(boolean current)
  {
    isCurrent = current;
  }

  public StringProperty tuborgPriceProperty()
  {
    return tuborgPrice;
  }

  public StringProperty totalPriceProperty()
  {
    return totalPrice;
  }

  public StringProperty redbullPriceProperty()
  {
    return redbullPrice;
  }

  public StringProperty popcornPriceProperty()
  {
    return popcornPrice;
  }

  public StringProperty pepsiPriceProperty()
  {
    return pepsiPrice;
  }

  public StringProperty peanutsPriceProperty()
  {
    return peanutsPrice;
  }

  public StringProperty oreoPriceProperty()
  {
    return oreoPrice;
  }

  public StringProperty nachosPriceProperty()
  {
    return nachosPrice;
  }

  public StringProperty fantaPriceProperty()
  {
    return fantaPrice;
  }

  public StringProperty colaPriceProperty()
  {
    return colaPrice;
  }

  public StringProperty candyPriceProperty()
  {
    return candyPrice;
  }

  public void bindCandy(StringProperty property){
    property.bindBidirectional(candiesAmount);
  }
  public void bindNachos(StringProperty property){
    property.bindBidirectional(nachosAmount);
  }
  public void bindOreo(StringProperty property){
    property.bindBidirectional(oreoAmount);
  }
  public void bindPopcorn(StringProperty property){
    property.bindBidirectional(popcornAmount);
  }
  public void bindPeanuts(StringProperty property){
    property.bindBidirectional(peanutsAmount);
  }
  public void bindCola(StringProperty property){
    property.bindBidirectional(colaAmount);
  }
  public void bindPepsi(StringProperty property){
    property.bindBidirectional(pepsiAmount);
  }
  public void bindFanta(StringProperty property){
    property.bindBidirectional(fantaAmount);
  }
  public void bindTuborg(StringProperty property){
    property.bindBidirectional(tuborgAmount);
  }
  public void bindRedbull(StringProperty property){
    property.bindBidirectional(redbullAmount);
  }

  public ViewState getViewState()
  {
    return viewState;
  }
  public void controlPrice(String size){

    total = Double.parseDouble(candiesAmount.get()) * model.getPriceForSize("candies",size) +
        Double.parseDouble(nachosAmount.get()) * model.getPriceForSize("nachos", size) +
        Double.parseDouble(oreoAmount.get()) * model.getPriceForSize("oreo", size) +
        Double.parseDouble(popcornAmount.get()) * model.getPriceForSize("popcorn", size) +
        Double.parseDouble(peanutsAmount.get()) * model.getPriceForSize("peanuts", size) +
        Double.parseDouble(colaAmount.get()) * model.getPriceForSize("cola", size) +
        Double.parseDouble(pepsiAmount.get()) * model.getPriceForSize("pepsi", size) +
        Double.parseDouble(fantaAmount.get()) * model.getPriceForSize("fanta", size) +
        Double.parseDouble(tuborgAmount.get()) * model.getPriceForSize("tuborg", size) +
        Double.parseDouble(redbullAmount.get()) * model.getPriceForSize("redbull", size);
    totalPrice.set(total + " DKK");
  }

  public void sizeChosen(String size){
    this.size = size;
    popcornPrice.set(model.getPriceForSize("popcorn", size) + " DKK");
    nachosPrice.set(model.getPriceForSize("nachos", size) + " DKK");
    candyPrice.set(model.getPriceForSize("candies",size) + " DKK");
    oreoPrice.set(model.getPriceForSize("oreo",size) + " DKK");
    peanutsPrice.set(model.getPriceForSize("peanuts",size) + " DKK");
    colaPrice.set(model.getPriceForSize("cola",size) + " DKK");
    pepsiPrice.set(model.getPriceForSize("pepsi",size) + " DKK");
    fantaPrice.set(model.getPriceForSize("fanta",size) + " DKK");
    tuborgPrice.set(model.getPriceForSize("tuborg",size) + " DKK");
    redbullPrice.set(model.getPriceForSize("redbull",size) + " DKK");
  }
  public void refreshUser(){viewState.setUser(model.getUserByUsername(viewState.getUser().getUsername()));}
  public int valueOfField(StringProperty property){
    if (property.get().equals("")){
      return 0;
    }else {
      try
      {
        return Integer.parseInt(property.get());
      }catch (NumberFormatException e){
        return 0;
      }
    }
  }

  public void addPressed(){
    viewState.setUser(model.getUserByUsername(viewState.getUser().getUsername()));
    Order order = model.getOrderByID(viewState.getSelectedOrder().orderIDProperty().get(),viewState.getUser());
try
{
  model.addSnackToOrder("popcorn",valueOfField(popcornAmount),order,viewState.getUser(),size);
  model.addSnackToOrder("nachos",valueOfField(nachosAmount),order,viewState.getUser(),size);
  model.addSnackToOrder("candies",valueOfField(candiesAmount),order,viewState.getUser(),size);
  model.addSnackToOrder("cola",valueOfField(colaAmount),order,viewState.getUser(),size);
  model.addSnackToOrder("pepsi",valueOfField(pepsiAmount),order,viewState.getUser(),size);
  model.addSnackToOrder("peanuts",valueOfField(peanutsAmount),order,viewState.getUser(),size);
  model.addSnackToOrder("fanta",valueOfField(fantaAmount),order,viewState.getUser(),size);
  model.addSnackToOrder("tuborg",valueOfField(tuborgAmount),order,viewState.getUser(),size);
  model.addSnackToOrder("redbull",valueOfField(redbullAmount),order,viewState.getUser(),size);
  model.addSnackToOrder("oreo",valueOfField(oreoAmount),order,viewState.getUser(),size);
}catch (Exception e)
{
  Alert alert = new Alert(Alert.AlertType.ERROR);
  alert.setHeaderText(e.getMessage());
  alert.showAndWait();
}
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() ->{
      if (evt.getPropertyName().equals("fatalError") && isCurrent){
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