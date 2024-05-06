package client_viewmodel;

import client_model.Model;
import client_model.Seat;
import client_model.Screening;
import client_model.User; // Import the User class if needed

public class SeatMappingViewModel {
  private Model model;

  public SeatMappingViewModel(Model model) {
    this.model = model;
  }

  public void reserveSeat(Seat seat, User user, Screening screening) {
    model.reserveSeat(seat, user, screening);
  }
}
