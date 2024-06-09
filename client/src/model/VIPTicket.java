package model;

import java.io.Serializable;
/**
 *a classs respresenting the VIP ticket type
 * inheritance from ticket class
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class VIPTicket extends Ticket implements Serializable
{
  /**
   * 4 argument constructor
   * initialises  a VIP ticket with the specified parameters.
   * @param ticketID The ID of the ticket.
   * @param ticketPrice The price of the ticket.
   * @param seat The seat associated with the ticket.
   * @param screening The screening associated with the ticket.
   */
  public VIPTicket(String ticketID, double ticketPrice, Seat seat,
      Screening screening)
  {
    super(ticketID, ticketPrice, seat, screening, "VIP");
  }
}
