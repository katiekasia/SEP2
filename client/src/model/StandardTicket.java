package model;

public class StandardTicket extends Ticket
{
  public StandardTicket(String ticketID, double ticketPrice, Seat seat,
       Screening screening, User customer)
  {
    super(ticketID, ticketPrice, seat, screening,customer, "Standard");
  }
}
