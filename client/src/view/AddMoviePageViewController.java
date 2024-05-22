package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewmodel.AdminPageViewModel;
import viewmodel.SimpleScreeningView;
import viewmodel.ViewState;

public class AddMoviePageViewController
{
  private Region root;
  private AdminPageViewModel viewModel;
  private ViewHandler viewHandler;
  private ViewState viewState;
  private SimpleScreeningView selected;

  @FXML private Label username;
  @FXML private Button signOut;
  @FXML private Button add;
  @FXML private Button cancel;


  @FXML public void onCancel()
  {

  }
  @FXML public void onAdd()
  {

  }

}
