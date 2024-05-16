package model;

import mediator.RmiClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class ModelManager implements Model, PropertyChangeListener
{
  private String HOST;
  private int PORT;
  private boolean running;


  //not sure if the user variable is the one connected here
  private User user;
  private PropertyChangeSupport propertyChangeSupport;
  private RmiClient client;

  public ModelManager(RmiClient client)
  {
    this.running=false;
    this.PORT=5678;
    this.HOST ="localhost";
    this.user = null;
    this.propertyChangeSupport = new PropertyChangeSupport(this);
    this.client = client;
    client.addListener(this);
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
    client.cancelOrder(order);
  }

  @Override public User logIn(String username, String password)
      throws RemoteException
  {
    try
    {
      return client.logIn(username, password);
    }
  catch(Exception e)
  {
    throw e;
  }
  }

  @Override public void connect()
  {
    client.connect();
  }

  @Override public void disconnect()
  {
    client.disconnect();
  }

  @Override public int getPort()
  {
    return client.getPort();
  }

  @Override public void setPort(int port)
  {
    this.PORT = port;
  }

  @Override public Order[] getAllOrders(User user) throws RemoteException
  {
   return client.getAllOrders(user);
  }

  @Override public Snack[] getSnacksFromOrder(Order order)
      throws RemoteException
  {
   return client.getSnacksFromOrder(order);
  }

  @Override public Ticket[] getTicketsFromOrder(Order order)
      throws RemoteException
  {
    return client.getTicketsFromOrder(order);
  }



  @Override public String getUsername()
  {
    return client.getUsername();
  }

  @Override public String getHost()
  {
    return client.getHost();
  }

  @Override public void reserveSeat(Seat seat, User customer,
      Screening screening)
  {
    try
    {
      client.reserveSeat(seat, customer, screening);

    }catch (Exception e){
      e.printStackTrace();
    }
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
    try
    {
      return client.checkSeatAvailability(index,screening);
    }catch (Exception e){
      e.printStackTrace();
    }
    return false;
  }

  @Override public Seat[] getAvailableSeats(Screening screening)
  {
    try
    {
      client.getAvailableSeats(screening);
    }catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public Seat[] getEmptySeats(Screening screening)
  {
    try
    {
      client.getEmptySeats(screening);
    }catch (Exception e){
      e.printStackTrace();
    }
    return null;
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
try
{
  client.updateSeatToBooked(seat,ticket);
}catch (Exception e){
  e.printStackTrace();
}
  }

  @Override public void addOrder(Order order)
  {
    try
    {
      client.addOrder(order);
    }catch (Exception e){
      e.printStackTrace();
    }
  }


    @Override public void register(String username, String password, String email,
      String firstName, String lastName, String phone)
  {
    try
    {
      client.register(username, password, email, firstName, lastName, phone);
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override public void addScreening(Screening screening)
  {
    try
    {
      client.addScreening(screening);
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override public void removeScreening(Screening screening)
  {
    try
    {
      client.removeScreening(screening);
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override public void removeByDate(SimpleDate date)
  {
    try
    {
      client.removeByDate(date);
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override public ArrayList<Screening> getAllScreenings()
  {
    try
    {
      return client.getAllScreenings();
    }catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public int getNbOfScreenings()
  {
    try
    {
      return client.getNbOfScreenings();
    }catch (Exception e){
      e.printStackTrace();
    }
    return 0;
  }

  @Override public Screening getScreening(Screening screening)
  {
    try
    {
     return client.getScreening(screening);
    }catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public ArrayList<Ticket> getAllTickets()
  {
    System.out.println("error here");
    try
    {
      return client.getAllTickets();
    }catch (NullPointerException e){
      System.out.println("MAYBE FIX LATER");
      //TODO
    }catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public User getUser()
  {
    try
    {
      return client.getUser();
    }catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public Screening findScreeningBySeatId(String seatId)
  {
    try
    {
      return client.findScreeningBySeatId(seatId);
    }catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public ArrayList<Screening> getScreaningsByMovieTitle(String title)
  {
    try
    {
      return client.getScreaningsByMovieTitle(title);
    }catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public ArrayList<Screening> getScreeningsByDate(LocalDate date)
  {
    try
    {
      return client.getScreeningsByDate(date);
    }catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public void reserveSeats(Seat[] seats, User customer,
      Screening screening)
  {
try
{
  client.reserveSeats(seats, customer, screening);
}catch (Exception e){
  e.printStackTrace();
}
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    switch (evt.getPropertyName()){
      case "reserve seats":
        propertyChangeSupport.firePropertyChange("reserveSeats", null, evt.getNewValue());
        break;
      case "reserve seat":
        propertyChangeSupport.firePropertyChange("reserveSeat", null, evt.getNewValue());
        break;
      case "update seat to booked":
        propertyChangeSupport.firePropertyChange("updateSeatToBooked", null, evt.getNewValue());
        break;
      case "add order":
        propertyChangeSupport.firePropertyChange("addOrder", null, evt.getNewValue());
        break;
      case "add screening":
        propertyChangeSupport.firePropertyChange("addScreening",null,evt.getNewValue());
        break;
      case "remove screening":
        propertyChangeSupport.firePropertyChange("removeScreening", null, evt.getNewValue());
        break;
      case "remove by date":
        propertyChangeSupport.firePropertyChange("removeByDate", null, evt.getNewValue());
        break;
    }
  }

}
