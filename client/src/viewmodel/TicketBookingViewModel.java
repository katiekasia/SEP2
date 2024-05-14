package viewmodel;

import model.Model;
import model.Screening;
import model.Seat;
import model.User;

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

