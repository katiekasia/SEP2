package client_mediator;



import client_model.*;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class RmiClient implements RemoteModel
{
  private RemoteModel server;

  public RmiClient(String host)
  {
    try
    {
      //UnicastRemoteObject.exportObject(this,0); for listener
      server = (RemoteModel) Naming.lookup("rmi://" + host + ":1099/Case");
      //server.addListener(this); for listener
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
public void start() throws RemoteException{
  Scanner input = new Scanner(System.in);
while (true){
//
}
}

  @Override public void reserveSeats(Seat[] seats, User customer,
      Screening screening) throws RemoteException
  {
    server.reserveSeats(seats,customer,screening);
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

  @Override public void updateSeatToBooked(Seat seat, Ticket ticket)
      throws RemoteException
  {
    server.updateSeatToBooked(seat,ticket);
  }

  @Override public void addOrder(Order order) throws RemoteException
  {
    server.addOrder(order);
  }
}
