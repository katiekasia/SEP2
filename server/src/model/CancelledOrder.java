package model;

import java.io.Serializable;

public class CancelledOrder extends OrderState implements Serializable
{
  public CancelledOrder(Order order){}
  @Override public void expire(Order order)
  {
  }

  @Override public void cancel(Order order)
  {
    throw new IllegalStateException("Order already cancelled.");
  }

  @Override public String status()
  {
    return "Cancelled";
  }
}
