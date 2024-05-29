package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Model;
import model.Order;
import model.Snack;
import model.Ticket;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Optional;

public class TicketConfirmationViewModel implements PropertyChangeListener,
    UnnamedPropertyChangeSubject
  {
    private Model model;
    private ViewState viewState;
    private PropertyChangeSupport property;
    private ObservableList<SimpleTicketView> tickets;
    private ObservableList<SimpleSnackView> snacks;
    private ObjectProperty<SimpleSnackView> selectedSnack;
    private ObjectProperty<SimpleTicketView> selectedTicket;
    private StringProperty upgradeCost;
    private boolean snackSelected;
    private boolean ticketSelected;
    private boolean isCurrent;


    public TicketConfirmationViewModel(Model model, ViewState viewState)

    {
      this.model = model;

      this.property = new PropertyChangeSupport(this);
      this.model.addListener(this);
isCurrent = false;
      ticketSelected = false;
      snackSelected = false;

      this.viewState = viewState;
      this.tickets = FXCollections.observableArrayList();
      snacks = FXCollections.observableArrayList();
      selectedSnack = new SimpleObjectProperty<>();
      this.selectedTicket = new SimpleObjectProperty<>();
      upgradeCost = new SimpleStringProperty();
    }

    public StringProperty upgradeCostProperty()
    {
      return upgradeCost;
    }

    public void loadFromModel(){

      snacks.clear();
      tickets.clear();
      Ticket[] orderTickets = model.getTicketsFromOrder(model.getOrderByID(viewState.getSelectedOrder().orderIDProperty().get(),
          viewState.getUser()));
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
      upgradeCost.set("+ " + (model.getPriceForTicket("vip") - model.getPriceForTicket("standard")) + " DKK");
    }

    public void setCurrent(boolean current)
    {
      isCurrent = current;
    }

    public void setSnackSelected(boolean snackSelected)
    {
      this.snackSelected = snackSelected;
    }

    public void setTicketSelected(boolean ticketSelected)
    {
      this.ticketSelected = ticketSelected;
    }

    public void bindSnacks(ObservableList<SimpleSnackView> property){
      snacks.addListener(
          (ListChangeListener<? super SimpleSnackView>) c -> {
            property.setAll(snacks);
          });
    }
    public void binTickets(ObservableList<SimpleTicketView> propery)
    {
      tickets.addListener(
          (ListChangeListener<? super SimpleTicketView>) c -> {
            propery.setAll(tickets);
          });
    }
    public void setTickets(ObservableList<SimpleTicketView> property)
    {
      property.setAll(tickets);
    }
    public void setSnacks(ObservableList<SimpleSnackView> property){
      property.setAll(snacks);
    }
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
    public boolean ticketSelected(){
      return ticketSelected ;
    }
    public boolean snackSelected(){
      return snackSelected ;
    }
    public ViewState getViewState()
    {
      return viewState;
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
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
      Platform.runLater(() ->{
        if (evt.getPropertyName().equals("fatalError") && isCurrent){
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

