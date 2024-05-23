package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  public void updateUser(User user, String previousUsername);
  void cancelOrder(Order order);
  Ticket getTicketForView(Order order, String ID);
  Order getOrderByID(int orderID, User user);

  Screening getScreeningForView(String time, String date,String title, int room);
   User logIn(String username, String password);
   void connect();
   void disconnect();
   int getPort();
   void setPort(int port);
  Order[] getAllOrders(User user);
  Snack[] getSnacksFromOrder(Order order);
  Ticket[] getTicketsFromOrder(Order order);
   String getHost();
   Order reserveSeats(Seat[] seats, User customer,
       Screening screening, int nbVIP);
   double getPriceForSize(String snackType, String size);
  void addSnackToOrder(String snackType, int amount, Order order, User user,String size);
   boolean checkSeatAvailability(int index, Screening screening);
  boolean logInAdmin(String username, String password);

   void reserveSeat(Seat seat, User customer,
      Screening screening);
   Seat[] getAvailableSeats(Screening screening);
   Seat[] getEmptySeats(Screening screening);

User getUserByUsername(String username);
   void updateSeatToBooked(Seat seat, Ticket ticket);
   void addOrder(Order order, User user);
void addScreening(Screening screening);
void removeScreening(Screening screening);
void removeByDate(SimpleDate date);
ArrayList<Screening> getAllScreenings();
int getNbOfScreenings();
Screening getScreening(Screening screening);
   ArrayList<Ticket> getAllTickets(User user);
  void downgradeTicket(Ticket ticket, Order order);
   void upgradeTicket(Ticket ticket, Order order);
   void cancelTicketFromOrder(Ticket ticket, Order order);
   void deleteSnackFromOrder(Snack snack, Order order);
  ArrayList<Screening> getScreaningsByMovieTitle(String title);
  ArrayList<Screening> getScreeningsByDate(LocalDate date);
  ArrayList<Screening> getScreeningsByDateAndTitle(String title, LocalDate date);
   ArrayList<Order> getOrdersForUser(String username);
  Movie getMovieForView(String title);
  void changePrices(ArrayList<Double> newPrices);
  void register(String username, String password, String email, String firstName, String lastName, String phone);




/*
 public Ticket getTicketBySeat(Seat seat);
  public Seat getSeatByScreening(Screening screening);

 */
}
