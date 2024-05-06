package client_model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestMain
{
  //********Just my ideas for po  ssible interface methods, subject for discussion
  public void reserveSeat(Seat seat, User customer,Screening screening){
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
  public void reserveSeats(Seat[] seats,User customer,ScreeningDay day, Screening screening ){
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

  //*************************Test area for the model**************************
  public static void main(String[] args)
  {
    Movie movie = new Movie(150,"sad","Pianist","documentary");
    Room room = new Room(12,40);
    LocalDate now = LocalDate.now();
    Screening morning = new Screening(10,10,now,movie,room);
    Screening afternoon = new Screening(15,15,now,movie,room);
    User test = new User("saibot", "Michal", "Barczuk","89829922", "test@swa.sd");

    TestMain Interface = new TestMain();
    Interface.reserveSeat(morning.getRoom().getSeat(1), test,morning);
    Snack snack = new Popcorn(21,"Medium");
    System.out.println(snack.getPrice());

    // DATABASE CONNECTIVITY TEST
    try {
      Connection connection = DataBaseHandler.getConnection();
      if (connection != null) {
        System.out.println("Database connection successful!");

        // Close the connection to release resources
        DataBaseHandler.closeConnection();
      } else {
        System.out.println("Failed to establish database connection.");
      }
    } catch (SQLException e) {
      // Handle any SQL exceptions
      System.out.println("Database connection error: " + e.getMessage());
    }
    // DATABASE CONNECTIVITY TEST

  }
}
