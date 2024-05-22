package viewmodel;

import model.Model;
import model.User;

import java.rmi.RemoteException;

public class ManageViewModel
{
  private Model model;

  private ViewState viewState;

  //all the others

  public ManageViewModel(Model model, ViewState viewState)
  {
    this.model= model;
    this.viewState= viewState;

  }
  public String getUsername() throws RemoteException
  {
    return viewState.getUser().getUsername();
  }

  public void updateUserInDatabase(User user, String previousUsername) throws RemoteException
  {
    User newUser =viewState.getUser();
    model.updateUser(user, previousUsername);

    for(int i=0; i<1; i++)
    {
      System.out.println("MANAGE VIEW MODEL\nUser name should not be '1'");
      System.out.println(newUser.getUsername());
    }
    System.out.println("User information added to model to client.\n");

  }
  public String getPassword() throws RemoteException
  {
    return viewState.getUser().getPassword();
  }
  public String getPhoneNumber() throws RemoteException
  {
    return viewState.getUser().getPhoneNumber();
  }
  public String getName() throws RemoteException
  {
    return viewState.getUser().getFstName();
  }
  public String getSurname() throws RemoteException
  {
    return viewState.getUser().getLstName();
  }
  public ViewState getViewState()
  {
    return viewState;
  }

  public String getEmail()
  {
    return viewState.getUser().getEmail();
  }
}
