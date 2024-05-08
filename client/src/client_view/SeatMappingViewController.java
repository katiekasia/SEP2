package client_view;

import client_viewmodel.SeatMappingViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;

public class SeatMappingViewController {
  private SeatMappingViewModel viewModel;
  private ViewHandler viewHandler;

  @FXML private Button reserveButton;

  public void init(ViewHandler viewHandler, SeatMappingViewModel viewModel,
      Region root) {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    reserveButton.setOnAction(event -> {

    });
  }
}
