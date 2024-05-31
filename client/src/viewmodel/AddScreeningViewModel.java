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

  public void setCurrent(boolean current)
  {
    isCurrent = current;
  }

  public StringProperty timeProperty()
  {
    return time;
  }
  public StringProperty roomProperty()
  {
    return room;
  }
  public StringProperty addingStatusProperty() {
    return addingStatus;
  }
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


  public ViewState getViewState()
  {
    return viewState;
  }



  public void deleteMovie()
  {
    String title= viewState.getSelectedMovie().getTitle();
    model.deleteMovie(model.getMovieForView(title));
    System.out.println("viewModel");
    loadFromModel();
  }

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

  public void setMovies(ObservableList<SimpleMovieView> property)
  {
    property.setAll(movies);
  }
  public void bindScreenings(ObservableList<SimpleMovieView> propery)
  {
    movies.addListener(
        (ListChangeListener<? super SimpleMovieView>) c -> {
          propery.setAll(movies);
        });
  }
  public void setSelected()
  {
    selectedObject.set(viewState.getSelectedMovie());
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }

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
