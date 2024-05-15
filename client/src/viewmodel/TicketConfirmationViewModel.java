package viewmodel;

import javafx.scene.control.Alert;
import model.Model;
import model.Screening;
import model.User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class TicketConfirmationViewModel
  {
    private Model model;
    private ViewState viewState;
    private ObservableList<SimpleScreeningView> screenings;
    private ObjectProperty<SimpleScreeningView> selectedObject;


    public TicketConfirmationViewModel(Model model, ViewState viewState)
    {
      this.model = model;
      this.viewState = viewState;
      this.screenings = FXCollections.observableArrayList();
      this.selectedObject = new SimpleObjectProperty<>();

      loadFromModel();
    }
    private void loadFromModel()
    {

try
{
  if (model.getAllScreenings() != null)
  {
    Screening[] allScreenings = model.getAllScreenings().toArray(new Screening[0]);
    for (Screening screening : allScreenings)
    {
      User user = viewState.getUser();
      SimpleScreeningView simpleScreeningView = new SimpleScreeningView(
          screening);
      screenings.add(simpleScreeningView);

    }
  }else {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("No screenings available currently");
    alert.showAndWait();
  }
}catch (Exception e){
  e.printStackTrace();
}
    }
    public void updateScreeningsWithSelectedSeats(ObservableList<String> selectedSeats) {
      ObservableList<SimpleScreeningView> updatedViews = FXCollections.observableArrayList();
      selectedSeats.forEach(seatId -> {
        try
        {
          Screening screening = model.findScreeningBySeatId(seatId);  // Assuming there's a method to find screenings by seat ID
          User user = viewState.getUser();
          SimpleScreeningView view = new SimpleScreeningView(screening);
          view.setSeatID(seatId); // Make sure SimpleScreeningView has this method
          updatedViews.add(view);
        }catch (Exception e){
          e.printStackTrace();
        }
      });
      screenings.setAll(updatedViews);
    }


    public void bindScreenings(ObservableList<SimpleScreeningView> propery)
    {
      screenings.addListener(
          (ListChangeListener<? super SimpleScreeningView>) c -> {
            propery.setAll(screenings);
          });
    }
    public void setScreenings(ObservableList<SimpleScreeningView> property)
    {
      property.setAll(screenings);
    }
    public ViewState getViewState()
    {
      return viewState;
    }
    public void setSelected()
    {
      selectedObject.set(viewState.getSelectedScreening());
    }
}

