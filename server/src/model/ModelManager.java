package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 * ModelManager class implements Model interface
 * manages data and holds the logic for the system on the server side
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class ModelManager implements Model
{
  private String HOST;
  private ScreeningsList screenings;
  private UsersList usersList;
  private MoviesList moviesList;
  private PricesManager pricesManager;
  private AdminsList admins;
  private RoomsList roomsList;
  //not sure if the user variable is the one connected here

  private PropertyChangeSupport propertyChangeSupport;
  /**
   * Constructs a new ModelManager object
   * Initializes the database connection, admin, user, screening, movie, room, and price manager lists.
   * Fetches necessary data from the database and sets up screenings based on existing orders.
   *
   * @throws RuntimeException if there is an error in the database connection.
   */
  public ModelManager()
  {
    // Set the host to localhost
    this.HOST = "localhost";

    this.propertyChangeSupport = new PropertyChangeSupport(this);


    try
    {
      // Initialize lists of admins, users, screenings, movies, rooms, and prices
      admins = new AdminsList(DataBaseHandler.getAdmins());
      usersList = new UsersList(DataBaseHandler.getAllCustomers());
      screenings = new ScreeningsList(DataBaseHandler.getAllScreenings());
      moviesList = new MoviesList(DataBaseHandler.getAllMovies());
      roomsList = new RoomsList(DataBaseHandler.getAllRooms());
      pricesManager = DataBaseHandler.fetchPrices();
    }
    catch (SQLException e)
    {
      throw new RuntimeException(
          "Database connection error. " + e.getMessage());
    }
    // Retrieve all orders from the database for existing users
    ArrayList<Order> orders = DataBaseHandler.getAllOrders(usersList);
    // Set up screenings based on existing orders
    setUpScreenings(orders, screenings);
  }
  /**
   * Sets up screenings by booking seats based on the tickets from existing orders.
   * This method iterates through the list of orders and their associated tickets,
   * then books seats for each ticket in the screenings list.
   *
   * @param orders     the list of orders containing tickets to be processed
   * @param screenings the list of screenings to be updated with booked seats
   */
  private void setUpScreenings(ArrayList<Order> orders,
      ScreeningsList screenings)
  {
    for (Order order : orders)
    {
      for (Ticket ticket : order.getTickets())
      {
        // Book the seat in the screening associated with the ticket
        screenings.bookSeatByID(ticket.getScreening(), ticket.getSeatID(),
            ticket);
      }
    }
  }
  /**
   * Updates user information in the system.
   * This method updates the information of a user in the database by calling the appropriate method in the
   * {@link DataBaseHandler} class. After updating the user, it refreshes the list of users in the system.
   *
   * @param user            the updated user object containing new information
   * @param previousUsername the previous username of the user before the update
   * @throws RuntimeException if a database connection error occurs during the update process
   */
  @Override public void updateUser(User user, String previousUsername)
  {
    try
    {
      DataBaseHandler.updateUser(user, previousUsername);
      usersList = new UsersList(DataBaseHandler.getAllCustomers());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(
          "Database connection error. " + e.getMessage());
    }
  }
  /**
   * Retrieves a list of all movies stored in the system.
   * This method returns an ArrayList containing all the movies stored in the system by
   * delegating the task to the {@link MoviesList} object.
   *
   * @return an ArrayList of Movie objects representing all movies in the system
   */
  @Override public ArrayList<Movie> getAllMovies()
  {
    return moviesList.getMovies();
  }
  /**
   * Deletes a movie from the system.
   * This method deletes the specified movie from the system by invoking the corresponding method
   * in the {@link DataBaseHandler} class. After deleting the movie, it updates the movies list
   * stored in the ModelManager by retrieving all movies from the database again.
   *
   * @param movie the Movie object to be deleted from the system
   * @throws RuntimeException if a database connection error occurs during the deletion process
   */
  @Override public void deleteMovie(Movie movie)
  {
    try
    {
      DataBaseHandler.deleteMovie(movie.getName());
      moviesList = new MoviesList(DataBaseHandler.getAllMovies());

    }catch (SQLException e){
      throw new RuntimeException(
          "Database connection error. " + e.getMessage());
    }
  }
  /**
   * Retrieves a screening for display.
   * This method retrieves a screening object based on the specified time, date, movie title, and room number.
   * It delegates the retrieval process to the {@link ScreeningsList} object, which manages the list of screenings.
   *
   * @param time  the time of the screening
   * @param date  the date of the screening
   * @param title the title of the movie being screened
   * @param room  the room number where the screening takes place
   * @return the Screening object corresponding to the specified parameters
   */
  @Override public Screening getScreeningForView(String time, String date,
      String title, int room)
  {
    // Delegate the retrieval process to the ScreeningsList object
    return screenings.getScreeningForView(time, date, title, room);
  }
  /**
   * Retrieves a movie for display.
   * This method retrieves a movie object based on the specified title.
   * It delegates the retrieval process to the {@link MoviesList} object, which manages the list of movies.
   *
   * @param title the title of the movie to retrieve
   * @return the Movie object corresponding to the specified title
   */
  @Override public Movie getMovieForView(String title)
  {
    return moviesList.getMovieByTitle(title);
  }
  /**
   * Retrieves a ticket for display.
   * This method retrieves a ticket object based on the specified order and ticket ID.
   * It delegates the retrieval process to the {@link Order} object, which manages the tickets associated with it.
   *
   * @param order the order associated with the ticket
   * @param ID    the ID of the ticket to retrieve
   * @return the Ticket object corresponding to the specified order and ticket ID
   */
  public Ticket getTicketForView(Order order, String ID)
  {
    return order.getTicketForView(ID);
  }
  /**
   * Logs in a user.
   * This method attempts to log in a user by verifying the provided username and password.
   * It delegates the login process to the {@link UsersList} object, which manages the list of users.
   *
   * @param username the username of the user attempting to log in
   * @param password the password of the user attempting to log in
   * @return the User object if login is successful, or null otherwise
   */
  @Override public User logIn(String username, String password)
  {
    return usersList.logIn(username, password);
  }
  /**
   * Changes the price
   *
   * @param item     the item whose price is to be changed
   * @param newPrice the new price for the item
   */
  @Override public void changePrice(String item, double newPrice)
  {
      pricesManager.changePrice(item, newPrice);
  }
  /**
   * Changes the prices of various items.
   * This method is synchronized to ensure thread safety when changing prices.
   * It updates the prices stored in the database through the {@code DataBaseHandler}.
   * If there's a SQL exception during the process, it throws a {@code RuntimeException} with an error message.
   */
@Override public void changePrices(){
    try
    {
      DataBaseHandler.changePrices(pricesManager);
    }catch (SQLException e){
      throw new RuntimeException(
          "Database connection error. " + e.getMessage());
    }
}
  /**
   * Retrieves the price for a specific size of a snack item.
   *
   * @param snackType The type of snack.
   * @param size The size of the snack.
   * @return The price for the specified size of the snack.
   */
  @Override public double getPriceForSize(String snackType, String size)
  {
    return pricesManager.getPriceForSizeOfSnack(snackType, size);
  }
  /**
   * Validates the login credentials for an admin user.
   *
   * @param username The username of the admin.
   * @param password The password of the admin.
   * @return True if the login credentials are valid, otherwise false.
   */
  @Override public boolean logInAdmin(String username, String password)
  {
      admins.logIn(username,password);
      return true;
  }
/*
add listener
 */
  @Override public void addListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(listener);

  }
