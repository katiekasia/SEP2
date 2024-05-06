package client_viewmodel;

import client_model.Model;

public class RegisterPageViewModel {
  private Model model;

  public RegisterPageViewModel(Model model) {
    this.model = model;
  }

  public void register(String username, String password, String email, String firstName, String lastName, String phone) {
    model.register(username, password, email, firstName, lastName, phone);
  }
}
