package viewmodel;

import javafx.application.Platform;
import model.Model;
import model.User;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ManageViewModel implements PropertyChangeListener,
    UnnamedPropertyChangeSubject
{
  private Model model;

  private ViewState viewState;
  private PropertyChangeSupport property;


  public ManageViewModel(Model model, ViewState viewState)
  {
    this.model= model;
    this.viewState= viewState;
    property = new PropertyChangeSupport(this);
    this.model.addListener(this);
  }
  public String getUsername()
  {
    return viewState.getUser().getUsername();
  }

  public void deleteAccount()
  {
    String username = viewState.getUser().getUsername();
    model.deleteAccount(username);
  }

  public void updateUserInDatabase(User user, String previousUsername)

  {
    User newUser =viewState.getUser();
    model.updateUser(user, previousUsername);
  }
  public String getPassword()
  {
    return viewState.getUser().getPassword();
  }
  public String getPhoneNumber()
  {
    return viewState.getUser().getPhoneNumber();
  }
  public String getName()
  {
    return viewState.getUser().getFstName();
  }
  public String getSurname()
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

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() ->{
      if (evt.getPropertyName().equals("fatalError")){
        property.firePropertyChange(evt.getPropertyName(),null,evt.getNewValue());
      }});
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }
}
