package mediator;

import model.*;
import utility.observer.subject.RemoteSubject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface RemoteModel extends Remote, RemoteSubject<String ,String >
{
  void deleteAccount(String username) throws RemoteException;
   void updateUser(User user, String previousUsername) throws RemoteException;
   Order reserveSeats(Seat[] seats, User customer,
      Screening screening, int nbVIP ) throws RemoteException;
  Order getOrderByID(int orderID, User user) throws RemoteException;
  double getPriceForSize(String snackType, String size) throws RemoteException;
  boolean logInAdmin(String username, String password) throws RemoteException;
  void addSnackToOrder(String snackType, int amount, Order order, User user,String size) throws RemoteException;
  Screening getScreeningForView(String time, String date,String title, int room) throws  RemoteException;
   User logIn(String username, String password) throws RemoteException;
   Ticket getTicketForView(Order order, String ID) throws RemoteException;
  Order[] getAllOrders(User user) throws RemoteException;
  Snack[] getSnacksFromOrder(Order order) throws RemoteException;
  Ticket[] getTicketsFromOrder(Order order) throws RemoteException;
  Movie getMovieForView(String title) throws RemoteException;
  void changePrices(ArrayList<Double> newPrices) throws RemoteException;
   void cancelOrder(Order order, User user) throws RemoteException;

   boolean checkSeatAvailability(int index, Screening screening) throws RemoteException;

   void reserveSeat(Seat seat, User customer ,
      Screening screening) throws RemoteException;
   Seat[] getAvailableSeats(Screening screening) throws RemoteException;
   Seat[] getEmptySeats(Screening screening) throws RemoteException;

  //public double calculateTotalPrice() throws RemoteException;
   void updateSeatToBooked(Seat seat, Ticket ticket) throws RemoteException;

   void addOrder(Order order, User user) throws RemoteException;
  void register(String username, String password, String email, String firstName, String lastName, String phone) throws RemoteException;
  void addScreening(Screening screening) throws RemoteException;
  void removeScreening(Screening screening) throws RemoteException;
  void removeByDate(SimpleDate date) throws RemoteException;
  ArrayList<Screening> getAllScreenings() throws RemoteException;
  int getNbOfScreenings() throws RemoteException;
  Screening getScreening(Screening screening) throws RemoteException;
   ArrayList<Ticket> getAllTickets(User user) throws RemoteException;
  void downgradeTicket(Ticket ticket, Order order, User user) throws RemoteException;
   void upgradeTicket(Ticket ticket, Order order, User user) throws RemoteException;
  void cancelTicketFromOrder(Ticket ticket, Order order) throws RemoteException;
  void deleteSnackFromOrder(Snack snack, Order order) throws RemoteException;
  ArrayList<Screening> getScreeningsByDateAndTitle(
      String title, LocalDate date) throws RemoteException;
  ArrayList<Order> getOrdersForUser(String username) throws RemoteException;

  ArrayList<Screening> getScreaningsByMovieTitle(String title) throws RemoteException;
  ArrayList<Screening> getScreeningsByDate(LocalDate date) throws RemoteException;
  User getUserByUsername(String username) throws RemoteException;
}
