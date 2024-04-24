package client_model;

import client_model.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class ModelManager implements Model
{
  private PropertyChangeSupport propertyChangeSupport;

  @Override public void addListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);

  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }

  @Override public void bookTicket(Ticket ticket, User customer)
  {

  }

  @Override public void logIn(User customer)
  {

  }

  @Override public void cancelTicket(Ticket ticket)
  {

  }
  @Override public void reserveSeat(Seat seat, User customer,Screening screening){
    if (screening.getRoom().availableSeats() <= 0){
      throw new RuntimeException("No available seats left for this screening");
    }
    Order temp = new Order(customer.getOrders().size() + 1);
    Ticket tempT = new StandardTicket(seat.getID(), 13,seat,screening,customer);
    seat.book(tempT);
    temp.addTicket(tempT);
    customer.addOrder(temp);
    System.out.println("booking complete");
    System.out.println(temp + "\n" + temp.getTickets().size());
  }
  @Override public void reserveSeats(Seat[] seats,User customer,ScreeningDay day, Screening screening ){
    if (screening.getRoom().availableSeats() < seats.length){
      throw new RuntimeException("Not enough available seats left for this screening");
    }
    Order temp = new Order(customer.getOrders().size() + 1);
    for (Seat seat:seats){
      Ticket tempT = new StandardTicket(seat.getID(), 13,seat,screening,customer);
      seat.book(tempT);
      temp.addTicket(tempT);
    }
    customer.addOrder(temp);
  }
}