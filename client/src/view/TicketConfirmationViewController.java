package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import viewmodel.SimpleSnackView;
import viewmodel.SimpleTicketView;
import viewmodel.TicketConfirmationViewModel;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TicketConfirmationViewController implements PropertyChangeListener
{
  private Region root;
  private TicketConfirmationViewModel viewModel;
  private ViewHandler viewHandler;
  private ViewState viewState;
  private SimpleTicketView selectedTicket;
  private SimpleSnackView selectedSnack;

  @FXML private Button manage;
  @FXML private Button signOut;
  @FXML private Button ticketConfirmation;

  @FXML private Label totalPrice;
  @FXML private Label upgradeCost;
  @FXML private Label usernameLabel;


  @FXML private TableView ticketsTable;
  @FXML private TableColumn seat;
  @FXML private TableColumn date;
  @FXML private TableColumn movie;
  @FXML private TableColumn time;
  @FXML private TableColumn ticketType;

@FXML private TableView snacksTable;
@FXML private TableColumn snackName;
@FXML private TableColumn size;
@FXML private TableColumn snackPrice;

  @FXML private Button deleteSnack;
@FXML private Button cancelTicket;
  @FXML private Button Continue;
  @FXML private Button snackSelection;
  @FXML private Button upgradeToVIP;

  public void init(ViewHandler viewHandler, TicketConfirmationViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    this.viewState= viewModel.getViewState();

    viewModel.setCurrent(true);
    viewModel.addListener(this);

usernameLabel.setText(viewState.getUser().getUsername());
    viewModel.setTickets(ticketsTable.getItems());
    viewModel.binTickets(ticketsTable.getItems());
    viewModel.setSnacks(snacksTable.getItems());
    viewModel.bindSnacks(snacksTable.getItems());
    snackName.setCellValueFactory(new PropertyValueFactory<>("type"));
    size.setCellValueFactory(new PropertyValueFactory<>("size"));
    snackPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


    this.movie.setCellValueFactory(new PropertyValueFactory<>("movie"));
    this.seat.setCellValueFactory(new PropertyValueFactory<>("seatID"));
    this.date.setCellValueFactory(new PropertyValueFactory<>("date"));
    this.time.setCellValueFactory(new PropertyValueFactory<>("time"));
    this.ticketType.setCellValueFactory(new PropertyValueFactory<>("ticketType"));


    ticketsTable.getSelectionModel().selectedItemProperty().addListener((obs,oldVal, newVal) -> {
      selectedTicket = (SimpleTicketView) newVal;
      viewState.setSelectedTicket((SimpleTicketView) newVal);
      viewModel.setSelected();
      viewModel.setTicketSelected(true);
      controlButtons();
    });

    snacksTable.getSelectionModel().selectedItemProperty().addListener((obs,oldVal, newVal) -> {
      selectedSnack = (SimpleSnackView) newVal;
      viewState.setSelectedSnack((SimpleSnackView) newVal);
      viewModel.setSelected();
      viewModel.setSnackSelected(true);
      controlButtons();
    });
    viewModel.loadFromModel();
  }
  private void controlButtons(){
    if (!viewModel.ticketSelected()){
      upgradeToVIP.setDisable(true);
      cancelTicket.setDisable(true);
    }
    else if (viewModel.ticketSelected())
    {
      upgradeToVIP.setDisable(false);
      cancelTicket.setDisable(false);
    }
    if (!viewModel.snackSelected()){
      deleteSnack.setDisable(true);
    }
    else if (viewModel.snackSelected()){
      deleteSnack.setDisable(false);
    }
  }


  @FXML public void onSignOut()
  {
    viewModel.setCurrent(false);
    viewState.logOut();
    viewHandler.openView("loginPage");
  }
  @FXML public void onManage()
  {
    viewModel.setCurrent(false);
    viewHandler.openView("managePage");
  }

  @FXML public void onConfirmOrder()
  {
    viewModel.setCurrent(false);
      viewHandler.openView("orderConfirmation");
  }
  @FXML public  void onOrderConfirmation(){
    viewModel.setCurrent(false);
    viewHandler.openView("orderConfirmation");
  }
  @FXML public void onSnackSelection()
  {
    viewModel.setCurrent(false);
    viewHandler.openView("snackSelection");
  }
  @FXML public void onUpgradeToVIP()
  {
    if (selectedTicket != null)
    {
      viewModel.upgradePressed();
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("No ticket selected.");
      alert.showAndWait();
    }
  }
  @FXML public void onDeleteSnack(){
    if (selectedSnack != null){
      viewModel.deleteSnackPressed();
    }else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("No snack selected.");
      alert.showAndWait();
    }
  }

  @FXML public void onCancelTicket()
  {
    if (selectedTicket != null){
      viewModel.cancelTicketPressed();
    }else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("No ticket selected.");
      alert.showAndWait();
    }
  }
  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() ->{
      if (evt.getPropertyName().equals("fatalError")){
        viewHandler.close();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("A fatal error has occured: " + evt.getNewValue());
        alert.showAndWait();
      }});
  }
}
