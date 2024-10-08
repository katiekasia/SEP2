package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 * interface for ModelManager
 *
 * @version 3.0
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public interface Model extends UnnamedPropertyChangeSubject
{
  void deleteAccount(String username);
  void deleteMovie (Movie movie);
  Room getRoomById(String id);
  ArrayList<Movie> getAllMovies();
  void changePrice(String item, double newPrice);
  void addMovie(Movie movie);
  void changePrices();
  double getPriceForTicket(String type);
  void removeScreening(Screening screening);
  public void updateUser(User user, String previousUsername);

  Ticket getTicketForView(Order order, String ID);
  Order getOrderByID(int orderID, User user);
  Screening getScreeningForView(String time, String date,String title, int room);
   User logIn(String username, String password);
  Order[] getAllOrders(User user);
  Snack[] getSnacksFromOrder(Order order);
  Ticket[] getTicketsFromOrder(Order order);
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
void removeByDate(SimpleDate date);
ArrayList<Screening> getAllScreenings();
int getNbOfScreenings();
Screening getScreening(Screening screening);
ArrayList<Ticket> getAllTickets(User user);
void downgradeTicket(Ticket ticket, Order order, User user);
void upgradeTicket(Ticket ticket, Order order, User user);
void cancelTicketFromOrder(Ticket ticket, Order order, User user);
void deleteSnackFromOrder(Snack snack, Order order);
ArrayList<Screening> getScreaningsByMovieTitle(String title);
ArrayList<Screening> getScreeningsByDate(LocalDate date);
ArrayList<Screening> getScreeningsByDateAndTitle(String title, LocalDate date);
ArrayList<Order> getOrdersForUser(String username);
Movie getMovieForView(String title);
void changePrices(ArrayList<Double> newPrices);
void cancelOrder(Order order, User user);
void register(User user);

}
