package view;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import viewmodel.AdminPageViewModel;
import viewmodel.ViewState;

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
  @FXML private DatePicker datePicker;


  public void init(ViewHandler viewHandler, Region root, AdminPageViewModel viewModel) {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

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
