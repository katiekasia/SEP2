package server_viewmodel;

import server_view.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class SeatMappingViewModel
{
  private client_viewmodel.SeatMappingViewModel viewModel;
  private ViewHandler viewHandler;

  @FXML private Button reserveButton;

  public void init(ViewHandler viewHandler,
      client_viewmodel.SeatMappingViewModel viewModel)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    reserveButton.setOnAction(event -> {

    });
  }
}
