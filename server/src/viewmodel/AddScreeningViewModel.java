package viewmodel;

import model.Model;
import model.Movie;

import java.time.LocalDate;
import java.util.ArrayList;

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


<<<<<<< Updated upstream
  public ArrayList<Movie> getAllMovies() {
      return model.getAllMovies();
}

public void addScreening(int hour, int minute, LocalDate date, String movieTitle, int roomID)
{
model.newScreening(hour,minute,date,movieTitle,roomID);
=======
  //public ArrayList<Movie> getAllMovies() {
//      return model.getAllMovies();
//}

public void addScreening(int hour, int minute, LocalDate date, String movieTitle, int roomID)
{
  //model.newScreening(hour,minute,date,movieTitle,roomID);
>>>>>>> Stashed changes
}
  }
