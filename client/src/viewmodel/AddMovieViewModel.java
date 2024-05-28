package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import model.Movie;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddMovieViewModel implements PropertyChangeListener,UnnamedPropertyChangeSubject
{
  private Model model;
  private PropertyChangeSupport property;
  private ViewState viewState;
  private StringProperty addingStatus;
  private StringProperty addingMessage;
  private StringProperty title;
  private StringProperty description;
  private StringProperty length;
  private StringProperty genre;

  public AddMovieViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.addingStatus = new SimpleStringProperty();
    this.addingMessage = new SimpleStringProperty();
    property = new PropertyChangeSupport(this);
    this.model.addListener(this);
    this.viewState = viewState;
    this.title = new SimpleStringProperty();
    this.description = new SimpleStringProperty();
    this.length = new SimpleStringProperty();
    this.genre = new SimpleStringProperty();
  }

  public ViewState getViewState()
  {
    return viewState;
  }

  public void addMovie(Movie movie) {

    try {
      model.addMovie(movie);
      addingStatus.set("SUCCESS");
      addingMessage.set("Your movie has been added successfully.");
    } catch (Exception e) {
      addingStatus.set("ERROR");
      addingMessage.set("Registration Failed: " + e.getMessage());
    }
  }

  public StringProperty titleProperty()
  {
    return title;
  }
  public StringProperty descriptionProperty()
  {
    return description;
  }
  public StringProperty lengthProperty() {return length;}
  public StringProperty genreProperty() {return genre;}

  public StringProperty addingStatusProperty() {
    return addingStatus;
  }
  public StringProperty addingMessageProperty() {
    return addingMessage;
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
