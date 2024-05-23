package viewmodel;

import model.Model;

public class AddScreeningViewModel
{
  private Model model;
  private ViewState viewState;

  public AddScreeningViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
  }
  public ViewState getViewState()
  {
    return viewState;
  }

}
