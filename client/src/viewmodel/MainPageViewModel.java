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

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MainPageViewModel
{
  private Model model;
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


try
{
  loadFromModel();
}catch (Exception e){
  e.printStackTrace();
}
  }

  private void loadFromModel() throws RemoteException
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


  public void loadScreenings(ArrayList<Screening> screenings){
    for (Screening screening :screenings){
      SimpleScreeningView screeningView = new SimpleScreeningView(screening);
      this.screenings.add(screeningView);
    }
  }
public void filterByTitle() throws RemoteException
{
if (input != null){
  screenings.clear();
  loadScreenings(model.getScreaningsByMovieTitle(input.get()));
}else {
  loadFromModel();
}
}
public void filterByDate(LocalDate date) throws RemoteException
{
    screenings.clear();
    loadScreenings(model.getScreeningsByDate(date));
}

}
