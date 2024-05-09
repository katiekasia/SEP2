package server_view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import server_view.ViewHandler;
import server_viewmodel.MainPageViewModel;

public class MainViewController
{
  private Region root;
  private MainPageViewModel viewModel;
  private ViewHandler viewHandler;

  @FXML private Label username;
  @FXML private Button fidelityPoints;
  @FXML private Button manage;
  @FXML private Button signOut;
  @FXML private Button ticketConfirmation;
  @FXML private Button bookTicket1;
  @FXML private Button search;
  @FXML private TextField searchBar;
  @FXML private TableColumn title;
  @FXML private TableColumn date;
  @FXML private TableColumn screeningTime;
  @FXML private TableColumn time;
  @FXML private TableColumn room;
  @FXML private DatePicker datePicker;


  public void init(ViewHandler viewHandler, Region root, MainPageViewModel viewModel)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    this.fidelityPoints.setVisible(true);
    this.manage.setVisible(true);
    this.signOut.setVisible(true);
    this.ticketConfirmation.setVisible(true);
    this.bookTicket1.setVisible(true);

   // this.username.textProperty().bind(viewModel.getUsername());
  }

  @FXML public void onManage()
  {

  }

  @FXML public void onSignOut()
  {

  }

  @FXML public void onFidelityPoints()
  {

  }
  @FXML public void onTicketConfirmation()
  {

  }
  @FXML public void bookTicket1()
  {
   viewHandler.openView("transitionPage");
  }
  @FXML public void onDatePicked()
  {

  }
  @FXML public void onSearch()
  {

  }
  @FXML public void onSearchBar()
  {

  }
}
