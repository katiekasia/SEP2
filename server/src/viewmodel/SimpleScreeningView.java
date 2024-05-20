  package viewmodel;

  import javafx.beans.property.IntegerProperty;
  import javafx.beans.property.SimpleIntegerProperty;
  import javafx.beans.property.SimpleStringProperty;
  import javafx.beans.property.StringProperty;
  import model.*;

  public class SimpleScreeningView
  {
  private StringProperty time;
  private IntegerProperty hour;
  private IntegerProperty minute;
    private StringProperty date;
    private StringProperty movie;
    private IntegerProperty room;
    private StringProperty length;
    private StringProperty genre;
    //for Ticket Confirmation table
    private StringProperty seatID;






    public SimpleScreeningView(Screening screening){

      minute = new SimpleIntegerProperty(screening.getMinute());
      hour = new SimpleIntegerProperty(screening.getHour());
  time = new SimpleStringProperty(screening.getTime());
  genre = new SimpleStringProperty(screening.getMovie().getGenre());
  date = new SimpleStringProperty(screening.getDate().toString());
  movie = new SimpleStringProperty(screening.getMovie().getName());
  room = new SimpleIntegerProperty(screening.getRoom().getRoomID());
  length = new SimpleStringProperty(screening.getMovie().getLenghth());

    }

    public void setRoom(int room)
    {
      this.room.set(room);
    }

    public void setSeatID(String seatID) {
      this.seatID.set(seatID);
    }
    public StringProperty seatIDProperty() {
      return seatID;
    }
    public String getSeatID() {
      return seatID.get();
    }
    public void setLength(String length)
    {
      this.length.set(length);
    }

    public String getTime()
    {
      return time.get();
    }

    public String getMovie()
    {
      return movie.get();
    }

    public String getDate()
    {
      return date.get();
    }

    public int getRoom()
    {
      return room.get();
    }

    public StringProperty lengthProperty()
    {
      return length;
    }

    public void setGenre(String genre)
    {
      this.genre.set(genre);
    }

    public StringProperty genreProperty()
    {
      return genre;
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
