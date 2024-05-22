package model;

import java.io.Serializable;

public class VIPTicket extends Ticket implements Serializable
{
  public VIPTicket(String ticketID, double ticketPrice, Seat seat,
      Screening screening)
  {
    super(ticketID, ticketPrice, seat, screening, "VIP");
  }
}
