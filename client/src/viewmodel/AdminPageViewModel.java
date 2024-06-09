package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Model;
import model.Screening;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
/**
 *Class responsible for managing the view state for admin,
 * handling operations related to managing screenings.
 * communicates with the model to add screening data,
 * listens for property changes, and updates the view
 *
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class AdminPageViewModel implements PropertyChangeListener,
    UnnamedPropertyChangeSubject
{
  private Model model;
  private ViewState viewState;
  private PropertyChangeSupport property;
  private ObservableList<SimpleScreeningView> screenings;
  private ObjectProperty<SimpleScreeningView> selectedObject;
  private boolean isCurrent;

  /**
   * Initialises an AdminPageViewModel object
   *
   * @param model The Model object.
   * @param viewState The ViewState object.
   */
  public AdminPageViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.screenings = FXCollections.observableArrayList();

    this.selectedObject = new SimpleObjectProperty<>();
    this.model.addListener(this);
    property = new PropertyChangeSupport(this);
    isCurrent = false;
    loadFromModel();
  }

  /**
   * Loads screenings data from the model.
   */
  public void loadFromModel()
  {
    screenings.clear();
    Screening[] allScreenings = model.getAllScreenings()
        .toArray(new Screening[0]);
    for (Screening screening : allScreenings)
    {
      SimpleScreeningView simpleScreeningView = new SimpleScreeningView(
          screening);
      screenings.add(simpleScreeningView);
    }
  }
  /**
   * Sets the current activity  status.
   *
   * @param current Boolean indicating if the view is currently active.
   */
  public void setCurrent(boolean current)
  {
    isCurrent = current;
  }
  /**
   * Deletes the selected screening from the model and updates the view.
   * It retrieves the details of the selected screening from the view state,
   * then attempts to remove the corresponding screening from the model.
   * If successful, it reloads the screenings data from the model to update the view.
   * If an exception occurs during the removal process, it displays an error message.
   */
  public void deleteScreening()
  {
    String time = viewState.getSelectedScreening().getTime();
    String date = viewState.getSelectedScreening().getDate();
    String movie = viewState.getSelectedScreening().getMovie();
    int room = viewState.getSelectedScreening().getRoom();

    try
    {
      model.removeScreening(model.getScreeningForView(time, date, movie, room));
    }catch (Exception e){
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText(e.getMessage());
      alert.showAndWait();
    }

    loadFromModel();

  }
  /**
   * A method returning the ViewState.
   * @return ViewState
   */
  public ViewState getViewState()
  {
    return viewState;
  }

  /**
   * method responsible for setting the table of objects of type screening
   * @param property
   */
  public void setScreenings(ObservableList<SimpleScreeningView> property)
  {
    property.setAll(screenings);
  }
  /**
   * Binds the screenings list to the provided property.
   * It adds a listener to the screenings list, which updates the provided property
   * with the content of the screenings list whenever a change occurs.
   * This ensures that the property always reflects the latest state of the screenings list.
   *
   * @param propery The property to bind the screenings list to.
   */
  public void bindScreenings(ObservableList<SimpleScreeningView> propery)
  {
    screenings.addListener(
        (ListChangeListener<? super SimpleScreeningView>) c -> {
          propery.setAll(screenings);
        });
  }

  /**
   * method responsible for setting the selected object of type screening
   */
  public void setSelected()
  {
    selectedObject.set(viewState.getSelectedScreening());
  }

  /**
   * Responds to property change events.
   *
   * @param evt The property change event to be handled.
   */
  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() ->{
    if (evt.getPropertyName().equals("addScreening") && isCurrent || evt.getPropertyName()
        .equals("removeScreening") && isCurrent)
    {
      loadFromModel();
    }else if (evt.getPropertyName().equals("fatalError") && isCurrent){
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
