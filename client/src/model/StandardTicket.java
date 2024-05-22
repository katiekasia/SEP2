package model;

import java.io.Serializable;

public class StandardTicket extends Ticket implements Serializable
{
  public StandardTicket(String ticketID, double ticketPrice, Seat seat,
       Screening screening)
  {
    super(ticketID, ticketPrice, seat, screening, "Standard");
  }
}
