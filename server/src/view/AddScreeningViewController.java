package view;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import viewmodel.AddScreeningViewModel;
import viewmodel.SimpleMovieView;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.time.LocalDate;

public class AddScreeningViewController
{
  private Region root;
  private AddScreeningViewModel viewModel;
  private ViewHandler viewHandler;
  private ViewState viewState;
  private SimpleMovieView selected;


  @FXML private TableView<SimpleMovieView> moviesTable;
  @FXML private TableColumn<SimpleMovieView, String> title;
  @FXML private Button deleteMovie;
  @FXML private Button backAdminPage;
  @FXML private Button signOut;
  @FXML private Button add;
  @FXML private DatePicker datePicker;
  @FXML private TextField timeField;
  @FXML private TextField roomField;

  public void init(ViewHandler viewHandler, Region root, AddScreeningViewModel viewModel)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    this.viewState = viewModel.getViewState();

    viewModel.setCurrent(true);
    viewModel.setMovies(moviesTable.getItems());
    viewModel.bindScreenings(moviesTable.getItems());
    this.title.setCellValueFactory(new PropertyValueFactory<>("title"));
    moviesTable.getSelectionModel().selectedItemProperty().addListener((obs,oldVal, newVal) -> {
      selected = (SimpleMovieView) newVal;
      viewState.setSelectedMovie((SimpleMovieView) newVal);
      viewModel.setSelected();
    });

    timeField.textProperty().bindBidirectional(viewModel.timeProperty());
    roomField.textProperty().bindBidirectional(viewModel.roomProperty());

    viewModel.addingStatusProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (newValue.equals("SUCCESS")) {
          showAlert("Adding was successful", viewModel.addingMessageProperty().get());
          clearFields();
        } else if (newValue.equals("ERROR")) {
          showAlert("Failed", viewModel.addingMessageProperty().get());
        }
      }
    });
    viewModel.loadFromModel();
  }

  @FXML public void backToAdmin() {
    viewModel.setCurrent(false);
    viewHandler.openView("adminPage");}
  @FXML
  public void onAdd() {
    if (selected == null) {
      showAlert("Incomplete Details", "Please select a movie.");
      return;
    }


    if (timeField.textProperty().isEmpty().get() || roomField.textProperty().isEmpty().get()) {
      showAlert("Incomplete Details", "Please fill in all fields.");
      return;
    }

    LocalDate date = datePicker.getValue();
    if (date == null) {
      showAlert("Incomplete Details", "Please select a date.");
      return;
    }


    try {
      String[] timeParts = timeField.getText().split(":");
      if (timeParts.length != 2) {
        showAlert("Invalid Time Format", "Please use HH:mm format for the time.");
        return;
      }




      viewModel.addScreening(date);
      clearFields();
    } catch (NumberFormatException e) {
      e.printStackTrace();      showAlert("Invalid Input", "Please enter valid numbers for time and room.");
    } catch (Exception e) {
      showAlert("Error", "An error occurred while adding the screening.");
    }
  }

  @FXML public void onSignOut()
  {
    viewModel.setCurrent(false);
    viewState.logOut();
    viewHandler.openView("login");
  }
  @FXML public void onDeleteMovie()
  {
    if (selected != null)
    {
      viewModel.deleteMovie();
    }
  }

  private void clearFields()
  {
    timeField.setText("");
    roomField.setText("");
    datePicker.setValue(null);
  }

  private void showAlert(String header, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
  }

  public void propertyChange(PropertyChangeEvent evt)
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
