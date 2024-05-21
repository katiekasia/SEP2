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
  private int PORT;
  private boolean running;
  private ScreeningsList screenings;
  private UsersList usersList;

  //not sure if the user variable is the one connected here

  private PropertyChangeSupport propertyChangeSupport;


  public ModelManager()
  {
    this.running = false;
    this.PORT = 5678;
    this.HOST = "localhost";

    this.propertyChangeSupport = new PropertyChangeSupport(this);

    try
    {
      usersList = new UsersList();
      screenings = new ScreeningsList();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    ArrayList<Order> orders = DataBaseHandler.getAllOrders(usersList);
    setUpScreenings(orders, screenings);
  }
  private void setUpScreenings(ArrayList<Order> orders, ScreeningsList screenings){
    for (Order order : orders){
      for (Ticket ticket : order.getTickets()){
        screenings.getScreening(ticket.getScreening()).getRoom().getSeatByID(ticket.getSeat().getID()).book(ticket);
      }
    }
  }
  @Override  public void updateUser(User user)
  {
    try
    {
      DataBaseHandler.updateUser(user);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  @Override public Screening getScreeningForView(String time, String date,String title, int room){
    return screenings.getScreeningForView(time, date, title, room);
  }
//  public Ticket getTicketForView(String time, String date,String title, int room, String username){
//    for (Order order : usersList.getByUsername(username).getOrders()){
//     if (order.getOrderDate().toString().equals(date))
//    }
//    throw
//  }
  @Override public User logIn(String username, String password)
  {
    try
    {

      ArrayList<User> users = DataBaseHandler.getAllCustomers();
      for (User user : users)
      {
        //checking if they have the same username and password
        if (user.getUsername().equals(username) && user.getPassword()
            .equals(password))
        {
          return user;
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    propertyChangeSupport.firePropertyChange("user", null, username);
    throw new IllegalStateException("No such user found.");
  }


  @Override public void addListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(listener);

  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }

  @Override public void cancelOrder(Order order)
  {
    order.cancelOrder();
  }

  @Override public Order getOrderByID(int orderID,User user)
  {
    return user.getOrderByID(orderID);
  }

  @Override public void connect()
  {
    //not for this sprint
  }

  @Override public void disconnect()
  {
    //not for this sprint
  }

  @Override public int getPort()
  {
    return PORT;
  }

  @Override public void setPort(int port)
  {
    this.PORT = port;
  }

  @Override public String getHost()
  {
    return HOST;
  }

  @Override public ArrayList<Ticket> getAllTickets(User user)

  {
    ArrayList<Ticket> tickets = new ArrayList<>();
    for (int i = 0; i < user.getOrders().size(); i++)
    {
      for (Ticket ticket : user.getOrders().get(i).getTickets())
      {
        if (ticket != null)
        {
          tickets.add(ticket);
        }
      }
    }
    return tickets;
  }

  /*
  @Override public Seat getSeatByScreening(Screening screening)
  {
    return screening.getRoom().getSeat(0);
  }

 */

  @Override public ArrayList<Screening> getScreaningsByMovieTitle(String title)
  {
    ArrayList<Screening> result = new ArrayList<>();
    for (int i = 0; i < screenings.getSize(); i++)
    {
      if (screenings.getScreeningById(i).getMovie().getName().toUpperCase()
          .equals(title.toUpperCase()))
      {
        result.add(screenings.getScreeningById(i));
      }
    }
    return result;
  }

  @Override public ArrayList<Screening> getScreeningsByDate(LocalDate date)
  {
    SimpleDate temp = new SimpleDate(date);
    ArrayList<Screening> result = new ArrayList<>();
    for (int i = 0; i < screenings.getSize(); i++)
    {
      if (screenings.getScreeningById(i).getDate().equals(temp)
          || screenings.getScreeningById(i).getDate().isAfter(temp))
      {
        result.add(screenings.getScreeningById(i));
      }
    }
    return result;
  }

  @Override public ArrayList<Screening> getScreeningsByDateAndTitle(
      String title, LocalDate date)
  {
    SimpleDate temp = new SimpleDate(date);
    ArrayList<Screening> result = new ArrayList<>();
    for (Screening screening:screenings.getScreenings()){
      if (screening.getMovie().getName().equals(title) && screening.getDate().equals(temp)){
        result.add(screening);
      }
    }
    return result;
  }

  @Override public void reserveSeat(Seat seat, User customer,
      Screening screening)
  {
    if (screening.getRoom().availableSeats() <= 0)
    {
      throw new RuntimeException("No available seats left for this screening");
    }
    Order temp = new Order(customer.getOrders().size() + 1);
    Ticket tempT = new StandardTicket(seat.getID(), 13, seat, screening);
    seat.book(tempT);
    temp.addTicket(tempT);
    customer.addOrder(temp);
    System.out.println("booking complete");
    System.out.println(temp + "\n" + temp.getTickets().size());

    updateDatabaseWithBooking(customer, tempT);
  }

  private void updateDatabaseWithBooking(User customer, Ticket ticket)
  {
    // Example method to illustrate saving to DB (requires actual implementation)
    try (Connection conn = DataBaseHandler.getConnection())
    {
      // SQL queries to insert ticket and update seat status
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public boolean checkSeatAvailability(int index, Screening screening)
  {
    return (screening.getRoom().getSeat(index).isAvailable());
  }

  @Override public Seat[] getAvailableSeats(Screening screening)
  {
    ArrayList<Seat> seats = new ArrayList<>();
    for (int i = 0; i < screening.getRoom().getNbSeats(); i++)
    {
      if (screening.getRoom().getSeat(i).isAvailable())
      {
        seats.add(screening.getRoom().getSeat(i));
      }
    }
    return seats.toArray(new Seat[screening.getRoom().getNbSeats()]);
  }

  @Override public Seat[] getEmptySeats(Screening screening)
  {
    ArrayList<Seat> emptySeats = new ArrayList<>();
    for (int i = 0; i < screening.getRoom().getNbSeats(); i++)
    {
      if (!screening.getRoom().getSeat(i).isAvailable())
      {
        emptySeats.add(screening.getRoom().getSeat(i));
      }
    }
    return emptySeats.toArray(new Seat[screening.getRoom().getNbSeats()]);
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
    if (user.getOrders() != null){
      Order[] result = user.getOrders().toArray(new Order[0]);
      return result;
    }
    return null;
  }

  @Override public Snack[] getSnacksFromOrder(Order order)
  {

    if (order.getSnacks() != null){
      Snack[] result = order.getSnacks().toArray(new Snack[0]);
      return result;
    }
    return null;
  }

  @Override public Ticket[] getTicketsFromOrder(Order order)
  {
    if (order.getTickets() != null){
      Ticket[] result = order.getTickets().toArray(new Ticket[0]);
      return result;
    }
    return null;
  }
@Override public void upgradeTicket(Ticket ticket, Order order){
  if (ticket instanceof StandardTicket)
  {
    order.upgrade(ticket);

  }else
  {
    throw new IllegalStateException("Ticket cannot be upgraded.");
  }
}

  @Override public void cancelTicketFromOrder(Ticket ticket, Order order)
  {
    order.removeTicket(ticket);
  }

  @Override public void deleteSnackFromOrder(Snack snack, Order order)
  {
    order.removeSnack(snack);
  }

  @Override public void downgradeTicket(Ticket ticket, Order order){
  if (ticket instanceof VIPTicket)
  {
order.downgrade(ticket);
  }else
  {
    throw new IllegalStateException("Ticket cannot be downgraded.");
  }
}


  @Override public void addScreening(Screening screening)
  {
    screenings.addScreening(screening);
  }

  @Override public void removeScreening(Screening screening)
  {
    screenings.removeScreening(screening);
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

  @Override public void reserveSeats(Seat[] seats, User customer,
      Screening screening, int nbVip)
  {
    boolean freeSeats = true;
    if (screening.getRoom().availableSeats() < seats.length)
    {
      throw new RuntimeException(
          "Not enough available seats left for this screening");
    }
    for (Seat seat : seats){
      if (!seat.isAvailable()){
        freeSeats = false;
      }
    }
    if (freeSeats)
    {
      Order temp = new Order(customer.getOrders().size() + 1);
      for (Seat seat : seats)
      {
        if (nbVip > 0)
        {
          Ticket tempT = new VIPTicket(seat.getID(), 13, seat, screening);
          seat.book(tempT);
          temp.addTicket(tempT);
          nbVip--;
        }
        else
        {
          Ticket tempT = new StandardTicket(seat.getID(), 13, seat, screening);
          seat.book(tempT);
          temp.addTicket(tempT);
        }
      }
      customer.addOrder(temp);
    }else throw new IllegalStateException("Seats are already reserved.");
  }

  @Override
  public ArrayList<Order> getOrdersForUser(String username) {
    return usersList.getByUsername(username).getOrders();
  }

  public void register(String username, String password, String email, String firstName, String lastName, String phone) throws RemoteException {
    try {
      DataBaseHandler.newUser(username, firstName, lastName, phone, email, password);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RemoteException("Error registering user", e);
    }
}
  }