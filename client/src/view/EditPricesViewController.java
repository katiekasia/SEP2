package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import viewmodel.*;

public class EditPricesViewController
{
  private Region root;
  private EditPricesViewModel viewModel;
  private ViewHandler viewHandler;

  private ViewState viewState;
  @FXML private TextField standardTicket;
  @FXML private TextField VIPTicket;
  @FXML private TextField fanta;
  @FXML private TextField cola;
  @FXML private TextField pepsi;
  @FXML private TextField redbull;
  @FXML private TextField tuborg;
  @FXML private TextField candy;
  @FXML private TextField nachos;
  @FXML private TextField oreo;
  @FXML private TextField popcorn;
  @FXML private TextField peanuts;



  public void init(ViewHandler viewHandler, Region root, EditPricesViewModel viewModel)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    this.viewState = viewModel.getViewState();


  }

    @FXML public void onAdd()
  {
    viewHandler.openView("adminPage");
  }

  @FXML public void onCancel()
  {
    viewHandler.openView("adminPage");
  }

  @FXML public void onSignOut()
  {
    viewState.logOut();
    viewHandler.openView("login");
  }

}

