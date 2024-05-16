package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import model.Model;

public class LoginViewModel
{
  private ViewState viewState;
  private Model model;
  private StringProperty usernameField;
  private StringProperty passwordField;

  public LoginViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.usernameField = new SimpleStringProperty();
    this.passwordField = new SimpleStringProperty();
    this.viewState = viewState;
  }

  public void login()
  {
    try
    {
      viewState.setUser(model.logIn(usernameField.get(), passwordField.get()));
    }
   catch(Exception e)
   {
     Alert alert= new Alert(Alert.AlertType.ERROR);
     alert.setHeaderText(e.getMessage());
     alert.showAndWait();
   }
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
