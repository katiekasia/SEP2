package client_view;

import client_viewmodel.SeatMappingViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SeatMappingViewController {
  private SeatMappingViewModel viewModel;
  private ViewHandler viewHandler;

  @FXML private Button reserveButton;

  public void init(ViewHandler viewHandler, SeatMappingViewModel viewModel) {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    reserveButton.setOnAction(event -> {

    });
  }
}
