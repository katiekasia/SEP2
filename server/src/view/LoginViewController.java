package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewmodel.LoginViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginViewController implements PropertyChangeListener
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
      viewHandler.openView("adminPage");
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
