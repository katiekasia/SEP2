package viewmodel;

import javafx.scene.control.Alert;
import model.Model;
import model.Screening;
import model.Ticket;
import model.User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class TicketConfirmationViewModel
  {
    private Model model;
    private ViewState viewState;
    private ObservableList<SimpleTicketView> tickets;
    private ObjectProperty<SimpleTicketView> selectedObject;


    public TicketConfirmationViewModel(Model model, ViewState viewState)
    {
      this.model = model;
      this.viewState = viewState;
      this.tickets = FXCollections.observableArrayList();
      this.selectedObject = new SimpleObjectProperty<>();

      loadFromModel();
    }
    private void loadFromModel()
    {

try
{
  if (model.getAllTickets() != null)
  {
    Ticket[] allTickets = model.getAllTickets().toArray(new Ticket[0]);
    if (allTickets == null){
      System.out.println("empty");
    }
    for (Ticket ticket : allTickets)
    {
      User user = viewState.getUser();
      SimpleTicketView simpleTicketView = new SimpleTicketView(
          ticket,user);
      tickets.add(simpleTicketView);

    }
  }else {
//    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//    alert.setHeaderText("");
//    alert.showAndWait();
  }
}catch (Exception e){
  e.printStackTrace();
}
    }


    public void bindScreenings(ObservableList<SimpleTicketView> propery)
    {
      tickets.addListener(
          (ListChangeListener<? super SimpleTicketView>) c -> {
            propery.setAll(tickets);
          });;
    }
    public void setScreenings(ObservableList<SimpleTicketView> property)
    {
      property.setAll(tickets);
    }
    public ViewState getViewState()
    {
      return viewState;
    }
    public void setSelected()
    {
      selectedObject.set(viewState.getSelectedTicket());
    }
}

