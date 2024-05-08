package server_viewmodel;

import server_mediator.RemoteModel;
import server_model.Model;
import server_model.Screening;
import server_model.Seat;
import server_model.User;

public class TicketBookingViewModel
{
  private Model model;

  public TicketBookingViewModel(Model model) {
    this.model = model;
  }

  public void bookTicket(Seat seat, User user, Screening screening) {
    try {
      model.reserveSeat(seat, user, screening);
    } catch (Exception e) {

    }
  }
}
