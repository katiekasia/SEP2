package client_view;

import client_viewmodel.MainPageViewModel;
import javafx.fxml.FXML;

public class MainPageViewController {
  private MainPageViewModel viewModel;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, MainPageViewModel viewModel) {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;

  }
}
