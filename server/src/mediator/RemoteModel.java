package mediator;

import model.*;
import utility.observer.subject.RemoteSubject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface RemoteModel extends Remote, RemoteSubject<String ,String >
{
  public void updateUser(User user, String previousUsername) throws RemoteException;
  public void reserveSeats(Seat[] seats, User customer,
      Screening screening, int nbVIP ) throws RemoteException;
  Screening getScreeningForView(String time, String date,String title, int room) throws  RemoteException;
  public User logIn(String username, String password) throws RemoteException;
  Order[] getAllOrders(User user) throws RemoteException;
  Snack[] getSnacksFromOrder(Order order) throws RemoteException;
  Ticket[] getTicketsFromOrder(Order order) throws RemoteException;
  public void cancelOrder(Order order) throws RemoteException;
  public boolean checkSeatAvailability(int index, Screening screening) throws RemoteException;

  public void reserveSeat(Seat seat, User customer ,
      Screening screening) throws RemoteException;
  public Seat[] getAvailableSeats(Screening screening) throws RemoteException;
  public Seat[] getEmptySeats(Screening screening) throws RemoteException;

  //public double calculateTotalPrice() throws RemoteException;
  public void updateSeatToBooked(Seat seat, Ticket ticket) throws RemoteException;

  public void addOrder(Order order,User user) throws RemoteException;
  void register(String username, String password, String email, String firstName, String lastName, String phone) throws RemoteException;
  void addScreening(Screening screening) throws RemoteException;
  void removeScreening(Screening screening) throws RemoteException;
  void removeByDate(SimpleDate date) throws RemoteException;
  ArrayList<Screening> getAllScreenings() throws RemoteException;
  int getNbOfScreenings() throws RemoteException;
  Screening getScreening(Screening screening) throws RemoteException;
  public ArrayList<Ticket> getAllTickets(User user) throws RemoteException;

  ArrayList<Screening> getScreaningsByMovieTitle(String title) throws RemoteException;
  ArrayList<Screening> getScreeningsByDate(LocalDate date) throws RemoteException;
}
