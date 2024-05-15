package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Order;
import model.Screening;
import model.User;

import java.util.List;

public class ViewState
{
  private SimpleScreeningView selectedScreening;
  private SimpleIntegerProperty numberOfStandardTickets = new SimpleIntegerProperty(
      0);
  private SimpleIntegerProperty numberOfVIPTickets = new SimpleIntegerProperty(
      0);

  private ObjectProperty<User> user;
  private ObjectProperty<List<Order>> orders;

  public ViewState()
  {
    this.user = new SimpleObjectProperty<>();
    this.orders = new SimpleObjectProperty<>();
  }

  public ObjectProperty<User> userProperty()
  {
    return user;
  }

  public void setUser(User user)
  {
    this.user.set(user);
  }

  public User getUser()
  {
    return user.get();
  }

  public ObjectProperty<List<Order>> ordersProperty()
  {
    return orders;
  }

  public void setOrders(List<Order> orders)
  {
    this.orders.set(orders);
  }

  public List<Order> getOrders()
  {
    return orders.get();
  }

  public SimpleScreeningView getSelectedScreening()
  {
    return selectedScreening;
  }

  public Screening getScreeningFromView()
  {
    SimpleScreeningView view = getSelectedScreening(); // Assumes this returns SimpleScreeningView
    return view.getScreening();
  }

  public void setSelectedScreening(SimpleScreeningView screening)
  {
    this.selectedScreening = screening;
  }

  public int getNumberOfStandardTickets()
  {
    return numberOfStandardTickets.get();
  }

  public SimpleIntegerProperty numberOfStandardTicketsProperty()
  {
    return numberOfStandardTickets;
  }

  public void setNumberOfStandardTickets(int numberOfStandardTickets)
  {
    this.numberOfStandardTickets.set(numberOfStandardTickets);
  }

  public int getNumberOfVIPTickets()
  {
    return numberOfVIPTickets.get();
  }

  public SimpleIntegerProperty numberOfVIPTicketsProperty()
  {
    return numberOfVIPTickets;
  }

  public void setNumberOfVIPTickets(int numberOfVIPTickets)
  {
    this.numberOfVIPTickets.set(numberOfVIPTickets);
  }
}
