package server_view;

import javafx.scene.layout.Region;
import server_view.ViewHandler;
import server_viewmodel.RegisterPageViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;

public class RegisterViewController
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

  public void init(ViewHandler viewHandler, RegisterPageViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    registerButton.setOnAction(
        event -> viewModel.register(usernameField.getText(),
            passwordField.getText(), emailField.getText(),
            firstNameField.getText(), lastNameField.getText(),
            phoneField.getText()));
  }
}
