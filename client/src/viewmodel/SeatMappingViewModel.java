package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Model;
import model.Screening;
import model.Seat;

import java.util.ArrayList;

public class SeatMappingViewModel {
  private Model model;
 private ViewState viewState;
  private ObservableList<String> selectedSeats = FXCollections.observableArrayList();
  private int nbTickets;
  private int myTickets;


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
    return model.getScreeningForView(viewState.getSelectedScreening().getTime(),viewState.getSelectedScreening().getDate(),
        viewState.getSelectedScreening().getMovie(), viewState.getSelectedScreening().getRoom()).getRoom().getBookedSeatsIDs();
  }
  public void backPressed(){
selectedSeats.clear();
  }
  public Seat[] selectedSeats(){
    Seat[] seatsToReserve = new Seat[myTickets];
    int index = 0;
try
{
  for (String s : selectedSeats){
    seatsToReserve[index] = model.getScreeningForView(viewState.getSelectedScreening().getTime(),viewState.getSelectedScreening().getDate(),
        viewState.getSelectedScreening().getMovie(), viewState.getSelectedScreening().getRoom()).getRoom().getSeatByID(s);
    index++;
  }
}catch (Exception e){
  e.printStackTrace();
}
    return seatsToReserve;
  }

  public void deselectSeat(String seatId) {
    selectedSeats.remove(seatId);
    nbTickets++;
  }

  //smth similar to the one in Main Page to load and bind the info
  public SeatMappingViewModel(Model model, ViewState viewState) {
    this.model = model;
    this.viewState = viewState;


    reset();


  }
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
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setHeaderText("Processing. Please wait.");
      alert.showAndWait();
      Screening screening = model.getScreeningForView(viewState.getTime(),
          viewState.getDate(), viewState.getTitle(), viewState.room());

      model.reserveSeats(selectedSeats(),viewState.getUser(),screening,
           viewState.getNumberOfVIPTickets());

    }catch (Exception e){
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText(e.getMessage());
      alert.showAndWait();
      return false;
    }

    return true;
  }

  public Screening getScreening()
  {
    return model.getScreeningForView(viewState.getSelectedScreening().getTime(),viewState.getSelectedScreening().getDate(),
        viewState.getSelectedScreening().getMovie(), viewState.getSelectedScreening().getRoom());

  }
}