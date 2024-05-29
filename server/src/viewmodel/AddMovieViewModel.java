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

  public AddMovieViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.addingStatus = new SimpleStringProperty();
    this.addingMessage = new SimpleStringProperty();
    property = new PropertyChangeSupport(this);
    this.model.addListener(this);
    this.viewState = viewState;
    isCurrent = false;
  }

  public StringProperty titleProperty()
  {
    return title;
  }

  public StringProperty lengthProperty()
  {
    return length;
  }

  public StringProperty genreProperty()
  {
    return genre;
  }

  public StringProperty descriptionProperty()
  {
    return description;
  }

  public ViewState getViewState()
  {
    return viewState;
  }

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

  public void setCurrent(boolean current)
  {
    isCurrent = current;
  }

  public StringProperty addingStatusProperty() {
    return addingStatus;
  }

  public StringProperty addingMessageProperty() {
    return addingMessage;
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
