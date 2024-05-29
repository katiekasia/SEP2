package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewmodel.RegisterPageViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RegisterViewController implements PropertyChangeListener
{
  private RegisterPageViewModel viewModel;
  private ViewHandler viewHandler;
  private Region root;

  @FXML private TextField usernameField;
  @FXML private PasswordField passwordField;
  @FXML private TextField emailField;
  @FXML private TextField firstNameField;
  @FXML private TextField lastNameField;
  @FXML private TextField phoneField;
  @FXML private Button registerButton;
  @FXML private Hyperlink haveAccount;

  public void init(ViewHandler viewHandler, RegisterPageViewModel viewModel, Region root) {
    firstNameField.requestFocus();
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    this.usernameField.textProperty().bindBidirectional(viewModel.getUsernameProperty());
    this.firstNameField.textProperty().bindBidirectional(viewModel.getFNameProperty());
    this.lastNameField.textProperty().bindBidirectional(viewModel.getLNameProperty());
    this.emailField.textProperty().bindBidirectional(viewModel.getEmailProperty());
    this.phoneField.textProperty().bindBidirectional(viewModel.getPhoneProperty());
    this.passwordField.textProperty().bindBidirectional(viewModel.getPasswordProperty());

    this.viewModel.setCurrent(true);
    this.viewModel.addListener(this);


  }

  @FXML public void backLogin() {
    viewModel.setCurrent(false);
    viewHandler.openView("login");
  }

  @FXML public void onRegister() {

    if (viewModel.getUsername().isBlank() || viewModel.getUsername().contains(" ")) {
      showAlert("Invalid Username", "Username cannot be blank or contain spaces.");
      return;
    }

    if (viewModel.getUsername().length() > 12) {
      showAlert("Invalid Username", "Username cannot be longer than 12 characters.");
      return;
    }

    if (viewModel.getPassword().isBlank() || viewModel.getPassword().contains(" ")) {
      showAlert("Invalid Password", "Password cannot be blank or contain spaces.");
      return;
    }

    if (viewModel.getEmail().isBlank() || viewModel.getEmail().contains(" ")|| !viewModel.getEmail().contains("@") )  {
      showAlert("Invalid Email", "Email cannot be blank or contain spaces.");
      return;
    }

    if (viewModel.getFName().isBlank() || viewModel.getFName().contains(" ")) {
      showAlert("Invalid First Name", "First name cannot be blank or contain spaces.");
      return;
    }

    if (viewModel.getLName().isBlank() || viewModel.getLName().contains(" ")) {
      showAlert("Invalid Last Name", "Last name cannot be blank or contain spaces.");
      return;
    }

    if (viewModel.getPhone().length() > 8) {
      showAlert("Invalid Phone Number", "Use a danish phone number");
      return;
    }

    if (viewModel.getPhone().isBlank() || viewModel.getPhone().contains(" ")) {
      showAlert("Invalid Phone Number", "Phone number cannot be blank or contain spaces.");
      return;
    }
    viewModel.register();
    if (viewModel.registrationStatusProperty()) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setHeaderText("Registration Successful " + viewModel.registrationMessageProperty().get());
      alert.showAndWait();
      clearFields();
      viewModel.setCurrent(false);
      viewHandler.openView("login");
    }
  }

  private void showAlert(String header, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
  }


  private void clearFields() {
    usernameField.setText("");
    passwordField.setText("");
    emailField.setText("");
    firstNameField.setText("");
    lastNameField.setText("");
    phoneField.setText("");
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