package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.User;
import viewmodel.ManageViewModel;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Optional;

public class ManageViewController implements PropertyChangeListener
{ private Region root;
  private ManageViewModel viewModel;
  private ViewHandler viewHandler;
  private ViewState viewState;

  @FXML private TextField username;
  @FXML private TextField password;
  @FXML private TextField name;
  @FXML private TextField surname;
  @FXML private TextField phoneNumber;
  @FXML private TextField email;
  @FXML private Button screenings;
  @FXML private Button signOut;
  @FXML private Button ticketConfirmation;
  @FXML private Button save;



  public void init(ViewHandler viewHandler, Region root, ManageViewModel viewModel)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    this.save.setDisable(true);
    setFields(true);

    this.viewState= viewModel.getViewState();


      this.username.setText(viewModel.getUsername());
      this.password.setText(viewModel.getPassword());
      this.name.setText(viewModel.getName());
      this.surname.setText(viewModel.getSurname());
      this.phoneNumber.setText(viewModel.getPhoneNumber());
      this.email.setText(viewModel.getEmail());
    }






  private void setFields(boolean disable){
    username.setDisable(disable);
    password.setDisable(disable);
    name.setDisable(disable);
    surname.setDisable(disable);
    phoneNumber.setDisable(disable);
    email.setDisable(disable);
  }


  @FXML public void onScreening()
  {
    viewHandler.openView("mainPage");
  }

  @FXML public void onSignOut()
  {
    viewHandler.openView("login");
  }

  @FXML public void onOrderConfirmation()
  {
    viewHandler.openView("orderConfirmation");
  }
  @FXML public void onDelete()
  {
    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationAlert.setTitle("Confirm Account Deletion");
    confirmationAlert.setHeaderText("Are you sure you want to delete your account?");
    confirmationAlert.setContentText("This action cannot be undone.");

    Optional<ButtonType> result = confirmationAlert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK)
    {
      try
      {
        viewModel.deleteAccount();
        viewHandler.openView("login");
      }
      catch (Exception e)
      {
        showAlert("Deletion Failed",
            "An error occurred while trying to delete your account. " + e.getMessage());
      }
    }
  }
  @FXML public void onEdit()
  {
    setFields(false);
    username.setEditable(true);
    password.setEditable(true);
    name.setEditable(true);
    surname.setEditable(true);
    phoneNumber.setEditable(true);
    email.setEditable(true);
    save.setDisable(false);
  }

  @FXML public void onSave()
  {
      setFields(true);
      username.setEditable(false);
      password.setEditable(false);
      name.setEditable(false);
      surname.setEditable(false);
      phoneNumber.setEditable(false);
      email.setEditable(false);
      save.setDisable(true);

      String username1 = username.getText();
      String name1 = name.getText();
      String surname1 = surname.getText();
      String phoneNumber1 = phoneNumber.getText();
      String email1 = email.getText();
      String password1 = password.getText();
      User user = new User(username1, name1, surname1, phoneNumber1, email1, password1);

      //the user is not updated before geting the info for model but whyy???
      User oldUser= viewState.getUser();
      viewState.setUser(user);

      viewModel.updateUserInDatabase(user, oldUser.getUsername());

    }

  private void showAlert(String deletionFailed, String s)
  {
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() ->{
      if (evt.getPropertyName().equals("fatalError")){
        viewHandler.close();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("A fatal error has occured: " + evt.getNewValue());
        alert.showAndWait();
      }});
  }
}