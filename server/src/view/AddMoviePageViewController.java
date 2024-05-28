package view;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.Movie;
import viewmodel.AddMovieViewModel;
import viewmodel.SimpleScreeningView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;

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

  public void init(ViewHandler viewHandler, Region root, AddMovieViewModel viewModel) {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    viewModel.addListener(this);

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

    viewModel.addMovie(new Movie(title,length,description,genre,releaseDate));
    clearFields();
  }

  @FXML public void backToAdmin() {
    viewHandler.openView("adminPage");
  }

  @FXML public void onSignOut() {
    viewHandler.openView("login");
  }

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