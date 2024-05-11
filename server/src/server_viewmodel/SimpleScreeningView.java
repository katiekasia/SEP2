  package server_viewmodel;

  import javafx.beans.property.IntegerProperty;
  import javafx.beans.property.SimpleIntegerProperty;
  import javafx.beans.property.SimpleStringProperty;
  import javafx.beans.property.StringProperty;
  import server_model.*;

  public class SimpleScreeningView
  {
  private StringProperty time;
    private StringProperty date;
    private StringProperty movie;
    private IntegerProperty room;
    private IntegerProperty length;
    private StringProperty genre;
    //for Ticket Confirmation table
    private StringProperty seatID;
    private Seat seat;
    private Screening screening;
    private Ticket ticket;
    private StringProperty ticketType;
    private StringProperty snacks;


    public SimpleScreeningView(Screening screening){
      this.screening = screening;
  time = new SimpleStringProperty(screening.getTime());
  genre = new SimpleStringProperty(screening.getMovie().getGenre());
  date = new SimpleStringProperty(screening.getDate().toString());
  movie = new SimpleStringProperty(screening.getMovie().getName());
  room = new SimpleIntegerProperty(screening.getRoom().getRoomID());
  length = new SimpleIntegerProperty(screening.getMovie().getLenghth());

    }
  public SimpleScreeningView(Screening screening, Seat seat, Ticket ticket)
  {
    this.seat= seat;
    this.ticket= ticket;
    seatID = new SimpleStringProperty(seat.getID());
    this.screening = screening;
    time = new SimpleStringProperty(screening.getTime());
    date = new SimpleStringProperty(screening.getDate().toString());
    movie = new SimpleStringProperty(screening.getMovie().getName());
    ticketType= new SimpleStringProperty(ticket.getTicketID());
  }
    public void setRoom(int room)
    {
      this.room.set(room);
    }

    public void setLength(int length)
    {
      this.length.set(length);
    }

    public void setScreening(Screening screening)
    {
      this.screening = screening;
    }
    public StringProperty ticketTypeProperty() {
      return ticketType;
    }

    public Screening getScreening()
    {
      return screening;
    }

    public IntegerProperty lengthProperty()
    {
      return length;
    }

    public Seat getSeat()
    {
      return seat;
    }
    public StringProperty getSeatID()
    {
      return seatID;
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
