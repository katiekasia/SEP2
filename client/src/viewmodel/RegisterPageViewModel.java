package viewmodel;

import model.Model;

public class RegisterPageViewModel
{
  private Model model;

  public RegisterPageViewModel(Model model) {
    this.model = model;
  }

  public void register(String username, String password, String email, String firstName, String lastName, String phone) {
    try
    {
      model.register(username, password, email, firstName, lastName, phone);
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
