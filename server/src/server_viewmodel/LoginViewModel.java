package server_viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import server_model.Model;

public class LoginViewModel
{
  private Model model;
  private StringProperty usernameField;
  private StringProperty passwordField;

  public LoginViewModel(Model model) {
    this.model = model;
    this.usernameField= new SimpleStringProperty();
    this.passwordField= new SimpleStringProperty();
  }

  public void login(String username, String password)
  {
    model.logIn(username, password);
  }

  public StringProperty getUsernameField()
  {
    return usernameField;
  }
  public StringProperty getPasswordField()
  {
     return passwordField;
  }

  public void reset()
  {
    usernameField.set("");
    passwordField.set("");
  }
}
