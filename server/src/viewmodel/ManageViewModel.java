package viewmodel;

import javafx.application.Platform;
import javafx.scene.control.Alert;
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
  private boolean isCurrent;


  public ManageViewModel(Model model, ViewState viewState)
  {
    this.model= model;
    this.viewState= viewState;
    property = new PropertyChangeSupport(this);
    this.model.addListener(this);
    isCurrent = false;
  }
  public String getUsername()
  {
    return viewState.getUser().getUsername();
  }

  public void setCurrent(boolean current)
  {
    isCurrent = current;
  }

  public void deleteAccount()
  {
    String username = viewState.getUser().getUsername();
    model.deleteAccount(username);
  }

  public void updateUserInDatabase(User user, String previousUsername)
  {
    User newUser =viewState.getUser();
    try
    {
      model.updateUser(user, previousUsername);
    }catch (Exception e){
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText(e.getMessage());
      alert.showAndWait();
    }

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
      if (evt.getPropertyName().equals("fatalError") && isCurrent){
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
