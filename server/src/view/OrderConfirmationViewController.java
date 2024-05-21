package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import viewmodel.OrderConfirmationViewModel;
import viewmodel.SimpleOrderView;
import viewmodel.SimpleScreeningView;
import viewmodel.ViewState;

public class OrderConfirmationViewController
{
  private Region root;
  private ViewHandler viewHandler;
  private OrderConfirmationViewModel viewModel;
  private ViewState viewState;
  private SimpleOrderView selected;

  @FXML private Label username;
  @FXML private Button fidelityPoints;
  @FXML private Button manage;
  @FXML private Button signOut;
  @FXML private Button backToScreenings;
  @FXML private Button CancelOrderButton;
  @FXML private Button orderDetailsButton;

  @FXML private TableView ordersTable;

  @FXML private TableColumn orderID;
  @FXML private TableColumn orderStatus;
  @FXML private TableColumn orderDate;
  @FXML private TableColumn orderPrice;



  public void init(ViewHandler viewHandler, OrderConfirmationViewModel viewModel, Region root){
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    this.viewState = viewModel.getViewState();

    viewModel.setOrders(ordersTable.getItems());
    viewModel.bindOrders(ordersTable.getItems());
    username.textProperty().bind(viewState.usernameProperty());
    this.orderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
    this.orderStatus.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
    this.orderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
    this.orderPrice.setCellValueFactory(new PropertyValueFactory<>("orderPrice"));

    ordersTable.getSelectionModel().selectedItemProperty().addListener((obs,oldVal, newVal) -> {
      selected = (SimpleOrderView) newVal;
      viewState.setSelectedOrder((SimpleOrderView) newVal);
      viewModel.setSelected();
    });

    viewModel.loadFromModel();
  }
  @FXML public void onSignOut(){
    viewState.logOut();
    viewHandler.openView("login");
  }
  @FXML public void onManage(){
    viewHandler.openView("managePage");
  }
  @FXML public void onBack(){
    viewHandler.openView("mainPage");
  }
  @FXML public void onFidelityPoints(){}
  @FXML public void onCancelOrder(){
    if (selected != null){
      viewModel.cancelOrderPressed();
    }
  }
  @FXML public  void onOrderDetails(){
    if (selected != null){
      viewHandler.openView("orderDetails");
    }else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("No order selected.");
      alert.showAndWait();
    }
  }
}
