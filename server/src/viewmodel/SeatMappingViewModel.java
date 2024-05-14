package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

public class SeatMappingViewModel {
  private Model model;
 private ViewState viewState;
  private ObservableList<String> selectedSeats = FXCollections.observableArrayList();

  public ObservableList<String> getSelectedSeats() {
    return selectedSeats;
  }

  public void selectSeat(String seatId) {
    if (!selectedSeats.contains(seatId)) {
      selectedSeats.add(seatId);
    }
  }

  public void deselectSeat(String seatId) {
    selectedSeats.remove(seatId);
  }

  //smth similar to the one in Main Page to load and bind the info
  public SeatMappingViewModel(Model model, ViewState viewState) {
    this.model = model;
    this.viewState = viewState;

  }


}