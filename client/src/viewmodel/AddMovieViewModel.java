package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import model.Model;

import java.beans.PropertyChangeListener;

public class AddMovieViewModel
{
  private Model model;
  private ViewState viewState;

  public AddMovieViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
  }
  public ViewState getViewState()
  {
    return viewState;
  }



}
