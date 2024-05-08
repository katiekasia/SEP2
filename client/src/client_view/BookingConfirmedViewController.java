package client_view;

import client_viewmodel.BookingConfirmedViewModel;
import client_viewmodel.MainPageViewModel;
import javafx.scene.layout.Region;

public class BookingConfirmedViewController
{
  private ViewHandler viewHandler;
  private BookingConfirmedViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, Region root, BookingConfirmedViewModel viewModel)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
  }
}
