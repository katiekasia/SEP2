package client_model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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

  @Override public void addListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);

  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }

  @Override public void logIn(User customer)
  {
    //not for this sprint
  }

  @Override public void connect()
  {
    //
  }

  @Override public void disconnect()
  {
    //
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
    return new Seat[0];
  }

  @Override public boolean holdSeat(Seat seat, User customer,
      Screening screening)
  {
    return false;
  }

  @Override public void releaseHoldSeat(Seat seat, User customer,
      Screening screening)
  {

  }

  @Override public double calculateTotalPrice(Seat[] seats, Screening screening)
  {
    return 0;
  }

  @Override public void updateSeatStatus(Seat seat)
  {

  }

  @Override public void reserveSeats(Seat[] seats, User customer,
      ScreeningDay day, Screening screening)
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