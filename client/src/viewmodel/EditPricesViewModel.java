package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import model.Model;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class EditPricesViewModel implements PropertyChangeListener,
    UnnamedPropertyChangeSubject
{
  private Model model;
  private ViewState viewState;
  private PropertyChangeSupport property;

  private StringProperty standard;
  private StringProperty vip;
  private StringProperty fanta;
  private StringProperty cola;
  private StringProperty pepsi;
  private StringProperty redbull;
  private StringProperty tuborg;
  private StringProperty candy;
  private StringProperty nachos;
  private StringProperty oreo;
  private StringProperty popcorn;
  private StringProperty peanuts;
  private ArrayList<StringProperty> fields;

  public EditPricesViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    property = new PropertyChangeSupport(this);

    this.standard = new SimpleStringProperty(this, "standard");
    this.vip = new SimpleStringProperty(this, "vip");
    this.candy = new SimpleStringProperty(this, "candies");
    this.cola = new SimpleStringProperty(this, "cola");
    this.popcorn = new SimpleStringProperty(this, "popcorn");
    this.peanuts = new SimpleStringProperty(this, "peanuts");
    this.nachos = new SimpleStringProperty(this, "nachos");
    this.oreo = new SimpleStringProperty(this, "oreo");
    this.tuborg = new SimpleStringProperty(this, "tuborg");
    this.redbull = new SimpleStringProperty(this, "redbull");
    this.pepsi = new SimpleStringProperty(this, "pepsi");
    this.fanta = new SimpleStringProperty(this, "fanta");

    fields = new ArrayList<>();
    fields.add(standard);
    fields.add(vip);
    fields.add(cola);
    fields.add(popcorn);
    fields.add(fanta);
    fields.add(pepsi);
    fields.add(nachos);
    fields.add(oreo);
    fields.add(tuborg);
    fields.add(peanuts);
    fields.add(redbull);
    fields.add(candy);


    this.model.addListener(this);
  }
  public ViewState getViewState()
  {
    return viewState;
  }
  public void reset(){
    standard.set("");
    vip.set("");
    popcorn.set("");
    pepsi.set("");
    peanuts.set("");
    nachos.set("");
    fanta.set("");
    tuborg.set("");
    redbull.set("");
    oreo.set("");
    candy.set("");
    cola.set("");
  }

  public void bindStandard(StringProperty property){
    property.bindBidirectional(standard);
  }
  public void bindVip(StringProperty property){
  property.bindBidirectional(vip);
}
  public void bindCandy(StringProperty property){
    property.bindBidirectional(candy);
  }
  public void bindCola(StringProperty property){
    property.bindBidirectional(cola);
  }
  public void bindPepsi(StringProperty property){
    property.bindBidirectional(pepsi);
  }
  public void bindPopcorn(StringProperty property){
    property.bindBidirectional(popcorn);
  }
  public void bindPeanuts(StringProperty property){
    property.bindBidirectional(peanuts);
  }
  public void bindFanta(StringProperty property){
    property.bindBidirectional(fanta);
  }
  public void bindNachos(StringProperty property){
    property.bindBidirectional(nachos);
  }
  public void bindTuborg(StringProperty property){
    property.bindBidirectional(tuborg);
  }
  public void bindOreo(StringProperty property){
    property.bindBidirectional(oreo);
  }
  public void bindRedbull(StringProperty property){
    property.bindBidirectional(redbull);
  }


  public void changePressed(){
    for (StringProperty property : fields){
      if (!property.get().equals("")){
        if (isDouble(property.get())){
          double newPrice = Double.parseDouble(property.get());
          try
          {
            model.changePrice(property.getName(), newPrice);
          }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
          }
        }else {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setHeaderText("Please input correct price.");
          alert.showAndWait();
        }
      }
    }
    model.changePrices();
  }
  public static boolean isDouble(String input) {
    try {

      Double.parseDouble(input);
      return true;
    } catch (NumberFormatException e) {

      return false;
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() ->{
      if (evt.getPropertyName().equals("fatalError")){
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
