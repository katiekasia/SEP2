package server_viewmodel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import server_model.Model;
import server_view.ViewHandler;

public class SnackSelectionViewModel
{
  private Region root;
  private SnackSelectionViewModel viewModel;
  private ViewHandler viewHandler;
  Model model;

  @FXML private Button reserveButton;

  public SnackSelectionViewModel(Model model) {
    this.model = model;
  }
  public void init(ViewHandler viewHandler, SnackSelectionViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

  }

}