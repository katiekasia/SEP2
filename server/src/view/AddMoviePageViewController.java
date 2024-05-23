package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import viewmodel.AddMovieViewModel;
import viewmodel.SimpleScreeningView;
import viewmodel.ViewState;

public class AddMoviePageViewController
{
  private Region root;
  private AddMovieViewModel viewModel;
  private ViewHandler viewHandler;
  private ViewState viewState;
  private SimpleScreeningView selected;

  @FXML private Label username;
  @FXML private Button signOut;
  @FXML private Button add;
  @FXML private Button cancel;

  public void init(ViewHandler viewHandler, Region root, AddMovieViewModel viewModel)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    this.viewState = viewModel.getViewState();

  }

  @FXML public void onCancel()
  {

  }
  @FXML public void onAdd()
  {

  }

}
