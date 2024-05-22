package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Ticket;

public class SimpleTicketView
{
  private StringProperty seatID;
  private StringProperty price;
  private StringProperty time;
  private StringProperty ticketType;
  private StringProperty date;
  private StringProperty movie;

  public SimpleTicketView(Ticket ticket)
    {
      price = new SimpleStringProperty(ticket.getTicketPrice() + "$");
      time = new SimpleStringProperty(ticket.getScreening().getTime());
      date = new SimpleStringProperty(ticket.getScreening().getDate().toString());
      movie = new SimpleStringProperty(ticket.getScreening().getMovie().getName());
     seatID = new SimpleStringProperty(ticket.getSeat().getID());
     ticketType= new SimpleStringProperty(ticket.getTicketType());

    }

  public String getDate()
  {
    return date.get();
  }

  public StringProperty timeProperty()
  {
    return time;
  }

  public StringProperty priceProperty()
  {
    return price;
  }

  public StringProperty movieProperty()
  {
    return movie;
  }

  public StringProperty dateProperty()
  {
    return date;
  }

  public String getMovie()
  {
    return movie.get();
  }

  public String getTime()
  {
    return time.get();
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
  public StringProperty ticketTypeProperty() {
    return ticketType;
  }
  public void setTicketType(String type) {
    this.ticketType.set(type);
  }
}
