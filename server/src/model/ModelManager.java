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
  private PricesManager pricesManager;
  private Admin admin;

  //not sure if the user variable is the one connected here

  private PropertyChangeSupport propertyChangeSupport;


  public ModelManager()
  {
    this.HOST = "localhost";
    this.pricesManager = new PricesManager();
    this.propertyChangeSupport = new PropertyChangeSupport(this);
    this.admin = new Admin(""); //TODO

    try
    {
      usersList = new UsersList(DataBaseHandler.getAllCustomers());
      screenings = new ScreeningsList(DataBaseHandler.getAllScreenings());

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
        screenings.bookSeatByID(ticket.getScreening(),ticket.getSeatID(),ticket);
      }
    }
  }

  @Override  public void updateUser(User user, String previousUsername) throws RemoteException
  {
    try
    {
      System.out.println("calling method to update the info in database");
      DataBaseHandler.updateUser(user, previousUsername);
      //System.out.println("calling method to update the info in database");
      usersList = new UsersList(DataBaseHandler.getAllCustomers());
    }
    catch(SQLException e)
    {
      e.printStackTrace();
    }
  }
  @Override public Screening getScreeningForView(String time, String date,String title, int room){
    return screenings.getScreeningForView(time, date, title, room);
  }
  public Ticket getTicketForView(Order order, String ID){
 return order.getTicketForView(ID);
  }
  @Override public User logIn(String username, String password)
  {
   try
   {
     return usersList.logIn(username, password);
   }catch (IllegalArgumentException e){
     logInAdmin(username, password);
   }
   return null;
  }

  @Override public double getPriceForSize(String snackType, String size)
  {
    switch (snackType){
      case "popcorn":
        return pricesManager.getPriceForSize(pricesManager.getPopcornPrice(), size);
      case "nachos":
        return pricesManager.getPriceForSize(pricesManager.getNachosPrice(),size);
    }
    return 0;
  }

  private Admin logInAdmin(String username, String password){
    if (username.equals("Admin") && password.equals(admin.getPassword())){
      return admin;
    }
    throw new IllegalArgumentException("No user with such credentials found");
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
@Override public void upgradeTicket(Ticket ticket, Order order){
    order.upgrade(ticket, pricesManager.getVipTicketPrice());
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
  order.downgrade(ticket, pricesManager.getStandardTicketPrice());
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
    if (screening.getNbOfAvailableSeats() < seats.length)
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
          Ticket tempT = new VIPTicket(seat.getID(),
              pricesManager.getVipTicketPrice(), seat, screening);
          seat.book(tempT);
          temp.addTicket(tempT);
          nbVip--;
        }
        else
        {
          Ticket tempT = new StandardTicket(seat.getID(),
              pricesManager.getStandardTicketPrice(), seat, screening);
          seat.book(tempT);
          temp.addTicket(tempT);
        }
      }
      customer.addOrder(temp);
    }else throw new IllegalStateException("Seats are already reserved.");
  }

  @Override
  public ArrayList<Order> getOrdersForUser(String username) {
    return usersList.getOrdersForUser(username);
  }

  public void register(String username, String password, String email, String firstName, String lastName, String phone) throws RemoteException {
    try {
      DataBaseHandler.newUser(username, firstName, lastName, phone, email, password);
      usersList = new UsersList(DataBaseHandler.getAllCustomers());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RemoteException("Error registering user", e);
    }
}
  }