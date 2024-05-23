package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import model.Model;
import model.Screening;

public class AdminPageViewModel
{
  private Model model;
  private ViewState viewState;
  private ObservableList<SimpleScreeningView> screenings;
  private ObjectProperty<SimpleScreeningView> selectedObject;


  public AdminPageViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.screenings = FXCollections.observableArrayList();

    this.selectedObject = new SimpleObjectProperty<>();
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
}
