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

  @Override public void updateUser(User user)
  {
    try
    {
      server.updateUser(user);
    }catch (RemoteException e) {
      e.printStackTrace();
  }
  }

  @Override public void cancelOrder(Order order)
  {
    try
    {
      server.cancelOrder(order);
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override public Screening getScreeningForView(String time, String date,
      String title, int room)
  {
      try
      {
        return server.getScreeningForView(time, date, title, room);
      }catch (RemoteException e){
        e.printStackTrace();
      }
      return null;
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

  @Override public Order[] getAllOrders(User user)
  {
    try
    {
      return server.getAllOrders(user);
    }catch (RemoteException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public Snack[] getSnacksFromOrder(Order order)

  {
   try
   {
     return server.getSnacksFromOrder(order);
   }catch (RemoteException e){
     e.printStackTrace();
   }
   return null;
  }

  @Override public Ticket[] getTicketsFromOrder(Order order)

  {
    try
    {
      return server.getTicketsFromOrder(order);
    }catch (RemoteException e){
      e.printStackTrace();
    }
    return null;
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
      Screening screening, int nbVIP)
  {
    try
    {
      server.reserveSeats(seats, customer, screening,nbVIP);
    }catch (RemoteException e){
      e.printStackTrace();
    }
  }

  @Override public boolean checkSeatAvailability(int index, Screening screening)

  {
    try
    {
      return server.checkSeatAvailability(index, screening);
    }catch (RemoteException e){
      e.printStackTrace();
    }
    return false;
  }

  @Override public void reserveSeat(Seat seat, User customer,
      Screening screening)
  {
    try
    {
      server.reserveSeat(seat, customer, screening);
    }catch (RemoteException e){
      e.printStackTrace();
    }
  }

  @Override public Seat[] getAvailableSeats(Screening screening)

  {
    try
    {
      return server.getAvailableSeats(screening);
    }catch (RemoteException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public Seat[] getEmptySeats(Screening screening)

  {
    try
    {
      return server.getEmptySeats(screening);
    }catch (RemoteException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public double calculateTotalPrice()
  {
    return 0;
  }

  @Override public void updateSeatToBooked(Seat seat, Ticket ticket)

  {
    try
    {
      server.updateSeatToBooked(seat, ticket);
    }catch (RemoteException e){
      e.printStackTrace();
    }
  }

  @Override public void addOrder(Order order)
  {
    try
    {
      server.addOrder(order);
    }catch (RemoteException e){
      e.printStackTrace();
    }
  }

  @Override public User logIn(String username, String password)
  {
    try
    {
      return server.logIn(username, password);
    }catch (RemoteException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public void register(String username, String password, String email,
      String firstName, String lastName, String phone)
  {
    try
    {
      server.register(username, password, email, firstName, lastName, phone);
    }catch (RemoteException e){
      e.printStackTrace();
    }
  }

  @Override public void addScreening(Screening screening)
  {
    try
    {
      server.addScreening(screening);
    }catch (RemoteException e){
      e.printStackTrace();
    }
  }

  @Override public void removeScreening(Screening screening)

  {
    try
    {
      server.removeScreening(screening);
    }catch (RemoteException e){
      e.printStackTrace();
    }
  }

  @Override public void removeByDate(SimpleDate date)
  {
    try
    {
      server.removeByDate(date);
    }catch (RemoteException e){
      e.printStackTrace();
    }
  }

  @Override public ArrayList<Screening> getAllScreenings()
  {
    try
    {
      return server.getAllScreenings();
    }catch (RemoteException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public int getNbOfScreenings()
  {
    try
    {
      return server.getNbOfScreenings();
    }catch (RemoteException e){
      e.printStackTrace();
    }
    return 0;
  }

  @Override public Screening getScreening(Screening screening)

  {
    try
    {
      return server.getScreening(screening);
    }catch (RemoteException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public ArrayList<Ticket> getAllTickets()
  {
    try
    {
      return server.getAllTickets();
    }catch (RemoteException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public User getUser()
  {
    try
    {
      return server.getUser();
    }catch (RemoteException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public Screening findScreeningBySeatId(String seatId)

  {
    try
    {
      return server.findScreeningBySeatId(seatId);
    }catch (RemoteException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public ArrayList<Screening> getScreaningsByMovieTitle(String title)

  {
    try
    {
      return server.getScreaningsByMovieTitle(title);
    }catch (RemoteException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public ArrayList<Screening> getScreeningsByDate(LocalDate date)

  {
    try
    {
      return server.getScreeningsByDate(date);
    }catch (RemoteException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public ArrayList<Order> getOrdersForUser(String username)
  {
    return null;
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
    switch (event.getPropertyName()){
      case "RESERVE SEATS":
        property.firePropertyChange("reserve seats",null, event.getValue2());
        break;
      case "RESERVE SEAT":
        property.firePropertyChange("reserve seat", null, event.getValue2());
        break;
      case "UPDATE SEAT TO BOOKED":
        property.firePropertyChange("update seat to booked", null, event.getValue2());
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
      case "REMOVE BY DATE":
        property.firePropertyChange("remove by date", null, event.getValue2());
        break;
    }
  }
}
