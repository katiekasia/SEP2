package view;

import viewmodel.LoginViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;


public class LoginViewController
{
  private Region root;
  private LoginViewModel viewModel;
  private ViewHandler viewHandler;
  @FXML private TextField usernameField;
  @FXML private PasswordField passwordField;
  @FXML private Button loginButton;

  public void init(ViewHandler viewHandler, LoginViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    this.usernameField.textProperty()
        .bindBidirectional(viewModel.getUsernameField());
    this.passwordField.textProperty()
        .bindBidirectional(viewModel.getPasswordField());
    this.loginButton.setVisible(true);

    loginButton.setOnAction(event -> viewModel.login(usernameField.getText(),
        passwordField.getText()));
  }

  public Region getRoot()
  {
    return root;
  }

}
