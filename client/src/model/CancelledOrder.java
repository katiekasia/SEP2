package model;

public class CancelledOrder extends OrderState
{
  public CancelledOrder(Order order){}
  @Override public void expire(Order order)
  {
  }

  @Override public void cancel(Order order)
  {
    throw new IllegalStateException("Order already cancelled.");
  }
}
