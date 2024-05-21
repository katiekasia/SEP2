package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import viewmodel.OrderDetailsViewModel;
import viewmodel.SimpleSnackView;
import viewmodel.SimpleTicketView;
import viewmodel.ViewState;

import java.util.Optional;

public class OrderDetailsViewController
{
  private Region root;
  private ViewHandler viewHandler;
  private OrderDetailsViewModel viewModel;
  private ViewState viewState;
  private SimpleSnackView selectedSnack;
  private SimpleTicketView selectedTicket;

  @FXML private TableView ticketsTable;
  @FXML private TableView snacksTable;

  @FXML private  TableColumn seatColumn;
  @FXML private  TableColumn ticketTypeColumn;
  @FXML private  TableColumn ticketPriceColumn;
  @FXML private TableColumn snackNameColumn;
  @FXML private TableColumn sizeColumn;
  @FXML private  TableColumn snackPriceColumn;

  @FXML private  Button upgradeButton;
  @FXML private  Button deleteTicketButton;
  @FXML private Button downgradeButton;
  @FXML private  Button addSnackButton;
  @FXML private Button deleteSnackButton;
  @FXML private Button backButton;

  public void init(ViewHandler viewHandler, OrderDetailsViewModel viewModel, Region root){
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    this.viewState = viewModel.getViewState();

    viewModel.loadFromModel();
    controlButtons();

    viewModel.setTickets(ticketsTable.getItems());
    viewModel.setSnacks(snacksTable.getItems());
    viewModel.bindSnacks(snacksTable.getItems());
    viewModel.bindTickets(ticketsTable.getItems());
    seatColumn.setCellValueFactory(new PropertyValueFactory<>("seatID"));
    ticketTypeColumn.setCellValueFactory(new PropertyValueFactory<>("ticketType"));
    ticketPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    snackNameColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
    snackPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    ticketsTable.getSelectionModel().selectedItemProperty().addListener((obs,oldVal, newVal) -> {
      selectedTicket = (SimpleTicketView) newVal;
      viewState.setSelectedTicket((SimpleTicketView) newVal);
      viewModel.setTicketSelected(true);
      viewModel.setSelected();
      controlButtons();
    });
    snacksTable.getSelectionModel().selectedItemProperty().addListener((obs,oldVal, newVal) -> {
      selectedSnack = (SimpleSnackView) newVal;
      viewState.setSelectedSnack((SimpleSnackView) newVal);
      viewModel.setSnackSelected(true);
      viewModel.setSelected();
      controlButtons();
    });
  }
  private void controlButtons(){
    if (!viewModel.ticketSelected()){
      upgradeButton.setDisable(true);
      deleteTicketButton.setDisable(true);
      downgradeButton.setDisable(true);
    }
    else if (viewModel.ticketSelected())
    {
      upgradeButton.setDisable(false);
      deleteTicketButton.setDisable(false);
      downgradeButton.setDisable(false);
    }
    if (!viewModel.snackSelected()){
      deleteSnackButton.setDisable(true);
    }
    else if (viewModel.snackSelected()){
      deleteSnackButton.setDisable(false);
    }
  }
  @FXML public void onUpgrade(){
    if (selectedTicket != null){
      viewModel.upgradePressed();
    }else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("No ticket selected.");
      alert.showAndWait();
    }
  }
  @FXML public void onDeleteTicket(){
    if (selectedTicket != null){
      viewModel.cancelTicketPressed();
    }else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("No ticket selected.");
      alert.showAndWait();
    }
  }
  @FXML public void onDowngrade(){
    if (selectedTicket != null){
      viewModel.downgradePressed();
    }else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("No ticket selected.");
      alert.showAndWait();
    }
  }
  @FXML public void onAddSnack(){
    //viewHandler.openView();
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
  @FXML public void onBack(){
    viewHandler.openView("orderConfirmation");
  }
}
