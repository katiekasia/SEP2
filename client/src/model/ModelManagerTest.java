package model;

import mediator.RmiClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Array;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


  public class ModelManagerTest implements Model, PropertyChangeListener
  {
    private User user;
    private PropertyChangeSupport propertyChangeSupport;
    ArrayList<User> usersList;
    ArrayList<Screening> screeningsList;
    ArrayList<Room> roomsList;
    ArrayList<Movie> moviesList;
    ArrayList<Order> ordersList;

    public ModelManagerTest()
    {
      this.user = null;
      this.propertyChangeSupport = new PropertyChangeSupport(this);
      Dummydata();

    setUpScreenings(ordersList);
    }
    public void Dummydata()
  {
    usersList = new ArrayList<>();
    ordersList= new ArrayList<>();
    screeningsList = new ArrayList<>();
    roomsList = new ArrayList<>();
    moviesList = new ArrayList<>();
  }

    public void bookSeatByID(Screening screening, String id, Ticket ticket)
    {
      getScreening(screening).bookSeatById(id, ticket);
    }
    private void setUpScreenings(ArrayList<Order> orders)
    {
      for (Order order : orders)
      {
        for (Ticket ticket : order.getTickets())
        {
          bookSeatByID(ticket.getScreening(), ticket.getSeatID(),
              ticket);
        }
      }
    }

    @Override public void addListener(PropertyChangeListener listener)
    {
      propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override public void removeListener(PropertyChangeListener listener)
    {
      propertyChangeSupport.removePropertyChangeListener(listener);
    }

    @Override public void removeScreening(Screening screening)
    {
      screeningsList.remove(screening);
    }

    @Override public void deleteMovie(Movie movie)
    {
      try
      {
        moviesList.remove(movie);
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }

    }

    @Override public ArrayList<Movie> getAllMovies()
    {
      try
      {
        return moviesList;
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }
    }


    @Override public void deleteAccount(String username)
    {

      try
      {
        for(User user: usersList)
        {
          if(user.getUsername().equals(username))
          usersList.remove(user);
        }
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }
    }


    @Override public void addMovie(Movie movie)
    {
      try
      {
        moviesList.add(movie);
      }catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }
    }

    @Override public void changePrices()
    {

    }

    @Override public double getPriceForTicket(String type)
    {
      return 0;
    }

    @Override public void cancelOrder(Order order, User user)
    {
      try
      {
        getOrderByID(order.getOrderID(),
            getUserByUsername(user.getUsername())).cancelOrder();
      ordersList.remove(getOrderByID(order.getOrderID(), getUserByUsername(user.getUsername())));
      }

      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }

    }

    @Override public void register(User user)
    {

    }

    @Override public Order getOrderByID(int orderID, User user)
    {
      try
      {
        return getUserByUsername(user.getUsername()).getOrderByID(orderID);
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }

    }

    @Override public Screening getScreeningForView(String time, String date,
        String title, int room)
    {
      try
      {
        for (Screening screening : screeningsList)
        {
          if (screening.getTime().equals(time) && screening.isOnTheSameDay(date)
              && screening.getMovieTitle().equals(title)
              && screening.getRoomID() == room)
          {
            return screening;
          }
        }
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }
      return null;
    }

    @Override public User logIn(String username, String password)
    {
      try
      {
        for (User user : usersList)
        {

          if (user.getUsername().equals(username) && user.getPassword()
              .equals(password))
          {
            return user;
          }
        }
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }
      return null;
    }

    @Override public void updateUser(User user, String previousUsername)
    {
      try
      {
        for(User userOld: usersList)
        {
          if(userOld.getUsername().equals(previousUsername))
          {
            userOld = user;
          }
        }
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }

    }

    @Override public Order[] getAllOrders(User user)
    {
      try
      {
        if (ordersList != null){
          Order[] result = ordersList.toArray(new Order[0]);
          return result;
        }
        return null;
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }

    }

    @Override public Snack[] getSnacksFromOrder(Order order)

    {
      try
      {
        if (order.getSnacks() != null){
          Snack[] result = order.getSnacks().toArray(new Snack[0]);
          return result;
        }
        return null;
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }

    }

    @Override public Ticket[] getTicketsFromOrder(Order order)

    {
      try
      {
        if (order.getTickets() != null)
        {
          Ticket[] result = order.getTickets().toArray(new Ticket[0]);
          return result;
        }
        return null;
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }

    }
    @Override public boolean checkSeatAvailability(int index, Screening screening)
    {
      try
      {
        return screening.getSeatAvailabilty(index);
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }

    }

    @Override public boolean logInAdmin(String username, String password)
    {
      try
      {
        for (User admin : usersList)
        {
          if (admin.getUsername().equals(username) && admin.getPassword()
              .equals(password))
          {
            return true;
          }
        }
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }
      return false;
    }

    @Override public void reserveSeat(Seat seat, User customer,
        Screening screening)
    {

    }

    @Override public Seat[] getAvailableSeats(Screening screening)
    {
      try
      {
        return getScreening(screening).getAvailableSeats();
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }

    }

    @Override public Seat[] getEmptySeats(Screening screening)
    {
      try
      {
        return getScreening(screening).getEmptySeats();
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }

    }

    @Override public User getUserByUsername(String username)
    {
      try
      {
        for (User user : usersList)
        {
          if (user.getUsername().equals(username))
          {
            return user;
          }
        }
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }
      return null;
    }

    @Override public void updateSeatToBooked(Seat seat, Ticket ticket)
    {
      try
      {
        seat.book(ticket);
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }

    }

    @Override public void addOrder(Order order, User user)
    {
      try
      {
        user.addOrder(order);
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }

    }


    @Override public void addScreening(Screening screening)
    {
      try
      {
        screeningsList.add(screening);
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }

    }

    @Override public void removeByDate(SimpleDate date)
    {
      try
      {
        for (Screening screening :screeningsList){
          if(screening.getDate().equals(date)){
            screeningsList.remove(screening);
            break;
          }
        }
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }

    }

    @Override public ArrayList<Screening> getAllScreenings()
    {
      try
      {
        return screeningsList;
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }

    }

    @Override public int getNbOfScreenings()
    {
      try
      {
        return screeningsList.size();
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }

    }

    @Override public Screening getScreening(Screening screening)
    {
      try
      {
        for (Screening scr : screeningsList){
          if (scr.getId() == screening.getId()){
            return scr;
          }
        }
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }
      return null;
    }

    @Override public ArrayList<Ticket> getAllTickets(User user)
    {
      try
      {
        ArrayList<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < ordersList.size(); i++)
        {
          for (Ticket ticket : ordersList.get(i).getTickets())
          {
            if (ticket != null)
            {
              tickets.add(ticket);
            }
          }
        }
        return tickets;
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }

    }

    @Override public Ticket getTicketForView(Order order, String ID)
    {
      try
      {
        return order.getTicketForView(ID);
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }

    }


    @Override public void cancelTicketFromOrder(Ticket ticket, Order order)
    {
      try
      {
        order.removeTicket(ticket);
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }

    }

    @Override public Room getRoomById(String id)
    {
      try
      {
        int ID = Integer.parseInt(id);
        for (Room room:roomsList){
          if (room.getRoomID()== ID){
            return room;
          }
        }
      }
      catch (RuntimeException e){
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }
      return null;
    }

    @Override public void deleteSnackFromOrder(Snack snack, Order order)
    {
      try
      {
        order.removeSnack(snack);
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }

    }
    public Screening getScreeningById(int id){
      return screeningsList.get(id);
    }
    @Override public ArrayList<Screening> getScreaningsByMovieTitle(String title)
    {
      try
      {
        ArrayList<Screening> result = new ArrayList<>();
        for (int i = 0; i < screeningsList.size(); i++)
        {
          if (getScreeningById(i).getMovieTitle().toUpperCase()
              .equals(title.toUpperCase()))
          {
            result.add(getScreeningById(i));
          }
        }
        return result;
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }
    }

    @Override public ArrayList<Screening> getScreeningsByDate(LocalDate date)
    {
      try
      {
        SimpleDate temp = new SimpleDate(date);
        ArrayList<Screening> result = new ArrayList<>();
        for (int i = 0; i < screeningsList.size(); i++)
        {
          if (getScreeningById(i).isOnTheSameDay(temp) || getScreeningById(i).isAfter(temp))
          {
            result.add(getScreeningById(i));
          }
        }
        return result;
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }
    }

    @Override public ArrayList<Screening> getScreeningsByDateAndTitle(
        String title, LocalDate date)
    {
      try
      {
        SimpleDate temp = new SimpleDate(date);
        ArrayList<Screening> result = new ArrayList<>();
        for (Screening screening : screeningsList){
          if (screening.getMovieTitle().equals(title) && screening.isOnTheSameDay(temp)){
            result.add(screening);
          }
        }
        return result;
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }
    }

    @Override public ArrayList<Order> getOrdersForUser(String username)
    {
      try
      {
        for(User user: usersList)
        {
          if (user.getUsername().equals(username))
          {
             return user.getOrders();
        }
      }
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }
      return null;
    }

    @Override public Movie getMovieForView(String title)
    {
      try
      {
        for (Movie movie : moviesList)
        {
          if (movie.getName().equals(title))
          {
            return movie;
          }
        }
      }
      catch (RuntimeException e)
      {
        propertyChangeSupport.firePropertyChange("fatalError", null,
            e.getMessage());
        throw e;
      }
      return null;
    }
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

      }
    }
    @Override public void changePrices(ArrayList<Double> newPrices)
    {
    }

    @Override public Order reserveSeats(Seat[] seats, User customer,
        Screening screening, int nbVIP)
    {

          return null;
    }

    @Override public double getPriceForSize(String snackType, String size)
    {
    return 0;
    }


    @Override public void addSnackToOrder(String snackType, int amount,
        Order order, User user, String size)
    {

    }
    @Override public void changePrice(String item, double newPrice)
    {

    }
    @Override public void upgradeTicket(Ticket ticket, Order order, User user)
    {

    }
    @Override public void downgradeTicket(Ticket ticket, Order order, User user)
    {
    }


  }





