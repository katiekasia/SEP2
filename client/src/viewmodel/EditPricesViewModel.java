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
/**
 * *Manages the view state for editing prices of tickets and snacks
 *  Handles operations related to changing prices,
 *  communicates with the model to update price data, and listens for property changes to update the view.

 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
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
  private boolean isCurrent;
  /**
   * Constructs an EditPricesViewModel with the specified model and view state.
   * Initializes string properties for prices and sets up listeners.
   *
   * @param model     The model to communicate with.
   * @param viewState The view state to manage.
   */
  public EditPricesViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    property = new PropertyChangeSupport(this);

    // Initialize string properties for prices
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

    isCurrent = false;
    this.model.addListener(this);
  }
  /**
   * Gets the view state.
   *
   * @return The view state.
   */
  public ViewState getViewState()
  {
    return viewState;
  }
  /**
   * Resets all price fields to empty strings.
   */
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

  /**
   * A method setting the boolean isCurrent.
   * @param current
   */
  public void setCurrent(boolean current)
  {
    isCurrent = current;
  }
  /**
   * Binds the standard price property to the specified property.
   *
   * @param property The property to bind.
   */
  public void bindStandard(StringProperty property){
    property.bindBidirectional(standard);
  }
  /**
   * Binds the vip price property to the specified property.
   *
   * @param property The property to bind.
   */
  public void bindVip(StringProperty property){
  property.bindBidirectional(vip);
}
  /**
   * Binds the vip candy property to the specified property.
   *
   * @param property The property to bind.
   */
  public void bindCandy(StringProperty property){
    property.bindBidirectional(candy);
  }
  /**
   * Binds the cola price property to the specified property.
   *
   * @param property The property to bind.
   */
  public void bindCola(StringProperty property){
    property.bindBidirectional(cola);
  }
  /**
   * Binds the pepsi price property to the specified property.
   *
   * @param property The property to bind.
   */
  public void bindPepsi(StringProperty property){
    property.bindBidirectional(pepsi);
  }
  /**
   * Binds the popcorn price property to the specified property.
   *
   * @param property The property to bind.
   */
  public void bindPopcorn(StringProperty property){
    property.bindBidirectional(popcorn);
  }
  /**
   * Binds the peanuts price property to the specified property.
   *
   * @param property The property to bind.
   */
  public void bindPeanuts(StringProperty property){
    property.bindBidirectional(peanuts);
  }
  /**
   * Binds the fanta price property to the specified property.
   *
   * @param property The property to bind.
   */
  public void bindFanta(StringProperty property){
    property.bindBidirectional(fanta);
  }
  /**
   * Binds the nachos price property to the specified property.
   *
   * @param property The property to bind.
   */
  public void bindNachos(StringProperty property){
    property.bindBidirectional(nachos);
  }
  /**
   * Binds the tuborg price property to the specified property.
   *
   * @param property The property to bind.
   */
  public void bindTuborg(StringProperty property){
    property.bindBidirectional(tuborg);
  }  /**
 * Binds the vip price property to the specified property.
 *
 * @param property The property to bind.
 */
  public void bindOreo(StringProperty property){
    property.bindBidirectional(oreo);
  }
  /**
   * Binds the redbull price property to the specified property.
   *
   * @param property The property to bind.
   */
  public void bindRedbull(StringProperty property){
    property.bindBidirectional(redbull);
  }

  /**
   * Handles the button press to change prices.
   * Parses the entered prices and updates the model
   * Displays an error message if the input is not valid.
   */
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
  /**
   * Checks if the given string can be parsed as a double.
   *
   * @param input The string to check.
   * @return True if the string can be parsed as a double, false otherwise.
   */
  public static boolean isDouble(String input) {
    try {

      Double.parseDouble(input);
      return true;
    } catch (NumberFormatException e) {

      return false;
    }
  }
  /**
   * Responds to property change events.
   *
   * @param evt The property change event to be handled.
   */
  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() ->{
      if (evt.getPropertyName().equals("fatalError") && isCurrent){
        property.firePropertyChange(evt.getPropertyName(),null,evt.getNewValue());
      }});
  }

  /**
   * Assigns listener to the property.
   * @param listener the listener to be added
   */
  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }
  /**
   * Removes listener from the property.
   * @param listener the listener to be added
   */
  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }
}
