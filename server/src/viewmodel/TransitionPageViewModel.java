package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import model.Model;
import model.Screening;
import model.Seat;
import model.User;

public class TransitionPageViewModel
{
  private Model model;
  private ViewState viewState;

  private StringProperty standardTickets;
  private StringProperty VIPTickets;
  private StringProperty movieTitle;
  private StringProperty length;
  private StringProperty movieGenre;
  private StringProperty movieDate;
  private StringProperty movieTime;
  private IntegerProperty roomID;
  private StringProperty totalPrice;


  public TransitionPageViewModel(Model model, ViewState viewState) {
    this.model = model;
    this.viewState = viewState;
    standardTickets = new SimpleStringProperty();
    VIPTickets = new SimpleStringProperty();
movieTitle =new SimpleStringProperty();
    movieDate = new SimpleStringProperty();
    length = new SimpleStringProperty();
    movieGenre = new SimpleStringProperty();
    movieTime = new SimpleStringProperty();
    roomID = new SimpleIntegerProperty();
totalPrice = new SimpleStringProperty();

  }
  public void reset(){

    movieTitle.set(viewState.getSelectedScreening().movieProperty().get());
    length.set(viewState.getSelectedScreening().lengthProperty().get());
    movieGenre.set(viewState.getSelectedScreening().genreProperty().get());
    movieDate.set(viewState.getSelectedScreening().dateProperty().get());
    movieTime.set(viewState.getSelectedScreening().timeProperty().get());
    roomID.set(viewState.getSelectedScreening().roomProperty().get());
    standardTickets.set("");
    VIPTickets.set("");
  }



  public StringProperty movieTitleProperty()
  {
    return movieTitle;
  }

  public StringProperty movieTimeProperty()
  {
    return movieTime;
  }

  public StringProperty movieGenreProperty()
  {
    return movieGenre;
  }

  public StringProperty movieDateProperty()
  {
    return movieDate;
  }

  public StringProperty lengthProperty()
  {
    return length;
  }

  public IntegerProperty roomIDProperty()
  {
    return roomID;
  }

  public ViewState getViewState()
  {
    return viewState;
  }

public void bindStandard(StringProperty property){
property.bindBidirectional(standardTickets);
}
public void bindVIP(StringProperty property){
    property.bindBidirectional(VIPTickets);
}
public void bindPrice(StringProperty property){
    property.bindBidirectional(totalPrice);
}
  private int parseTicketNumber(String text) {
    try {
      return Integer.parseInt(text);
    } catch (NumberFormatException e) {
      return 0;
    }
  }

  public void updateTotal() {
      try
      {
        int standardTickets = parseTicketNumber(this.standardTickets.get());
        int vipTickets = parseTicketNumber(VIPTickets.get());
        int total = standardTickets + vipTickets;
        Screening screening = model.getScreeningForView(viewState.getSelectedScreening().getTime(), viewState.getSelectedScreening().getDate(),
            viewState.getSelectedScreening().getMovie(), viewState.getSelectedScreening().getRoom());
        if (total <= screening.getRoom().availableSeats()) {
          viewState.setNumberOfStandardTickets(standardTickets);
          viewState.setNumberOfVIPTickets(vipTickets);
          int totalPriceAmount = standardTickets * 120 + vipTickets * 170;
          totalPrice.set(String.valueOf(totalPriceAmount) + " DKK");
        } else {
          totalPrice.set("Limit exceeded!");
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setHeaderText("Please enter correct amount of tickets. Seats available: " + screening.getRoom().availableSeats());
          alert.showAndWait();
        }
      }catch (Exception e){e.printStackTrace();}
  }
  public boolean isValidTicketEntry() {
    try
    {
      int standardTickets = parseTicketNumber(this.standardTickets.get());
      int vipTickets = parseTicketNumber(VIPTickets.get());
      int totalTickets = standardTickets + vipTickets;

      Screening screening = model.getScreeningForView(viewState.getSelectedScreening().getTime(), viewState.getSelectedScreening().getDate(),
          viewState.getSelectedScreening().getMovie(), viewState.getSelectedScreening().getRoom());
      return totalTickets > 0 && totalTickets <= screening.getRoom().availableSeats();
    }catch (Exception e){e.printStackTrace();}
    return false;
    }

}
