package viewmodel;

import javafx.beans.property.*;
import model.Order;

public class SimpleOrderView
{
  private IntegerProperty orderID;
  private StringProperty orderPrice;
  private StringProperty orderDate;
  private Order order;

  public SimpleOrderView(Order order){
    this.order = order;
    orderID = new SimpleIntegerProperty(order.getOrderID());
    orderPrice = new SimpleStringProperty(order.getOrderPrice() + "$");
    orderDate = new SimpleStringProperty(order.getOrderDate().toString());
  }

  public void setOrderPrice(String orderPrice)
  {
    this.orderPrice.set(orderPrice);
  }

  public void setOrder(Order order)
  {
    this.order = order;
  }

  public void setOrderID(int orderID)
  {
    this.orderID.set(orderID);
  }

  public void setOrderDate(String orderDate)
  {
    this.orderDate.set(orderDate);
  }

  public StringProperty orderPriceProperty()
  {
    return orderPrice;
  }

  public StringProperty orderDateProperty()
  {
    return orderDate;
  }

  public IntegerProperty orderIDProperty()
  {
    return orderID;
  }
}
