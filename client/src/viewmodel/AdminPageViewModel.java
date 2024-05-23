package viewmodel;

import model.Model;

public class AdminPageViewModel
{
  private Model model;
  private ViewState viewState;

  public AdminPageViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
  }
  public ViewState getViewState()
  {
    return viewState;
  }

}
