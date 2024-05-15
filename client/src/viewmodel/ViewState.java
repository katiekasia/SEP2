package viewmodel;

import model.Screening;
import javafx.beans.property.SimpleIntegerProperty;
import model.User;

public class ViewState
{
  private SimpleScreeningView selectedScreening;
  private User user;
  private SimpleTicketView selectedTicket;
  private SimpleIntegerProperty numberOfStandardTickets = new SimpleIntegerProperty(
      0);
  private SimpleIntegerProperty numberOfVIPTickets = new SimpleIntegerProperty(
      0);

  public ViewState()
  {
    user = new User("Lorem", "John", "Doe", "333", "mail@sw.sw");
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

  public Screening getScreeningFromView()
  {
    SimpleScreeningView view = getSelectedScreening(); // Assumes this returns SimpleScreeningView
    return view.getScreening();
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
