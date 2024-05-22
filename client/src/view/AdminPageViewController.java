package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewmodel.AdminPageViewModel;
import viewmodel.MainPageViewModel;
import viewmodel.SimpleScreeningView;
import viewmodel.ViewState;

public class AdminPageViewController
{

  private Region root;
  private AdminPageViewModel viewModel;
  private ViewHandler viewHandler;
  private ViewState viewState;
  private SimpleScreeningView selected;

  @FXML private Label username;
  @FXML private Button addMovie;
  @FXML private Button deleteScreening;
  @FXML private Button signOut;
  @FXML private Button addScreening;
  @FXML private Button editPrices;
  @FXML private TableView screeningsTable;
  @FXML private TableColumn title;
  @FXML private TableColumn date;
  @FXML private TableColumn screeningTime;
  @FXML private TableColumn time;
  @FXML private TableColumn room;
  @FXML private DatePicker datePicker;


  @FXML public void onDeleteScreening()
  {

  }
  @FXML public void onEditPrices()
  {

  }
  @FXML public void onAddScreening()
  {

  }
  @FXML public void onAddMovie()
  {

  }

}
