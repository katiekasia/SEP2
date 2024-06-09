package model;

import java.io.Serializable;
/**
 * class representing the state of a pending order
 *
 * inheritance from oRDER CLASS
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class PendingOrder extends OrderState implements Serializable
{


  private boolean expired;
  /**
   * Initialises a new PendingOrder object.
   * @param order The order associated with this pending state.
   */
  public PendingOrder(Order order){
    expired = false;
  }
  /**
   * Marks the order as expired if it is not already expired.
   * If the order is expired, it transitions to the ExpiredOrder state.
   * @param order The order to check for expiration.
   */
  @Override public void expire(Order order)
  {
    if (!expired){
      if (order.isExpired()){
        expired = true;
        order.setOrderState(new ExpiredOrder(order));
      }
    }
  }
  /**
   * Cancels the order and transitions it to the CancelledOrder state.
   * Also cancels all tickets associated with the order.
   * @param order The order to cancel.
   */
  @Override public void cancel(Order order)
  {
    order.setOrderState(new CancelledOrder(order));
    for (Ticket ticket : order.getTickets()){
      ticket.cancelTicket();
    }
  }
  /**
   * Returns the status of the order state, which is "Pending".
   * @return The status of the order state.
   */
  @Override public String status()
  {
    return "Pending";
  }
}