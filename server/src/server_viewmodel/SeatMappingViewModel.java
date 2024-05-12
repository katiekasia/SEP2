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
import server_model.User;
import server_view.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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