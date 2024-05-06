package client_model;

import client_mediator.RmiClient;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class ModelManager implements Model
{
  private RmiClient client;

  //not sure if the user variable is the one connected here
  private User user;
  private PropertyChangeSupport propertyChangeSupport;

  public ModelManager()
  {

    this.user = null;
// comment see if it works
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);

  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }

  @Override public void logIn(User customer)
  {
    //not for this sprint
  }

  @Override public void connect()
  {
    //not for this sprint
  }

  @Override public void disconnect()
  {
    //not for this sprint
  }

  @Override public int getPort()
  {
    //    return client.getPort;
    return 0;
  }

  @Override public void setPort(int port)
  {
    // this.PORT = port;
  }

  @Override public String getHost()
  {
    //return HOST;
    return null;
  }

  @Override public void reserveSeat(Seat seat, User customer,
      Screening screening)
  {
    try
    {
      client.reserveSeat(seat, customer, screening);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

  }

  @Override public boolean checkSeatAvailability(int index, Screening screening)
  {
    try
    {
      return client.checkSeatAvailability(index, screening);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override public Seat[] getAvailableSeats(Screening screening)
  {
    try
    {
      return client.getAvailableSeats(screening);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Seat[] getEmptySeats(Screening screening)
  {
    try
    {
      return client.getEmptySeats(screening);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public double calculateTotalPrice()
  {
    double price = 0;
    for (int i = 0; i < user.getOrders().size(); i++)
    {
      price += user.getOrders().get(0).getOrderPrice();
    }
    return price;
  }

  @Override public void updateSeatToBooked(Seat seat, Ticket ticket)
  {
    try
    {
      client.updateSeatToBooked(seat, ticket);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addOrder(Order order)
  {
    try
    {
      client.addOrder(order);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override public void logIn(String username, String password)
  {
    //LATER
  }

  @Override public void register(String username, String password, String email,
      String firstName, String lastName, String phone)
  {
//LATER
  }

  @Override public void reserveSeats(Seat[] seats, User customer,
      Screening screening)
  {
    try
    {
      client.reserveSeats(seats, customer, screening);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}