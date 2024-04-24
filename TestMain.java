import client_model.*;

import java.time.LocalDate;

public class TestMain
{
  //********Just my ideas for possible interface methods, subject for discussion
  public void reserveSeat(Seat seat, User customer,ScreeningDay day, Screening screening){
    if (screening.availableSeats() <= 0){
      throw new RuntimeException("No available seats left for this screening");
    }
    Order temp = new Order(customer.getOrders().size() + 1);
    Ticket tempT = new StandardTicket(seat.getID(), 13,seat,day,screening,customer);
    seat.book(tempT);
    temp.addTicket(tempT);
    customer.addOrder(temp);
    System.out.println("booking complete");
    System.out.println(temp + "\n" + temp.getTickets().size());
  }
  public void reserveSeats(Seat[] seats,User customer,ScreeningDay day, Screening screening ){
    if (screening.availableSeats() < seats.length){
      throw new RuntimeException("Not enough available seats left for this screening");
    }
    Order temp = new Order(customer.getOrders().size() + 1);
    for (Seat seat:seats){
      Ticket tempT = new StandardTicket(seat.getID(), 13,seat,day,screening,customer);
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
    Screening morning = new Screening(room.getNbSeats(),10,30 );
    Screening afternoon = new Screening(room.getNbSeats(),15,25);
    ScreeningDay day = new ScreeningDay(morning,afternoon,null,new SimpleDate(
        LocalDate.now()), room);
    User test = new User("saibot", "Michal", "Barczuk","89829922", "test@swa.sd");
movie.addScreeningDay(day);
    System.out.println(movie.getScreeningDays().toString());

    TestMain Interface = new TestMain();
    Interface.reserveSeat(morning.getSeat(2),test,day,morning);
    Snack snack = new Popcorn(21,"Medium");
    System.out.println(snack.getPrice());
    day.addScreening(1,afternoon);
  }
}
