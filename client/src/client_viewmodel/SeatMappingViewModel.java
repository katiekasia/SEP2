package client_viewmodel;

import client_model.Model;
import client_model.Seat;
import client_model.Screening;

public class SeatMappingViewModel {
  private Model model;

  public SeatMappingViewModel(Model model) {
    this.model = model;
  }

  public void reserveSeat(Seat seat, Screening screening) {

    model.reserveSeat(seat, screening);
  }
}
