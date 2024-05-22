package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Order;

public class SimpleOrderView
{
  private IntegerProperty orderID;
  private StringProperty orderPrice;
  private StringProperty orderDate;
  private StringProperty orderStatus;


  public SimpleOrderView(Order order){
    orderID = new SimpleIntegerProperty(order.getOrderID());
    orderPrice = new SimpleStringProperty(order.getOrderPrice() + "$");
    orderDate = new SimpleStringProperty(order.getOrderDate().toString());
    orderStatus = new SimpleStringProperty(order.getOrderState().status());
  }

  public void setOrderPrice(String orderPrice)
  {
    this.orderPrice.set(orderPrice);
  }

  public StringProperty orderStatusProperty()
  {
    return orderStatus;
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
