package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import viewmodel.MainPageViewModel;
import viewmodel.SimpleScreeningView;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainViewController implements PropertyChangeListener
{
  private Region root;
  private MainPageViewModel viewModel;
  private ViewHandler viewHandler;
  private ViewState viewState;
  private SimpleScreeningView selected;

  @FXML private Label username;
  @FXML private Button manage;
  @FXML private Button signOut;
  @FXML private Button orderConfirmation;
  @FXML private Button bookTicket1;
  @FXML private Button search;
  @FXML private Button clearFilters;
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

    this.manage.setVisible(true);
    this.signOut.setVisible(true);
    this.orderConfirmation.setVisible(true);
    this.bookTicket1.setVisible(true);
    viewModel.setScreenings(screeningsTable.getItems());
    viewModel.bindScreenings(screeningsTable.getItems());
    searchBar.textProperty().bindBidirectional(viewModel.inputProperty());
    username.textProperty().bind(viewState.nameProperty());

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
viewModel.setCurrent(true);
viewModel.loadFromModel();
   // this.username.textProperty().bind(viewModel.getUsername());
  }

  @FXML public void onManage()
  {
    viewModel.setCurrent(false);
    viewHandler.openView("managePage");
  }

  @FXML public void onSignOut()
  {
    viewModel.setCurrent(false);
    viewState.logOut();
    viewHandler.openView("login");
  }


  @FXML public void onOrderConfirmation()
  {
    viewModel.setCurrent(false);
    viewHandler.openView("orderConfirmation");
  }
  @FXML public void bookTicket1()
  {
    if (selected != null)
    {
      viewModel.setCurrent(false);
      viewHandler.openView("transitionPage");
    }else
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("No screening selected.");
      alert.showAndWait();
    }
  }
  @FXML public void onDatePicked()
  {
    viewModel.filterByDate(datePicker.getValue());
  }
  @FXML public void onSearch()
  {
    viewModel.onSearch(datePicker.getValue());
  }
  @FXML public void onClearFiters(){
    viewModel.clearFilters();
  }
  @FXML public void onSearchBar()
  {
    viewModel.filterByTitle();
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
