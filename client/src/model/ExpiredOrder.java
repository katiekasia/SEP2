package model;

import java.io.Serializable;

public class ExpiredOrder extends OrderState implements Serializable
{
  private boolean expired;
  public ExpiredOrder(Order order){
    expired = true;
  }
  @Override public void expire(Order order)
  {
  }

  @Override public void cancel(Order order)
  {
    throw new IllegalStateException("Order has expired and cannot be cancelled or modified.");
  }

  @Override public String status()
  {
    return "Expired";
  }
}
