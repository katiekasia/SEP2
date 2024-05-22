package view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.Order;
import viewmodel.SeatMappingViewModel;
import viewmodel.SimpleScreeningView;
import viewmodel.TicketConfirmationViewModel;
import viewmodel.ViewState;

public class TicketConfirmationViewController
{
  private Region root;
  private TicketConfirmationViewModel viewModel;
  private ViewHandler viewHandler;
  private ViewState viewState;
  private SimpleScreeningView selected;

  @FXML private Button manage;
  @FXML private Button signOut;
  @FXML private Button ticketConfirmation;


  @FXML private TableView screeningsTable;
  @FXML private TableColumn seat;
  @FXML private TableColumn date;
  @FXML private TableColumn movie;
  @FXML private TableColumn time;
  @FXML private TableColumn ticketType;

  @FXML private TableColumn snacks;
@FXML private Label username;
  @FXML private Button cancel;
  @FXML private Button confirmOrder;
  @FXML private Button snackSelection;
  @FXML private Button upgradeToVIP;
  private SeatMappingViewModel seatMappingViewModel;

  public void init(ViewHandler viewHandler, TicketConfirmationViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    this.viewState= viewModel.getViewState();
    username.textProperty().bind(viewState.nameProperty());

    viewModel.setScreenings(screeningsTable.getItems());
    viewModel.bindScreenings(screeningsTable.getItems());

    /*
    movie.setText(viewState.getSelectedScreening().movieProperty().get());
    seat.setText(String.valueOf(viewState.getSelectedScreening().getSeatID().get()));
    //ticketType.setText(viewState.getSelectedScreening().);
    date.setText(viewState.getSelectedScreening().dateProperty().get());
    time.setText(viewState.getSelectedScreening().timeProperty().get());
    //roomID.setText(String.valueOf(viewState.getSelectedScreening().roomProperty().get()));
     */
    this.movie.setCellValueFactory(new PropertyValueFactory<>("movie"));
    this.seat.setCellValueFactory(new PropertyValueFactory<>("seatID"));
    this.date.setCellValueFactory(new PropertyValueFactory<>("date"));
    this.time.setCellValueFactory(new PropertyValueFactory<>("time"));
    this.ticketType.setCellValueFactory(new PropertyValueFactory<>("ticketType"));


    screeningsTable.getSelectionModel().selectedItemProperty().addListener((obs,oldVal, newVal) -> {
      selected = (SimpleScreeningView) newVal;
      viewState.setSelectedScreening((SimpleScreeningView) newVal);
      viewModel.setSelected();


      ObservableList<String> selectedSeats = seatMappingViewModel.getSelectedSeats();
     // viewModel.updateScreeningsWithSelectedSeats(selectedSeats);

      // Bind the updated screenings list to the table view
      screeningsTable.setItems(screeningsTable.getItems());
    });
  }


  @FXML public void onSignOut()
  {
    viewHandler.openView("loginPage");
  }
  @FXML public void onManage()
  {
    //we need a page with user page
    viewHandler.openView(" ");
  }

  @FXML public void onConfirmOrder()
  {
    //we need a information after confirming the order
    if (selected != null)
    {
      viewHandler.openView(" ");
    }else
      System.out.println("no selection");
  }
  @FXML public void onSnackSelection()
  {

  }
//  @FXML public void onUpgradeToVIP()
//  {
//    if (selected != null)
//    {
//      selected.ticketTypeProperty().set("VIP ticket");
//      //should upgrade the info also in database
//      Order firstOrder = selected.getUser().getOrders().get(0);
//      double newPrice = firstOrder.getOrderPrice() + 50;
//      firstOrder.setOrderPrice(newPrice);
//    }
//    else
//      System.out.println("no selection");
//  }
  @FXML public void onCancelOrder()
  {

  }
  @FXML public void onTicketConfirmation()
  {

  }
}
