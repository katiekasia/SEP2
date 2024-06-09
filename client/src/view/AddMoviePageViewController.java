package view;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewmodel.AddMovieViewModel;
import viewmodel.SimpleScreeningView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
/**
 * Controller class for the Add Movie Page view.
 * used to add movie to the system
 *
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class AddMoviePageViewController implements PropertyChangeListener {
  private Region root;
  private AddMovieViewModel viewModel;
  private ViewHandler viewHandler;
  private SimpleScreeningView selected;

  @FXML private TextField titleField;
  @FXML private TextField descriptionField;
  @FXML private TextField lengthField;
  @FXML private TextField genreField;
  @FXML private DatePicker releaseDatePicker;

  @FXML private Label username;
  @FXML private Button signOut;
  @FXML private Button add;
  @FXML private Button backAdminPage;
  /**
   * Initializes the controller with the necessary dependencies.
   *
   * @param viewHandler The ViewHandler instance.
   * @param root The root Region of the view.
   * @param viewModel The AddMovieViewModel instance.
   */
  public void init(ViewHandler viewHandler, Region root, AddMovieViewModel viewModel) {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    viewModel.setCurrent(true);
    this.viewModel.addListener(this);

    viewModel.addListener(this);
    titleField.textProperty().bindBidirectional(viewModel.titleProperty());
    descriptionField.textProperty().bindBidirectional(viewModel.descriptionProperty());
    lengthField.textProperty().bindBidirectional(viewModel.lengthProperty());
    genreField.textProperty().bindBidirectional(viewModel.genreProperty());

    viewModel.addingStatusProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (newValue.equals("SUCCESS")) {
          showAlert("adding Successful", viewModel.addingMessageProperty().get());
          clearFields();
        } else if (newValue.equals("ERROR")) {
          showAlert("Registration Failed", viewModel.addingMessageProperty().get());
        }
      }
    });

  }

  /**
   * Handles the "Add" button click event.
   * Validates the input fields and adds a new movie.
   */
  @FXML public void onAdd() {
    String title = titleField.getText();
    String description = descriptionField.getText();
    String length = lengthField.getText();
    String genre = genreField.getText();

    if (title.isBlank() || description.isBlank() || length.isBlank() || genre.isBlank()) {
      showAlert("Incomplete Details", "Please fill in all fields.");
      return;
    }
    // Now, validate the release date field
    LocalDate releaseDate = releaseDatePicker.getValue();
    if (releaseDate == null) {
      showAlert("Incomplete Details", "Please select a release date.");
      return;
    }

    viewModel.addMovie(releaseDate);
    clearFields();
  }
  /**
   * Handles the "Back to Admin" button click event.
   * Navigates back to the admin page.
   */
  @FXML public void backToAdmin() {viewModel.setCurrent(false);
    viewHandler.openView("adminPage");
  }
  /**
   * Handles the "Sign Out" button click event.
   * Signs out the current user and navigates to the login page.
   */
  @FXML public void onSignOut() {viewModel.setCurrent(false);
    viewHandler.openView("login");
  }
  /**
   * Displays an alert with the specified header and content.
   *
   * @param header The header text of the alert.
   * @param content The content text of the alert.
   */
  private void showAlert(String header, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
  }

  private void clearFields() {
    titleField.setText("");
    descriptionField.setText("");
    releaseDatePicker.setValue(null);
    lengthField.setText("");
    genreField.setText("");
  }

  /**
   * Listens for property changes and handles fatal errors.
   *
   * @param evt The property change event.
   */
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
