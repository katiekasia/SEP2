package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Movie;
/**
 * Class for a simplified view of a movie.
 *  It holds the title of the movie as a string property.
 *
 * Used to display movie information to the user interface
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class SimpleMovieView
{
  private StringProperty title;
  /**
   * Initialises a SimpleMovieView object with the specified Movie object.
   * Initialises the title property with the name of the movie.
   *
   * @param movie The Movie object.
   */
  public SimpleMovieView(Movie movie){
    title = new SimpleStringProperty(movie.getName());
  }
  /**
   * Sets the title of the movie.
   *
   * @param title The title of the movie.
   */
  public void setTitle(String title)
  {
    this.title.set(title);
  }
  /**
   * Gets the title of the movie.
   *
   * @return The title of the movie.
   */
  public String getTitle()
  {
    return title.get();
  }
  /**
   * Returns the StringProperty representing the title of the movie.
   *
   * @return The StringProperty for the title of the movie.
   */
  public StringProperty titleProperty()
  {
    return title;
  }
}
