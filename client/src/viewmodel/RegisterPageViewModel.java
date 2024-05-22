package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class RegisterPageViewModel {
  private Model model;
  private StringProperty registrationStatus;
  private StringProperty registrationMessage;

  public RegisterPageViewModel(Model model) {
    this.model = model;
    this.registrationStatus = new SimpleStringProperty();
    this.registrationMessage = new SimpleStringProperty();
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
}