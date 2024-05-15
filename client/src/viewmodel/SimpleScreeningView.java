  package viewmodel;

  import model.Screening;
  import model.User;
  import javafx.beans.property.IntegerProperty;
  import javafx.beans.property.SimpleIntegerProperty;
  import javafx.beans.property.SimpleStringProperty;
  import javafx.beans.property.StringProperty;

  public class SimpleScreeningView
  {
  private StringProperty time;
    private StringProperty date;
    private StringProperty movie;
    private IntegerProperty room;
    private StringProperty length;
    private StringProperty genre;
    //for Ticket Confirmation table

    private Screening screening;



    public SimpleScreeningView(Screening screening){
      this.screening = screening;
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




    public void setLength(String length)
    {
      this.length.set(length);
    }

    public void setScreening(Screening screening)
    {
      this.screening = screening;
    }



    public Screening getScreening()
    {
      return screening;
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
