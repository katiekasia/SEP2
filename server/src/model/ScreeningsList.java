package model;


import java.time.LocalDate;
import java.util.ArrayList;

public class ScreeningsList
{
  private ArrayList<Screening> screenings;

  public ScreeningsList(){
    this.screenings = new ArrayList<>();
  }
  public ScreeningsList(ArrayList<Screening> screenings){
    this.screenings = screenings;
  }
public void addScreening(Screening screening){
  screenings.add(screening);
}
public void removeScreening(Screening screening){
  screenings.remove(screening);
}
public void removeByDate(SimpleDate date){
  for (Screening screening :screenings){
    if(screening.getDate().equals(date)){
      screenings.remove(screening);
      break;
    }
  }
}
public boolean contains(Screening screening){
return screenings.contains(screening);
}

  public ArrayList<Screening> getScreeningsByDateAndTitle(
      String title, LocalDate date)
  {
    SimpleDate temp = new SimpleDate(date);
    ArrayList<Screening> result = new ArrayList<>();
    for (Screening screening : screenings){
      if (screening.getMovieTitle().equals(title) && screening.isOnTheSameDay(temp)){
        result.add(screening);
      }
    }
    return result;
  }
  public ArrayList<Screening> getScreeningsByDate(LocalDate date)
  {
    SimpleDate temp = new SimpleDate(date);
    ArrayList<Screening> result = new ArrayList<>();
    for (int i = 0; i < screenings.size(); i++)
    {
      if (getScreeningById(i).isOnTheSameDay(temp) || getScreeningById(i).isAfter(temp))
      {
        result.add(getScreeningById(i));
      }
    }
    return result;
  }

  public Seat[] getAvailableSeats(Screening screening)
  {
    return getScreening(screening).getAvailableSeats();
  }

  public Seat[] getEmptySeats(Screening screening)
  {
return getScreening(screening).getEmptySeats();
  }
  public void bookSeatByID(Screening screening, String id, Ticket ticket){
    getScreening(screening).bookSeatById(id,ticket);
  }

  public ArrayList<Screening> getScreaningsByMovieTitle(String title)
  {
    ArrayList<Screening> result = new ArrayList<>();
    for (int i = 0; i < screenings.size(); i++)
    {
      if (getScreeningById(i).getMovieTitle().toUpperCase()
          .equals(title.toUpperCase()))
      {
        result.add(getScreeningById(i));
      }
    }
    return result;
  }
public Screening getScreeningForView(String time, String date,String title, int room){
    for (Screening screening:screenings){
      if (screening.getTime().equals(time) && screening.isOnTheSameDay(date)&&
      screening.getMovieTitle().equals(title) && screening.getRoomID() == room){
        return screening;
      }
    }
    throw new RuntimeException("No such screening found.");
}

  public ArrayList<Screening> getScreenings()
  {
    return new ArrayList<>(screenings);
  }
  public Screening getScreeningById(int id){
  return screenings.get(id);
  }

  public int getSize(){
  return screenings.size();
}
public Screening getScreening(Screening screening){
  for (Screening scr : screenings){
    if (scr.equals(screening)){
      return scr;
    }
  }
throw new IllegalStateException("No such screening was found.");
}
}

