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

public class AdminPageViewModel implements PropertyChangeListener,
    UnnamedPropertyChangeSubject
{
  private Model model;
  private ViewState viewState;
  private PropertyChangeSupport property;
  private ObservableList<SimpleScreeningView> screenings;
  private ObjectProperty<SimpleScreeningView> selectedObject;
  private boolean isCurrent;


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

  public void setCurrent(boolean current)
  {
    isCurrent = current;
  }

  public void deleteScreening()
  {
    String time = viewState.getSelectedScreening().getTime();
    String date = viewState.getSelectedScreening().getDate();
    String movie = viewState.getSelectedScreening().getMovie();
    int room = viewState.getSelectedScreening().getRoom();
//    Screening screening = ;
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

  public ViewState getViewState()
  {
    return viewState;
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

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
property.removePropertyChangeListener(listener);
  }

}
