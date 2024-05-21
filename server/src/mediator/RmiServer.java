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
import java.time.LocalDate;
import java.util.ArrayList;

public class RmiServer implements RemoteModel
{
private Model cinema;
private PropertyChangeHandler<String ,String > property;
public RmiServer(){
  cinema = new ModelManager();
  property = new PropertyChangeHandler<>(this);
try
{
startRegistry();
start();
}catch (Exception e){
e.printStackTrace();
}
}
private void startRegistry() throws RemoteException
{
try
{
  Registry reg = LocateRegistry.createRegistry(1099);
  System.out.println("Registry started...");
}catch (ExportException e){
  System.out.println("Registry already started? " + e.getMessage());
}
}
private void start() throws RemoteException, MalformedURLException
{

  UnicastRemoteObject.exportObject(this,0);
  Naming.rebind("Case", this);
}

  @Override public void updateUser(User user, String previousUsername) throws RemoteException
  {
    System.out.println("rmi server");
    cinema.updateUser(user, previousUsername);
      }

  @Override public void reserveSeats(Seat[] seats, User customer,
       Screening screening, int nbVIP) throws RemoteException
  {
    cinema.reserveSeats(seats,customer, screening,nbVIP);
    property.firePropertyChange("RESERVE SEATS", null, screening.getTime());
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

  @Override public void cancelOrder(Order order) throws RemoteException
  {
    cinema.cancelOrder(order);
  }

  @Override public boolean checkSeatAvailability(int index, Screening screening)
      throws RemoteException
  {
    return cinema.checkSeatAvailability(index,screening);
  }

  @Override public void reserveSeat(Seat seat, User customer,
      Screening screening) throws RemoteException
  {
    cinema.reserveSeat(seat,customer,screening);
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

  @Override public void updateSeatToBooked(Seat seat, Ticket ticket) throws RemoteException
  {
    cinema.updateSeatToBooked(seat, ticket);
    property.firePropertyChange("UPDATE SEAT TO BOOKED", null, ticket.toString());
  }

  @Override public void addOrder(Order order,User user) throws RemoteException
  {
    cinema.addOrder(order,user);
    property.firePropertyChange("ADD ORDER", null, order.toString());
  }

  @Override public User logIn(String username, String password)
      throws RemoteException
  {
   return cinema.logIn(username,password);
  }

  @Override public void register(String username, String password, String email,
      String firstName, String lastName, String phone) throws RemoteException
  {
    cinema.register(username, password, email, firstName, lastName, phone);
  }

  @Override public void addScreening(Screening screening) throws RemoteException
  {
    cinema.addScreening(screening);
    property.firePropertyChange("ADD SCREENING",null, screening.getTime());
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

  @Override public ArrayList<Ticket> getAllTickets(User user) throws RemoteException
  {
    return cinema.getAllTickets(user);
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

  //Overrides for methods
  public static void main(String[] args)
  {
    RemoteModel server = new RmiServer();
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
