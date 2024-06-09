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
import model.Movie;
import model.Room;
import model.Screening;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
/**
 *Class responsible for managing the view state ,
 * handling operations related to adding a new screening.
 * communicates with the model to add screening data,
 * listens for property changes, and updates the view
 *
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class AddScreeningViewModel implements PropertyChangeListener,
    UnnamedPropertyChangeSubject
{
  private Model model;
  private ViewState viewState;
  private PropertyChangeSupport property;
  private ObservableList<SimpleMovieView> movies;
  private ObjectProperty<SimpleMovieView> selectedObject;
  private StringProperty addingStatus;
  private StringProperty addingMessage;
  private StringProperty time;
  private StringProperty room;
  private boolean isCurrent;
  /**
   * Initialisses  an AddScreeningViewModel object
   *
   * @param model The Model object.
   * @param viewState The ViewState object.
   */
  public AddScreeningViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.movies= FXCollections.observableArrayList();
    this.selectedObject = new SimpleObjectProperty<>();
    this.model.addListener(this);
isCurrent = false;
    property = new PropertyChangeSupport(this);
    this.addingStatus = new SimpleStringProperty();
    this.addingMessage = new SimpleStringProperty();
    this.time = new SimpleStringProperty();
    this.room = new SimpleStringProperty();
    loadFromModel();
  }

  /**
   * A method setting the isCurrent boolean
   * @param current
   */
  public void setCurrent(boolean current)
  {
    isCurrent = current;
  }
  /**
   * A method returning StringProperty representing the time.
   * @return  StringProperty representing the time.
   */
  public StringProperty timeProperty()
  {
    return time;
  }
  /**
   * A method returning StringProperty representing the room.
   * @return  StringProperty representing the room.
   */
  public StringProperty roomProperty()
  {
    return room;
  }
  /**
   * A method returning StringProperty representing the adding status.
   * @return  StringProperty representing the adding.
   */
  public StringProperty addingStatusProperty() {
    return addingStatus;
  }
  /**
   * A method returning StringProperty representing the adding message.
   * @return  StringProperty representing the adding message.
   */
  public StringProperty addingMessageProperty() {
    return addingMessage;
  }
  /**
   * Adds a screening for the specified date.
   *
   * @param date The date of the screening.
   */
  public void addScreening(LocalDate date) {
    String[] timeParts = time.get().split(":");
    int hour = Integer.parseInt(timeParts[0]);
    int minute = Integer.parseInt(timeParts[1]);
Screening screening = new Screening(hour, minute, date, model.getMovieForView(selectedObject.get().getTitle()), model.getRoomById(room.get()));

    try {
      model.addScreening(screening);
      addingStatus.set("SUCCESS");
      addingMessage.set("Your screening has been added successfully.");
    } catch (Exception e) {
      addingStatus.set("ERROR");
      addingMessage.set("Registration Failed: " + e.getMessage());
    }
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
   * A method responsible for deleting a movie.
   */
  public void deleteMovie()
  {
    String title= viewState.getSelectedMovie().getTitle();
    model.deleteMovie(model.getMovieForView(title));
    System.out.println("viewModel");
    loadFromModel();
  }

  /**
   * A method responsible for loading data from model.
   */
  public void loadFromModel()
  {
    movies.clear();
    Movie[] allMovies = model.getAllMovies()
        .toArray(new Movie[0]);
    for (Movie movie : allMovies)
    {
      SimpleMovieView simpleMovieView = new SimpleMovieView(
          movie);
      movies.add(simpleMovieView);
    }
  }

  /**
   * A method responsible for setting the movies in the table
   * @param property
   */
  public void setMovies(ObservableList<SimpleMovieView> property)
  {
    property.setAll(movies);
  }

  /**
   * A method responsible for binding the Observable object of SimpleMovieView
   * @param propery
   */
  public void bindScreenings(ObservableList<SimpleMovieView> propery)
  {
    movies.addListener(
        (ListChangeListener<? super SimpleMovieView>) c -> {
          propery.setAll(movies);
        });
  }

  /**
   * A method responsible for settning the selected object
   */
  public void setSelected()
  {
    selectedObject.set(viewState.getSelectedMovie());
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

  /**
   * Responds to property change events.
   *
   * @param evt The property change event to be handled.
   */
  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() ->{
      if (evt.getPropertyName().equals("deleteMovie") && isCurrent || evt.getPropertyName()
          .equals("deleteMovie") && isCurrent)
      {
        loadFromModel();
      }else if (evt.getPropertyName().equals("fatalError") && isCurrent){
        property.firePropertyChange(evt.getPropertyName(),null,evt.getNewValue());
      }});
  }
}
