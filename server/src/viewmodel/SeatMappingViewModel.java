package viewmodel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Model;
import model.Screening;
import model.Seat;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class SeatMappingViewModel
    implements PropertyChangeListener, UnnamedPropertyChangeSubject
{
  private Model model;
 private ViewState viewState;
  private ObservableList<String> selectedSeats = FXCollections.observableArrayList();
  private PropertyChangeSupport property;
  private int nbTickets;
  private int myTickets;


  public SeatMappingViewModel(Model model, ViewState viewState) {
    this.model = model;
    this.viewState = viewState;

    property = new PropertyChangeSupport(this);
    this.model.addListener(this);
    reset();


  }
  public ObservableList<String> getSelectedSeats() {
    return selectedSeats;
  }

  public void selectSeat(String seatId) {
    if (!selectedSeats.contains(seatId) && keepSelecting()) {
      selectedSeats.add(seatId);
      nbTickets--;
    }else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.showAndWait();
    }
  }
  public ArrayList<String> setSeats(){
    try
    {
      return model.getScreeningForView(viewState.getSelectedScreening().getTime(),viewState.getSelectedScreening().getDate(),
          viewState.getSelectedScreening().getMovie(), viewState.getSelectedScreening().getRoom()).getRoom().getBookedSeatsIDs();
    }catch (Exception e){}
    return null;
  }
  public Seat[] selectedSeats(){
try
{
  Seat[] seatsToReserve = new Seat[myTickets];
  int index = 0;
  for (String s : selectedSeats){
    seatsToReserve[index] = model.getScreeningForView(viewState.getSelectedScreening().getTime(),viewState.getSelectedScreening().getDate(),

        viewState.getSelectedScreening().getMovie(),
        viewState.getSelectedScreening().getRoom()).getRoom().getSeatByID(s);
    index++;
  }
  return seatsToReserve;
}catch (Exception e){
  e.printStackTrace();
}
return null;
  }

  public void deselectSeat(String seatId) {
    selectedSeats.remove(seatId);
    nbTickets++;
  }

  //smth similar to the one in Main Page to load and bind the info

  public void reset(){
    myTickets = viewState.getNumberOfStandardTickets() + viewState.getNumberOfVIPTickets();
    nbTickets = myTickets;
  }
  public boolean keepSelecting(){
    if (nbTickets <= 0){
      return false;
    }else return true;
  }
  public boolean proceedPressed(){
    if (nbTickets > 0){
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Please select all seats. Seats left: " + nbTickets);
      alert.showAndWait();
      return false;
    }
    try
    {
     viewState.setSelectedOrder(new SimpleOrderView(model.reserveSeats(selectedSeats(),viewState.getUser(),model.getScreeningForView(viewState.getSelectedScreening().getTime(),viewState.getSelectedScreening().getDate(),
             viewState.getSelectedScreening().getMovie(), viewState.getSelectedScreening().getRoom()),
         viewState.getNumberOfVIPTickets())));
//      model.reserveSeats(selectedSeats(),viewState.getUser(),model.getScreeningForView(viewState.getSelectedScreening().getTime(),viewState.getSelectedScreening().getDate(),
//              viewState.getSelectedScreening().getMovie(), viewState.getSelectedScreening().getRoom()),
//          viewState.getNumberOfVIPTickets());
      selectedSeats.clear();
    }catch (Exception e){
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText(e.getMessage());
      alert.showAndWait();
      return false;
    }
    return true;
  }
  @Override public void addListener(PropertyChangeListener listener){
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }

  public Screening getScreening()
  {
   try
   {
     return model.getScreeningForView(viewState.getSelectedScreening().getTime(),viewState.getSelectedScreening().getDate(),
         viewState.getSelectedScreening().getMovie(), viewState.getSelectedScreening().getRoom());
   }catch (Exception e){e.printStackTrace();}
   return null;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() ->{
      if (evt.getPropertyName().equals("reserveSeat" + getScreening().getTime()) || evt.getPropertyName()
          .equals("reserveSeats" + getScreening().getTime()))
      {
        property.firePropertyChange("reset",null, evt.getNewValue());
      }else if (evt.getPropertyName().equals("fatalError")){
        property.firePropertyChange(evt.getPropertyName(),null,evt.getNewValue());
      }});

  }
}