package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import model.Model;
import model.User;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel implements PropertyChangeListener,
    UnnamedPropertyChangeSubject
{
  private ViewState viewState;
  private Model model;
  private PropertyChangeSupport property;
  private StringProperty usernameField;
  private StringProperty passwordField;
  private boolean logged;
  private boolean admin;
  private boolean isCurrent;

  public LoginViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.usernameField = new SimpleStringProperty();
    this.passwordField = new SimpleStringProperty();
    this.viewState = viewState;
    property = new PropertyChangeSupport(this);
    logged = false;
    admin = false;
    model.addListener(this);
    isCurrent = false;

  }

  public boolean isAdmin()
  {
    return admin;
  }

  public void login()
  {
    try
    {
      viewState.setUser(model.logIn(usernameField.get(), passwordField.get()));
      logged = true;


    }
   catch(Exception e)
   {
     logged = false;
     try
     {
       admin = model.logInAdmin(usernameField.get(), passwordField.get());
       viewState.setUser(new User("Admin","","","","",passwordField.get()));
       logged = true;
       admin = true;
     }catch (Exception s){
       logged = false;
       admin = false;
       reset();
       Alert alert= new Alert(Alert.AlertType.ERROR);
       alert.setHeaderText(s.getMessage());
       alert.showAndWait();
     }
   }
  }

  public boolean isLogged()
  {
    return logged;
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
    logged = false;
    admin = false;
  }

  public void setCurrent(boolean current)
  {
    isCurrent = current;
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
