package server_view;

import client_view.ViewHandler;
import client_viewmodel.SeatMappingViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
public class SeatMappingViewController
{
  private client_viewmodel.SeatMappingViewModel viewModel;
  private client_view.ViewHandler viewHandler;

  @FXML private Button reserveButton;

  public void init(ViewHandler viewHandler, SeatMappingViewModel viewModel) {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    reserveButton.setOnAction(event -> {

    });
  }
}
