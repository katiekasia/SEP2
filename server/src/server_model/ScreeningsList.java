package server_model;

import java.sql.SQLException;
import java.util.ArrayList;

public class ScreeningsList
{
  private ArrayList<Screening> screenings;


  public ScreeningsList() throws SQLException
  {
    screenings = DataBaseHandler.getAllScreenings();
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
  System.out.println("not found");
  //TODO
  return null;
}
}
