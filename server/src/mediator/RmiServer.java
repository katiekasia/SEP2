package mediator;

import model.*;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RmiServer implements RemoteModel
{
  private Model cinema;
  private PropertyChangeHandler<String, String> property;
  private Registry registry;

  public RmiServer()
  {
    cinema = new ModelManager();
    property = new PropertyChangeHandler<>(this);
    try
    {
      startRegistry();
      start();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  public void deleteAccount(String username) throws RemoteException
  {
    cinema.deleteAccount(username);
  }
  @Override public void changePrices(){
    cinema.changePrices();
  }

  @Override public void changePrice(String item, double newPrice)
      throws RemoteException
  {
    cinema.changePrice(item, newPrice);
  }

  public void stop(){
   try
   {
     stopRegistry();
     UnicastRemoteObject.unexportObject(this,true);
   }catch (Exception e){
     e.printStackTrace();
   }
  }

  private void stopRegistry(){
    try {
      if (registry != null) {
        UnicastRemoteObject.unexportObject(registry, true);
        System.out.println("Registry stopped...");
      }
    } catch (Exception e) {
      System.out.println("Error while stopping registry: " + e.getMessage());
    }
  }
  private void startRegistry() throws RemoteException
  {
    try
    {
      registry = LocateRegistry.createRegistry(1099);

      System.out.println("Registry started...");
    }
    catch (ExportException e)
    {
      System.out.println("Registry already started? " + e.getMessage());
    }
  }

  private void start() throws RemoteException, MalformedURLException
  {

    UnicastRemoteObject.exportObject(this, 0);
    Naming.rebind("Case", this);
  }

  @Override public void updateUser(User user, String previousUsername)
      throws RemoteException
  {
    cinema.updateUser(user, previousUsername);
  }

  @Override public Order reserveSeats(Seat[] seats, User customer,
      Screening screening, int nbVIP) throws RemoteException
  {
    property.firePropertyChange("RESERVE SEATS", null, screening.getTime());
    return cinema.reserveSeats(seats, customer, screening, nbVIP);

  }
  @Override public double getPriceForSize(String snackType, String size){
    return cinema.getPriceForSize(snackType, size);
  }

  @Override public boolean logInAdmin(String username, String password)
      throws RemoteException
  {
    return cinema.logInAdmin(username, password);
  }

  @Override public void addSnackToOrder(String snackType, int amount, Order order, User user,String size){
    cinema.addSnackToOrder(snackType, amount, order, user, size);
  }

  @Override public Order getOrderByID(int orderID, User user)
  {
    return cinema.getOrderByID(orderID, user);
  }

  @Override public Screening getScreeningForView(String time, String date,
      String title, int room) throws RemoteException
  {
    return cinema.getScreeningForView(time, date, title, room);
  }

  @Override public Order[] getAllOrders(User user) throws RemoteException
  {
    return cinema.getAllOrders(user);
  }

  @Override public Snack[] getSnacksFromOrder(Order order)
      throws RemoteException
  {
    return cinema.getSnacksFromOrder(order);
  }

  @Override public Ticket[] getTicketsFromOrder(Order order)
      throws RemoteException
  {
    return cinema.getTicketsFromOrder(order);
  }

  @Override public Movie getMovieForView(String title) throws RemoteException
  {
    return cinema.getMovieForView(title);
  }

  @Override public void changePrices(ArrayList<Double> newPrices)
      throws RemoteException
  {
    cinema.changePrices(newPrices);
  }

  @Override public void cancelOrder(Order order, User user) throws RemoteException
  {
    cinema.cancelOrder(order, user);
    property.firePropertyChange("REMOVE ORDER", null, user.getUsername());
  }

  @Override public boolean checkSeatAvailability(int index, Screening screening)
      throws RemoteException
  {
    return cinema.checkSeatAvailability(index, screening);
  }

  @Override public void reserveSeat(Seat seat, User customer,
      Screening screening) throws RemoteException
  {
    cinema.reserveSeat(seat, customer, screening);
    property.firePropertyChange("RESERVE SEAT", null, screening.getTime());
  }

  @Override public Seat[] getAvailableSeats(Screening screening)
      throws RemoteException
  {
    return cinema.getAvailableSeats(screening);
  }

  @Override public Seat[] getEmptySeats(Screening screening)
      throws RemoteException
  {
    return cinema.getEmptySeats(screening);
  }

  @Override public void updateSeatToBooked(Seat seat, Ticket ticket)
      throws RemoteException
  {
    cinema.updateSeatToBooked(seat, ticket);
    property.firePropertyChange("UPDATE SEAT TO BOOKED", null,
        ticket.toString());
  }

  @Override public void addOrder(Order order, User user) throws RemoteException
  {
    cinema.addOrder(order, user);
    property.firePropertyChange("ADD ORDER", null, user.getUsername());
  }

  @Override public User logIn(String username, String password)
      throws RemoteException
  {
    return cinema.logIn(username, password);
  }

  @Override public Ticket getTicketForView(Order order, String ID)
      throws RemoteException
  {
    return cinema.getTicketForView(order, ID);
  }

  @Override public void register(String username, String password, String email,
      String firstName, String lastName, String phone) throws RemoteException
  {
    cinema.register(username, password, email, firstName, lastName, phone);
  }

  @Override public void addScreening(Screening screening) throws RemoteException
  {
    cinema.addScreening(screening);
    property.firePropertyChange("ADD SCREENING", null, screening.getTime());
  }

  @Override public void removeScreening(Screening screening)
      throws RemoteException
  {
    cinema.removeScreening(screening);
    property.firePropertyChange("REMOVE SCREENING", null, screening.getTime());
  }

  @Override public void removeByDate(SimpleDate date) throws RemoteException
  {
    cinema.removeByDate(date);
    property.firePropertyChange("REMOVE BY DATE", null, date.toString());
  }

  @Override public ArrayList<Screening> getAllScreenings()
      throws RemoteException
  {
    return cinema.getAllScreenings();
  }

  @Override public int getNbOfScreenings() throws RemoteException
  {
    return cinema.getNbOfScreenings();
  }

  @Override public Screening getScreening(Screening screening)
      throws RemoteException
  {
    return cinema.getScreening(screening);
  }

  @Override public ArrayList<Ticket> getAllTickets(User user)
      throws RemoteException
  {
    return cinema.getAllTickets(user);
  }

  @Override public void downgradeTicket(Ticket ticket, Order order, User user)
      throws RemoteException
  {
    cinema.downgradeTicket(ticket, order, user);
  }

  @Override public void upgradeTicket(Ticket ticket, Order order, User user)
      throws RemoteException
  {
     cinema.upgradeTicket(ticket, order, user);
  }

  @Override public void cancelTicketFromOrder(Ticket ticket, Order order)
      throws RemoteException
  {
    cinema.cancelTicketFromOrder(ticket, order);
  }

  @Override public void deleteSnackFromOrder(Snack snack, Order order)
      throws RemoteException
  {
    cinema.deleteSnackFromOrder(snack, order);
  }

  @Override public ArrayList<Screening> getScreeningsByDateAndTitle(
      String title, LocalDate date) throws RemoteException
  {
    return cinema.getScreeningsByDateAndTitle(title, date);
  }

  @Override public ArrayList<Order> getOrdersForUser(String username)
      throws RemoteException
  {
    return cinema.getOrdersForUser(username);
  }

  @Override public ArrayList<Screening> getScreaningsByMovieTitle(String title)
      throws RemoteException
  {
    return cinema.getScreaningsByMovieTitle(title);
  }

  @Override public ArrayList<Screening> getScreeningsByDate(LocalDate date)
      throws RemoteException
  {
    return cinema.getScreeningsByDate(date);
  }

  @Override public User getUserByUsername(String username)
      throws RemoteException
  {
    return cinema.getUserByUsername(username);
  }

  @Override public boolean addListener(GeneralListener<String, String> listener,
      String... propertyNames) throws RemoteException
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
      throws RemoteException
  {
    return property.removeListener(listener, propertyNames);
  }
}
