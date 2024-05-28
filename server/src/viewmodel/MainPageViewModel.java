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
import java.time.LocalDate;
import java.util.ArrayList;

public class MainPageViewModel implements PropertyChangeListener,
    UnnamedPropertyChangeSubject
{
  private Model model;
  private PropertyChangeSupport property;
  private ObservableList<SimpleScreeningView> screenings;
  private ObjectProperty<SimpleScreeningView> selectedObject;
  private ViewState viewState;
  private StringProperty input;

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
  }

  private void loadFromModel()
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

  public StringProperty inputProperty()
  {
    return input;
  }

  public void setScreenings(ObservableList<SimpleScreeningView> property)
  {
    property.setAll(screenings);
  }

  public void bindScreenings(ObservableList<SimpleScreeningView> propery)
  {
    screenings.addListener(
        (ListChangeListener<? super SimpleScreeningView>) c -> {
          propery.setAll(screenings);
        });
  }

  public void setSelected()
  {
    selectedObject.set(viewState.getSelectedScreening());
  }

  public ViewState getViewState()
  {
    return viewState;
  }

  public void loadScreenings(ArrayList<Screening> screenings)
  {
    for (Screening screening : screenings)
    {
      SimpleScreeningView screeningView = new SimpleScreeningView(screening);
      this.screenings.add(screeningView);
    }
  }

  public void clearFilters()
  {
    loadFromModel();
  }

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

  public void filterByTitle()
  {
    if (input != null)
    {
      screenings.clear();
      loadScreenings(model.getScreaningsByMovieTitle(input.get()));
    }
    else
    {
      loadFromModel();
    }
  }

  public void filterByDate(LocalDate date)
  {
    screenings.clear();
    loadScreenings(model.getScreeningsByDate(date));
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() ->{
      if (evt.getPropertyName().equals("addScreening") || evt.getPropertyName()
          .equals("removeScreening"))
      {
        loadFromModel();
      }else if (evt.getPropertyName().equals("fatalError")){
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
