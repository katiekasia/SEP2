package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Model;
import model.Order;
import model.Snack;
import model.Ticket;

import java.util.Optional;

public class OrderDetailsViewModel
{
  private Model model;
  private ViewState viewState;
  private ObservableList<SimpleTicketView> tickets;
  private ObservableList<SimpleSnackView> snacks;
  private ObjectProperty<SimpleSnackView> selectedSnack;
  private ObjectProperty<SimpleTicketView> selectedTicket;
  private boolean snackSelected;
  private boolean ticketSelected;

  public OrderDetailsViewModel(Model model, ViewState viewState){
    this.model = model;
    this.viewState = viewState;
    tickets = FXCollections.observableArrayList();
    snacks = FXCollections.observableArrayList();
    ticketSelected = false;
    snackSelected = false;

    selectedSnack = new SimpleObjectProperty<>();
    selectedTicket = new SimpleObjectProperty<>();
  }

  public void loadFromModel(){

    snacks.clear();
    tickets.clear();
    Ticket[] orderTickets = model.getTicketsFromOrder(model.getOrderByID(viewState.getSelectedOrder().orderIDProperty().get(),
        viewState.getUser()));
    System.out.println(orderTickets.length);
    Snack[] orderSnacks = model.getSnacksFromOrder(model.getOrderByID(viewState.getSelectedOrder().orderIDProperty().get(),
        viewState.getUser()));
    for (Ticket ticket : orderTickets){
      SimpleTicketView simpleTicketView = new SimpleTicketView(ticket);
      tickets.add(simpleTicketView);
    }
    for (Snack snack : orderSnacks){
      SimpleSnackView simpleSnackView = new SimpleSnackView(snack);
      snacks.add(simpleSnackView);
    }
ticketSelected = false;
    snackSelected = false;
  }
  public void setTickets(ObservableList<SimpleTicketView> property){
    property.setAll(tickets);
  }
  public void bindTickets(ObservableList<SimpleTicketView> property){
    tickets.addListener((ListChangeListener<? super SimpleTicketView>) c -> {
      property.setAll(tickets);
    });
  }

  public void setSnackSelected(boolean snackSelected)
  {
    this.snackSelected = snackSelected;
  }

  public void setTicketSelected(boolean ticketSelected)
  {
    this.ticketSelected = ticketSelected;
  }

  public void setSnacks(ObservableList<SimpleSnackView> property){
    property.setAll(snacks);
  }
  public void bindSnacks(ObservableList<SimpleSnackView> property){
    snacks.addListener((ListChangeListener<? super SimpleSnackView>) c -> {
      property.setAll(snacks);
    });
  }
  public void setSelected(){
    if (viewState.getSelectedTicket() != null)
    {
      selectedTicket.set(viewState.getSelectedTicket());
    }
    if (viewState.getSelectedSnack() != null)
    {
      selectedSnack.set(viewState.getSelectedSnack());
    }
    System.out.println(viewState.getSelectedTicket());
  }
  public boolean ticketSelected(){
    return ticketSelected ;
  }
  public boolean snackSelected(){
    return snackSelected ;
  }
  public ViewState getViewState(){return viewState;}
  private boolean confirmation(){
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Are you sure you wish to delete ticket: " + selectedTicket.get().ticketTypeProperty().get() + "?");
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }
  public void upgradePressed(){
    Ticket[] tickets = model.getTicketsFromOrder(model.getOrderByID(viewState.getSelectedOrder().orderIDProperty().get(),
        viewState.getUser()));
    Order order = model.getOrderByID(viewState.getSelectedOrder().orderIDProperty().get(), viewState.getUser());
    for (Ticket ticket : tickets){
      if (ticket.getSeat().getID().equals(selectedTicket.get().getSeatID())){
        model.upgradeTicket(ticket, order, viewState.getUser());
        ticketSelected = false;
        loadFromModel();
      }
    }
  }
  public void downgradePressed(){
    Ticket[] tickets = model.getTicketsFromOrder(model.getOrderByID(viewState.getSelectedOrder().orderIDProperty().get(),
        viewState.getUser()));
    Order order = model.getOrderByID(viewState.getSelectedOrder().orderIDProperty().get(), viewState.getUser());
    for (Ticket ticket : tickets){
      if (ticket.getSeat().getID().equals(selectedTicket.get().getSeatID())){
        model.downgradeTicket(ticket, order, viewState.getUser());
        ticketSelected = false;
        loadFromModel();
      }
    }
  }
  public void cancelTicketPressed(){
    Ticket[] tickets = model.getTicketsFromOrder(model.getOrderByID(viewState.getSelectedOrder().orderIDProperty().get(),
        viewState.getUser()));
    Order order = model.getOrderByID(viewState.getSelectedOrder().orderIDProperty().get(),
        viewState.getUser());
    if (confirmation())
    {
      for (Ticket ticket : tickets)
      {
        if (ticket.getSeat().getID().equals(selectedTicket.get().getSeatID()))
        {
          model.cancelTicketFromOrder(ticket, order);
          ticketSelected = false;
        }
      }
      loadFromModel();
    }
  }
  public void deleteSnackPressed(){
    Snack[] orderSnacks = model.getSnacksFromOrder(model.getOrderByID(viewState.getSelectedOrder().orderIDProperty().get(),
        viewState.getUser()));
    Order order = model.getOrderByID(viewState.getSelectedOrder().orderIDProperty().get(),
        viewState.getUser());
    for (Snack snack : orderSnacks){
      if (selectedSnack.get().priceProperty().get().equals(snack.getPrice()) &&
      selectedSnack.get().sizeProperty().get().equals(snack.getSize())&&
      selectedSnack.get().typeProperty().get().equals(snack.getType())){
        model.deleteSnackFromOrder(snack, order);
        snackSelected = false;
      }
    }

    loadFromModel();
  }


  public String getTime()
  {
    return viewState.getSelectedScreening().getTime();
  }
  public String getMovie()
  {
    return viewState.getSelectedScreening().getMovie();
  }
  public String getDate()
  {
    return viewState.getSelectedScreening().getDate();
  }
  public String getOrderID()
  {
    return viewState.getSelectedOrder().orderIDProperty().asString().getValue();
  }
}
