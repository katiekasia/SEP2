package viewmodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Screening;
import model.Ticket;
import model.User;

public class ViewState
{
  private SimpleScreeningView selectedScreening;
  private User user;
  private SimpleTicketView selectedTicket;
  private SimpleOrderView selectedOrder;
  private SimpleSnackView selectedSnack;
  private SimpleIntegerProperty numberOfStandardTickets = new SimpleIntegerProperty(
      0);
  private SimpleIntegerProperty numberOfVIPTickets = new SimpleIntegerProperty(
      0);


  public ViewState()
  {

  }
  public void logOut(){
    user = null;
    selectedScreening = null;
    selectedOrder = null;
    selectedTicket = null;
    selectedSnack = null;
    numberOfStandardTickets.set(0);
    numberOfVIPTickets.set(0);
  }

  public void setSelectedOrder(SimpleOrderView selectedOrder)
  {
    this.selectedOrder = selectedOrder;
  }

  public SimpleSnackView getSelectedSnack()
  {
    return selectedSnack;
  }

  public void setSelectedSnack(SimpleSnackView selectedSnack)
  {
    this.selectedSnack = selectedSnack;
  }


  public SimpleOrderView getSelectedOrder()
  {
    return selectedOrder;
  }

  public SimpleScreeningView getSelectedScreening()
  {
    return selectedScreening;
  }

  public SimpleTicketView getSelectedTicket()
  {
    return selectedTicket;
  }

  public void setSelectedTicket(SimpleTicketView selectedTicket)
  {
    this.selectedTicket = selectedTicket;
  }

  public User getUser()
  {
    return user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }
  public StringProperty usernameProperty(){
    return new SimpleStringProperty(user.getUsername());
  }

  public StringProperty nameProperty()
  {
    return new SimpleStringProperty(user.getFstName());
  }

  public void setSelectedScreening(SimpleScreeningView screening)
  {
    this.selectedScreening = screening;
  }

  public int getNumberOfStandardTickets()
  {
    return numberOfStandardTickets.get();
  }

  public SimpleIntegerProperty numberOfStandardTicketsProperty()
  {
    return numberOfStandardTickets;
  }

  public void setNumberOfStandardTickets(int numberOfStandardTickets)
  {
    this.numberOfStandardTickets.set(numberOfStandardTickets);
  }

  public int getNumberOfVIPTickets()
  {
    return numberOfVIPTickets.get();
  }

  public SimpleIntegerProperty numberOfVIPTicketsProperty()
  {
    return numberOfVIPTickets;
  }

  public void setNumberOfVIPTickets(int numberOfVIPTickets)
  {
    this.numberOfVIPTickets.set(numberOfVIPTickets);
  }
}
