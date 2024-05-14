package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import viewmodel.TicketBookingViewModel;

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
    this.reserveButton.setVisible(true);
  }
  @FXML public void reserveButtonPressed()
  {

    viewHandler.openView("bookingConfirmed");
  }
}
