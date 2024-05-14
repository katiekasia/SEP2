package mediator;

import model.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.RemoteListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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
      //server.addListener(this); for listener
      UnicastRemoteObject.exportObject(this,0);
      server.addListener(this);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void start() throws RemoteException
  {
    Scanner input = new Scanner(System.in);
    while (true)
    {
      //
    }
  }

  @Override public void logIn(User user)
  {

  }

  @Override public void connect()
  {

  }

  @Override public void disconnect()
  {

  }

  @Override public int getPort()
  {
    return 0;
  }

  @Override public void setPort(int port)
  {

  }

  @Override public String getUsername()
  {
    return null;
  }

  @Override public String getHost()
  {
    return null;
  }

  @Override public void reserveSeats(Seat[] seats, User customer,
      Screening screening) throws RemoteException
  {
    server.reserveSeats(seats, customer, screening);
  }

  @Override public boolean checkSeatAvailability(int index, Screening screening)
      throws RemoteException
  {
    return server.checkSeatAvailability(index, screening);
  }

  @Override public void reserveSeat(Seat seat, User customer,
      Screening screening) throws RemoteException
  {
    server.reserveSeat(seat, customer, screening);
  }

  @Override public Seat[] getAvailableSeats(Screening screening)
      throws RemoteException
  {
    return server.getAvailableSeats(screening);
  }

  @Override public Seat[] getEmptySeats(Screening screening)
      throws RemoteException
  {
    return server.getEmptySeats(screening);
  }

  @Override public double calculateTotalPrice()
  {
    return 0;
  }

  @Override public void updateSeatToBooked(Seat seat, Ticket ticket)
      throws RemoteException
  {
    server.updateSeatToBooked(seat, ticket);
  }

  @Override public void addOrder(Order order) throws RemoteException
  {
    server.addOrder(order);
  }

  @Override public void logIn(String username, String password) throws RemoteException
  {
    server.logIn(username, password);
  }

  @Override public void register(String username, String password, String email,
      String firstName, String lastName, String phone) throws RemoteException
  {
server.register(username, password, email, firstName, lastName, phone);
  }

  @Override public void addScreening(Screening screening) throws RemoteException
  {
server.addScreening(screening);
  }

  @Override public void removeScreening(Screening screening)
      throws RemoteException
  {
server.removeScreening(screening);
  }

  @Override public void removeByDate(SimpleDate date) throws RemoteException
  {
server.removeByDate(date);
  }

  @Override public ArrayList<Screening> getAllScreenings()
      throws RemoteException
  {
    return server.getAllScreenings();
  }

  @Override public int getNbOfScreenings() throws RemoteException
  {
    return server.getNbOfScreenings();
  }

  @Override public Screening getScreening(Screening screening)
      throws RemoteException
  {
    return server.getScreening(screening);
  }

  @Override public ArrayList<Ticket> getAllTickets() throws RemoteException
  {
    return server.getAllTickets();
  }

  @Override public User getUser() throws RemoteException
  {
    return server.getUser();
  }

  @Override public Screening findScreeningBySeatId(String seatId)
      throws RemoteException
  {
    return server.findScreeningBySeatId(seatId);
  }

  @Override public ArrayList<Screening> getScreaningsByMovieTitle(String title)
      throws RemoteException
  {
    return server.getScreaningsByMovieTitle(title);
  }

  @Override public ArrayList<Screening> getScreeningsByDate(LocalDate date)
      throws RemoteException
  {
    return server.getScreeningsByDate(date);
  }

  public static void main(String[] args) throws RemoteException
  {
    if (System.getSecurityManager() == null)
    {
      System.setSecurityManager(new SecurityManager());
    }
    String host = "localhost";
    if (args.length > 0)
    {
      host = args[0];
    }
    RmiClient client = new RmiClient(host);
    client.start();
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

  }
}
