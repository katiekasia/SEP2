package server_viewmodel;

import javafx.beans.property.SimpleStringProperty;
import server_model.Model;

  public class TicketConfirmationViewModel
  {
    private Model model;
    private ViewState viewState;

    public TicketConfirmationViewModel(Model model)
    {
      this.model = model;

    }
    public ViewState getViewState()
    {
      return viewState;
    }
}

