package mediator;

import model.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.RemoteListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;

public class RmiClient implements Model, RemoteListener<String,String>
{
  private RemoteModel server;
  private PropertyChangeSupport property;

  public RmiClient(String host)
  {
    property = new PropertyChangeSupport(this);
    try
    {

      server = (RemoteModel) Naming.lookup("rmi://" + host + ":1099/Case");
      UnicastRemoteObject.exportObject(this,0);
      server.addListener(this);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  public void stop(){
    try
    {
      UnicastRemoteObject.unexportObject(this,true);
    }catch (Exception e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public ArrayList<Movie> getAllMovies()
  {
    try
    {
      return server.getAllMovies();
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public void deleteMovie(Movie movie)
  {
    try
    {
      server.deleteMovie(movie);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public void deleteAccount(String username)
  {
    try
    {
      server.deleteAccount(username);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public void changePrice(String item, double newPrice)
  {
    try
    {
      server.changePrice(item, newPrice);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  @Override public void changePrices(){
    try
    {
      server.changePrices();
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());}
  }

  @Override public double getPriceForTicket(String type)
  {
    try
    {
    return server.getPriceForTicket(type);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public void updateUser(User user, String previousUsername)
  {
    try
    {
      server.updateUser(user, previousUsername);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }


  @Override public void cancelOrder(Order order, User user)
  {
    try
    {
      server.cancelOrder(order, user);
    }catch (Exception e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public Ticket getTicketForView(Order order, String ID)
  {
    try
    {
      return server.getTicketForView(order, ID);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public Order getOrderByID(int orderID, User user)
  {
    try
    {
      return server.getOrderByID(orderID, user);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public Screening getScreeningForView(String time, String date,
      String title, int room)
  {
      try
      {
        return server.getScreeningForView(time, date, title, room);
      }catch (RemoteException e){
        throw new RuntimeException("Server connection error. "  + e.getMessage());
      }
  }


  @Override public Order[] getAllOrders(User user)
  {
    try
    {
      return server.getAllOrders(user);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }

  }

  @Override public Snack[] getSnacksFromOrder(Order order)

  {
   try
   {
     return server.getSnacksFromOrder(order);
   }catch (RemoteException e){
     throw new RuntimeException("Server connection error. "  + e.getMessage());
   }
  }

  @Override public Ticket[] getTicketsFromOrder(Order order)

  {
    try
    {
      return server.getTicketsFromOrder(order);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public Order reserveSeats(Seat[] seats, User customer,
      Screening screening, int nbVIP)
  {
    try
    {
     return server.reserveSeats(seats, customer, screening,nbVIP);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public double getPriceForSize(String snackType, String size)
  {
    try
    {
      return server.getPriceForSize(snackType, size);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  @Override public void addSnackToOrder(String snackType, int amount, Order order, User user,String size){
    try
    {
      server.addSnackToOrder(snackType, amount, order, user, size);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public boolean checkSeatAvailability(int index, Screening screening)

  {
    try
    {
      return server.checkSeatAvailability(index, screening);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public boolean logInAdmin(String username, String password)
  {
    try
    {
      return server.logInAdmin(username, password);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public void reserveSeat(Seat seat, User customer,
      Screening screening)
  {
    try
    {
      server.reserveSeat(seat, customer, screening);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public Seat[] getAvailableSeats(Screening screening)

  {
    try
    {
      return server.getAvailableSeats(screening);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public Seat[] getEmptySeats(Screening screening)

  {
    try
    {
      return server.getEmptySeats(screening);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public User getUserByUsername(String username)
  {
    try
    {
      return server.getUserByUsername(username);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }


  @Override public void updateSeatToBooked(Seat seat, Ticket ticket)

  {
    try
    {
      server.updateSeatToBooked(seat, ticket);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public void addOrder(Order order, User user)
  {
    try
    {
      server.addOrder(order, user);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }


  @Override public User logIn(String username, String password)
  {
    try
    {
      return server.logIn(username, password);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public void register(String username, String password, String email,
      String firstName, String lastName, String phone)
  {
    try
    {
      server.register(username, password, email, firstName, lastName, phone);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public void addScreening(Screening screening)
  {
    try
    {
      server.addScreening(screening);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public void removeScreening(Screening screening)

  {
    try
    {
      server.removeScreening(screening);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public void removeByDate(SimpleDate date)
  {
    try
    {
      server.removeByDate(date);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public ArrayList<Screening> getAllScreenings()
  {
    try
    {
      return server.getAllScreenings();
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public int getNbOfScreenings()
  {
    try
    {
      return server.getNbOfScreenings();
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public Screening getScreening(Screening screening)

  {
    try
    {
      return server.getScreening(screening);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public ArrayList<Ticket> getAllTickets(User user)
  {
    try
    {
      return server.getAllTickets(user);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public void downgradeTicket(Ticket ticket, Order order, User user)
  {
    try
    {
      server.downgradeTicket(ticket, order, user);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public void upgradeTicket(Ticket ticket, Order order, User user)
  {
    try
    {
      server.upgradeTicket(ticket, order, user);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public void cancelTicketFromOrder(Ticket ticket, Order order)
  {
    try
    {
      server.cancelTicketFromOrder(ticket, order);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public void deleteSnackFromOrder(Snack snack, Order order)
  {
    try
    {
      server.deleteSnackFromOrder(snack, order);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }



  @Override public ArrayList<Screening> getScreaningsByMovieTitle(String title)

  {
    try
    {
      return server.getScreaningsByMovieTitle(title);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public ArrayList<Screening> getScreeningsByDate(LocalDate date)

  {
    try
    {
      return server.getScreeningsByDate(date);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public ArrayList<Screening> getScreeningsByDateAndTitle(
      String title, LocalDate date)
  {
    try
    {
      return server.getScreeningsByDateAndTitle(title, date);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public ArrayList<Order> getOrdersForUser(String username)
  {
    try
    {
      return server.getOrdersForUser(username);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public Movie getMovieForView(String title)
  {
    try
    {
      return server.getMovieForView(title);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public void changePrices(ArrayList<Double> newPrices)
  {
    try
    {
      server.changePrices(newPrices);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
property.removePropertyChangeListener(listener);
  }

  @Override public void propertyChange(ObserverEvent<String, String> event)
      throws RemoteException
  {
    switch (event.getPropertyName()){
      case "RESERVE SEATS":
        property.firePropertyChange("reserve seats",null, event.getValue2());
        break;
      case "RESERVE SEAT":
        property.firePropertyChange("reserve seat", null, event.getValue2());
        break;
      case "ADD ORDER":
        property.firePropertyChange("add order", null, event.getValue2());
        break;
      case "ADD SCREENING":
        property.firePropertyChange("add screening",null,event.getValue2());
        break;
      case "REMOVE SCREENING":
        property.firePropertyChange("remove screening", null, event.getValue2());
        break;
      case "REMOVE ORDER":
        property.firePropertyChange("remove order", null,event.getValue2());
        break;
//      case "REMOVE BY DATE":
//        property.firePropertyChange("remove by date", null, event.getValue2());
//        break;
    }
  }
}
