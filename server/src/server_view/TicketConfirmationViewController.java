package server_view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Region;
import server_model.Ticket;
import server_viewmodel.SeatMappingViewModel;
import server_viewmodel.TicketConfirmationViewModel;

public class TicketConfirmationViewController
{
  private Region root;
  private TicketConfirmationViewModel viewModel;
  private ViewHandler viewHandler;

  @FXML private Button fidelityPoints;
  @FXML private Button manage;
  @FXML private Button signOut;
  @FXML private Button ticketConfirmation;
  @FXML private Button cancel;
  @FXML private Button confirmOrder;
  @FXML private Button snackSelection;
  @FXML private Button upgradeToVIP;

  public void init(ViewHandler viewHandler, TicketConfirmationViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

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
