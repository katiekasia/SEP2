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
private PropertyChangeHandler<String ,String> property;
public RmiServer(){
  cinema = new ModelManager();
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
  property = new PropertyChangeHandler<>(this,true);
  UnicastRemoteObject.exportObject(this,0);
  Naming.rebind("Case", this);
}

  @Override public void reserveSeats(Seat[] seats, User customer,
       Screening screening) throws RemoteException
  {
    property.firePropertyChange("RESERVE SEATS",null,screening.getTime());
    cinema.reserveSeats(seats,customer, screening);
  }

  @Override public boolean checkSeatAvailability(int index, Screening screening)
      throws RemoteException
  {
    return cinema.checkSeatAvailability(index,screening);
  }

  @Override public void reserveSeat(Seat seat, User customer,
      Screening screening) throws RemoteException
  {
    property.firePropertyChange("RESERVE SEAT", null, screening.getTime());
    cinema.reserveSeat(seat,customer,screening);
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
  {
    property.firePropertyChange("UPDATE TO BOOKED", null, ticket.getTicketID());
    cinema.updateSeatToBooked(seat, ticket);
  }

  @Override public void addOrder(Order order) throws RemoteException
  {
    property.firePropertyChange("ADD ORDER", null, order.toString());
    cinema.addOrder(order);
  }

  @Override public void logIn(String username, String password)
      throws RemoteException
  {
    cinema.logIn(username, password);
  }

  @Override public void register(String username, String password, String email,
      String firstName, String lastName, String phone) throws RemoteException
  {
    cinema.register(username, password, email, firstName, lastName, phone);
  }

  @Override public void addScreening(Screening screening) throws RemoteException
  {
    property.firePropertyChange("ADD SCREENING",null, screening.getTime());
cinema.addScreening(screening);
  }

  @Override public void removeScreening(Screening screening)
      throws RemoteException
  {
    property.firePropertyChange("REMOVE SCREENING", null, screening.getTime());
cinema.removeScreening(screening);
  }

  @Override public void removeByDate(SimpleDate date) throws RemoteException
  {
    property.firePropertyChange("REMOVE BY DATE", null, date.toString());
cinema.removeByDate(date);
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

  @Override public ArrayList<Ticket> getAllTickets() throws RemoteException
  {
    return cinema.getAllTickets();
  }

  @Override public User getUser() throws RemoteException
  {
    return cinema.getUser();
  }

  @Override public Screening findScreeningBySeatId(String seatId)
      throws RemoteException
  {
    return cinema.findScreeningBySeatId(seatId);
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

    return property.addListener(listener,propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
      throws RemoteException
  {
    return property.removeListener(listener, propertyNames);
  }
}
