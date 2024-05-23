package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import viewmodel.*;

public class AdminPageViewController
{

  private Region root;
  private AdminPageViewModel viewModel;
  private ViewHandler viewHandler;
  private ViewState viewState;

  @FXML private Label username;

  @FXML private TableView screeningsTable;
  @FXML private TableColumn title;
  @FXML private TableColumn date;
  @FXML private TableColumn screeningTime;
  @FXML private TableColumn time;
  @FXML private TableColumn room;
  private SimpleScreeningView selected;


  public void init(ViewHandler viewHandler, Region root, AdminPageViewModel viewModel) {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    this.viewState= viewModel.getViewState();


    viewModel.setScreenings(screeningsTable.getItems());
    viewModel.bindScreenings(screeningsTable.getItems());
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
  }


  @FXML public void onDeleteScreening()
  {

  }
  @FXML public void onEditPrices()
  {
    viewHandler.openView("editPrices");
  }
  @FXML public void onAddScreening()
  {
    viewHandler.openView("addScreening");
  }
  @FXML public void onAddMovie()
  {
    viewHandler.openView("addMovie");
  }

}
