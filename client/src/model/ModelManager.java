package model;

import mediator.RmiClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class ModelManager implements Model, PropertyChangeListener
{
  private User user;
  private PropertyChangeSupport propertyChangeSupport;
  private RmiClient client;

  public ModelManager(RmiClient client)
  {
    this.user = null;
    this.propertyChangeSupport = new PropertyChangeSupport(this);
    this.client = client;
    client.addListener(this);
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }

  @Override public void deleteAccount(String username)
  {
    client.deleteAccount(username);
  }

  @Override public void cancelOrder(Order order, User user)
  {
    client.cancelOrder(order, user);
  }

  @Override public Order getOrderByID(int orderID, User user)
  {
    return client.getOrderByID(orderID, user);
  }

  @Override public Screening getScreeningForView(String time, String date,
      String title, int room)
  {
      return client.getScreeningForView(time, date, title, room);
  }

  @Override public User logIn(String username, String password)
  {
      User loggedInUser = client.logIn(username, password);
      this.user = loggedInUser;
      return loggedInUser;
  }
  @Override  public void updateUser(User user, String previousUsername)
  {
      client.updateUser(user, previousUsername);
  }
  @Override public Order[] getAllOrders(User user)
  {
   return client.getAllOrders(user);
  }

  @Override public Snack[] getSnacksFromOrder(Order order)

  {
   return client.getSnacksFromOrder(order);
  }

  @Override public Ticket[] getTicketsFromOrder(Order order)

  {
    return client.getTicketsFromOrder(order);
  }

  @Override public void reserveSeat(Seat seat, User customer,
      Screening screening)
  {
      client.reserveSeat(seat, customer, screening);
  }

  @Override public boolean checkSeatAvailability(int index, Screening screening)
  {
      return client.checkSeatAvailability(index,screening);
  }

  @Override public boolean logInAdmin(String username, String password)
  {
    return client.logInAdmin(username, password);
  }

  @Override public Seat[] getAvailableSeats(Screening screening)
  {
     return client.getAvailableSeats(screening);
  }

  @Override public Seat[] getEmptySeats(Screening screening)
  {
     return client.getEmptySeats(screening);
  }

  @Override public User getUserByUsername(String username)
  {
    return client.getUserByUsername(username);
  }



  @Override public void updateSeatToBooked(Seat seat, Ticket ticket)
  {
  client.updateSeatToBooked(seat,ticket);
  }

  @Override public void addOrder(Order order, User user)
  {
    client.addOrder(order, user);
  }


    @Override public void register(String username, String password, String email,
      String firstName, String lastName, String phone)
  {
      client.register(username, password, email, firstName, lastName, phone);
  }

  @Override public void addScreening(Screening screening)
  {
      client.addScreening(screening);
  }

  @Override public void removeScreening(Screening screening)
  {
      client.removeScreening(screening);
  }

  @Override public void removeByDate(SimpleDate date)
  {
      client.removeByDate(date);
  }

  @Override public ArrayList<Screening> getAllScreenings()
  {
      return client.getAllScreenings();
  }

  @Override public int getNbOfScreenings()
  {
      return client.getNbOfScreenings();
  }

  @Override public Screening getScreening(Screening screening)
  {
     return client.getScreening(screening);

  }

  @Override public ArrayList<Ticket> getAllTickets(User user)
  {
      return client.getAllTickets(user);
  }
@Override public Ticket getTicketForView(Order order, String ID){
    return client.getTicketForView(order, ID);
}
  @Override public void downgradeTicket(Ticket ticket, Order order, User user)
  {
    client.downgradeTicket(ticket,order, user);
  }

  @Override public void upgradeTicket(Ticket ticket, Order order, User user)
  {
    client.upgradeTicket(ticket, order, user);
  }

  @Override public void cancelTicketFromOrder(Ticket ticket, Order order)
  {
    client.cancelTicketFromOrder(ticket, order);
  }

  @Override public void deleteSnackFromOrder(Snack snack, Order order)
  {
    client.deleteSnackFromOrder(snack, order);
  }


  @Override public ArrayList<Screening> getScreaningsByMovieTitle(String title)
  {
    try
    {
      return client.getScreaningsByMovieTitle(title);
    }catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public ArrayList<Screening> getScreeningsByDate(LocalDate date)
  {
    try
    {
      return client.getScreeningsByDate(date);
    }catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public ArrayList<Screening> getScreeningsByDateAndTitle(
      String title, LocalDate date)
  {
    return client.getScreeningsByDateAndTitle(title, date);
  }

  @Override public ArrayList<Order> getOrdersForUser(String username)
  {
    return client.getOrdersForUser(username);
  }

  @Override public Movie getMovieForView(String title)
  {
    return client.getMovieForView(title);
  }

  @Override public void changePrices(ArrayList<Double> newPrices)
  {
    client.changePrices(newPrices);
  }

  @Override public Order reserveSeats(Seat[] seats, User customer,
      Screening screening, int nbVIP)
  {
try
{
 return client.reserveSeats(seats, customer, screening, nbVIP);
}catch (Exception e){
  e.printStackTrace();
}
return null;
  }

  @Override public double getPriceForSize(String snackType, String size)
  {
    return client.getPriceForSize(snackType, size);
  }
  @Override public void addSnackToOrder(String snackType, int amount, Order order, User user,String size){
    client.addSnackToOrder(snackType, amount, order, user, size);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    switch (evt.getPropertyName()){
      case "reserve seats":
        propertyChangeSupport.firePropertyChange("reserveSeats" + evt.getNewValue(), null, evt.getNewValue());
        break;
      case "reserve seat":
        propertyChangeSupport.firePropertyChange("reserveSeat" + evt.getNewValue(), null, evt.getNewValue());
        break;
      case "add order":
        propertyChangeSupport.firePropertyChange("addOrder" + evt.getNewValue(), null, evt.getNewValue());
        break;
      case "remove order":
        propertyChangeSupport.firePropertyChange("removeOrder" + evt.getNewValue(), null, evt.getNewValue());
        break;
      case "add screening":
        propertyChangeSupport.firePropertyChange("addScreening",null,evt.getNewValue());
        break;
      case "remove screening":
        propertyChangeSupport.firePropertyChange("removeScreening", null, evt.getNewValue());
        break;
//      case "remove by date":
//        propertyChangeSupport.firePropertyChange("removeByDate", null, evt.getNewValue());
//        break;
    }
  }

}
