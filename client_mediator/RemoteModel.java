package client_mediator;

import server_model.Screening;
import server_model.Seat;
import server_model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteModel extends Remote
{
  public void reserveSeats(Seat[] seats, User customer,
       Screening screening ) throws RemoteException;
  public boolean checkSeatAvailability(int index, Screening screening) throws RemoteException;

  public void reserveSeat(Seat seat, User customer,
      Screening screening) throws RemoteException;
  public Seat[] getAvailableSeats(Screening screening) throws RemoteException;
  public Seat[] getEmptySeats(Screening screening) throws RemoteException;
}
