package view;

import viewmodel.BookingConfirmedViewModel;
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
