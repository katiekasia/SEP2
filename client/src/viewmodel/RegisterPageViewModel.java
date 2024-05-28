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

public class RegisterPageViewModel implements PropertyChangeListener,
    UnnamedPropertyChangeSubject
{
  private Model model;
  private ViewState viewState;
  private PropertyChangeSupport property;

  private StringProperty usernameProperty;
  private StringProperty passwordProperty;
  private StringProperty emailProperty;
  private StringProperty fNameProperty;
  private StringProperty lNameProperty;
  private StringProperty phoneProperty;
  private boolean registrationStatus;
  private StringProperty registrationMessage;

  public RegisterPageViewModel(Model model, ViewState viewState) {
    this.model = model;
    this.registrationStatus = false;
    this.registrationMessage = new SimpleStringProperty();
    this.usernameProperty= new SimpleStringProperty();
    this.fNameProperty = new SimpleStringProperty();
    this.lNameProperty = new SimpleStringProperty();
    this.emailProperty= new SimpleStringProperty();
    this.phoneProperty = new SimpleStringProperty();
    this.passwordProperty = new SimpleStringProperty();

    property = new PropertyChangeSupport(this);
    this.model.addListener(this);
  }

  public void register() {
    try {
      User user = new User(usernameProperty.get(),fNameProperty.get(),lNameProperty.get(),phoneProperty.get(),emailProperty.get(),passwordProperty.get());

      model.register(user.getUsername(),user.getPassword(),user.getEmail(),user.getFstName(),user.getLstName(),user.getPhoneNumber());
      registrationStatus=true;
      registrationMessage.set("Your account has been created successfully.");

    }
    catch (Exception e) {
      registrationStatus=false;
      registrationMessage.set("Registration Failed: " + e.getMessage());
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(e.getMessage());
        alert.showAndWait();
    }
  }

  public String getUsername()
  {
    return usernameProperty.get();
  }
  public String getPassword()
  {
    return passwordProperty.get();
  }
  public String getFName()
  {
    return fNameProperty.get();
  }
  public String getLName()
  {
    return lNameProperty.get();
  }
  public String getPhone()
  {
    return phoneProperty.get();
  }

  public String getEmail()
  {
    return emailProperty.get();
  }

  public StringProperty getUsernameProperty()
  {
    return usernameProperty;
  }

  public StringProperty getPasswordProperty()
  {
    return passwordProperty;
  }

  public StringProperty getFNameProperty()
  {
    return fNameProperty;
  }

  public StringProperty getLNameProperty()
  {
    return lNameProperty;
  }

  public StringProperty getPhoneProperty()
  {
    return phoneProperty;
  }

  public StringProperty getEmailProperty()
  {
    return emailProperty;
  }

  public boolean registrationStatusProperty() {
    return registrationStatus;
  }

  public StringProperty registrationMessageProperty() {
    return registrationMessage;
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