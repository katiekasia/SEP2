package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import viewmodel.AdminPageViewModel;
import viewmodel.SimpleScreeningView;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AdminPageViewController implements PropertyChangeListener
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
    viewModel.setCurrent(true);
    this.viewModel.addListener(this);

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
    viewModel.loadFromModel();
  }


  @FXML public void onDeleteScreening()
  {
    if (selected != null)
    {
      viewModel.deleteScreening();
    }
    if (selected==null) {
      showAlert("No screening selected", "Select a screening to delete.");
      return;
    }
  }

  @FXML public void onSignOut()
  {
    viewModel.setCurrent(false);
    viewHandler.openView("login");
  }
  @FXML public void onEditPrices()
  {
    viewModel.setCurrent(false);
    viewHandler.openView("editPrices");
  }
  @FXML public void onAddScreening()
  {
    viewModel.setCurrent(false);
    viewHandler.openView("addScreening");
  }
  @FXML public void onAddMovie()
  {
    viewModel.setCurrent(false);
    viewHandler.openView("addMovie");
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
  private void showAlert(String header, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
  }

}
