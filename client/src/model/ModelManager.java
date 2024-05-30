package model;

import mediator.RmiClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
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

  @Override public void removeScreening(Screening screening)
  {
    client.removeScreening(screening);
  }

  @Override public void deleteMovie(Movie movie)
  {
    try
    {
      client.deleteMovie(movie);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public ArrayList<Movie> getAllMovies()
  {
    try
    {
      return client.getAllMovies();
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }
  }

  @Override public void deleteAccount(String username)
  {

    try
    {
      client.deleteAccount(username);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }
  }

  @Override public void changePrice(String item, double newPrice)
  {
    try
    {
      client.changePrice(item, newPrice);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public void addMovie(Movie movie)
  {
    try
    {
      client.addMovie(movie);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }
  }

  @Override public void changePrices()
  {
    try
    {
      client.changePrices();
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public double getPriceForTicket(String type)
  {
    try
    {
      return client.getPriceForTicket(type);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public void cancelOrder(Order order, User user)
  {
    try
    {
      client.cancelOrder(order, user);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public Order getOrderByID(int orderID, User user)
  {
    try
    {
      return client.getOrderByID(orderID, user);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public Screening getScreeningForView(String time, String date,
      String title, int room)
  {
    try
    {
      return client.getScreeningForView(time, date, title, room);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public User logIn(String username, String password)
  {
    try
    {
      User loggedInUser = client.logIn(username, password);
      this.user = loggedInUser;
      return loggedInUser;
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
     throw e;
    }
  }

  @Override public void updateUser(User user, String previousUsername)
  {
    try
    {
      client.updateUser(user, previousUsername);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public Order[] getAllOrders(User user)
  {
    try
    {
      return client.getAllOrders(user);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public Snack[] getSnacksFromOrder(Order order)

  {
    try
    {
      return client.getSnacksFromOrder(order);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public Ticket[] getTicketsFromOrder(Order order)

  {
    try
    {
      return client.getTicketsFromOrder(order);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public void reserveSeat(Seat seat, User customer,
      Screening screening)
  {
    try
    {
      client.reserveSeat(seat, customer, screening);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public boolean checkSeatAvailability(int index, Screening screening)
  {
    try
    {
      return client.checkSeatAvailability(index, screening);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public boolean logInAdmin(String username, String password)
  {
    try
    {
      return client.logInAdmin(username, password);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public Seat[] getAvailableSeats(Screening screening)
  {
    try
    {
      return client.getAvailableSeats(screening);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public Seat[] getEmptySeats(Screening screening)
  {
    try
    {
      return client.getEmptySeats(screening);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public User getUserByUsername(String username)
  {
    try
    {
      return client.getUserByUsername(username);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public void updateSeatToBooked(Seat seat, Ticket ticket)
  {
    try
    {
      client.updateSeatToBooked(seat, ticket);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public void addOrder(Order order, User user)
  {
    try
    {
      client.addOrder(order, user);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public void register(User user)
  {
    try
    {
      client.register(user);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public void addScreening(Screening screening)
  {
    try
    {
      client.addScreening(screening);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public void removeByDate(SimpleDate date)
  {
    try
    {
      client.removeByDate(date);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public ArrayList<Screening> getAllScreenings()
  {
    try
    {
      return client.getAllScreenings();
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public int getNbOfScreenings()
  {
    try
    {
      return client.getNbOfScreenings();
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public Screening getScreening(Screening screening)
  {
    try
    {
      return client.getScreening(screening);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public ArrayList<Ticket> getAllTickets(User user)
  {
    try
    {
      return client.getAllTickets(user);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public Ticket getTicketForView(Order order, String ID)
  {
    try
    {
      return client.getTicketForView(order, ID);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public void downgradeTicket(Ticket ticket, Order order, User user)
  {
    try
    {
      client.downgradeTicket(ticket, order, user);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public void upgradeTicket(Ticket ticket, Order order, User user)
  {
    try
    {
      client.upgradeTicket(ticket, order, user);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public void cancelTicketFromOrder(Ticket ticket, Order order)
  {
    try
    {
      client.cancelTicketFromOrder(ticket, order);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public Room getRoomById(String id)
  {
    try
    {
     return client.getRoomById(id);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e){
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }
  }

  @Override public void deleteSnackFromOrder(Snack snack, Order order)
  {
    try
    {
      client.deleteSnackFromOrder(snack, order);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public ArrayList<Screening> getScreaningsByMovieTitle(String title)
  {
    try
    {
      return client.getScreaningsByMovieTitle(title);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }
  }

  @Override public ArrayList<Screening> getScreeningsByDate(LocalDate date)
  {
    try
    {
      return client.getScreeningsByDate(date);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }
  }

  @Override public ArrayList<Screening> getScreeningsByDateAndTitle(
      String title, LocalDate date)
  {
    try
    {
      return client.getScreeningsByDateAndTitle(title, date);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }
  }

  @Override public ArrayList<Order> getOrdersForUser(String username)
  {
    try
    {
      return client.getOrdersForUser(username);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public Movie getMovieForView(String title)
  {
    try
    {
      return client.getMovieForView(title);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public void changePrices(ArrayList<Double> newPrices)
  {
    try
    {
      client.changePrices(newPrices);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public Order reserveSeats(Seat[] seats, User customer,
      Screening screening, int nbVIP)
  {
    try
    {
      return client.reserveSeats(seats, customer, screening, nbVIP);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }
  }

  @Override public double getPriceForSize(String snackType, String size)
  {
    try
    {
      return client.getPriceForSize(snackType, size);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public void addSnackToOrder(String snackType, int amount,
      Order order, User user, String size)
  {
    try
    {
      client.addSnackToOrder(snackType, amount, order, user, size);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    switch (evt.getPropertyName())
    {
      case "reserve seats":
        propertyChangeSupport.firePropertyChange(
            "reserveSeats" + evt.getNewValue(), null, evt.getNewValue());
        break;
      case "reserve seat":
        propertyChangeSupport.firePropertyChange(
            "reserveSeat" + evt.getNewValue(), null, evt.getNewValue());
        break;
      case "add order":
        propertyChangeSupport.firePropertyChange("addOrder" + evt.getNewValue(),
            null, evt.getNewValue());
        break;
      case "remove order":
        propertyChangeSupport.firePropertyChange(
            "removeOrder" + evt.getNewValue(), null, evt.getNewValue());
        break;
      case "add screening":
        propertyChangeSupport.firePropertyChange("addScreening", null,
            evt.getNewValue());
        break;
      case "remove screening":
        propertyChangeSupport.firePropertyChange("removeScreening", null,
            evt.getNewValue());
        break;
      //      case "remove by date":
      //        propertyChangeSupport.firePropertyChange("removeByDate", null, evt.getNewValue());
      //        break;
    }
  }

}
