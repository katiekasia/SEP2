package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import model.Model;
import model.Screening;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TransitionPageViewModel implements PropertyChangeListener,
    UnnamedPropertyChangeSubject
{
  private Model model;
  private ViewState viewState;
  private PropertyChangeSupport property;

  private StringProperty standardTickets;
  private StringProperty VIPTickets;
  private StringProperty movieTitle;
  private StringProperty length;
  private StringProperty movieGenre;
  private StringProperty movieDate;
  private StringProperty movieTime;
  private IntegerProperty roomID;
  private StringProperty totalPrice;
  private StringProperty releaseDate;
  private StringProperty standardPrice;
  private StringProperty vipPrice;


  public TransitionPageViewModel(Model model, ViewState viewState) {
    this.model = model;
    this.viewState = viewState;
    property = new PropertyChangeSupport(this);
    standardTickets = new SimpleStringProperty();
    VIPTickets = new SimpleStringProperty();
movieTitle =new SimpleStringProperty();
    movieDate = new SimpleStringProperty();
    length = new SimpleStringProperty();
    movieGenre = new SimpleStringProperty();
    movieTime = new SimpleStringProperty();
    roomID = new SimpleIntegerProperty();
totalPrice = new SimpleStringProperty();
    releaseDate= new SimpleStringProperty();
    vipPrice = new SimpleStringProperty(this,"vip");
    standardPrice = new SimpleStringProperty(this, "standard");
    this.model.addListener(this);

  }
  public void reset(){

    movieTitle.set(viewState.getSelectedScreening().movieProperty().get());
    length.set(viewState.getSelectedScreening().lengthProperty().get());
    movieGenre.set(viewState.getSelectedScreening().genreProperty().get());
    movieDate.set(viewState.getSelectedScreening().dateProperty().get());
    movieTime.set(viewState.getSelectedScreening().timeProperty().get());
    roomID.set(viewState.getSelectedScreening().roomProperty().get());
    releaseDate.set(viewState.getSelectedScreening().getReleaseDate());
    vipPrice.set("Price per ticket: " +model.getPriceForTicket(vipPrice.getName()) + " DKK");
    standardPrice.set("Price per ticket: " + model.getPriceForTicket(standardPrice.getName()) + " DKK");
    totalPrice.set("");

    standardTickets.set("");
    VIPTickets.set("");
  }

  public StringProperty vipPriceProperty()
  {
    return vipPrice;
  }

  public StringProperty standardPriceProperty()
  {
    return standardPrice;
  }

  public StringProperty getReleaseDate()
  {
    return releaseDate;
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
          double totalPriceAmount = standardTickets * model.getPriceForTicket(this.standardPrice.getName()) + vipTickets * model.getPriceForTicket(this.vipPrice.getName());
          totalPrice.set(totalPriceAmount + " DKK");
        } else {
          totalPrice.set("Limit exceeded!");
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setHeaderText("Please enter correct amount of tickets. Seats available: " + screening.getRoom().availableSeats());
          alert.showAndWait();
        }
      }catch (Exception e)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(e.getMessage());
        alert.showAndWait();
      }
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
    }catch (Exception e)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText(e.getMessage());
      alert.showAndWait();
    }
    return false;
    }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() ->{
      if (evt.getPropertyName().equals("fatalError")){
        property.firePropertyChange(evt.getPropertyName(),null,evt.getNewValue());
      }});
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }
}
