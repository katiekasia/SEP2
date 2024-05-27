package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import model.Model;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TicketConfirmationViewModel implements PropertyChangeListener,
    UnnamedPropertyChangeSubject
  {
    private Model model;
    private ViewState viewState;
    private PropertyChangeSupport property;
    private ObservableList<SimpleScreeningView> screenings;
    private ObjectProperty<SimpleScreeningView> selectedObject;


    public TicketConfirmationViewModel(Model model, ViewState viewState)

    {
      this.model = model;

      this.property = new PropertyChangeSupport(this);
      this.model.addListener(this);

      this.viewState = viewState;
      this.screenings = FXCollections.observableArrayList();
      this.selectedObject = new SimpleObjectProperty<>();

      //loadFromModel();
    }
//    private void loadFromModel() throws RemoteException
//    {
//      Screening[] allScreenings = model.getAllScreenings()
//          .toArray(new Screening[0]);
//      for (Screening screening : allScreenings)
//      {
//        User user = model.getUser();
//        SimpleScreeningView simpleScreeningView = new SimpleScreeningView(
//            screening);
//        screenings.add(simpleScreeningView);
//
//      }
//    }
//    public void updateScreeningsWithSelectedSeats(ObservableList<String> selectedSeats) {
//      ObservableList<SimpleScreeningView> updatedViews = FXCollections.observableArrayList();
//      selectedSeats.forEach(seatId -> {
//        Screening screening = null;  // Assuming there's a method to find screenings by seat ID
//        try
//        {
//          screening = model.findScreeningBySeatId(seatId);
//        }
//        catch (RemoteException e)
//        {
//          throw new RuntimeException(e);
//        }
//        User user = null;
//        try
//        {
//          user = model.getUser();
//        }
//        catch (RemoteException e)
//        {
//          throw new RuntimeException(e);
//        }
//        SimpleScreeningView view = new SimpleScreeningView(screening);
//        view.setSeatID(seatId); // Make sure SimpleScreeningView has this method
//        updatedViews.add(view);
//      });
//      screenings.setAll(updatedViews);
//    }


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

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
      Platform.runLater(() ->{
        if (evt.getPropertyName().equals("fatalError")){
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

