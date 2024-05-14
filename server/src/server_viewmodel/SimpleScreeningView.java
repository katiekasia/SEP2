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
    private StringProperty length;
    private StringProperty genre;
    //for Ticket Confirmation table
    private StringProperty seatID;
    private Screening screening;
    private User user;
    private StringProperty ticketType;
    private StringProperty snacks;


    public SimpleScreeningView(Screening screening){
      this.screening = screening;
  time = new SimpleStringProperty(screening.getTime());
  genre = new SimpleStringProperty(screening.getMovie().getGenre());
  date = new SimpleStringProperty(screening.getDate().toString());
  movie = new SimpleStringProperty(screening.getMovie().getName());
  room = new SimpleIntegerProperty(screening.getRoom().getRoomID());
  length = new SimpleStringProperty(screening.getMovie().getLenghth());

    }
  public SimpleScreeningView(Screening screening, User user)
  {
    this.user = user;
    this.screening = screening;
    time = new SimpleStringProperty(screening.getTime());
    date = new SimpleStringProperty(screening.getDate().toString());
    movie = new SimpleStringProperty(screening.getMovie().getName());
    seatID = new SimpleStringProperty(user.getOrders().get(0).getTickets().get(0).getSeat().getID());
    ticketType= new SimpleStringProperty(user.getOrders().get(0).getTickets().get(0).getTicketType());
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

    public void setScreening(Screening screening)
    {
      this.screening = screening;
    }
    public User getUser()
    {
      return user;
    }
    public StringProperty ticketTypeProperty() {
      return ticketType;
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

    public void setTicketType(String type) {
      this.ticketType.set(type);
    }

  }
