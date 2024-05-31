package model;

import java.io.Serializable;
/**
 * class for the state of an Expired order

 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class ExpiredOrder extends OrderState implements Serializable
{
  private boolean expired;

  /**
   * Constructs a new ExpiredOrder object.
   * @param order The order associated with this expired state.
   */
  public ExpiredOrder(Order order){
    expired = true;
  }
  /**
   * This method is empty as the order is already expired.
   * @param order The order to check for expiration (not applicable in this state).
   */
  @Override public void expire(Order order)
  {
  }
  /**
   * Throws an IllegalStateException as an expired order cannot be cancelled or modified.
   * @param order The order to cancel (not allowed in this state).
   * @throws IllegalStateException If an attempt is made to cancel an expired order.
   */
  @Override public void cancel(Order order)
  {
    throw new IllegalStateException("Order has expired and cannot be cancelled or modified.");
  }

  /**
   * Returns the status of the order state, which is "Expired".
   * @return The status of the order state.
   */
  @Override public String status()
  {
    return "Expired";
  }
}
