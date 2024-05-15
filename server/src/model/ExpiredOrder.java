package model;

public class ExpiredOrder extends OrderState
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
}
