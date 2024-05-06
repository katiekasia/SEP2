package server_model;



import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class ModelManager implements Model
{
  private String HOST;
  private int PORT;
  private boolean running;


  //not sure if the user variable is the one connected here
  private User user;
  private PropertyChangeSupport propertyChangeSupport;

  public ModelManager()
  {
    this.running=false;
    this.PORT=5678;
    this.HOST ="localhost";
    this.user = null;
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);

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

  @Override public void reserveSeat(Seat seat, User customer,
      Screening screening)
  {
    if (screening.getRoom().availableSeats() <= 0)
    {
      throw new RuntimeException("No available seats left for this screening");
    }
    Order temp = new Order(customer.getOrders().size() + 1);
    Ticket tempT = new StandardTicket(seat.getID(), 13, seat, screening,
        customer);
    seat.book(tempT);
    temp.addTicket(tempT);
    customer.addOrder(temp);
    System.out.println("booking complete");
    System.out.println(temp + "\n" + temp.getTickets().size());
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
          customer);
      seat.book(tempT);
      temp.addTicket(tempT);
    }
    customer.addOrder(temp);
  }
}