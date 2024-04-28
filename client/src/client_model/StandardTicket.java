package client_model;

import client_model.Screening;
import client_model.Seat;
import client_model.Ticket;

public class StandardTicket extends Ticket
{
  public StandardTicket(int ticketID, double ticketPrice, Seat seat,
       Screening screening, User customer)
  {
    super(ticketID, ticketPrice, seat, screening,customer);
  }
}
