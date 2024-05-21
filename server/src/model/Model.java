package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  public void updateUser(User user);
  void cancelOrder(Order order);
  Order getOrderByID(int orderID, User user);

  Screening getScreeningForView(String time, String date,String title, int room);
  public User logIn(String username, String password);
  public void connect();
  public void disconnect();
  public int getPort();
  public void setPort(int port);
  Order[] getAllOrders(User user);
  Snack[] getSnacksFromOrder(Order order);
  Ticket[] getTicketsFromOrder(Order order);
  public String getHost();
  public void reserveSeats(Seat[] seats, User customer,
       Screening screening, int nbVIP);
  public boolean checkSeatAvailability(int index, Screening screening);

  public void reserveSeat(Seat seat, User customer,
      Screening screening);
  public Seat[] getAvailableSeats(Screening screening);
  public Seat[] getEmptySeats(Screening screening);

User getUserByUsername(String username);
  public void updateSeatToBooked(Seat seat, Ticket ticket);

<<<<<<< Updated upstream
  public void addOrder(Order order, User user);
  void register(String username, String password, String email, String firstName, String lastName, String phone);
void addScreening(Screening screening);
void removeScreening(Screening screening);
void removeByDate(SimpleDate date);
ArrayList<Screening> getAllScreenings();
int getNbOfScreenings();
Screening getScreening(Screening screening);
  public ArrayList<Ticket> getAllTickets(User user);
  void downgradeTicket(Ticket ticket, Order order);
   void upgradeTicket(Ticket ticket, Order order);
   void cancelTicketFromOrder(Ticket ticket, Order order);
   void deleteSnackFromOrder(Snack snack, Order order);
  ArrayList<Screening> getScreaningsByMovieTitle(String title);
  ArrayList<Screening> getScreeningsByDate(LocalDate date);
  ArrayList<Screening> getScreeningsByDateAndTitle(String title, LocalDate date);

=======
  public void addOrder(Order order) throws RemoteException;
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
>>>>>>> Stashed changes
  public ArrayList<Order> getOrdersForUser(String username);
  void register(String username, String password, String email, String firstName, String lastName, String phone) throws RemoteException;




/*
 public Ticket getTicketBySeat(Seat seat);
  public Seat getSeatByScreening(Screening screening);

 */
}
