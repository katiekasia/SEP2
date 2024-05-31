package model;

import java.util.ArrayList;
/**
 * Class representing a list of movies
 *
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class MoviesList
{
  private ArrayList<Movie> movies;
  /**
   * one argument constructor
   * Constructs a new MoviesList object with the specified list of movies.
   *
   * @param movies The list of movies to be included in the MoviesList
   */
  public MoviesList(ArrayList<Movie> movies){
    this.movies = movies;
  }
  /**
   * Retrieves the list of movies.
   *
   * @return The list of movies
   */
  public ArrayList<Movie> getMovies()
  {
    return movies;
  }

  /**
   * Retrieves a movie by its title.
   *
   * @param title The title of the movie to retrieve
   * @return The movie with the specified title
   * @throws IllegalArgumentException if no movie with the specified title is found
   */
  public Movie getMovieByTitle(String title){
    for (Movie movie : movies){
      if (movie.getName().equals(title)){
        return movie;
      }
    }
    throw new IllegalArgumentException("No such movie found.");
  }
}
