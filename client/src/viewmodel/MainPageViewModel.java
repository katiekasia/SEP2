package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import model.Model;
import model.Screening;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MainPageViewModel implements PropertyChangeListener,
    UnnamedPropertyChangeSubject
{
  /**
   *Class responsible for managing the view state for admin,
   * handling operations related to managing screenings.
   * communicates with the model to add screening data,
   * listens for property changes, and updates the view
   *
   * @version 3.0   may 2024
   * @author Michal Barczuk, Kasia, Sandut, Catalina
   * @param boolean isCurrent - representing if the site is curently viewed
   * @param Model model
   */
  private Model model;
  private PropertyChangeSupport property;
  private ObservableList<SimpleScreeningView> screenings;
  private ObjectProperty<SimpleScreeningView> selectedObject;
  private ViewState viewState;
  private StringProperty input;
  private boolean isCurrent;

  public MainPageViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.screenings = FXCollections.observableArrayList();
    this.selectedObject = new SimpleObjectProperty<>();
    this.input = new SimpleStringProperty();

    this.model.addListener(this);
    property = new PropertyChangeSupport(this);
    loadFromModel();
    isCurrent = false;
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
   * A method returning StringProperty representing the input property.
   * @return  StringProperty representing the input property.
   */
  public StringProperty inputProperty()
  {
    return input;
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
   * Sets the current activity  status.
   *
   * @param current Boolean indicating if the view is currently active.
   */
  public void setCurrent(boolean current)
  {
    isCurrent = current;
  }

  /**
   * A method returning the boolean represetning if the page is viewed
   * @return
   */
  public boolean isCurrent()
  {
    return isCurrent;
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
   * A method returning the ViewState.
   * @return ViewState
   */
  public ViewState getViewState()
  {
    return viewState;
  }

  /**
   * A method responsible for loading screenings and converting them to SimpleScreeningView
   * @param screenings
   */
  public void loadScreenings(ArrayList<Screening> screenings)
  {
    System.out.println("Load screenings is executed");
    for (Screening screening : screenings)
    {
      SimpleScreeningView screeningView = new SimpleScreeningView(screening);
      this.screenings.add(screeningView);
    }
    System.out.println("screeningView is added to screenings.");
  }

  /**
   * A method responsible for clearing filters
   */
  public void clearFilters()
  {
    loadFromModel();
  }

  /**
   * A method responsible for applying filters.
   * @param date
   */
  public void onSearch(LocalDate date)
  {
    if (input != null && date != null)
    {
      model.getScreeningsByDateAndTitle(input.get(), date);
    }
    else if (input != null && date == null)
    {
      filterByTitle();
    }
    else if (input == null && date != null)
    {
      filterByDate(date);
    }
    else
    {
      loadFromModel();
    }
  }
  /**
   * A method responsible for applying filtering by the title.
   *
   */
  public void filterByTitle()
  {
    if (input != null)
    {
      screenings.clear();
      System.out.println("Clear function called");
      loadScreenings(model.getScreaningsByMovieTitle(input.get()));
      System.out.println("Load screenings called with arguments taken from model");
    }
    else
    {
      loadFromModel();
      System.out.println("Input was null");
    }
  }
  /**
   * A method responsible for applying filtering by the date.
   * @param date
   */
  public void filterByDate(LocalDate date)
  {
    screenings.clear();
    loadScreenings(model.getScreeningsByDate(date));
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
