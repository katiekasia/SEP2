package server_view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import server_viewmodel.LoginViewModel;

public class LoginViewController
{
  private LoginViewModel viewModel;
  private ViewHandler viewHandler;
  @FXML private TextField usernameField;
  @FXML private PasswordField passwordField;
  @FXML private Button loginButton;

  public void init(ViewHandler viewHandler, LoginViewModel viewModel)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    loginButton.setOnAction(event -> viewModel.login(usernameField.getText(),
        passwordField.getText()));
  }
}
