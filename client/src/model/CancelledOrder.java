package model;

import java.io.Serializable;
/**
 *Classs representing the state of a cancelled order
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class CancelledOrder extends OrderState implements Serializable
{
  /**
   * Initialises a new CancelledOrder object.
   * @param order The order associated with this pending state.
   */
  public CancelledOrder(Order order){}
  /**
   * Marks the order as expired if it is not already expired.
   * If the order is expired, it transitions to the ExpiredOrder state.
   * @param order The order to check for expiration.
   */
  @Override public void expire(Order order)
  {
  }
  /**
   * Cancels the order and transitions it to the CancelledOrder state.
   * Also cancels all tickets associated with the order.
   * @param order The order to cancel.
   */
  @Override public void cancel(Order order)
  {
    throw new IllegalStateException("Order already cancelled.");
  }
  /**
   * Returns the status of the order state, which is "Pending".
   * @return The status of the order state.
   */
  @Override public String status()
  {
    return "Cancelled";
  }
}
