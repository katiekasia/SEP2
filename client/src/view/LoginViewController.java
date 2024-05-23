package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.LoginViewModel;

public class LoginViewController
{
  private Region root;
  private LoginViewModel viewModel;
  private ViewHandler viewHandler;
  @FXML private TextField usernameField;
  @FXML private PasswordField passwordField;
  @FXML private Button loginButton;
  @FXML private Hyperlink newAccount;

  public void init(ViewHandler viewHandler, LoginViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    viewModel.reset();
    this.usernameField.textProperty()
        .bindBidirectional(viewModel.getUsernameField());
    this.passwordField.textProperty()
        .bindBidirectional(viewModel.getPasswordField());
    this.loginButton.setVisible(true);

  }

  @FXML public void onLogin()
  {
    viewModel.login();
    if (viewModel.isLogged() && !viewModel.isAdmin())
    {
      viewHandler.openView("mainPage");
    }
    else if (viewModel.isAdmin() && viewModel.isLogged()){
      //viewHandler.openView(); TODO admin main page
      System.out.println("ADMIN LOGGED");
    }

  }
  @FXML public void onNewAccount()
  {
    viewHandler.openView("registerPage");
  }
  public Region getRoot()
  {
    return root;
  }

}
