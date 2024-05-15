package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import model.Model;
import model.Screening;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainPageViewModel
{
  private Model model;
  private ObservableList<SimpleScreeningView> screenings;
  private ObjectProperty<SimpleScreeningView> selectedObject;
  private ViewState viewState;
  private StringProperty input;
  private StringProperty username;

  public MainPageViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.screenings = FXCollections.observableArrayList();
    this.selectedObject = new SimpleObjectProperty<>();
    this.input = new SimpleStringProperty();
    updateUsername();

    loadFromModel();
  }

  private void loadFromModel()
  {
    Screening[] allScreenings = model.getAllScreenings()
        .toArray(new Screening[0]);
    for (Screening screening : allScreenings)
    {
      SimpleScreeningView simpleScreeningView = new SimpleScreeningView(
          screening);
      screenings.add(simpleScreeningView);
    }
  }

  public void updateUsername()
  {
    if (viewState.getUser() != null)
    {
      username.set(viewState.getUser().getUsername());
    }
  }

  public StringProperty usernameProperty()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username.set(username);
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

  public StringProperty getUsername()
  {
    return username;
  }
  public void loadScreenings(ArrayList<Screening> screenings){
    for (Screening screening :screenings){
      SimpleScreeningView screeningView = new SimpleScreeningView(screening);
      this.screenings.add(screeningView);
    }
  }
public void filterByTitle(){
if (input != null){
  screenings.clear();
  loadScreenings(model.getScreaningsByMovieTitle(input.get()));
}else {
  loadFromModel();
}
}
public void filterByDate(LocalDate date){
    screenings.clear();
    loadScreenings(model.getScreeningsByDate(date));
}

}
