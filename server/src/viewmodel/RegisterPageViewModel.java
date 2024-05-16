package viewmodel;

import model.Model;

import java.rmi.RemoteException;

public class RegisterPageViewModel
{
  private Model model;

  public RegisterPageViewModel(Model model) {
    this.model = model;
  }

  public void register(String username, String password, String email, String firstName, String lastName, String phone)
      throws RemoteException
  {
    model.register(username, password, email, firstName, lastName, phone);
  }
}
