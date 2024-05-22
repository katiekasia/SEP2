package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import model.Model;
import view.ViewHandler;

public class SnackSelectionViewModel
{
private Model model;
private ViewState viewState;
private double total;
private StringProperty totalPrice;

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

  public SnackSelectionViewModel(Model model, ViewState viewState) {
    this.model = model;
    this.viewState = viewState;

    total= 0;
    totalPrice = new SimpleStringProperty();
    size ="";

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
    size = "";
  }

  public void setSize(String size)
  {
    this.size = size;
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

  public void sizeChosen(){
    popcornPrice.set(String.valueOf(model.getPriceForSize("popcorn", size)) + "DKK");
    nachosPrice.set(String.valueOf(model.getPriceForSize("nachos", size)) + "DKK");
  }
}