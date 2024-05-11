package server_viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import server_model.Model;
import server_model.Screening;
import server_model.Seat;
import server_model.Ticket;

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
      Screening[] allScreenings = model.getAllScreenings()
          .toArray(new Screening[0]);
      for (Screening screening : allScreenings)
      {
        //Seat seat = model.getSeatByScreening(screening);
        //Ticket ticket = model.getTicketBySeat(seat);
        SimpleScreeningView simpleScreeningView = new SimpleScreeningView(
            screening);
        screenings.add(simpleScreeningView);

      }
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

