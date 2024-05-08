package client_view;

import client_viewmodel.RegisterPageViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;

public class RegisterViewController
{
  private RegisterPageViewModel viewModel;
  private ViewHandler viewHandler;

  @FXML private TextField usernameField;
  @FXML private PasswordField passwordField;
  @FXML private TextField emailField;
  @FXML private TextField firstNameField;
  @FXML private TextField lastNameField;
  @FXML private TextField phoneField;
  @FXML private Button registerButton;

  public void init(ViewHandler viewHandler, RegisterPageViewModel viewModel,
      Region root) {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    registerButton.setOnAction(event -> viewModel.register(usernameField.getText(), passwordField.getText(), emailField.getText(),
        firstNameField.getText(), lastNameField.getText(), phoneField.getText()));
  }
}
