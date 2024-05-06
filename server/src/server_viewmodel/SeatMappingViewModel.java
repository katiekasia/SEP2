package server_viewmodel;

import server_model.Model;
import server_view.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SeatMappingViewModel {
  private Model model;

  public SeatMappingViewModel(Model model) {
    this.model = model;
  }

}