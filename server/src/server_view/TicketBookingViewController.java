package server_view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import server_viewmodel.SeatMappingViewModel;
import server_viewmodel.TicketBookingViewModel;

public class TicketBookingViewController
{

    private Region root;
    private TicketBookingViewModel viewModel;
    private ViewHandler viewHandler;

    @FXML private Button reserveButton;

    public void init(ViewHandler viewHandler, TicketBookingViewModel viewModel, Region root)
    {
      this.viewHandler = viewHandler;
      this.viewModel = viewModel;
      this.root = root;
      reserveButton.setOnAction(event -> {

      });
    }
  }
