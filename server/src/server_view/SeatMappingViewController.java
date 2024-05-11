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
  @FXML private Button a3;
  @FXML private Button a4;
  @FXML private Button a5;
  @FXML private Button a6;
  @FXML private Button a7;
  @FXML private Button a8;
  @FXML private Button a9;
  @FXML private Button a10;
  @FXML private Button a11;
  @FXML private Button b1;
  @FXML private Button b2;
  @FXML private Button b3;
  @FXML private Button b4;
  @FXML private Button b5;
  @FXML private Button b6;
  @FXML private Button b7;
  @FXML private Button b8;
  @FXML private Button b9;
  @FXML private Button b10;
  @FXML private Button b11;
  @FXML private Button c1;
  @FXML private Button c2;
  @FXML private Button c3;
  @FXML private Button c4;
  @FXML private Button c5;
  @FXML private Button c6;
  @FXML private Button c7;
  @FXML private Button c8;
  @FXML private Button c9;
  @FXML private Button c10;
  @FXML private Button c11;
  @FXML private Button d1;
  @FXML private Button d2;
  @FXML private Button d3;
  @FXML private Button d4;
  @FXML private Button d5;
  @FXML private Button d6;
  @FXML private Button d7;
  @FXML private Button d8;
  @FXML private Button d9;
  @FXML private Button d10;
  @FXML private Button d11;
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

