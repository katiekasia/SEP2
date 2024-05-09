package server_viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import server_model.Model;
import server_model.Screening;

public class MainPageViewModel
{
  private Model model;
  private ObservableList<SimpleScreeningView> screenings;
  private ObjectProperty<SimpleScreeningView> selectedObject;
  private ViewState viewState;
  private StringProperty username;

  public MainPageViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.screenings = FXCollections.observableArrayList();
    this.selectedObject = new SimpleObjectProperty<>();

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
  public void setScreenings(ObservableList<SimpleScreeningView> property){
property.setAll(screenings);
  }
public void bindScreenings(ObservableList<SimpleScreeningView> propery){
screenings.addListener((ListChangeListener<? super SimpleScreeningView>) c -> {
propery.setAll(screenings);
});
}
public void setSelected(){
selectedObject.set(viewState.getSelectedScreening());
}
public ViewState getViewState(){
    return viewState;
}

  public StringProperty getUsername()
  {
    return username;
  }

}
