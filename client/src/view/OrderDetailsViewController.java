package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import viewmodel.OrderDetailsViewModel;
import viewmodel.SimpleSnackView;
import viewmodel.SimpleTicketView;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OrderDetailsViewController implements PropertyChangeListener
{
  private Region root;
  private ViewHandler viewHandler;
  private OrderDetailsViewModel viewModel;
  private ViewState viewState;
  private SimpleSnackView selectedSnack;
  private SimpleTicketView selectedTicket;

  @FXML private Label date;
  @FXML private Label movie;
  @FXML private Label orderId;
  @FXML private Label time;

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

    this.viewModel.addListener(this);

    this.viewModel.loadFromModel();
    controlButtons();

    this.viewModel.setTickets(ticketsTable.getItems());
    this.viewModel.setSnacks(snacksTable.getItems());
    this.viewModel.bindSnacks(snacksTable.getItems());
    this.viewModel.bindTickets(ticketsTable.getItems());

    time.setText(viewModel.getTime());
    movie.setText(viewModel.getMovie());
    date.setText(viewModel.getDate());
    orderId.setText(viewModel.getOrderID());

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
    viewModel.setCurrent(true);
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
    viewModel.setCurrent(false);
    viewHandler.openView("snackSelection");
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
    viewModel.setCurrent(false);
    viewHandler.openView("orderConfirmation");
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
