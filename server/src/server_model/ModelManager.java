package server_model;



import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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


  //not sure if the user variable is the one connected here
  private User user;
  private PropertyChangeSupport propertyChangeSupport;

  public ModelManager()
  {
    this.running=false;
    this.PORT=5678;
    this.HOST ="localhost";
    this.user = null;
    this.propertyChangeSupport = new PropertyChangeSupport(this);
    screenings = new ScreeningsList();
    DummyData();
  }

  public void DummyData(){
    Movie n = new Movie(420,"ssdawd","movie","adsss");
    Room r = new Room(12,35);
    Screening screening = new Screening(10,30, LocalDate.now(),n,r);
    Seat seat = new Seat("A1", false);
    User customer= new User("Kasia", "kasia", "Olej", "7915355423", "emailsjdhh");
    Ticket ticket = new StandardTicket(seat.getID(), 124.0,seat, screening, customer,"Standard");
    Order order = new Order(0);
    order.addTicket(ticket);
    customer.addOrder(order);

    user=customer;
    screenings.addScreening(screening);
  }
  @Override public void addListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(listener);

  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }
  @Override public String getUsername()
  {
    return user.getUsername();
  }

  @Override public void logIn(User customer)
  {
    //not for this sprint
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

    @Override public ArrayList<Ticket> getAllTickets()

   {
      return user.getOrders().get(0).getTickets();
   }
    /*
    @Override public Seat getSeatByScreening(Screening screening)
    {
      return screening.getRoom().getSeat(0);
    }

   */
  @Override public Screening findScreeningBySeatId(String seatId)
  {
    for(int i=0; i<screenings.getSize(); i++)
    {
      if (screenings.getScreenings().get(i).getRoom().getSeat(i).getID().equals(seatId));
      return screenings.getScreenings().get(i);
    }
    return null;
  }

  @Override public ArrayList<Screening> getScreaningsByMovieTitle(String title)
  {
    ArrayList<Screening> result = new ArrayList<>();
    for (int i = 0; i < screenings.getSize(); i++)
    {
      if (screenings.getScreeningById(i).getMovie().getName().toUpperCase().equals(title.toUpperCase())){
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
      if (screenings.getScreeningById(i).getDate().equals(temp) || screenings.getScreeningById(i).getDate().isAfter(temp)){
        result.add(screenings.getScreeningById(i));
      }
    }
    return result;
  }

  @Override public User getUser()
  {
    return user;
  }
  @Override public void reserveSeat(Seat seat, User customer,
      Screening screening)
  {
    if (screening.getRoom().availableSeats() <= 0)
    {
      throw new RuntimeException("No available seats left for this screening");
    }
    Order temp = new Order(customer.getOrders().size() + 1);
    Ticket tempT = new StandardTicket(seat.getID(), 13, seat, screening,
        customer, "Standard");
    seat.book(tempT);
    temp.addTicket(tempT);
    customer.addOrder(temp);
    System.out.println("booking complete");
    System.out.println(temp + "\n" + temp.getTickets().size());

    updateDatabaseWithBooking(customer, tempT);
  }
  private void updateDatabaseWithBooking(User customer, Ticket ticket) {
    // Example method to illustrate saving to DB (requires actual implementation)
    try (Connection conn = DataBaseHandler.getConnection()) {
      // SQL queries to insert ticket and update seat status
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override public boolean checkSeatAvailability(int index, Screening screening)
  {
    return (screening.getRoom().getSeat(index).isAvailable());
  }

  @Override public Seat[] getAvailableSeats(Screening screening)
  {
    ArrayList<Seat> seats= new ArrayList<>();
    for(int i=0; i<screening.getRoom().getNbSeats(); i++)
    {
      if(screening.getRoom().getSeat(i).isAvailable())
      {
        seats.add(screening.getRoom().getSeat(i));
      }
    }
    return seats.toArray(new Seat[screening.getRoom().getNbSeats()]);
  }

  @Override public Seat[] getEmptySeats(Screening screening)
  {
    ArrayList<Seat> emptySeats= new ArrayList<>();
    for(int i=0; i<screening.getRoom().getNbSeats(); i++)
    {
      if(!screening.getRoom().getSeat(i).isAvailable())
      {
        emptySeats.add(screening.getRoom().getSeat(i));
      }
    }
    return emptySeats.toArray(new Seat[screening.getRoom().getNbSeats()]);
  }


  @Override public double calculateTotalPrice()
  {
    double price =0;
    for(int i=0; i<user.getOrders().size(); i++)
    {
      price +=user.getOrders().get(0).getOrderPrice();
    }
    return price;
  }

  @Override public void updateSeatToBooked(Seat seat, Ticket ticket)
  {
    seat.book(ticket);
  }

  @Override public void addOrder(Order order)
  {
    user.addOrder(order);
  }

  @Override public void logIn(String username, String password)
  {

  }

  @Override public void register(String username, String password, String email,
      String firstName, String lastName, String phone)
  {

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
       Screening screening)
  {
    if (screening.getRoom().availableSeats() < seats.length)
    {
      throw new RuntimeException(
          "Not enough available seats left for this screening");
    }
    Order temp = new Order(customer.getOrders().size() + 1);
    for (Seat seat : seats)
    {
      Ticket tempT = new StandardTicket(seat.getID(), 13, seat, screening,
          customer, "Standard");
      seat.book(tempT);
      temp.addTicket(tempT);
    }
    customer.addOrder(temp);
  }


}