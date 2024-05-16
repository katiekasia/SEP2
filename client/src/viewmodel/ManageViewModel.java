package viewmodel;

import model.DataBaseHandler;
import model.Model;
import model.User;

import java.rmi.RemoteException;

public class ManageViewModel
{
  private Model model;

  private ViewState viewState;

  //all the others

  public void updateUserInDatabase() throws RemoteException
  {
    model.updateUser(viewState.getUser());
  }
  public ManageViewModel(Model model, ViewState viewState)
  {
    this.model= model;
    this.viewState= viewState;

  }
  public String getUsername() throws RemoteException
  {
    return viewState.getUser().getUsername();
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
