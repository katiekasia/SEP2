package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.SnackSelectionViewModel;
import viewmodel.ViewState;

public class SnackSelectionViewController
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

    viewModel.bindCandy(candiesAmount.textProperty());
    viewModel.bindNachos(nachosAmount.textProperty());
    viewModel.bindOreo(oreoAmount.textProperty());
    viewModel.bindPopcorn(popcornAmount.textProperty());
    viewModel.bindPeanuts(peanutsAmount.textProperty());
    viewModel.bindCola(colaAmount.textProperty());
    viewModel.bindPepsi(pepsiAmount.textProperty());
    viewModel.bindFanta(fantaAmount.textProperty());
    viewModel.bindTuborg(tuborgAmount.textProperty());
    viewModel.bindRedbull(redbullAmount.textProperty());

    viewModel.reset();
  }

  @FXML public void onConfirm(){
    viewModel.refreshUser();
    viewHandler.openView("orderConfirmation");
  }
  @FXML public void onAdd(){
    try
    {
      viewModel.addPressed();
    }catch (Exception e){
      e.printStackTrace();
    }
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
}
