package server_view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import server_viewmodel.SeatMappingViewModel;

public class SeatMappingViewController
{
  private Region root;
  private SeatMappingViewModel viewModel;
  private ViewHandler viewHandler;

  @FXML private Button back;
  @FXML private Button confirm;
  @FXML private Button A1;
  @FXML private Button A2;
  @FXML private Button A3;
  @FXML private Button A4;
  @FXML private Button A5;
  @FXML private Button A6;
  @FXML private Button A7;
  @FXML private Button A8;
  @FXML private Button A9;
  @FXML private Button A10;
  @FXML private Button A11;
  @FXML private Button B1;
  @FXML private Button B2;
  @FXML private Button B3;
  @FXML private Button B4;
  @FXML private Button B5;
  @FXML private Button B6;
  @FXML private Button B7;
  @FXML private Button B8;
  @FXML private Button B9;
  @FXML private Button B10;
  @FXML private Button B11;
  @FXML private Button C1;
  @FXML private Button C2;
  @FXML private Button C3;
  @FXML private Button C4;
  @FXML private Button C5;
  @FXML private Button C6;
  @FXML private Button C7;
  @FXML private Button C8;
  @FXML private Button C9;
  @FXML private Button C10;
  @FXML private Button C11;
  @FXML private Button D1;
  @FXML private Button D2;
  @FXML private Button D3;
  @FXML private Button D4;
  @FXML private Button D5;
  @FXML private Button D6;
  @FXML private Button D7;
  @FXML private Button D8;
  @FXML private Button D9;
  @FXML private Button D10;
  @FXML private Button D11;
  public void init(ViewHandler viewHandler, SeatMappingViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
  }

  @FXML public void onConfirm()
  {
    viewHandler.openView("ticketConfirmation");
  }
  @FXML public void onBack()
  {
    viewHandler.openView("transitionPage");
  }
}

