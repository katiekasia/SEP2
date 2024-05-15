package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import viewmodel.MainPageViewModel;
import viewmodel.SimpleScreeningView;
import viewmodel.ViewState;

public class MainViewController
{
  private Region root;
  private MainPageViewModel viewModel;
  private ViewHandler viewHandler;
  private ViewState viewState;
  private SimpleScreeningView selected;

  @FXML private Label username;
  @FXML private Button fidelityPoints;
  @FXML private Button manage;
  @FXML private Button signOut;
  @FXML private Button ticketConfirmation;
  @FXML private Button bookTicket1;
  @FXML private Button search;
  @FXML private TextField searchBar;
  @FXML private TableView screeningsTable;
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
    this.viewState = viewModel.getViewState();

    this.fidelityPoints.setVisible(true);
    this.manage.setVisible(true);
    this.signOut.setVisible(true);
    this.ticketConfirmation.setVisible(true);
    this.bookTicket1.setVisible(true);
    viewModel.setScreenings(screeningsTable.getItems());
    viewModel.bindScreenings(screeningsTable.getItems());
    searchBar.textProperty().bindBidirectional(viewModel.inputProperty());

    username.textProperty().bind(viewModel.usernameProperty());
    this.title.setCellValueFactory(new PropertyValueFactory<>("movie"));
    this.screeningTime.setCellValueFactory(new PropertyValueFactory<>("length"));
    this.date.setCellValueFactory(new PropertyValueFactory<>("date"));
    this.time.setCellValueFactory(new PropertyValueFactory<>("time"));
    this.room.setCellValueFactory(new PropertyValueFactory<>("room"));

screeningsTable.getSelectionModel().selectedItemProperty().addListener((obs,oldVal, newVal) -> {
selected = (SimpleScreeningView) newVal;
viewState.setSelectedScreening((SimpleScreeningView) newVal);
viewModel.setSelected();
});

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
    if (selected != null)
    {
      viewHandler.openView("transitionPage");
    }else
      System.out.println("no selection");
  }
  @FXML public void onDatePicked()
  {
    viewModel.filterByDate(datePicker.getValue());
  }
  @FXML public void onSearch()
  {
    viewModel.filterByTitle();
  }
  @FXML public void onSearchBar()
  {
    viewModel.filterByTitle();
  }
}
