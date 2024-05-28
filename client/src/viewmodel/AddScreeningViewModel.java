package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import model.Model;
import model.Movie;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddScreeningViewModel implements PropertyChangeListener,
    UnnamedPropertyChangeSubject
{
  private Model model;
  private ViewState viewState;

  private PropertyChangeSupport property;
  private ObservableList<SimpleMovieView> movies;
  private ObjectProperty<SimpleMovieView> selectedObject;

  public AddScreeningViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.movies= FXCollections.observableArrayList();
    this.selectedObject = new SimpleObjectProperty<>();

    model.addListener(this);

    this.model.addListener(this);
    property = new PropertyChangeSupport(this);


    loadFromModel();
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

  private void loadFromModel()
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
      if (evt.getPropertyName().equals("deleteMovie") || evt.getPropertyName()
          .equals("deleteMovie"))
      {
        loadFromModel();
      }else if (evt.getPropertyName().equals("fatalError")){
        property.firePropertyChange(evt.getPropertyName(),null,evt.getNewValue());
      }});
  }
}

