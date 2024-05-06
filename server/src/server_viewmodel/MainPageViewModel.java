package server_viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import server_model.Model;

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
