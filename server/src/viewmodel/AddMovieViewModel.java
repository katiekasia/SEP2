package viewmodel;


import model.Model;

public class AddMovieViewModel
{
  private Model model;
  private ViewState viewState;

  public AddMovieViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
  }
//
  public ViewState getViewState()
  {
    return viewState;
  }
//
//  public void addMovie(String name, String length, String description, String genre, LocalDate releaseDate)
//  {
//    model.addMovie(name, length, description, genre, releaseDate);
//  }
}
