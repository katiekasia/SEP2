package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class ModelManager implements Model
{
  private String HOST;
  private ScreeningsList screenings;
  private UsersList usersList;
  private MoviesList moviesList;
  private PricesManager pricesManager;
  private Admin admin;
  private RoomsList roomsList;
  //not sure if the user variable is the one connected here

  private PropertyChangeSupport propertyChangeSupport;

  public ModelManager()
  {
    this.HOST = "localhost";

    this.propertyChangeSupport = new PropertyChangeSupport(this);
    this.admin = new Admin();

    try
    {
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
    ArrayList<Order> orders = DataBaseHandler.getAllOrders(usersList);
    setUpScreenings(orders, screenings);
  }

  private void setUpScreenings(ArrayList<Order> orders,
      ScreeningsList screenings)
  {
    for (Order order : orders)
    {
      for (Ticket ticket : order.getTickets())
      {
        screenings.bookSeatByID(ticket.getScreening(), ticket.getSeatID(),
            ticket);
      }
    }
  }

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

  @Override public Screening getScreeningForView(String time, String date,
      String title, int room)
  {
    return screenings.getScreeningForView(time, date, title, room);
  }

  @Override public Movie getMovieForView(String title)
  {
    return moviesList.getMovieByTitle(title);
  }

  public Ticket getTicketForView(Order order, String ID)
  {
    return order.getTicketForView(ID);
  }

  @Override public User logIn(String username, String password)
  {
    return usersList.logIn(username, password);
  }

  @Override public void changePrice(String item, double newPrice)
  {
      pricesManager.changePrice(item, newPrice);
  }
@Override public void changePrices(){
    try
    {
      DataBaseHandler.changePrices(pricesManager);
    }catch (SQLException e){
      throw new RuntimeException(
          "Database connection error. " + e.getMessage());
    }
}
  @Override public double getPriceForSize(String snackType, String size)
  {
    return pricesManager.getPriceForSizeOfSnack(snackType, size);
  }

  @Override public boolean logInAdmin(String username, String password)
  {
    if (username.equals(Admin.USERNAME) && password.equals(Admin.PASSWORD))
    {
      return true;
    }
    throw new IllegalArgumentException("No user with such credentials found.");
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(listener);

  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }

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

  @Override public Order getOrderByID(int orderID, User user)
  {
    return getUserByUsername(user.getUsername()).getOrderByID(orderID);
  }

  @Override public String getHost()
  {
    return HOST;
  }

  @Override public ArrayList<Ticket> getAllTickets(User user)

  {
    return user.getAllTickets();
  }

  @Override public ArrayList<Screening> getScreaningsByMovieTitle(String title)
  {
    return screenings.getScreaningsByMovieTitle(title);
  }

  @Override public ArrayList<Screening> getScreeningsByDate(LocalDate date)
  {
    return screenings.getScreeningsByDate(date);
  }

  @Override public ArrayList<Screening> getScreeningsByDateAndTitle(
      String title, LocalDate date)
  {
    return screenings.getScreeningsByDateAndTitle(title, date);
  }

  @Override public void reserveSeat(Seat seat, User customer,
      Screening screening)
  {
    if (screening.getNbOfAvailableSeats() <= 0)
    {
      throw new RuntimeException("No available seats left for this screening");
    }
    Order temp = new Order(customer.getOrders().size() + 1);
    Ticket tempT = new StandardTicket(seat.getID(),
        pricesManager.getStandardTicketPrice(), seat, screening);
    seat.book(tempT);
    temp.addTicket(tempT);
    customer.addOrder(temp);
  }

  @Override public boolean checkSeatAvailability(int index, Screening screening)
  {
    return screening.getSeatAvailabilty(index);
  }

  @Override public Seat[] getAvailableSeats(Screening screening)
  {
    return screenings.getAvailableSeats(screening);
  }

  @Override public Seat[] getEmptySeats(Screening screening)
  {
    return screenings.getEmptySeats(screening);
  }

  @Override public User getUserByUsername(String username)
  {
    return usersList.getByUsername(username);
  }

  @Override public void updateSeatToBooked(Seat seat, Ticket ticket)
  {
    seat.book(ticket);
  }

  @Override public void addOrder(Order order, User user)
  {
    user.addOrder(order);
  }

  @Override public Order[] getAllOrders(User user)
  {
    return user.getAllOrders();
  }

  @Override public Snack[] getSnacksFromOrder(Order order)
  {
    return order.getSnacksFromOrder();
  }

  @Override public Ticket[] getTicketsFromOrder(Order order)
  {
    return order.getTicketsFromOrder();

  }

  @Override public void upgradeTicket(Ticket ticket, Order order, User user)
  {
    getOrderByID(order.getOrderID(), user).upgrade(ticket,
        pricesManager.getVipTicketPrice());
  }

  @Override public void cancelTicketFromOrder(Ticket ticket, Order order)
  {
    order.removeTicket(ticket);
  }

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

  @Override public void addScreening(Screening screening)
  {
    screenings.addScreening(screening);
    try
    {
      DataBaseHandler.addScreeningToDatabase(screening);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(
          "Database connection error. " + e.getMessage());
    }
  }

  @Override public void removeScreening(Screening screening)
  {
    try
    {
      DataBaseHandler.deleteScreening(screening);
      getScreening(screening).deleteID();
      screenings = new ScreeningsList(DataBaseHandler.getAllScreenings());

    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void removeByDate(SimpleDate date)
  {
    screenings.removeByDate(date);
  }

  @Override public ArrayList<Screening> getAllScreenings()
  {
    return screenings.getScreenings();
  }

  @Override public int getNbOfScreenings()
  {
    return screenings.getSize();
  }

  @Override public Screening getScreening(Screening screening)
  {
    return screenings.getScreening(screening);
  }

  @Override public Order reserveSeats(Seat[] seats, User customer,
      Screening screening, int nbVip)
  {
    Screening scr = getScreening(screening);
    User user = getUserByUsername(customer.getUsername());
    boolean freeSeats = true;
    if (scr.getNbOfAvailableSeats() < seats.length)
    {
      throw new RuntimeException(
          "Not enough available seats left for this screening");
    }
    for (Seat seat : seats)
    {
      if (!seat.isAvailable())
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
          if (nbVip > 0)
          {
            Ticket tempT = new VIPTicket(temp.generateTicketID(),
                pricesManager.getVipTicketPrice(), seat, scr);
            scr.bookSeatById(seat.getID(), tempT);
            temp.addTicket(tempT);
            DataBaseHandler.addTicketToDatabase(tempT, temp);
            nbVip--;
          }
          else
          {
            Ticket tempT = new StandardTicket(temp.generateTicketID(),
                pricesManager.getStandardTicketPrice(), seat, screening);
            scr.bookSeatById(seat.getID(), tempT);
            DataBaseHandler.addTicketToDatabase(tempT, temp);
            temp.addTicket(tempT);
          }
        }
        user.addOrder(temp);

        return user.getOrderByID(temp.getOrderID());
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e.getMessage());
      }
    }
    else
      throw new IllegalStateException("Seats are already reserved.");
  }

  @Override public ArrayList<Order> getOrdersForUser(String username)
  {
    return usersList.getOrdersForUser(username);
  }

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

  @Override public void changePrices(ArrayList<Double> newPrices)
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

  @Override public void register(String username, String password, String email,
      String firstName, String lastName, String phone)
  {

  }

  @Override public void deleteAccount(String username)
  {

  }

  @Override
  public void addMovie(Movie movie) {
    try {
      DataBaseHandler.newMovie(movie);
      moviesList= new MoviesList(DataBaseHandler.getAllMovies());
    } catch (SQLException e) {
      throw new RuntimeException("Database connection error. " + e.getMessage());
    }
  }


}