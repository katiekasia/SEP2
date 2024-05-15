package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Screening;
import model.Ticket;
import model.User;

public class SimpleTicketView
{
  private StringProperty seatID;
  private User user;
  private StringProperty time;
  private StringProperty ticketType;
  private StringProperty date;
  private StringProperty movie;
  private Ticket ticket;
  public SimpleTicketView(Ticket ticket, User user)
    {
      this.user = user;
      this.ticket = ticket;
      time = new SimpleStringProperty(ticket.getScreening().getTime());
      date = new SimpleStringProperty(ticket.getScreening().getDate().toString());
      movie = new SimpleStringProperty(ticket.getScreening().getMovie().getName());
//      seatID = new SimpleStringProperty(user.getOrders().get(0).getTickets().get(0).getSeat().getID());
//      ticketType= new SimpleStringProperty(user.getOrders().get(0).getTickets().get(0).getTicketType()); TODO
      seatID = new SimpleStringProperty("lorem");
      ticketType= new SimpleStringProperty("Ipsum");
    }
  public void setSeatID(String  seatID) {
    this.seatID.set(seatID);
  }
  public StringProperty seatIDProperty() {
    return seatID;
  }
  public String getSeatID() {
    return seatID.get();
  }
  public User getUser()
  {
    return user;
  }
  public StringProperty ticketTypeProperty() {
    return ticketType;
  }
  public void setTicketType(String type) {
    this.ticketType.set(type);
  }
}
