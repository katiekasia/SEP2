package server_model;

public class StandardTicket extends Ticket
{
  public StandardTicket(String ticketID, double ticketPrice, Seat seat,
       Screening screening, User customer, String ticketType)
  {
    super(ticketID, ticketPrice, seat, screening,customer, ticketType);
  }
}