/*
remove listener
 */
  @Override public void removeListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }
  /**
   * Cancels an order placed by a user.
   *
   * @param order The order to be canceled.
   * @param user The user who placed the order.
   */
  @Override public void cancelOrder(Order order, User user)
  {
      getOrderByID(order.getOrderID(),
          getUserByUsername(user.getUsername())).cancelOrder();
    try
    {
      DataBaseHandler.deleteOrder(order.getOrderID());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(
          "Database connection error. " + e.getMessage());
    }
  }
  /**
   * Retrieves an order by its ID for a specific user.
   *
   * @param orderID The ID of the order to retrieve.
   * @param user The user associated with the order.
   * @return The order with the specified ID.
   */
  @Override public Order getOrderByID(int orderID, User user)
  {
    return getUserByUsername(user.getUsername()).getOrderByID(orderID);
  }

  @Override public String getHost()
  {
    return HOST;
  }
  /**
   * Retrieves all tickets associated with a user.
   *
   * @param user The user whose tickets are to be retrieved.
   * @return An ArrayList containing all tickets associated with the user.
   */
  @Override public ArrayList<Ticket> getAllTickets(User user)

  {
    return user.getAllTickets();
  }
  /**
   * Retrieves all screenings for a specific movie title.
   *
   * @param title The title of the movie for which screenings are to be retrieved.
   * @return An ArrayList containing all screenings for the specified movie title.
   */
  @Override public ArrayList<Screening> getScreaningsByMovieTitle(String title)
  {
    System.out.println("Screening by movie title function executed");
    return screenings.getScreaningsByMovieTitle(title);
  }
  /**
   * Retrieves all screenings scheduled for a specific date.
   *
   * @param date The date for which screenings are to be retrieved.
   * @return An ArrayList containing all screenings scheduled for the specified date.
   */
  @Override public ArrayList<Screening> getScreeningsByDate(LocalDate date)
  {
    return screenings.getScreeningsByDate(date);
  }

  /**
   * Retrieves all screenings scheduled for a specific movie title and date.
   *
   * @param title The title of the movie for which screenings are to be retrieved.
   * @param date The date for which screenings are to be retrieved.
   * @return An ArrayList containing all screenings scheduled for the specified movie title and date.
   */
  @Override public ArrayList<Screening> getScreeningsByDateAndTitle(
      String title, LocalDate date)
  {
    return screenings.getScreeningsByDateAndTitle(title, date);
  }

  /**
   * Reserves a seat for a customer in a screening.
   *
   * @param seat The seat to be reserved.
   * @param customer The user/customer for whom the seat is being reserved.
   * @param screening The screening in which the seat is being reserved.
   * @throws RuntimeException if no available seats are left for the screening.
   */
  @Override
  public synchronized void reserveSeat(Seat seat, User customer, Screening screening) {
    if (screening.getNbOfAvailableSeats() <= 0) {
      throw new RuntimeException("No available seats left for this screening");
    }
    Order temp = new Order(customer.getOrders().size() + 1);
    Ticket tempT = new StandardTicket(seat.getID(),
        pricesManager.getStandardTicketPrice(), seat, screening);
    seat.book(tempT);
    temp.addTicket(tempT);
    customer.addOrder(temp);
  }

  /**
   * Checks the availability of a seat in a screening.
   *
   * @param index The index of the seat to be checked.
   * @param screening The screening in which the seat availability is to be checked.
   * @return true if the seat is available, false otherwise.
   */
  @Override
  public boolean checkSeatAvailability(int index, Screening screening) {
    return screening.getSeatAvailabilty(index);
  }
  /**
   * Retrieves the available seats for a given screening.
   *
   * @param screening The screening for which available seats are to be retrieved.
   * @return An array of available seats.
   */
  @Override
  public Seat[] getAvailableSeats(Screening screening) {
    return screenings.getAvailableSeats(screening);
  }

  /**
   * Retrieves the empty seats for a given screening.
   *
   * @param screening The screening for which empty seats are to be retrieved.
   * @return An array of empty seats.
   */
  @Override
  public Seat[] getEmptySeats(Screening screening) {
    return screenings.getEmptySeats(screening);
  }

  /**
   * Retrieves a user by their username.
   *
   * @param username The username of the user to be retrieved.
   * @return The user corresponding to the given username.
   */
  @Override
  public User getUserByUsername(String username) {
    return usersList.getByUsername(username);
  }

  /**
   * Updates the status of a seat to booked.
   *
   * @param seat   The seat to be updated.
   * @param ticket The ticket associated with the booking.
   */
  @Override public void updateSeatToBooked(Seat seat, Ticket ticket)
  {
    seat.book(ticket);
  }
  /**
   * Adds an order to a user's list of orders.
   *
   * @param order The order to be added.
   * @param user  The user to whom the order belongs.
   */
  @Override public void addOrder(Order order, User user)
  {
    user.addOrder(order);
  }

  @Override public Order[] getAllOrders(User user)
  {
    return user.getAllOrders();
  }
  /**
   * Retrieves an array of snacks from the specified order.
   *
   * @param order the order from which snacks are to be retrieved
   * @return an array of snacks from the order
   */
  @Override public Snack[] getSnacksFromOrder(Order order)
  {
    return order.getSnacksFromOrder();
  }
  /**
   * Retrieves an array of tickets from the specified order.
   *
   * @param order the order from which tickets are to be retrieved
   * @return an array of tickets from the order
   */
  @Override public Ticket[] getTicketsFromOrder(Order order)
  {
    return order.getTicketsFromOrder();

  }

  /**
   * Upgrades a ticket to the VIP type.
   * @param ticket
   * @param order
   * @param user
   */
  @Override public void upgradeTicket(Ticket ticket, Order order, User user)
  {
    getOrderByID(order.getOrderID(), user).upgrade(ticket,
        pricesManager.getVipTicketPrice());
  }
  /**
   * Cancels a ticket from an order and updates the database accordingly.
   *
   * @param ticket the ticket to be canceled
   * @param order the order from which the ticket is to be canceled
   */
  @Override public void cancelTicketFromOrder(Ticket ticket, Order order, User user)
  {
    try
    {
      DataBaseHandler.deleteTicket(ticket.getTicketID());
      order.removeTicket(ticket);
      getUserByUsername(user.getUsername()).addOrder(order);
    }catch (SQLException e)
    {
      throw new RuntimeException(
          "Database connection error. " + e.getMessage());
    }

  }
  /**
   * Removes a snack from an order and updates the database accordingly.
   *
   * @param snack the snack to be removed from the order
   * @param order the order from which the snack is to be removed
   */
  @Override public void deleteSnackFromOrder(Snack snack, Order order)
  {
    order.removeSnack(snack);
    try
    {
      DataBaseHandler.deleteSnack(snack, order);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(
          "Database connection error. " + e.getMessage());
    }
  }
  /**
   * Downgrades the ticket of a specific order to a standard ticket and updates the database.
   *
   * @param ticket the ticket to be downgraded
   * @param order the order associated with the ticket
   * @param user the user who made the order
   */
  @Override public void downgradeTicket(Ticket ticket, Order order, User user)
  {
    getOrderByID(order.getOrderID(), user).downgrade(ticket,
        pricesManager.getStandardTicketPrice());
    try
    {
      DataBaseHandler.changeTicketType(ticket);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(
          "Database connection error. " + e.getMessage());
    }
  }
  /**
   * Adds a new screening to the system and updates the database.
   *
   * @param screening the screening to add
   */
  @Override public synchronized void addScreening(Screening screening)
  {
    screenings.addScreening(screening);
    try
    {
      DataBaseHandler.addScreeningToDatabase(screening);
      screenings = new ScreeningsList(DataBaseHandler.getAllScreenings());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(
          "Database connection error. " + e.getMessage());
    }
  }
  /**
   * Retrieves a room from the list of rooms based on the provided room ID.
   *
   * @param id the ID of the room to retrieve
   * @return the room corresponding to the given ID
   */
  @Override public Room getRoomById(String id){
    int ID = Integer.parseInt(id);
    return roomsList.getRoomById(ID);
  }
  /**
   * Removes a screening from the system.
   *
   * @param screening the screening to be removed
   */
  @Override public synchronized void removeScreening(Screening screening)
  {
    try
    {
      DataBaseHandler.deleteScreening(screening);
      getScreening(screening).deleteID();
      screenings = new ScreeningsList(DataBaseHandler.getAllScreenings());

    }
    catch (SQLException e)
    {
      throw new RuntimeException(
          "Database connection error. " + e.getMessage());
    }
  }
  /**
   * Removes all the screenings scheduled for a specific date from the system.
   *
   * @param date the date for which screenings need to be removed
   */
  @Override public void removeByDate(SimpleDate date)
  {
    screenings.removeByDate(date);
  }
  /**
   * Retrieves all the screenings available in the system.
   *
   * @return an ArrayList containing all the screenings
   */
  @Override public ArrayList<Screening> getAllScreenings()
  {
    return screenings.getScreenings();
  }
  /**
   * Retrieves the number of screenings currently available in the system.
   *
   * @return the number of screenings
   */
  @Override public int getNbOfScreenings()
  {
    return screenings.getSize();
  }
  /**
   * Retrieves the Screening object corresponding to the provided screening.
   *
   * @param screening the Screening object to retrieve
   * @return the Screening object corresponding to the provided screening
   */
  @Override public Screening getScreening(Screening screening)
  {
    return screenings.getScreening(screening);
  }
  /**
   * Reserves seats for a screening on behalf of a customer.
   *
   * @param seats     an array of Seat objects representing the seats to be reserved
   * @param customer  the User object representing the customer who is reserving the seats
   * @param screening the Screening object for which the seats are being reserved
   * @param nbVip     the number of VIP seats to be reserved
   * @return the Order object representing the reservation made for the seats
   * @throws IllegalArgumentException if there are not enough available seats for the reservation
   * @throws IllegalStateException    if the seats are already reserved
   */
  @Override public synchronized Order reserveSeats(Seat[] seats, User customer,
      Screening screening, int nbVip)
  {
    Screening scr = getScreening(screening);
    User user = getUserByUsername(customer.getUsername());
    boolean freeSeats = true;
    if (scr.getNbOfAvailableSeats() < seats.length)
    {
      throw new IllegalArgumentException(
          "Not enough available seats left for this screening");
    }
    for (Seat seat : seats)
    {
      if (!scr.getAvailabilityById(seat.getID()))
      {
        freeSeats = false;
      }
    }
    if (freeSeats)
    {
      try
      {
        Order temp = new Order(user.generateOrderID());
        DataBaseHandler.addOrderToDatabase(temp, user.getUsername());
        for (Seat seat : seats)
        {
          Ticket tempT;
          if (nbVip > 0)
          {
             tempT = new VIPTicket(temp.generateTicketID(),
                pricesManager.getVipTicketPrice(), seat, scr);
            nbVip--;
          }
          else
          {
             tempT = new StandardTicket(temp.generateTicketID(),
                pricesManager.getStandardTicketPrice(), seat, screening);
          }
          scr.bookSeatById(seat.getID(), tempT);
          DataBaseHandler.addTicketToDatabase(tempT, temp);
          temp.addTicket(tempT);
        }
        user.addOrder(temp);
        return getOrderByID(temp.getOrderID(), getUserByUsername(
            user.getUsername()));
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e.getMessage());
      }
    }
      throw new IllegalStateException("Seats are already reserved.");
  }
  /**
   * Retrieves all orders associated with a specific user.
   *
   * @param username the username of the user whose orders are to be retrieved
   * @return an ArrayList of orders associated with the specified user
   */
  @Override public ArrayList<Order> getOrdersForUser(String username)
  {
    return usersList.getOrdersForUser(username);
  }
  /**
   * Adds snacks to an existing order for a specific user.
   *
   * @param snackType the type of snack to be added
   * @param amount    the quantity of the snack to be added
   * @param order     the order to which the snacks will be added
   * @param user      the user who owns the order
   * @param size      the size of the snack to be added
   */
  @Override public void addSnackToOrder(String snackType, int amount,
      Order order, User user, String size)
  {
    User customer = getUserByUsername(user.getUsername());
    Order temp = getOrderByID(order.getOrderID(), customer);
    for (int i = 0; i < amount; i++)
    {
      Snack snack = new Snack(pricesManager.getPriceForSize(
          pricesManager.getPriceForSnack(snackType), size), snackType, size);
      temp.addSnack(snack);
      try
      {
        DataBaseHandler.addSnack(snack, temp);
      }
      catch (SQLException e)
      {
        throw new RuntimeException(
            "Database connection error. " + e.getMessage());
      }
    }
  }
  /**
   * Changes the prices of various items and updates the database accordingly.
   *
   * @param newPrices the new prices to be set
   */
  @Override public synchronized void changePrices(ArrayList<Double> newPrices)
  {
    pricesManager.setAllPrices(newPrices);
    try
    {
      DataBaseHandler.changePrices(pricesManager);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(
          "Database connection error. " + e.getMessage());
    }
  }
  /**
   * Retrieves the price for a ticket of the specified type from the prices manager.
   *
   * @param type the type of the ticket (e.g., "Standard", "VIP")
   * @return the price of the ticket
   */
  @Override public double getPriceForTicket(String type)
  {
   return pricesManager.getPriceForTicket(type);
  }
  /**
   * Registers a new user in the system.
   * This method checks if the username provided by the user is available by iterating through
   * the list of administrators and the list of registered users. If the username is already
   * taken by either an administrator or another registered user, an IllegalArgumentException
   * is thrown with an appropriate error message.
   * If the username is available, the method attempts to add the new user to the database
   * using the DataBaseHandler.newUser method. After successfully adding the user to the
   * database, it updates the list of users in the model by fetching all customers from
   * the database again.
   *
   * @param user the user object representing the new user to be registered
   * @throws IllegalArgumentException if the username is unavailable
   * @throws RuntimeException         if there is a database connection error
   */
  public void register(User user)
  {
    for (User admin : admins.getAdmins()){
      if (admin.getUsername().equals(user.getUsername())){
        System.out.println("admin");
        throw new IllegalArgumentException("This username is unavailable.");
      }
    }
      if (usersList.isRegistered(user)){
        throw new IllegalArgumentException("This username is unavailable.");
      }

    try
    {
      DataBaseHandler.newUser(user);
      usersList = new UsersList(DataBaseHandler.getAllCustomers());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(
          "Database connection error. " + e.getMessage());
    }
  }
  /**
   * Deletes the user account associated with the given username.
   * This method deletes the user account from the system along with any associated orders.
   * It first cancels any active orders belonging to the user by calling the cancelOrder method.
   * Then, it deletes the user account from the database using the DataBaseHandler.deleteUser method.
   * After deleting the user, it updates the list of users in the model by fetching all customers from the database again.
   *
   * @param username the username of the user whose account is to be deleted
   * @throws RuntimeException if there is a database connection error
   */
  public synchronized void deleteAccount(String username)
  {
    try
    {
      for (Order order:getUserByUsername(username).getOrders()){
        if (!order.isExpired()){
          cancelOrder(order,getUserByUsername(username));
        }
    }
      DataBaseHandler.deleteUser(username);
      usersList = new UsersList(DataBaseHandler.getAllCustomers());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(
          "Database connection error. " + e.getMessage());
    }
  }
  /**
   * Adds a new movie to the system.
   * This method adds a new movie to the system by invoking the corresponding method in the database handler.
   * After adding the movie, it updates the list of movies in the model by fetching all movies from the database again.
   *
   * @param movie the Movie object representing the movie to be added
   * @throws RuntimeException if there is a database connection error
   */
  @Override public synchronized void addMovie(Movie movie){
    try
    {
      DataBaseHandler.newMovie(movie);
      moviesList = new MoviesList(DataBaseHandler.getAllMovies());
    }catch (SQLException e){
      throw new RuntimeException(
          "Database connection error. " + e.getMessage());
    }
  }

}