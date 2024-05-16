package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.User;
import model.DataBaseHandler;
import viewmodel.ManageViewModel;
import viewmodel.SimpleScreeningView;
import viewmodel.ViewState;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ManageViewController
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
  @FXML private Button fidelityPoints;
  @FXML private Button manage;
  @FXML private Button signOut;
  @FXML private Button ticketConfirmation;
  @FXML private Button save;



  public void init(ViewHandler viewHandler, Region root, ManageViewModel viewModel)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    this.save.setDisable(true);

    this.viewState= viewModel.getViewState();

    try
    {
      this.username.setText(viewModel.getUsername());
      this.password.setText(viewModel.getPassword());
      this.name.setText(viewModel.getName());
      this.surname.setText(viewModel.getSurname());
      this.phoneNumber.setText(viewModel.getPhoneNumber());
      this.email.setText(viewModel.getEmail());
    }
    catch (Exception e)
    {
      System.out.println("ManageViewController username and password error");
      e.printStackTrace();
    }


    // this.username.textProperty().bind(viewModel.getUsername());
  }

  @FXML public void onManage()
  {
    viewHandler.openView("managePage");
  }

  @FXML public void onSignOut()
  {
    viewHandler.openView("login");
  }

  @FXML public void onFidelityPoints()
  {

  }
  @FXML public void onTicketConfirmation()
  {
    viewHandler.openView("ticketConfirmation");
  }
  @FXML public void onDelete()
  {

  }
  @FXML public void onEdit()
  {
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
    try
    {
      viewModel.updateUserInDatabase();

      username.setEditable(false);
      password.setEditable(false);
      name.setEditable(false);
      surname.setEditable(false);
      phoneNumber.setEditable(false);
      email.setEditable(false);
      save.setDisable(true);
      System.out.println("User information saved successfully.");
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }


}