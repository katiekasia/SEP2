package client_viewmodel;

import client_mediator.RemoteModel;
import client_model.Model;
import javafx.beans.property.StringProperty;

public class MainPageViewModel
{
  private Model model;
  private StringProperty username;

  public MainPageViewModel(Model model)
  {
    this.model = model;
  }

  public StringProperty getUsername()
  {
    return username;
  }

}

