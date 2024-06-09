package view;

import javafx.scene.layout.Region;
import org.junit.jupiter.api.Test;
import viewmodel.MainPageViewModel;
import viewmodel.SimpleScreeningView;
import viewmodel.ViewState;

import static org.junit.jupiter.api.Assertions.*;

class MainViewControllerTest
{
  private Region root;
  private MainPageViewModel viewModel;
  private ViewHandler viewHandler;
  private ViewState viewState;
  private SimpleScreeningView selected;

  @Test void onSignOut()
  {
    viewModel.setCurrent(false);
    viewState.logOut();
    viewHandler.openView("login");
  }
}