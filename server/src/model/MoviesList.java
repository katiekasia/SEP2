package model;

import java.util.ArrayList;

public class MoviesList
{
  private ArrayList<Movie> movies;
  public MoviesList(ArrayList<Movie> movies){
    this.movies = movies;
  }

  public ArrayList<Movie> getMovies()
  {
    return movies;
  }
  public Movie getMovieByTitle(String title){
    for (Movie movie : movies){
      if (movie.getName().equals(title)){
        return movie;
      }
    }
    throw new IllegalArgumentException("No such movie found.");
  }
}
