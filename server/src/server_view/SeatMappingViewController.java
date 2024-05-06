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

  @FXML private Button reserveButton;

  public void init(ViewHandler viewHandler, SeatMappingViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    reserveButton.setOnAction(event -> {

    });
  }
}

