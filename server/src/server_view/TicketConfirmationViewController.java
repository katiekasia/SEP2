package server_view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import server_model.Ticket;
import server_viewmodel.SeatMappingViewModel;
import server_viewmodel.SimpleScreeningView;
import server_viewmodel.TicketConfirmationViewModel;
import server_viewmodel.ViewState;

public class TicketConfirmationViewController
{
  private Region root;
  private TicketConfirmationViewModel viewModel;
  private ViewHandler viewHandler;
  private ViewState viewState;
  private SimpleScreeningView selected;

  @FXML private Button fidelityPoints;
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

  @FXML private Button cancel;
  @FXML private Button confirmOrder;
  @FXML private Button snackSelection;
  @FXML private Button upgradeToVIP;

  public void init(ViewHandler viewHandler, TicketConfirmationViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    this.viewState= viewModel.getViewState();

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
    this.seat.setCellValueFactory(new PropertyValueFactory<>("seat"));
    this.date.setCellValueFactory(new PropertyValueFactory<>("date"));
    this.time.setCellValueFactory(new PropertyValueFactory<>("time"));
    this.ticketType.setCellValueFactory(new PropertyValueFactory<>("ticketType"));


    screeningsTable.getSelectionModel().selectedItemProperty().addListener((obs,oldVal, newVal) -> {
      selected = (SimpleScreeningView) newVal;
      viewState.setSelectedScreening((SimpleScreeningView) newVal);
      viewModel.setSelected();
    });
  }
  @FXML public void onSignOut()
  {

  }
  @FXML public void onManage()
  {

  }
  @FXML public void onFidelityPoints()
  {

  }
  @FXML public void onConfirmOrder()
  {

  }
  @FXML public void onSnackSelection()
  {

  }
  @FXML public void onUpgradeToVIP()
  {

  }
  @FXML public void onCancelOrder()
  {

  }
}
