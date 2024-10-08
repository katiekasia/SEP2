package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewmodel.SnackSelectionViewModel;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SnackSelectionViewController implements PropertyChangeListener
{
  private Region root;
  private SnackSelectionViewModel viewModel;
  private ViewHandler viewHandler;
  private ViewState viewState;

  @FXML private Label candyPrice;
  @FXML private Label nachosPrice;
  @FXML private Label oreoPrice;
  @FXML private Label popcornPrice;
  @FXML private Label peanutsPrice;
  @FXML private Label colaPrice;
  @FXML private Label pepsiPrice;
  @FXML private Label fantaPrice;
  @FXML private Label tuborgPrice;
  @FXML private Label redbullPrice;

  @FXML private SplitMenuButton Size;

  @FXML private TextField candiesAmount;
  @FXML private TextField nachosAmount;
  @FXML private TextField oreoAmount;
  @FXML private TextField popcornAmount;
  @FXML private TextField peanutsAmount;
  @FXML private TextField colaAmount;
  @FXML private TextField pepsiAmount;
  @FXML private TextField fantaAmount;
  @FXML private TextField tuborgAmount;
  @FXML private TextField redbullAmount;

  @FXML private Label totalPrice;
  @FXML private Button addButton;

  public void init(ViewHandler viewHandler, SnackSelectionViewModel viewModel, Region root){
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    viewState = viewModel.getViewState();
this.viewModel.setCurrent(true);
    this.viewModel.addListener(this);

    addButton.setDisable(true);

    candyPrice.textProperty().bind(viewModel.candyPriceProperty());
    nachosPrice.textProperty().bind(viewModel.nachosPriceProperty());
    oreoPrice.textProperty().bind(viewModel.oreoPriceProperty());
    popcornPrice.textProperty().bind(viewModel.popcornPriceProperty());
    peanutsPrice.textProperty().bind(viewModel.peanutsPriceProperty());
    colaPrice.textProperty().bind(viewModel.colaPriceProperty());
    pepsiPrice.textProperty().bind(viewModel.pepsiPriceProperty());
    fantaPrice.textProperty().bind(viewModel.fantaPriceProperty());
    tuborgPrice.textProperty().bind(viewModel.tuborgPriceProperty());
    redbullPrice.textProperty().bind(viewModel.redbullPriceProperty());

    totalPrice.textProperty().bind(viewModel.totalPriceProperty());

    this.viewModel.bindCandy(candiesAmount.textProperty());
    this.viewModel.bindNachos(nachosAmount.textProperty());
    this.viewModel.bindOreo(oreoAmount.textProperty());
    this.viewModel.bindPopcorn(popcornAmount.textProperty());
    this.viewModel.bindPeanuts(peanutsAmount.textProperty());
    this.viewModel.bindCola(colaAmount.textProperty());
    this.viewModel.bindPepsi(pepsiAmount.textProperty());
    this.viewModel.bindFanta(fantaAmount.textProperty());
    this. viewModel.bindTuborg(tuborgAmount.textProperty());
    this. viewModel.bindRedbull(redbullAmount.textProperty());

    this. viewModel.reset();
  }

  @FXML public void onConfirm(){
    viewModel.setCurrent(false);
    viewModel.refreshUser();
    viewHandler.openView("orderConfirmation");
  }
  @FXML public void onAdd(){

      viewModel.addPressed();
    addButton.setDisable(true);
  }
  @FXML public void onS(){
    addButton.setDisable(false);
    viewModel.sizeChosen("S");
  }
  @FXML public void onM(){
    addButton.setDisable(false);
    viewModel.sizeChosen("M");
  }
  @FXML public void onL(){
    addButton.setDisable(false);
    viewModel.sizeChosen("L");
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
