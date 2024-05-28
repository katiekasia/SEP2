package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import viewmodel.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EditPricesViewController implements PropertyChangeListener
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

    viewModel.bindCandy(candy.textProperty());
    viewModel.bindCandy(redbull.textProperty());
    viewModel.bindCandy(oreo.textProperty());
    viewModel.bindCandy(tuborg.textProperty());
    viewModel.bindCandy(cola.textProperty());
    viewModel.bindCandy(pepsi.textProperty());
    viewModel.bindCandy(peanuts.textProperty());
    viewModel.bindCandy(popcorn.textProperty());
    viewModel.bindCandy(standardTicket.textProperty());
    viewModel.bindCandy(VIPTicket.textProperty());
    viewModel.bindCandy(fanta.textProperty());
    viewModel.bindCandy(nachos.textProperty());
    viewModel.reset();
    viewModel.addListener(this);

  }

    @FXML public void onAdd()
  {
    viewModel.changePressed();
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

