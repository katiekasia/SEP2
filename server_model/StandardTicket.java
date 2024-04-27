package server_model;

public class StandardTicket extends Ticket
{
  public StandardTicket(int ticketID, double ticketPrice, Seat seat,
       Screening screening, User customer)
  {
    super(ticketID, ticketPrice, seat, screening,customer);
  }
}
