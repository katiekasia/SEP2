package viewmodel;

import javafx.beans.property.SimpleIntegerProperty;
import model.Screening;

public class ViewState
{
  private SimpleScreeningView selectedScreening;
  private SimpleIntegerProperty numberOfStandardTickets = new SimpleIntegerProperty(
      0);
  private SimpleIntegerProperty numberOfVIPTickets = new SimpleIntegerProperty(
      0);

  public SimpleScreeningView getSelectedScreening()
  {
    return selectedScreening;
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
