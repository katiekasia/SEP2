package viewmodel;

import model.Model;

public class EditPricesViewModel
{
  private Model model;
  private ViewState viewState;

  public EditPricesViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
  }
  public ViewState getViewState()
  {
    return viewState;
  }

}
