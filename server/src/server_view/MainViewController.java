package server_view;

import server_view.ViewHandler;
import server_viewmodel.MainPageViewModel;

public class MainViewController
{
  private MainPageViewModel viewModel;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, MainPageViewModel viewModel)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;

  }
}
