package viewmodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.User;
/**
 * Class for storing UI states
 *
 * holds references to selected objects(screenings,tickets,orders,snacks,movies and user)
 * Tracks ticket counting
 * Manages states

 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class ViewState
{
  private SimpleScreeningView selectedScreening;
  private User user;
  private SimpleTicketView selectedTicket;
  private SimpleOrderView selectedOrder;
  private SimpleSnackView selectedSnack;
  private SimpleMovieView selectedMovie;
  private SimpleIntegerProperty numberOfStandardTickets = new SimpleIntegerProperty(
      0);
  private SimpleIntegerProperty numberOfVIPTickets = new SimpleIntegerProperty(
      0);

  /**
   * Initialises an instance of the class
   */
  public ViewState()
  {

  }

  /**
   * clears data
   * resets ticket counting
   */
  public void logOut(){
    user = null;
    selectedScreening = null;
    selectedOrder = null;
    selectedTicket = null;
    selectedSnack = null;
    numberOfStandardTickets.set(0);
    numberOfVIPTickets.set(0);
  }

  /**
//These setters update the state of the ViewState object,
// allowing the view to react to changes,
// such as updating the UI to reflect the current selection or user

   Sets the selected order for the current session.
   *
   * @param selectedOrder The SimpleOrderView object representing the selected order.
   *                      It contains information about the order to be set.
   */
  public void setSelectedOrder(SimpleOrderView selectedOrder)
  {
    this.selectedOrder = selectedOrder;
  }
  /**
   * Retrieves the selected snack
   *
   * @return The SimpleSnackView object representing the selected snack.
   *         It contains information about the snack that was previously set.
   */
  public SimpleSnackView getSelectedSnack()
  {
    return selectedSnack;
  }
  /**
   Sets the selected snack
   *
   * @param selectedSnack The SimpleSnackView object representing the selected order.
   *                      It contains information about the order to be set.
   */
  public void setSelectedSnack(SimpleSnackView selectedSnack)
  {
    this.selectedSnack = selectedSnack;
  }
  /**
   * Retrieves the selected order
   *
   * @return The SimpleOrderView object representing the selected order.
   *         It contains information about the snack that was previously set.
   */
  public SimpleOrderView getSelectedOrder()
  {
    return selectedOrder;
  }

  /**
   * same logic applies to other setters and getters
   * @return
   */
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
  /**
   * Generates a StringProperty representing the first name of the user.
   *
   * @return The StringProperty object representing the first name of the user.
   *         It allows observing and binding to the first name property.
   */
  public StringProperty nameProperty()
  {
    return new SimpleStringProperty(user.getFstName());
  }

  public void setSelectedScreening(SimpleScreeningView screening)
  {
    this.selectedScreening = screening;
  }
  public void setSelectedMovie(SimpleMovieView movie)
  {
    this.selectedMovie= movie;
  }

  public SimpleMovieView getSelectedMovie()
  {
    return selectedMovie;
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
