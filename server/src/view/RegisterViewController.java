package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.RegisterPageViewModel;

public class RegisterViewController {
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


    viewModel.registrationStatusProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (newValue.equals("SUCCESS")) {
          showSuccessAlert("Registration Successful", viewModel.registrationMessageProperty().get());
          clearFields();
        } else if (newValue.equals("ERROR")) {
          showAlert("Registration Failed", viewModel.registrationMessageProperty().get());
        }
      }
    });
  }

  @FXML public void backLogin() {
    viewHandler.openView("login");
  }

  @FXML public void onRegister() {
    String username = usernameField.getText();
    String password = passwordField.getText();
    String email = emailField.getText();
    String firstName = firstNameField.getText();
    String lastName = lastNameField.getText();
    String phone = phoneField.getText();

    if (username.isBlank() || username.contains(" ")) {
      showAlert("Invalid Username", "Username cannot be blank or contain spaces.");
      return;
    }

    if (username.length() > 12) {
      showAlert("Invalid Username", "Username cannot be longer than 12 characters.");
      return;
    }

    if (password.isBlank() || password.contains(" ")) {
      showAlert("Invalid Password", "Password cannot be blank or contain spaces.");
      return;
    }

    if (email.isBlank() || email.contains(" ")|| !email.contains("@") )  {
      showAlert("Invalid Email", "Email cannot be blank or contain spaces.");
      return;
    }

    if (firstName.isBlank() || firstName.contains(" ")) {
      showAlert("Invalid First Name", "First name cannot be blank or contain spaces.");
      return;
    }

    if (lastName.isBlank() || lastName.contains(" ")) {
      showAlert("Invalid Last Name", "Last name cannot be blank or contain spaces.");
      return;
    }

    if (phone.length() > 8) {
      showAlert("Invalid Phone Number", "Use a danish phone number");
      return;
    }

    if (phone.isBlank() || phone.contains(" ")) {
      showAlert("Invalid Phone Number", "Phone number cannot be blank or contain spaces.");
      return;
    }

    viewModel.register(username, password, email, firstName, lastName, phone);

  }

  private void showAlert(String header, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
  }

  private void showSuccessAlert(String header, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
}