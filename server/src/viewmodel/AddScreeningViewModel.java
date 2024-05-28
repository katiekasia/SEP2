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



//  public ArrayList<Movie> getAllMovies() {
//      return model.getAllMovies();
//}
//
//public void addScreening(int hour, int minute, LocalDate date, String movieTitle, int roomID)
//{
//model.newScreening(hour,minute,date,movieTitle,roomID);
//
//  //public ArrayList<Movie> getAllMovies() {
////      return model.getAllMovies();
////}
//
//public void addScreening(int hour, int minute, LocalDate date, String movieTitle, int roomID)
//{
//  //model.newScreening(hour,minute,date,movieTitle,roomID);
//
//}
  }
