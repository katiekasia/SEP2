package server_viewmodel;

import server_model.Model;

public class LoginViewModel
{
  private Model model;

  public LoginViewModel(Model model) {
    this.model = model;
  }

  public void login(String username, String password)
  {
    model.logIn(username, password);
  }
}
