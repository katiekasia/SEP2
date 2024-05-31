package mediator;

import model.*;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 *This class represents the RMIserver for the system
 * It implements the RemoteModel interface, providing remote access to the system's functionality
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class RmiServer implements RemoteModel
{
  private Model cinema;
  private PropertyChangeHandler<String, String> property;
  private Registry registry;
  /**
   * Constructor for RmiServer class.
   * Initializes the cinema model, property change handler, and starts the RMI server.
   */
  public RmiServer()
  {
    cinema = new ModelManager();
    property = new PropertyChangeHandler<>(this);
    try
    {
      startRegistry();
      start();
      String hostAddress = InetAddress.getLocalHost().getHostAddress();
      System.out.println(hostAddress);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  /**
   * Method to delete an account for the customer
   * @param username The username of the account to be deleted.
   * @throws RemoteException If an RMI-related communication error occurs.
   */
  public void deleteAccount(String username) throws RemoteException
  {
    cinema.deleteAccount(username);
  }
  /**
   * Adds a movie to the system.
   * @param movie The movie object to be added.
   * @throws RemoteException If an RMI-related communication error occurs.
   */
  @Override public void addMovie(Movie movie) throws RemoteException
  {
    cinema.addMovie(movie);
  }
  /**
   * Deletes a movie from the system.
   * @param movie The movie object to be deleted.
   * @throws RemoteException If an RMI-related communication error occurs.
   */
  @Override public void deleteMovie(Movie movie) throws RemoteException
  {
    cinema.deleteMovie(movie);
  }
  /**
   * Retrieves all movies from the system.
   * @return ArrayList of Movie objects representing all movies in the system.
   * @throws RemoteException If an RMI-related communication error occurs.
   */
  @Override public ArrayList<Movie> getAllMovies() throws RemoteException
  {
    return cinema.getAllMovies();
  }

  /**
   * Changes the prices of items in the system.
   */
  @Override public void changePrices(){
    cinema.changePrices();
  }
  /**
   * Changes the price of a specific item in the system.
   * @param item The name of the item whose price is to be changed.
   * @param newPrice The new price for the item.
   * @throws RemoteException If an RMI-related communication error occurs.
   */
  @Override public void changePrice(String item, double newPrice)
      throws RemoteException
  {
    cinema.changePrice(item, newPrice);
  }
  /**
   * Retrieves the price for a specific type of ticket.
   * @param type The type of ticket.
   * @return The price of the ticket.
   */
  @Override public double getPriceForTicket(String type){
   return cinema.getPriceForTicket(type);
  }
  /**
   * Stops the RMI server by unexporting the object and stopping the registry.
   */
  public void stop(){
   try
   {
     stopRegistry();
     UnicastRemoteObject.unexportObject(this,true);
   }catch (Exception e){
     e.printStackTrace();
   }
  }
  /**
   * Stops the RMI registry
   * @throws RemoteException If an RMI-related communication error occurs.
   */
  private void stopRegistry(){
    try {
      if (registry != null) {
        UnicastRemoteObject.unexportObject(registry, true);
        System.out.println("Registry stopped...");
      }
    } catch (Exception e) {
      System.out.println("Error while stopping registry: " + e.getMessage());
    }
  }
  /**
   * Starts the RMI registry on port 1099.
   * @throws RemoteException If an RMI-related communication error occurs.
   */
  private void startRegistry() throws RemoteException
  {
    try
    {
      registry = LocateRegistry.createRegistry(1099);

      System.out.println("Registry started...");

    }
    catch (ExportException e)
    {
      System.out.println("Registry already started? " + e.getMessage());
    }
  }
  /**
   * Starts the RMI server by exporting the RmiServer object and binding it to a name.
   * @throws RemoteException If an RMI-related communication error occurs.
   * @throws MalformedURLException If the provided URL is malformed.
   */
  private void start() throws RemoteException, MalformedURLException
  {

    UnicastRemoteObject.exportObject(this, 0);
    Naming.rebind("Case", this);
  }

  /**
   * Updates the information of a user in the system.
   *
   * @param user             The updated user object.
   * @param previousUsername The previous username of the user.
   * @throws RemoteException If an RMI-related exception occurs.
   */
  @Override
  public void updateUser(User user, String previousUsername) throws RemoteException {
    cinema.updateUser(user, previousUsername);
  }

  /**
   * Reserves seats for a screening for a customer.
   *
   * @param seats     An array of seats to be reserved.
   * @param customer  The user/customer reserving the seats.
   * @param screening The screening for which the seats are being reserved.
   * @param nbVIP     The number of VIP seats to be reserved.
   * @return The order containing the reserved seats.
   * @throws RemoteException If an RMI-related exception occurs.
   */
  @Override
  public Order reserveSeats(Seat[] seats, User customer, Screening screening, int nbVIP)
      throws RemoteException {
    // Notifies property listeners about the reservation process
    property.firePropertyChange("RESERVE SEATS", null, screening.getTime());
    return cinema.reserveSeats(seats, customer, screening, nbVIP);
  }

  @Override public double getPriceForSize(String snackType, String size){
    return cinema.getPriceForSize(snackType, size);
  }

  @Override public boolean logInAdmin(String username, String password)
      throws RemoteException
  {
    return cinema.logInAdmin(username, password);
  }

  @Override public void addSnackToOrder(String snackType, int amount, Order order, User user,String size){
    cinema.addSnackToOrder(snackType, amount, order, user, size);
  }

  /**
   * Retrieves an order by its ID associated with a specific user.
   *
   * @param orderID The ID of the order to retrieve.
   * @param user    The user associated with the order.
   * @return The order object matching the provided ID.
   * @throws RemoteException If an RMI-related exception occurs.
   */
  @Override
  public Order getOrderByID(int orderID, User user) throws RemoteException {
    return cinema.getOrderByID(orderID, user);
  }

  /**
   * Retrieves a screening for viewing based on the provided time, date, title, and room.
   *
   * @param time  The time of the screening.
   * @param date  The date of the screening.
   * @param title The title of the movie being screened.
   * @param room  The room number where the screening is taking place.
   * @return The screening object matching the provided parameters.
   * @throws RemoteException If an RMI-related exception occurs.
   */
  @Override
  public Screening getScreeningForView(String time, String date, String title, int room)
      throws RemoteException {
    return cinema.getScreeningForView(time, date, title, room);
  }

  /**
   * Retrieves all orders associated with a specific user.
   *
   * @param user The user for whom to retrieve orders.
   * @return An array of orders belonging to the specified user.
   * @throws RemoteException If an RMI-related exception occurs.
   */
  @Override public Order[] getAllOrders(User user) throws RemoteException
  {
    return cinema.getAllOrders(user);
  }

  @Override public Snack[] getSnacksFromOrder(Order order)
      throws RemoteException
  {
    return cinema.getSnacksFromOrder(order);
  }

  @Override public Ticket[] getTicketsFromOrder(Order order)
      throws RemoteException
  {
    return cinema.getTicketsFromOrder(order);
  }

  @Override public Movie getMovieForView(String title) throws RemoteException
  {
    return cinema.getMovieForView(title);
  }

  @Override public void changePrices(ArrayList<Double> newPrices)
      throws RemoteException
  {
    cinema.changePrices(newPrices);
  }

  @Override public void cancelOrder(Order order, User user) throws RemoteException
  {
    cinema.cancelOrder(order, user);
    property.firePropertyChange("REMOVE ORDER", null, user.getUsername());
  }

  @Override public boolean checkSeatAvailability(int index, Screening screening)
      throws RemoteException
  {
    return cinema.checkSeatAvailability(index, screening);
  }

  @Override public  void reserveSeat(Seat seat, User customer,
      Screening screening) throws RemoteException
  {
    cinema.reserveSeat(seat, customer, screening);
    property.firePropertyChange("RESERVE SEAT", null, screening.getTime());
  }

  @Override public Seat[] getAvailableSeats(Screening screening)
      throws RemoteException
  {
    return cinema.getAvailableSeats(screening);
  }
@Override public Room getRoomById(String id){
    return cinema.getRoomById(id);
}
  @Override public Seat[] getEmptySeats(Screening screening)
      throws RemoteException
  {
    return cinema.getEmptySeats(screening);
  }

  @Override public void updateSeatToBooked(Seat seat, Ticket ticket)
      throws RemoteException
  {
    cinema.updateSeatToBooked(seat, ticket);
    property.firePropertyChange("UPDATE SEAT TO BOOKED", null,
        ticket.toString());
  }

  @Override public  void addOrder(Order order, User user) throws RemoteException
  {
    cinema.addOrder(order, user);
    property.firePropertyChange("ADD ORDER", null, user.getUsername());
  }

  @Override public User logIn(String username, String password)
      throws RemoteException
  {
    return cinema.logIn(username, password);
  }

  @Override public Ticket getTicketForView(Order order, String ID)
      throws RemoteException
  {
    return cinema.getTicketForView(order, ID);
  }

  @Override public void register(User user) throws RemoteException
  {
    cinema.register(user);
  }

  @Override public void addScreening(Screening screening) throws RemoteException
  {
    cinema.addScreening(screening);
    property.firePropertyChange("ADD SCREENING", null, screening.getTime());
  }

  @Override public void removeScreening(Screening screening)
      throws RemoteException
  {
    cinema.removeScreening(screening);
    property.firePropertyChange("REMOVE SCREENING", null, screening.getTime());
  }

  @Override public void removeByDate(SimpleDate date) throws RemoteException
  {
    cinema.removeByDate(date);
    property.firePropertyChange("REMOVE BY DATE", null, date.toString());
  }

  @Override public ArrayList<Screening> getAllScreenings()
      throws RemoteException
  {
    return cinema.getAllScreenings();
  }

  @Override public int getNbOfScreenings() throws RemoteException
  {
    return cinema.getNbOfScreenings();
  }

  @Override public Screening getScreening(Screening screening)
      throws RemoteException
  {
    return cinema.getScreening(screening);
  }

  @Override public ArrayList<Ticket> getAllTickets(User user)
      throws RemoteException
  {
    return cinema.getAllTickets(user);
  }

  @Override public  void downgradeTicket(Ticket ticket, Order order, User user)
      throws RemoteException
  {
    cinema.downgradeTicket(ticket, order, user);
  }

  @Override public void upgradeTicket(Ticket ticket, Order order, User user)
      throws RemoteException
  {
     cinema.upgradeTicket(ticket, order, user);
  }

  @Override public  void cancelTicketFromOrder(Ticket ticket, Order order)
      throws RemoteException
  {
    cinema.cancelTicketFromOrder(ticket, order);
  }

  @Override public void deleteSnackFromOrder(Snack snack, Order order)
      throws RemoteException
  {
    cinema.deleteSnackFromOrder(snack, order);
  }

  @Override public ArrayList<Screening> getScreeningsByDateAndTitle(
      String title, LocalDate date) throws RemoteException
  {
    return cinema.getScreeningsByDateAndTitle(title, date);
  }

  @Override public ArrayList<Order> getOrdersForUser(String username)
      throws RemoteException
  {
    return cinema.getOrdersForUser(username);
  }

  @Override public ArrayList<Screening> getScreaningsByMovieTitle(String title)
      throws RemoteException
  {
    return cinema.getScreaningsByMovieTitle(title);
  }

  @Override public ArrayList<Screening> getScreeningsByDate(LocalDate date)
      throws RemoteException
  {
    return cinema.getScreeningsByDate(date);
  }

  @Override public User getUserByUsername(String username)
      throws RemoteException
  {
    return cinema.getUserByUsername(username);
  }

  @Override public boolean addListener(GeneralListener<String, String> listener,
      String... propertyNames) throws RemoteException
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
      throws RemoteException
  {
    return property.removeListener(listener, propertyNames);
  }
}
