package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  public void updateUser(User user) throws RemoteException;
  void cancelOrder(Order order);
  public User logIn(String username, String password) throws RemoteException;
  public void connect();
  public void disconnect();
  public int getPort();
  public void setPort(int port);
  Order[] getAllOrders(User user) throws RemoteException;
  Snack[] getSnacksFromOrder(Order order) throws RemoteException;
  Ticket[] getTicketsFromOrder(Order order) throws RemoteException;
  public String getUsername();
  public String getHost();
  public void reserveSeats(Seat[] seats, User customer,
       Screening screening ) throws RemoteException;
  public boolean checkSeatAvailability(int index, Screening screening) throws RemoteException;

  public void reserveSeat(Seat seat, User customer,
      Screening screening) throws RemoteException;
  public Seat[] getAvailableSeats(Screening screening) throws RemoteException;
  public Seat[] getEmptySeats(Screening screening) throws RemoteException;

  public double calculateTotalPrice();
  public void updateSeatToBooked(Seat seat, Ticket ticket) throws RemoteException;

  public void addOrder(Order order) throws RemoteException;
  void register(String username, String password, String email, String firstName, String lastName, String phone) throws RemoteException;
void addScreening(Screening screening) throws RemoteException;
void removeScreening(Screening screening) throws RemoteException;
void removeByDate(SimpleDate date) throws RemoteException;
ArrayList<Screening> getAllScreenings() throws RemoteException;
int getNbOfScreenings() throws RemoteException;
Screening getScreening(Screening screening) throws RemoteException;
  public ArrayList<Ticket> getAllTickets() throws RemoteException;
  public User getUser() throws RemoteException;
  Screening findScreeningBySeatId(String seatId) throws RemoteException;
  ArrayList<Screening> getScreaningsByMovieTitle(String title) throws RemoteException;
  ArrayList<Screening> getScreeningsByDate(LocalDate date) throws RemoteException;

/*
 public Ticket getTicketBySeat(Seat seat);
  public Seat getSeatByScreening(Screening screening);

 */
}
