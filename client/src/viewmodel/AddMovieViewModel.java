package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import model.Model;
import model.Movie;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
/**
 *Class responsible for managing the view state ,
 * handling operations related to adding a new movie.
 * communicates with the model to add movie data,
 * listens for property changes, and updates the view
 *
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class AddMovieViewModel implements PropertyChangeListener,UnnamedPropertyChangeSubject
{
  private Model model;
  private PropertyChangeSupport property;
  private ViewState viewState;
  private StringProperty addingStatus;
  private StringProperty addingMessage;
  private StringProperty length;
  private StringProperty title;
  private StringProperty description;
  private StringProperty genre;
  private boolean isCurrent;
  /**
   * Constructor for the AddMovieViewModel class.
   *
   * @param model     The Model object responsible for managing data.
   * @param viewState The ViewState object responsible for managing the view state.
   */
  public AddMovieViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.addingStatus = new SimpleStringProperty();
    this.addingMessage = new SimpleStringProperty();
    property = new PropertyChangeSupport(this);
    this.model.addListener(this);
    this.viewState = viewState;
    isCurrent = false;
    genre = new SimpleStringProperty();
    title = new SimpleStringProperty();
    description = new SimpleStringProperty();
    length = new SimpleStringProperty();
  }
  /**
   * Retrieves the title property.
   *
   * @return The StringProperty representing the title.
   */
  public StringProperty titleProperty()
  {
    return title;
  }

  /**
   * A method returning StringProperty representing the title.
   * @return StringProperty representing the title.
   */
  public StringProperty lengthProperty()
  {
    return length;
  }

  /**
   * A method returning StringProperty representing the genre.
   * @return StringProperty representing the genre.
   */
  public StringProperty genreProperty()
  {
    return genre;
  }

  /**
   * A method returning StringProperty representing the description.
   * @return StringProperty representing the genre.
   */
  public StringProperty descriptionProperty()
  {
    return description;
  }
  /**
   * A method returning ViewState
   * @return ViewState.
   */
  public ViewState getViewState()
  {
    return viewState;
  }

  /**
   * Adds a new movie.
   *
   * @param date The release date of the movie.
   */
  public void addMovie(LocalDate date) {
    Movie movie = new Movie(length.get(), description.get(), title.get(),genre.get(), date);
    try {
      model.addMovie(movie);
      addingStatus.set("SUCCESS");
      addingMessage.set("Your movie has been added successfully.");
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText(e.getMessage());
      alert.showAndWait();
    }
  }
  /**
   * Sets whether the view is currently active.
   *
   * @param current True if the view is current, false otherwise.
   */
  public void setCurrent(boolean current)
  {
    isCurrent = current;
  }

  /**
   * A method returning StringProperty representing the adding status.
   * @return  StringProperty representing the adding status.
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
