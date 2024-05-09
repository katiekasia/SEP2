package server_view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import server_model.Ticket;
import server_viewmodel.SeatMappingViewModel;
import server_viewmodel.TicketConfirmationViewModel;
import server_viewmodel.ViewState;

public class TicketConfirmationViewController
{
  private Region root;
  private TicketConfirmationViewModel viewModel;
  private ViewHandler viewHandler;
  private ViewState viewState;

  @FXML private Button fidelityPoints;
  @FXML private Button manage;
  @FXML private Button signOut;
  @FXML private Button ticketConfirmation;

  @FXML private Label movieTitle;
  @FXML private Label date;
  @FXML private Label seat;
  @FXML private Label ticketType;
  @FXML private Label movieTime;
  @FXML private Label snacks;

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

    movieTitle.setText(viewState.getSelectedScreening().movieProperty().get());
   // length.setText(String.valueOf(viewState.getSelectedScreening().lengthProperty().get()));
   // movieGenre.setText(viewState.getSelectedScreening().genreProperty().get());
    date.setText(viewState.getSelectedScreening().dateProperty().get());
    movieTime.setText(viewState.getSelectedScreening().timeProperty().get());
    //roomID.setText(String.valueOf(viewState.getSelectedScreening().roomProperty().get()));

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
