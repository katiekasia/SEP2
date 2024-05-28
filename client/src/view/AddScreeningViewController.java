package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import viewmodel.AddScreeningViewModel;
import viewmodel.SimpleMovieView;
import viewmodel.ViewState;

public class AddScreeningViewController
{
  private Region root;
  private AddScreeningViewModel viewModel;
  private ViewHandler viewHandler;
  private ViewState viewState;
  private SimpleMovieView selected;


  @FXML private TableView moviesTable;
  @FXML private TableColumn title;

  @FXML private Button deleteMovie;
  @FXML private Label username;
  @FXML private Button signOut;
  @FXML private Button add;
  @FXML private Button cancel;

  public void init(ViewHandler viewHandler, Region root, AddScreeningViewModel viewModel)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    this.viewState = viewModel.getViewState();

    viewModel.setMovies(moviesTable.getItems());
    viewModel.bindScreenings(moviesTable.getItems());
    username.textProperty().bind(viewState.nameProperty());

    this.title.setCellValueFactory(new PropertyValueFactory<>("title"));

    moviesTable.getSelectionModel().selectedItemProperty().addListener((obs,oldVal, newVal) -> {
      selected = (SimpleMovieView) newVal;
      viewState.setSelectedMovie((SimpleMovieView) newVal);
      viewModel.setSelected();
    });
  }

  @FXML public void onCancel()
  {
    viewHandler.openView("adminPage");
  }
  @FXML public void onAdd()
  {

  }
  @FXML public void onSignOut()
  {
    viewHandler.openView("login");
  }
  @FXML public void onDeleteMovie()
  {
    if (selected != null)
    {
      viewModel.deleteMovie();
    }
  }
}
