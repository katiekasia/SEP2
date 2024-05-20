package model;

import java.io.Serializable;
import java.time.LocalDate;

public class PendingOrder extends OrderState implements Serializable
{

  private boolean expired;
  public PendingOrder(Order order){
    expired = false;
    // expire(order);
  }
  @Override public void expire(Order order)
  {
    while (!expired){
      if (order.getOrderDate().isAfter(new SimpleDate(LocalDate.now()))){
        expired = true;
        order.setOrderState(new ExpiredOrder(order));
      }
    }
  }

  @Override public void cancel(Order order)
  {
    order.setOrderState(new CancelledOrder(order));
  }

  @Override public String status()
  {
    return "Pending";
  }
}
