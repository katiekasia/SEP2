public class StandardTicket extends Ticket
{
  public StandardTicket(int ticketID, double ticketPrice, Seat seat,
      ScreeningDay screeningDay, Screening screening, User customer)
  {
    super(ticketID, ticketPrice, seat, screeningDay, screening,customer);
  }
}
