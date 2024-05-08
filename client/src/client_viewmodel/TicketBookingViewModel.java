package client_viewmodel;

import client_mediator.RemoteModel;
import client_model.Model;
import client_model.Screening;
import client_model.Seat;
import client_model.User;

import java.rmi.Remote;

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

