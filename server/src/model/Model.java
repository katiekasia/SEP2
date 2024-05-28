package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  void deleteMovie(Movie movie) throws  RemoteException;
  ArrayList<Movie> getAllMovies() throws RemoteException;
  public void updateUser(User user, String previousUsername);
  void changePrice(String item, double newPrice);
  void changePrices();
   void cancelOrder(Order order, User user);
  Order getOrderByID(int orderID, User user);

  Screening getScreeningForView(String time, String date,String title, int room);
   User logIn(String username, String password);
   double getPriceForSize(String snackType, String size);
  Order[] getAllOrders(User user);
   Ticket getTicketForView(Order order, String ID);
  Snack[] getSnacksFromOrder(Order order);
  Ticket[] getTicketsFromOrder(Order order);
   String getHost();
   Order reserveSeats(Seat[] seats, User customer,
       Screening screening, int nbVIP);
   boolean checkSeatAvailability(int index, Screening screening);

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
  void downgradeTicket(Ticket ticket, Order order, User user);
  void upgradeTicket(Ticket ticket, Order order, User user);
   void cancelTicketFromOrder(Ticket ticket, Order order);
   void deleteSnackFromOrder(Snack snack, Order order);
  ArrayList<Screening> getScreaningsByMovieTitle(String title);
  ArrayList<Screening> getScreeningsByDate(LocalDate date);
  ArrayList<Screening> getScreeningsByDateAndTitle(String title, LocalDate date);
   ArrayList<Order> getOrdersForUser(String username);
  boolean logInAdmin(String username, String password);
  Movie getMovieForView(String title);
  void addSnackToOrder(String snackType, int amount, Order order, User user,String size);
  void changePrices(ArrayList<Double> newPrices);

  double getPriceForTicket(String type);
  void register(String username, String password, String email, String firstName, String lastName, String phone);
void deleteAccount(String username);
void addMovie(Movie movie);
}
