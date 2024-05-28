package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Movie;

public class SimpleMovieView
{
  private StringProperty title;
  public SimpleMovieView(Movie movie){
    title = new SimpleStringProperty(movie.getName());
  }

  public void setTitle(String title)
  {
    this.title.set(title);
  }
  public String getTitle()
  {
    return title.get();
  }
  public StringProperty titleProperty()
  {
    return title;
  }
}
