package model;

import mediator.RmiClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *   ModelManager class acts as a central controller in the MVVM architecture,
 *   managing all interactions  via RMI and notifying views of any changes
 *   in model state using property change support.
 *  This class holds the logic of the system
 *
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


public class ModelManager implements Model, PropertyChangeListener
{
  private User user;
  private PropertyChangeSupport propertyChangeSupport;
  private RmiClient client;
  /**
   * one argument constructor
   *
   *  This constructor initializes the client
   *
   * It sets up the RMI client for communication with the backend
   * and registers the model manager as a listener
   * to handle events (like updates or changes) received from the server.
   *
   * @param client The RMI client used to interact with the remote server.
   */
  public ModelManager(RmiClient client)
  {
    this.user = null;
    this.propertyChangeSupport = new PropertyChangeSupport(this);
    this.client = client;
    client.addListener(this);
  }
  /**
   * Registers a new listener for property changes.
   * This method allows other parts of the application to be notified when changes occur in the model's state.
   * For example, when a new movie is added or a user logs in, registered listeners can update their views or perform other related actions.
   *
   * @param listener The property change listener to be added. This listener should implement the PropertyChangeListener interface.
   */
  @Override public void addListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }
  /**
   * Unregisters a previously added listener for property changes.
   * This method allows removing a listener that was previously registered to receive notifications about changes in the model's state.
   *
   * @param listener The property change listener to be removed. This should be the same instance that was previously registered using addListener.
   */
  @Override public void removeListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }
  /**
   * Removes a screening from the system.
   * This method delegates the removal operation to the RmiClient, which communicates with the server to remove the specified screening.
   *
   * @param screening The screening to be removed from the system.
   */
  @Override public void removeScreening(Screening screening)
  {
    client.removeScreening(screening);
  }
  /**
   * Deletes a movie from the system.
   * This method delegates the deletion operation to the RmiClient, which communicates with the server to remove the specified movie.
   *
   * @param movie The movie to be deleted from the system.
   * @throws IllegalArgumentException If the provided movie is invalid or null.
   * @throws IllegalStateException    If the system is in an illegal state to perform the deletion operation.
   * @throws RuntimeException         If an unexpected error occurs during the deletion process.
   */
  @Override public void deleteMovie(Movie movie)
  {
    try
    {
      client.deleteMovie(movie);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Retrieves all movies from the system.
   * This method delegates the retrieval operation to the RmiClient, which communicates with the server to fetch all available movies.
   *
   * @return An ArrayList containing all the movies in the system.
   * @throws IllegalArgumentException If there is an invalid argument passed to the method.
   * @throws IllegalStateException    If the system is in an illegal state to perform the retrieval operation.
   * @throws RuntimeException         If an unexpected error occurs during the retrieval process.
   */
  @Override public ArrayList<Movie> getAllMovies()
  {
    try
    {
      return client.getAllMovies();
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }
  }
  /**
   * Deletes a user account from the cinema booking system.
   * This method delegates the deletion operation to the RmiClient, which communicates with the serverto remove the specified user account.
   *
   * @param username The username of the user account to be deleted.
   * @throws IllegalArgumentException If the provided username is invalid or null.
   * @throws IllegalStateException    If the system is in an illegal state to perform the deletion operation.
   * @throws RuntimeException         If an unexpected error occurs during the deletion process.
   */
  @Override public void deleteAccount(String username)
  {

    try
    {
      client.deleteAccount(username);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }
  }
  /**
   * Changes the price of a specific item within the  system.
   *
   * @param item     The item whose price is to be changed.
   * @param newPrice The new price to set for the specified item.
   * @throws IllegalArgumentException If the provided item or new price is invalid or null.
   * @throws IllegalStateException    If the system is in an illegal state to perform the price change operation.
   * @throws RuntimeException         If an unexpected error occurs during the price change process.
   */
  @Override public void changePrice(String item, double newPrice)
  {
    try
    {
      client.changePrice(item, newPrice);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Adds a new movie to the cinema booking system.
   *
   * @param movie The movie object to be added.
   * @throws IllegalArgumentException If the provided movie is invalid or null.
   * @throws IllegalStateException    If the system is in an illegal state to perform the movie addition operation.
   * @throws RuntimeException         If an unexpected error occurs during the movie addition process.
   */
  @Override public void addMovie(Movie movie)
  {
    try
    {
      client.addMovie(movie);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      // If an unexpected runtime exception occurs, notify listeners about the fatal error and rethrow it.
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }
  }
  /**
   * Changes all the prices  in the system.
   *
   * It delegates the operation to the RmiClient to perform the price update.
   *
   * @throws IllegalArgumentException If the price change operation encounters invalid arguments.
   * @throws IllegalStateException    If the system is in an illegal state to perform the price change operation.
   * @throws RuntimeException         If an unexpected error occurs during the price change process.
   */
  @Override public void changePrices()
  {
    try
    {
      client.changePrices();
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Retrieves the price for a specific type of ticket
   *
   * This method communicates with the server through the RmiClient to fetch the price
   * associated with the given ticket type.
   *
   * @param type The type of ticket for which the price is requested.
   * @return The price of the ticket.
   * @throws IllegalArgumentException If the provided ticket type is invalid.
   * @throws IllegalStateException    If the system is in an illegal state to fetch the ticket price.
   * @throws RuntimeException         If an unexpected error occurs while retrieving the ticket price.
   */
  @Override public double getPriceForTicket(String type)
  {
    try
    {
      return client.getPriceForTicket(type);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Cancels an order made by a user.
   *
   * This method communicates with theserver through the RmiClient to cancel the specified order
   * made by the given user.
   *
   * @param order The order to be canceled.
   * @param user  The user who placed the order.
   * @throws IllegalArgumentException If the provided order or user is invalid.
   * @throws IllegalStateException    If the system is in an illegal state to process the cancellation.
   * @throws RuntimeException         If an unexpected error occurs while canceling the order.
   */
  @Override public void cancelOrder(Order order, User user)
  {
    try
    {
      client.cancelOrder(order, user);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * gets order by id for the specified user.
   *
   * This method communicates with t server through the RmiClient to retrieve the order
   * identified by the given order ID for the specified user.
   *
   * @param orderID The unique identifier of the order to be retrieved.
   * @param user    The user for whom the order is being retrieved.
   * @return The order corresponding to the provided order ID for the specified user.
   * @throws IllegalArgumentException If the provided order ID or user is invalid.
   * @throws IllegalStateException    If the system is in an illegal state to process the retrieval.
   * @throws RuntimeException         If an unexpected error occurs while retrieving the order.
   */
  @Override public Order getOrderByID(int orderID, User user)
  {
    try
    {
      return client.getOrderByID(orderID, user);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Retrieves a screening for display based on the provided time, date, movie title, and room number.
   *
   * This method communicates with the server through the RmiClient to retrieve the screening
   * identified by the given parameters for display purposes.
   *
   * @param time  The time of the screening.
   * @param date  The date of the screening.
   * @param title The title of the movie for which the screening is being retrieved.
   * @param room  The room number in which the screening takes place.
   * @return The screening information for display.
   * @throws IllegalArgumentException If any of the provided parameters are invalid.
   * @throws IllegalStateException    If the system is in an illegal state to process the retrieval.
   * @throws RuntimeException         If an unexpected error occurs while retrieving the screening.
   */
  @Override public Screening getScreeningForView(String time, String date,
      String title, int room)
  {
    try
    {
      return client.getScreeningForView(time, date, title, room);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * logs in a user by their username and password.
   *
   * This method communicates with the server through the RmiClient to validate the user's
   * credentials. If the authentication is successful, the user is logged in, and their information
   * is stored in the local 'user' field.
   *
   * @param username The username of the user trying to log in.
   * @param password The password of the user trying to log in.
   * @return The User object representing the logged-in user.
   * @throws IllegalArgumentException If the provided username or password is invalid.
   * @throws IllegalStateException    If the system is in an illegal state to process the authentication.
   * @throws RuntimeException         If an unexpected error occurs during the authentication process.
   */
  @Override public User logIn(String username, String password)
  {
    try
    {
      User loggedInUser = client.logIn(username, password);
      this.user = loggedInUser;
      return loggedInUser;
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
     throw e;
    }
  }
  /**
   * Updates user information in the system.
   *
   * This method communicates with the backend server through the RmiClient to update the user's
   * details, such as their username or password. It takes the new User object representing the updated
   * information and the previous username of the user. It handles any potential errors that may occur
   * during the update process and notifies listeners about fatal errors.
   *
   * @param user             The updated User object with the new information.
   * @param previousUsername The previous username of the user before the update.
   * @throws IllegalArgumentException If the provided user information is invalid.
   * @throws IllegalStateException    If the system is in an illegal state to process the update.
   * @throws RuntimeException         If an unexpected error occurs during the update process.
   */
  @Override public void updateUser(User user, String previousUsername)
  {
    try
    {
      client.updateUser(user, previousUsername);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Retrieves all orders associated with a specific user from the system.
   *
   * This method communicates with the backend server through the RmiClient to retrieve all orders
   * associated with the provided user. It returns an array of Order objects representing the user's
   * orders. It handles any potential errors that may occur during the retrieval process and notifies
   * listeners about fatal errors.
   *
   * @param user The user for whom the orders are to be retrieved.
   * @return An array of Order objects representing the user's orders.
   * @throws IllegalArgumentException If the provided user is invalid.
   * @throws IllegalStateException    If the system is in an illegal state to retrieve orders.
   * @throws RuntimeException         If an unexpected error occurs during the retrieval process.
   */
  @Override public Order[] getAllOrders(User user)
  {
    try
    {
      return client.getAllOrders(user);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Retrieves the snacks associated with a specific order from the system.
   *
   * This method communicates with the backend server through the RmiClient to retrieve the snacks
   * associated with the provided order. It returns an array of Snack objects representing the snacks
   * in the order. It handles any potential errors that may occur during the retrieval process and
   * notifies listeners about fatal errors.
   *
   * @param order The order for which snacks are to be retrieved.
   * @return An array of Snack objects representing the snacks in the order.
   * @throws IllegalArgumentException If the provided order is invalid.
   * @throws IllegalStateException    If the system is in an illegal state to retrieve snacks from the order.
   * @throws RuntimeException         If an unexpected error occurs during the retrieval process.
   */
  @Override public Snack[] getSnacksFromOrder(Order order)

  {
    try
    {
      return client.getSnacksFromOrder(order);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Retrieves the tickets associated with a specific order from the system.
   *
   * This method communicates with the backend server through the RmiClient to retrieve the tickets
   * associated with the provided order. It returns an array of Ticket objects representing the tickets
   * in the order. It handles any potential errors that may occur during the retrieval process and
   * notifies listeners about fatal errors.
   *
   * @param order The order for which tickets are to be retrieved.
   * @return An array of Ticket objects representing the tickets in the order.
   * @throws IllegalArgumentException If the provided order is invalid.
   * @throws IllegalStateException    If the system is in an illegal state to retrieve tickets from the order.
   * @throws RuntimeException         If an unexpected error occurs during the retrieval process.
   */
  @Override public Ticket[] getTicketsFromOrder(Order order)

  {
    try
    {
      return client.getTicketsFromOrder(order);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Reserves a seat for a customer in a specific screening.
   *
   *
   * @param seat The seat to be reserved.
   * @param customer The customer reserving the seat.
   * @param screening The screening for which the seat is being reserved.
   * @throws IllegalArgumentException If the provided arguments are invalid.
   * @throws IllegalStateException If the reservation cannot be made due to the current state of the system.
   * @throws RuntimeException If an unexpected error occurs during the reservation process.
   */
  @Override public void reserveSeat(Seat seat, User customer,
      Screening screening)
  {
    try
    {
      client.reserveSeat(seat, customer, screening);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Checks the availability of a seat at the specified index for a given screening.
   *
   * @param index     The index of the seat to check.
   * @param screening The screening for which the seat availability is being checked.
   * @return          True if the seat is available, false otherwise.
   * @throws IllegalArgumentException if the index or screening is invalid.
   * @throws IllegalStateException    if the operation is not allowed due to the state of the object.
   * @throws RuntimeException          if a fatal error occurs during the operation.
   */
  @Override public boolean checkSeatAvailability(int index, Screening screening)
  {
    try
    {
      return client.checkSeatAvailability(index, screening);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Attempts to log in as an administrator using the provided username and password.
   *
   * @param username The username of the administrator.
   * @param password The password of the administrator.
   * @return         True if the login is successful, false otherwise.
   * @throws IllegalArgumentException if the username or password is invalid.
   * @throws IllegalStateException    if the operation is not allowed due to the state of the object.
   * @throws RuntimeException          if a fatal error occurs during the operation.
   */
  @Override public boolean logInAdmin(String username, String password)
  {
    try
    {
      return client.logInAdmin(username, password);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Retrieves an array of available seats for a given screening.
   *
   * @param screening The screening for which available seats are to be retrieved.
   * @return          An array of available seats.
   * @throws IllegalArgumentException if the screening is invalid.
   * @throws IllegalStateException    if the operation is not allowed due to the state of the object.
   * @throws RuntimeException          if a fatal error occurs during the operation.
   */
  @Override public Seat[] getAvailableSeats(Screening screening)
  {
    try
    {
      return client.getAvailableSeats(screening);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Retrieves an array of empty seats for a given screening.
   *
   * @param screening The screening for which empty seats are to be retrieved.
   * @return          An array of empty seats.
   * @throws IllegalArgumentException if the screening is invalid.
   * @throws IllegalStateException    if the operation is not allowed due to the state of the object.
   * @throws RuntimeException          if a fatal error occurs during the operation.
   */
  @Override public Seat[] getEmptySeats(Screening screening)
  {
    try
    {
      return client.getEmptySeats(screening);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }

   /* REST OF THE METHODS FOLLOW the same logic,Retrieves information
    associated with similar object from the system.
    communicates with  server made through the RmiClient to retrieve information
   * associated with the provided user. It returns an array of Order objects representing the user's
    * orders. It handles any potential errors that may occur during the retrieval process and notifies
   * listeners about fatal errors.
    **/



  /**
   * Retrieves a user object based on the provided username.
   *
   * @param username The username of the user to retrieve.
   * @return The user object associated with the given username.
   * @throws IllegalArgumentException If the provided username is invalid.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs during the retrieval process.
   */
  @Override public User getUserByUsername(String username)
  {
    try
    {
      return client.getUserByUsername(username);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Updates the status of a seat to "booked" when a ticket is purchased.
   *
   * @param seat   The seat to be updated.
   * @param ticket The ticket associated with the seat.
   * @throws IllegalArgumentException If the provided seat or ticket is invalid.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs during the update process.
   */
  @Override public void updateSeatToBooked(Seat seat, Ticket ticket)
  {
    try
    {
      client.updateSeatToBooked(seat, ticket);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Adds an order associated with a user.
   *
   * @param order The order to be added.
   * @param user  The user associated with the order.
   * @throws IllegalArgumentException If the provided order or user is invalid.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs during the addition process.
   */
  @Override public void addOrder(Order order, User user)
  {
    try
    {
      client.addOrder(order, user);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Registers a new user.
   *
   * @param user The user to be registered.
   * @throws IllegalArgumentException If the provided user is invalid.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs during the registration process.
   */
  @Override public void register(User user)
  {
    try
    {
      client.register(user);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Adds a new screening.
   *
   * @param screening The screening to be added.
   * @throws IllegalArgumentException If the provided screening is invalid.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs while adding the screening.
   */

  @Override public void addScreening(Screening screening)
  {
    try
    {
      client.addScreening(screening);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Removes screenings by date.
   *
   * @param date The date of the screenings to be removed.
   * @throws IllegalArgumentException If the provided date is invalid.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs while removing the screenings.
   */
  @Override public void removeByDate(SimpleDate date)
  {
    try
    {
      client.removeByDate(date);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Retrieves all screenings.
   *
   * @return An ArrayList containing all screenings.
   * @throws IllegalArgumentException If there is an illegal argument provided.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs while retrieving the screenings.
   */
  @Override public ArrayList<Screening> getAllScreenings()
  {
    try
    {
      return client.getAllScreenings();
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Retrieves the total number of screenings.
   *
   * @return The total number of screenings.
   * @throws IllegalArgumentException If there is an illegal argument provided.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs while retrieving the number of screenings.
   */
  @Override public int getNbOfScreenings()
  {
    try
    {
      return client.getNbOfScreenings();
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Retrieves the screening matching the provided screening details.
   *
   * @param screening The screening details to match.
   * @return The screening matching the provided details.
   * @throws IllegalArgumentException If there is an illegal argument provided.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs while retrieving the screening.
   */
  @Override public Screening getScreening(Screening screening)
  {
    try
    {
      return client.getScreening(screening);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Retrieves all tickets associated with the provided user.
   *
   * @param user The user for whom to retrieve tickets.
   * @return An ArrayList containing all tickets associated with the provided user.
   * @throws IllegalArgumentException If there is an illegal argument provided.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs while retrieving the tickets.
   */
  @Override public ArrayList<Ticket> getAllTickets(User user)
  {
    try
    {
      return client.getAllTickets(user);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Retrieves the ticket associated with the provided order and ticket ID for viewing purposes.
   *
   * @param order The order to which the ticket belongs.
   * @param ID    The unique identifier of the ticket.
   * @return The ticket associated with the provided order and ticket ID.
   * @throws IllegalArgumentException If there is an illegal argument provided.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs while retrieving the ticket.
   */
  @Override public Ticket getTicketForView(Order order, String ID)
  {
    try
    {
      return client.getTicketForView(order, ID);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Downgrades the provided ticket associated with an order and user.
   *
   * @param ticket The ticket to be downgraded.
   * @param order  The order associated with the ticket.
   * @param user   The user who requests the downgrade.
   * @throws IllegalArgumentException If there is an illegal argument provided.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs while downgrading the ticket.
   */
  @Override public void downgradeTicket(Ticket ticket, Order order, User user)
  {
    try
    {
      client.downgradeTicket(ticket, order, user);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Upgrades the provided ticket associated with an order and user.
   *
   * @param ticket The ticket to be upgraded.
   * @param order  The order associated with the ticket.
   * @param user   The user who requests the upgrade.
   * @throws IllegalArgumentException If there is an illegal argument provided.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs while upgrading the ticket.
   */
  @Override public void upgradeTicket(Ticket ticket, Order order, User user)
  {
    try
    {
      client.upgradeTicket(ticket, order, user);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Cancels the specified ticket from the provided order.
   *
   * @param ticket The ticket to be canceled.
   * @param order  The order from which the ticket should be canceled.
   * @throws IllegalArgumentException If there is an illegal argument provided.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs while canceling the ticket from the order.
   */
  @Override public void cancelTicketFromOrder(Ticket ticket, Order order)
  {
    try
    {
      client.cancelTicketFromOrder(ticket, order);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Retrieves the room with the specified ID.
   *
   * @param id The ID of the room to retrieve.
   * @return The room with the specified ID, if found; otherwise, returns null.
   * @throws IllegalArgumentException If there is an illegal argument provided.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs while retrieving the room.
   */
  @Override public Room getRoomById(String id)
  {
    try
    {
     return client.getRoomById(id);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e){
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }
  }
  /**
   * Deletes a snack from the specified order.
   *
   * @param snack The snack to be deleted from the order.
   * @param order The order from which the snack is to be deleted.
   * @throws IllegalArgumentException If there is an illegal argument provided.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs while deleting the snack from the order.
   */
  @Override public void deleteSnackFromOrder(Snack snack, Order order)
  {
    try
    {
      client.deleteSnackFromOrder(snack, order);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Retrieves screenings by movie title from the client.
   *
   * @param title The title of the movie to search for screenings.
   * @return An ArrayList containing screenings of the movie with the specified title.
   * @throws IllegalArgumentException If there is an illegal argument provided.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs while retrieving screenings.
   */
  @Override public ArrayList<Screening> getScreaningsByMovieTitle(String title)
  {
    try
    {
      return client.getScreaningsByMovieTitle(title);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }
  }
  /**
   * Retrieves screenings by date from the client.
   *
   * @param date The date for which to retrieve screenings.
   * @return An ArrayList containing screenings scheduled for the specified date.
   * @throws IllegalArgumentException If there is an illegal argument provided.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs while retrieving screenings.
   */
  @Override public ArrayList<Screening> getScreeningsByDate(LocalDate date)
  {
    try
    {
      return client.getScreeningsByDate(date);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }
  }
  /**
   * Retrieves screenings by movie title and date from the client.
   *
   * @param title The title of the movie for which to retrieve screenings.
   * @param date  The date for which to retrieve screenings.
   * @return An ArrayList containing screenings of the specified movie scheduled for the specified date.
   * @throws IllegalArgumentException If there is an illegal argument provided.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs while retrieving screenings.
   */
  @Override public ArrayList<Screening> getScreeningsByDateAndTitle(
      String title, LocalDate date)
  {
    try
    {
      return client.getScreeningsByDateAndTitle(title, date);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }
  }
  /**
   * Retrieves orders for a specific user from the client.
   *
   * @param username The username of the user for whom to retrieve orders.
   * @return An ArrayList containing orders associated with the specified user.
   * @throws IllegalArgumentException If there is an illegal argument provided.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs while retrieving orders.
   */
  @Override public ArrayList<Order> getOrdersForUser(String username)
  {
    try
    {
      return client.getOrdersForUser(username);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Retrieves a movie for viewing based on its title.
   *
   * @param title The title of the movie to retrieve.
   * @return The movie object corresponding to the specified title.
   * @throws IllegalArgumentException If there is an illegal argument provided.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs while retrieving the movie.
   */
  @Override public Movie getMovieForView(String title)
  {
    try
    {
      return client.getMovieForView(title);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Changes the prices of items.
   *
   * @param newPrices The new prices to be applied.
   * @throws IllegalArgumentException If there is an illegal argument provided.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs while changing the prices.
   */
  @Override public void changePrices(ArrayList<Double> newPrices)
  {
    try
    {
      client.changePrices(newPrices);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Reserves seats for a customer in a screening.
   *
   * @param seats     The array of seats to be reserved.
   * @param customer  The customer who is reserving the seats.
   * @param screening The screening for which the seats are being reserved.
   * @param nbVIP     The number of VIP seats to be reserved.
   * @return The order containing the reserved seats.
   * @throws IllegalArgumentException If there is an illegal argument provided.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs while reserving the seats.
   */
  @Override public Order reserveSeats(Seat[] seats, User customer,
      Screening screening, int nbVIP)
  {
    try
    {
      return client.reserveSeats(seats, customer, screening, nbVIP);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }
  }
  /**
   * Retrieves the price of a snack based on its type and size.
   *
   * @param snackType The type of snack for which the price is requested.
   * @param size      The size of the snack for which the price is requested.
   * @return The price of the snack for the specified type and size.
   * @throws IllegalArgumentException If there is an illegal argument provided.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs while retrieving the price.
   */
  @Override public double getPriceForSize(String snackType, String size)
  {
    try
    {
      return client.getPriceForSize(snackType, size);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Adds a snack to an order with specified quantity, size, and associated user.
   *
   * @param snackType The type of snack to add to the order.
   * @param amount    The quantity of the snack to add to the order.
   * @param order     The order to which the snack is being added.
   * @param user      The user associated with the order.
   * @param size      The size of the snack being added.
   * @throws IllegalArgumentException If there is an illegal argument provided.
   * @throws IllegalStateException    If the operation cannot be performed in the current state.
   * @throws RuntimeException        If an unexpected error occurs while adding the snack to the order.
   */
  @Override public void addSnackToOrder(String snackType, int amount,
      Order order, User user, String size)
  {
    try
    {
      client.addSnackToOrder(snackType, amount, order, user, size);
    }
    catch (IllegalArgumentException e){
      throw e;
    }    catch (IllegalStateException e){
      throw e;
    }
    catch (RuntimeException e)
    {
      propertyChangeSupport.firePropertyChange("fatalError", null,
          e.getMessage());
      throw e;
    }

  }
  /**
   * Responds to property change events fired by the client and forwards them to registered listeners.
   *
   * @param evt The property change event containing information about the change.
   */
  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    switch (evt.getPropertyName())
    {
      case "reserve seats":
        propertyChangeSupport.firePropertyChange(
            "reserveSeats" + evt.getNewValue(), null, evt.getNewValue());
        break;
      case "reserve seat":
        propertyChangeSupport.firePropertyChange(
            "reserveSeat" + evt.getNewValue(), null, evt.getNewValue());
        break;
      case "add order":
        propertyChangeSupport.firePropertyChange("addOrder" + evt.getNewValue(),
            null, evt.getNewValue());
        break;
      case "remove order":
        propertyChangeSupport.firePropertyChange(
            "removeOrder" + evt.getNewValue(), null, evt.getNewValue());
        break;
      case "add screening":
        propertyChangeSupport.firePropertyChange("addScreening", null,
            evt.getNewValue());
        break;
      case "remove screening":
        propertyChangeSupport.firePropertyChange("removeScreening", null,
            evt.getNewValue());
        break;
      //      case "remove by date":
      //        propertyChangeSupport.firePropertyChange("removeByDate", null, evt.getNewValue());
      //        break;
    }
  }

}
