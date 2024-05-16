package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import model.*;

import java.rmi.RemoteException;

public class TicketConfirmationViewModel
  {
    private Model model;
    private ViewState viewState;
    private ObservableList<SimpleScreeningView> screenings;
    private ObjectProperty<SimpleScreeningView> selectedObject;


    public TicketConfirmationViewModel(Model model, ViewState viewState)
        throws RemoteException
    {
      this.model = model;
      this.viewState = viewState;
      this.screenings = FXCollections.observableArrayList();
      this.selectedObject = new SimpleObjectProperty<>();

      loadFromModel();
    }
    private void loadFromModel() throws RemoteException
    {
      Screening[] allScreenings = model.getAllScreenings()
          .toArray(new Screening[0]);
      for (Screening screening : allScreenings)
      {
        User user = model.getUser();
        SimpleScreeningView simpleScreeningView = new SimpleScreeningView(
            screening, user);
        screenings.add(simpleScreeningView);

      }
    }
    public void updateScreeningsWithSelectedSeats(ObservableList<String> selectedSeats) {
      ObservableList<SimpleScreeningView> updatedViews = FXCollections.observableArrayList();
      selectedSeats.forEach(seatId -> {
        Screening screening = null;  // Assuming there's a method to find screenings by seat ID
        try
        {
          screening = model.findScreeningBySeatId(seatId);
        }
        catch (RemoteException e)
        {
          throw new RuntimeException(e);
        }
        User user = null;
        try
        {
          user = model.getUser();
        }
        catch (RemoteException e)
        {
          throw new RuntimeException(e);
        }
        SimpleScreeningView view = new SimpleScreeningView(screening, user);
        view.setSeatID(seatId); // Make sure SimpleScreeningView has this method
        updatedViews.add(view);
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

