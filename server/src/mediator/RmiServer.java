package mediator;


import model.*;
import utility.observer.listener.GeneralListener;

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

  UnicastRemoteObject.exportObject(this,0);
  Naming.rebind("Case", this);
}

  @Override public void reserveSeats(Seat[] seats, User customer,
       Screening screening) throws RemoteException
  {
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
    cinema.updateSeatToBooked(seat, ticket);
  }

  @Override public void addOrder(Order order) throws RemoteException
  {
    cinema.addOrder(order);
  }

  @Override public void logIn(String username, String password)
      throws RemoteException
  {

  }

  @Override public void register(String username, String password, String email,
      String firstName, String lastName, String phone) throws RemoteException
  {

  }

  @Override public void addScreening(Screening screening) throws RemoteException
  {

  }

  @Override public void removeScreening(Screening screening)
      throws RemoteException
  {

  }

  @Override public void removeByDate(SimpleDate date) throws RemoteException
  {

  }

  @Override public ArrayList<Screening> getAllScreenings()
      throws RemoteException
  {
    return null;
  }

  @Override public int getNbOfScreenings() throws RemoteException
  {
    return 0;
  }

  @Override public Screening getScreening(Screening screening)
      throws RemoteException
  {
    return null;
  }

  @Override public ArrayList<Ticket> getAllTickets() throws RemoteException
  {
    return null;
  }

  @Override public User getUser() throws RemoteException
  {
    return null;
  }

  @Override public Screening findScreeningBySeatId(String seatId)
      throws RemoteException
  {
    return null;
  }

  @Override public ArrayList<Screening> getScreaningsByMovieTitle(String title)
      throws RemoteException
  {
    return null;
  }

  @Override public ArrayList<Screening> getScreeningsByDate(LocalDate date)
      throws RemoteException
  {
    return null;
  }

  //Overrides for methods
  public static void main(String[] args)
  {
    RemoteModel server = new RmiServer();
  }

  @Override public boolean addListener(GeneralListener<String, String> listener,
      String... propertyNames) throws RemoteException
  {
    return false;
  }

  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
      throws RemoteException
  {
    return false;
  }
}
