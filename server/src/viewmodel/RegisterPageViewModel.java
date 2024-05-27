package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RegisterPageViewModel implements PropertyChangeListener,
    UnnamedPropertyChangeSubject
{
  private Model model;
  private PropertyChangeSupport property;
  private StringProperty registrationStatus;
  private StringProperty registrationMessage;

  public RegisterPageViewModel(Model model) {
    this.model = model;
    this.registrationStatus = new SimpleStringProperty();
    this.registrationMessage = new SimpleStringProperty();

    property = new PropertyChangeSupport(this);
    this.model.addListener(this);
  }

  public void register(String username, String password, String email, String firstName, String lastName, String phone) {
    try {
      model.register(username, password, email, firstName, lastName, phone);
      registrationStatus.set("SUCCESS");
      registrationMessage.set("Your account has been created successfully.");
    } catch (Exception e) {
      registrationStatus.set("ERROR");
      registrationMessage.set("Registration Failed: " + e.getMessage());
    }
  }

  public StringProperty registrationStatusProperty() {
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