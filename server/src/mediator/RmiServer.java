package mediator;


import server_model.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class RmiServer implements RemoteModel
{
private Model cinema;
public RmiServer() throws SQLException
{
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
  //Overrides for methods
  public static void main(String[] args) throws SQLException
  {
    RemoteModel server = new RmiServer();
  }
}
