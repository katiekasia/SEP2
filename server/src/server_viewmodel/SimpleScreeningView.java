package server_viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import server_model.Screening;

public class SimpleScreeningView
{
private StringProperty time;
  private StringProperty date;
  private StringProperty movie;
  private IntegerProperty room;

  public SimpleScreeningView(Screening screening){
time = new SimpleStringProperty(screening.getTime());
date = new SimpleStringProperty(screening.getDate().toString());
movie = new SimpleStringProperty(screening.getMovie().getName());
room = new SimpleIntegerProperty(screening.getRoom().getRoomID());
  }

  public void setRoom(int room)
  {
    this.room.set(room);
  }

  public void setMovie(String movie)
  {
    this.movie.set(movie);
  }

  public void setDate(String date)
  {
    this.date.set(date);
  }

  public void setTime(String time)
  {
    this.time.set(time);
  }

  public StringProperty timeProperty()
  {
    return time;
  }

  public IntegerProperty roomProperty()
  {
    return room;
  }

  public StringProperty movieProperty()
  {
    return movie;
  }

  public StringProperty dateProperty()
  {
    return date;
  }

}
