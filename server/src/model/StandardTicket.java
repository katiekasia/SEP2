package model;

import java.io.Serializable;
/**
 *a classs respresenting the standard ticket type
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class StandardTicket extends Ticket implements Serializable
{
  /**
   * 4 argument constructor
   * initialises  a standard ticket with the specified parameters.
   * @param ticketID The ID of the ticket.
   * @param ticketPrice The price of the ticket.
   * @param seat The seat associated with the ticket.
   * @param screening The screening associated with the ticket.
   */
  public StandardTicket(String ticketID, double ticketPrice, Seat seat,
      Screening screening)
  {
    super(ticketID, ticketPrice, seat, screening, "Standard");
  }
}

