package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import model.Order;
import model.User;

import java.util.List;

public class LoginViewModel
{
  private ViewState viewState;
  private Model model;
  private StringProperty usernameField;
  private StringProperty passwordField;

  public LoginViewModel(Model model)
  {
    this.model = model;
    this.usernameField = new SimpleStringProperty();
    this.passwordField = new SimpleStringProperty();
    this.viewState = viewState;
  }

  public void login(String username, String password)
  {
    try
    {
      username = usernameField.get();
      password = passwordField.get();
      model.logIn(username, password);
      User loggedInUser = model.getUser();
      viewState.setUser(loggedInUser);
      List<Order> previousOrders = model.getOrdersForUser(loggedInUser.getUsername());
      viewState.setOrders(previousOrders);
    }
    catch (Exception e)
    {
      e.printStackTrace();
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
