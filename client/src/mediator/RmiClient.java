package mediator;

import model.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.RemoteListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 * The RmiClient class handles remote interactions with the server,
 * implementing remote method invocation functionality to access
 * the next entities: movies, screenings,tickets,orders and user accounts.
 * It acts as the client-side of an RMI communication setup.
 *
 * @version 3.0
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class RmiClient implements Model, RemoteListener<String,String>
{
  private RemoteModel server;
  private PropertyChangeSupport property;

  /**
   * one-argument constructor for RmiClient class
   *
   *   - This constructor initializes the RmiClient instance by performing the following tasks:
   *  - Initializes the PropertyChangeSupport object for managing property change events.
   *  - Looks up the remote server object using the RMI registry.
   *  - Exports the current object as a remote object for receiving remote method calls.
   *  - Adds the current object as a listener to the server for handling events from the server.
   *
   * @param host -a String representing the host address of the remote server.
   * @throws RuntimeException - if there is a server connection error.
   */
  public RmiClient(String host) {
    property = new PropertyChangeSupport(this);
    try {
      // The server object is found using the specified host address and the name "Case"
      server = (RemoteModel) Naming.lookup("rmi://" + host + ":1099/Case");

      // This allows the current object to receive remote method calls
      UnicastRemoteObject.exportObject(this, 0);

      // This enables the client to handle events from the server
      server.addListener(this);
    } catch (Exception e) {
      // Throw a RuntimeException if there is a server connection error/debugging purposes
      throw new RuntimeException("Server connection error. " + e.getMessage());
    }
  }

  /**
   * Retrieves a Room object from the server by its ID.
   * This method calls the getRoomById method on the remote server object to fetch the Room details.
   *
   * @param id a String representing the ID of the Room to be retrieved.
   * @return the Room object corresponding to the specified ID.
   * @throws RuntimeException if there is a server connection error.
   */
  @Override public Room getRoomById(String id){
    try
    {
     return server.getRoomById(id);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Stops the RMI client by unregistering it from the RMI runtime.
   * This method calls UnicastRemoteObject.unexportObject to unexport the client object,
   * stopping it from receiving remote method invocations.
   *
   * @throws RuntimeException if there is a server connection error during unexporting.
   */
  public void stop(){
    try
    {
      // Unregister the RMI client object from the RMI runtime
      // The 'true' parameter forces the object to be unexported even if there are pending calls
      UnicastRemoteObject.unexportObject(this,true);
    }catch (Exception e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Retrieves a list of all movies from the server.
   * This method makes a remote call to the server's getAllMovies method to fetch the movie list.
   *
   * @return an ArrayList of Movie objects representing all movies available on the server.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public ArrayList<Movie> getAllMovies()
  {
    try
    {
      return server.getAllMovies();
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Deletes a movie from the server.
   * This method makes a remote call to the server's deleteMovie method to remove the specified movie.
   *
   * @param movie the Movie object to be deleted from the server.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public void deleteMovie(Movie movie)
  {
    try
    {
      server.deleteMovie(movie);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Deletes a user account from the server.
   * This method makes a remote call to the server's deleteAccount method to remove the specified user account.
   *
   * @param username the username of the account to be deleted from the server.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public void deleteAccount(String username)
  {
    try
    {
      server.deleteAccount(username);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  /**
   * Changes the price of a specified item on the server.
   * This method makes a remote call to the server's changePrice method to update the price of the specified item.
   *
   * @param item -type String-the name of the item whose price is to be changed.
   * @param newPrice -type double-the new price to be set for the specified item.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public void changePrice(String item, double newPrice)
  {
    try
    {
      server.changePrice(item, newPrice);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Adds a new movie to the server.
   * This method makes a remote call to the server's addMovie method to add the specified movie.
   *
   * @param movie the Movie object to be added to the server.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public void addMovie(Movie movie)
  {
    try
    {
      server.addMovie(movie);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }


  /**
   * Triggers a change in prices on the server.
   * This method makes a remote call to the server's changePrices method to update the prices.
   *
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public void changePrices(){
    try
    {
      server.changePrices();
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());}
  }
  /**
   * Retrieves the price of a ticket for a given type from the server.
   * This method makes a remote call to the server's getPriceForTicket method, passing the ticket type as a parameter.
   *
   * @param type -type String-the type of the ticket whose price is to be retrieved.
   * @return the price of the ticket for the given type.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public double getPriceForTicket(String type)
  {
    try
    {
      return server.getPriceForTicket(type);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Updates the details of a user on the server.
   * This method makes a remote call to the server's updateUser method,
   * passing the User object with the new details and the previous username as parameters.
   *
   * @param user the User object containing the new user details.
   * @param previousUsername the previous username of the user that needs to be updated.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public void updateUser(User user, String previousUsername)
  {
    try
    {
      server.updateUser(user, previousUsername);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  /**
   * Cancels the order user selected, on the server.
   * This method makes a remote call to the server's cancelOrder method,
   * passing the Order and User objects as parameters.
   *
   * @param order the Order object represents the order to be canceled.
   * @param user the User object represents the user who owns the order.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public void cancelOrder(Order order, User user)
  {
    try
    {
      server.cancelOrder(order, user);
    }catch (Exception e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Retrieves ticket from an order by its ID from the server.
   * This method makes a remote call to the server's getTicketForView method, passing the Order object and the ticket ID as parameters.
   *
   * @param order the Order object representing the order containing the ticket.
   * @param ID the String representing the ID of the ticket to be retrieved.
   * @return the Ticket object representing the ticket retrieved from the order.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public Ticket getTicketForView(Order order, String ID)
  {
    try
    {
      return server.getTicketForView(order, ID);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Retrieves a specific order by its ID for a given user from the server.
   * This method makes a remote call to the server's getOrderByID method, passing the order ID and the User object as parameters.
   *
   * @param orderID the int representing the ID of the order to be retrieved.
   * @param user the User object representing the user associated with the order.
   * @return the Order object representing the order retrieved by its ID.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public Order getOrderByID(int orderID, User user)
  {
    try
    {
      return server.getOrderByID(orderID, user);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Retrieves a specific screening based on the provided time, date, title, and room number.
   * This method makes a remote call to the server's getScreeningForView method, passing the time, date, title, and room number as parameters.
   *
   * @param time the String representing the time of the screening.
   * @param date the String representing the date of the screening.
   * @param title the String representing the title of the movie.
   * @param room the int representing the room number where the screening is held.
   * @return the Screening object representing the screening that matches the provided parameters.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public Screening getScreeningForView(String time, String date,
      String title, int room)
  {
      try
      {
        // Pass the time, date, title, and room number as parameters
        return server.getScreeningForView(time, date, title, room);
      }catch (RemoteException e){
        throw new RuntimeException("Server connection error. "  + e.getMessage());
      }
  }

  /**
   * Retrieves all orders for the specified user.
   * This method makes a remote call to the server's getAllOrders method, passing the user as a parameter.
   *
   * @param user the User object representing the user whose orders are to be retrieved.
   * @return an array of Order objects representing all orders for the specified user.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public Order[] getAllOrders(User user)
  {
    try
    {
      return server.getAllOrders(user);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }

  }
  /**
   * Retrieves all snacks from the order.
   * This method makes a remote call to the server's getSnacksFromOrder method, passing the order as a parameter.
   *
   * @param order the Order object representing the order whose snacks are to be retrieved.
   * @return an array of Snack objects representing all snacks from the order order.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public Snack[] getSnacksFromOrder(Order order)

  {
   try
   {
     return server.getSnacksFromOrder(order);
   }catch (RemoteException e){
     throw new RuntimeException("Server connection error. "  + e.getMessage());
   }
  }
  /**
   * Retrieves all tickets associated with the specified order.
   * This method makes a remote call to the server's getTicketsFromOrder method, passing the order as a parameter.
   *
   * @param order the Order object representing the order whose tickets are to be retrieved.
   * @return an array of Ticket objects representing all tickets associated with the specified order.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public Ticket[] getTicketsFromOrder(Order order)

  {
    try
    {
      return server.getTicketsFromOrder(order);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Reserves seats for a customer in a specified screening.
   * This method makes a remote call to the server's reserveSeats method, passing the necessary details for the reservation.
   *
   * @param seats an array of Seat objects representing the seats to be reserved.
   * @param customer the User object representing the customer making the reservation.
   * @param screening the Screening object representing the screening for which seats are being reserved.
   * @param nbVIP the number of VIP seats to be reserved.
   * @return an Order object representing the reservation details.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public Order reserveSeats(Seat[] seats, User customer,
      Screening screening, int nbVIP)
  {
    try
    {
     return server.reserveSeats(seats, customer, screening,nbVIP);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Retrieves the price of a snack based on its type and size.
   * This method makes a remote call to the server to get the price of the specified snack.
   *
   * @param snackType the type of the snack
   * @param size the size of the snack
   * @return the price of the specified snack.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public double getPriceForSize(String snackType, String size)
  {
    try
    {
      return server.getPriceForSize(snackType, size);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Adds a specified number of snacks to an order.
   * This method makes a remote call to the server to add the snacks to the specified order.
   *
   * @param snackType the type of the snack
   * @param amount the number of snacks to add.
   * @param order the order to which the snacks should be added.
   * @param user the user who placed the order.
   * @param size the size of the snack
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public void addSnackToOrder(String snackType, int amount, Order order, User user,String size){
    try
    {
      server.addSnackToOrder(snackType, amount, order, user, size);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Checks the availability of a specific seat for a given screening.
   * This method makes a remote call to the server to check the seat's availability.
   *
   * @param index the index of the seat to check.
   * @param screening the screening for which to check the seat availability.
   * @return true if the seat is available, false otherwise.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public boolean checkSeatAvailability(int index, Screening screening)

  {
    try
    {
      return server.checkSeatAvailability(index, screening);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Authenticates an admin user by checking the provided username and password.
   * This method makes a remote call to the server to verify the admin credentials.
   *
   * @param username the username of the admin.
   * @param password the password of the admin.
   * @return true if the login is successful, false otherwise.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public boolean logInAdmin(String username, String password)
  {
    try
    {
      return server.logInAdmin(username, password);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Reserves a specific seat for a user for a particular screening.
   * This method makes a remote call to the server to perform the reservation.
   *
   * @param seat the seat to be reserved.
   * @param customer the user who is reserving the seat.
   * @param screening the screening for which the seat is being reserved.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public void reserveSeat(Seat seat, User customer,
      Screening screening)
  {
    try
    {
      server.reserveSeat(seat, customer, screening);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Retrieves all available seats for a specific screening.
   * This method makes a remote call to the server to fetch the available seats.
   *
   * @param screening the screening for which available seats are being retrieved.
   * @return an array of available seats for the specified screening.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public Seat[] getAvailableSeats(Screening screening)

  {
    try
    {
      return server.getAvailableSeats(screening);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Retrieves all empty seats for a specific screening.
   * This method makes a remote call to the server to fetch the empty seats.
   *
   * @param screening the screening for which empty seats are being retrieved.
   * @return an array of empty seats for the specified screening.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public Seat[] getEmptySeats(Screening screening)

  {
    try
    {
      return server.getEmptySeats(screening);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Retrieves a User object based on the provided username.
   * This method makes a remote call to the server to fetch the user details.
   *
   * @param username the username of the user to be retrieved.
   * @return the User object associated with the specified username.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public User getUserByUsername(String username)
  {
    try
    {
      return server.getUserByUsername(username);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  /**
   * Updates the status of a seat to "booked" based on the provided seat and ticket information.
   * This method makes a remote call to the server to update the seat status.
   *
   * @param seat the Seat object representing the seat to be updated.
   * @param ticket the Ticket object associated with the booking.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public void updateSeatToBooked(Seat seat, Ticket ticket)

  {
    try
    {
      server.updateSeatToBooked(seat, ticket);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Adds a new order to the server.
   * This method makes a remote call to the server to add the order.
   *
   * @param order the Order object representing the order to be added.
   * @param user the User object representing the user placing the order.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public void addOrder(Order order, User user)
  {
    try
    {
      server.addOrder(order, user);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  /**
   * Authenticates a user by verifying their username and password.
   * This method makes a remote call to the server to perform the authentication.
   *
   * @param username the username of the user attempting to log in.
   * @param password the password of the user attempting to log in.
   * @return the User object representing the authenticated user.
   * @throws RuntimeException if there is a server connection error during the remote call.
   */
  @Override public User logIn(String username, String password)
  {
    try
    {
      return server.logIn(username, password);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Registers a user with the server.
   *
   * @param user The user object to be registered.
   * @throws RuntimeException If there is an error in connecting to the server.
   */
  @Override public void register(User user)
  {
    try
    {
      server.register(user);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Adds a screening to the server.
   *
   * @param screening The screening object to be added.
   * @throws RuntimeException If there is an error in connecting to the server.
   */
  @Override public void addScreening(Screening screening)
  {
    try
    {
      server.addScreening(screening);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Removes a screening from the server.
   *
   * @param screening The screening object to be removed.
   * @throws RuntimeException If there is an error in connecting to the server.
   */
  @Override public void removeScreening(Screening screening)

  {
    try
    {
      server.removeScreening(screening);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Removes screenings scheduled for a specific date from the server.
   *
   * @param date The date for which screenings are to be removed.
   * @throws RuntimeException If there is an error in connecting to the server.
   */
  @Override public void removeByDate(SimpleDate date)
  {
    try
    {
      server.removeByDate(date);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Retrieves all screenings from the server.
   *
   * @return An ArrayList containing all screenings retrieved from the server.
   * @throws RuntimeException If there is an error in connecting to the server.
   */
  @Override public ArrayList<Screening> getAllScreenings()
  {
    try
    {
      return server.getAllScreenings();
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Retrieves the total number of screenings from the server.
   *
   * @return The total number of screenings stored on the server.
   * @throws RuntimeException If there is an error in connecting to the server.
   */
  @Override public int getNbOfScreenings()
  {
    try
    {
      return server.getNbOfScreenings();
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Retrieves the details of a specific screening from the server.
   *
   * @param screening The screening object representing the screening to retrieve details for.
   * @return The screening object containing the details retrieved from the server.
   * @throws RuntimeException If there is an error in connecting to the server.
   */
  @Override public Screening getScreening(Screening screening)

  {
    try
    {
      return server.getScreening(screening);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Retrieves all tickets associated with a specific user from the server.
   *
   * @param user The user for whom tickets are to be retrieved.
   * @return An ArrayList containing all tickets associated with the specified user.
   * @throws RuntimeException If there is an error in connecting to the server.
   */
  @Override public ArrayList<Ticket> getAllTickets(User user)
  {
    try
    {
      return server.getAllTickets(user);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Downgrades a ticket associated with a specific order and user on the server.
   *
   * @param ticket The ticket to be downgraded.
   * @param order The order containing the ticket to be downgraded.
   * @param user The user associated with the ticket and order.
   * @throws RuntimeException If there is an error in connecting to the server.
   */
  @Override public void downgradeTicket(Ticket ticket, Order order, User user)
  {
    try
    {
      server.downgradeTicket(ticket, order, user);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Upgrades a ticket associated with a specific order and user on the server.
   *
   * @param ticket The ticket to be upgraded.
   * @param order The order containing the ticket to be upgraded.
   * @param user The user associated with the ticket and order.
   * @throws RuntimeException If there is an error in connecting to the server.
   */
  @Override public void upgradeTicket(Ticket ticket, Order order, User user)
  {
    try
    {
      server.upgradeTicket(ticket, order, user);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Cancels a ticket from a specific order on the server.
   *
   * @param ticket The ticket to be canceled.
   * @param order The order from which the ticket is to be canceled.
   * @throws RuntimeException If there is an error in connecting to the server.
   */
  @Override public void cancelTicketFromOrder(Ticket ticket, Order order, User user)
  {
    try
    {
      server.cancelTicketFromOrder(ticket, order, user);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Deletes a snack from a specific order on the server.
   *
   * @param snack The snack to be deleted.
   * @param order The order from which the snack is to be deleted.
   * @throws RuntimeException If there is an error in connecting to the server.
   */
  @Override public void deleteSnackFromOrder(Snack snack, Order order)
  {
    try
    {
      server.deleteSnackFromOrder(snack, order);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }

  /**
   * Retrieves screenings for a specific movie title from the server.
   *
   * @param title The title of the movie for which screenings are to be retrieved.
   * @return An ArrayList containing all screenings associated with the specified movie title.
   * @throws RuntimeException If there is an error in connecting to the server.
   */

  @Override public ArrayList<Screening> getScreaningsByMovieTitle(String title)

  {
    try
    {
      return server.getScreaningsByMovieTitle(title);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Retrieves screenings for a specific date from the server.
   *
   * @param date The date for which screenings are to be retrieved.
   * @return An ArrayList containing all screenings scheduled for the specified date.
   * @throws RuntimeException If there is an error in connecting to the server.
   */
  @Override public ArrayList<Screening> getScreeningsByDate(LocalDate date)

  {
    try
    {
      return server.getScreeningsByDate(date);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Retrieves screenings for a specific movie title and date from the server.
   *
   * @param title The title of the movie for which screenings are to be retrieved.
   * @param date The date for which screenings are to be retrieved.
   * @return An ArrayList containing all screenings scheduled for the specified movie title and date.
   * @throws RuntimeException If there is an error in connecting to the server.
   */
  @Override public ArrayList<Screening> getScreeningsByDateAndTitle(
      String title, LocalDate date)
  {
    try
    {
      return server.getScreeningsByDateAndTitle(title, date);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Retrieves orders associated with a specific user from the server.
   *
   * @param username The username of the user for whom orders are to be retrieved.
   * @return An ArrayList containing all orders associated with the specified user.
   * @throws RuntimeException If there is an error in connecting to the server.
   */
  @Override public ArrayList<Order> getOrdersForUser(String username)
  {
    try
    {
      return server.getOrdersForUser(username);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Retrieves movie details for viewing based on the movie title from the server.
   *
   * @param title The title of the movie for which details are to be retrieved.
   * @return The Movie object containing details of the movie.
   * @throws RuntimeException If there is an error in connecting to the server.
   */
  @Override public Movie getMovieForView(String title)
  {
    try
    {
      return server.getMovieForView(title);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Changes the prices of items on the server.
   *
   * @param newPrices An ArrayList containing the new prices to be set for items.
   * @throws RuntimeException If there is an error in connecting to the server.
   */
  @Override public void changePrices(ArrayList<Double> newPrices)
  {
    try
    {
      server.changePrices(newPrices);
    }catch (RemoteException e){
      throw new RuntimeException("Server connection error. "  + e.getMessage());
    }
  }
  /**
   * Adds a PropertyChangeListener to listen for property change events.
   *
   * @param listener The PropertyChangeListener to be added.
   */
  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }
  /**
   * Removes a PropertyChangeListener from listening for property change events.
   *
   * @param listener The PropertyChangeListener to be removed.
   */
  @Override public void removeListener(PropertyChangeListener listener)
  {
property.removePropertyChangeListener(listener);
  }
  /**
   * Handles property change events received from the server and fires corresponding property change events.
   *
   * @param event The ObserverEvent representing the property change event.
   * @throws RemoteException If there is an error in the remote communication.
   */
  @Override public void propertyChange(ObserverEvent<String, String> event)
      throws RemoteException
  {
    // Switch statement to handle different types of property change events
    switch (event.getPropertyName()){
      case "RESERVE SEATS":
        // Fire a property change event indicating seats reservation
        property.firePropertyChange("reserve seats",null, event.getValue2());
        break;
      case "RESERVE SEAT":
        property.firePropertyChange("reserve seat", null, event.getValue2());
        break;
      case "ADD ORDER":
        property.firePropertyChange("add order", null, event.getValue2());
        break;
      case "ADD SCREENING":
        property.firePropertyChange("add screening",null,event.getValue2());
        break;
      case "REMOVE SCREENING":
        property.firePropertyChange("remove screening", null, event.getValue2());
        break;
      case "REMOVE ORDER":
        property.firePropertyChange("remove order", null,event.getValue2());
        break;

    }
  }
}
