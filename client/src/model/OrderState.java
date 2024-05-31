package model;
/**
 * an abstract class to represent the state of an order
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public abstract class OrderState
{
  /**
   * Marks the order as expired.
   * @param order The order to expire.
   */
public void expire(Order order){}
  /**
   * Cancels the order.
   * @param order The order to cancel.
   */
  public abstract void cancel(Order order);
  /**
   * Returns the status of the order state.
   * @return The status of the order state.
   */
  public abstract String status();
}
