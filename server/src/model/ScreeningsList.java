package model;
//import model.Screening;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;
//
//public class ScreeningsList
//{
//  private Set<Screening> screenings;
//
//  public ScreeningsList(ArrayList<Screening> screenings)
//  {
//    this.screenings = new HashSet<>();
//    setScreenings(screenings);
//  }
//
//  public void setScreenings(ArrayList<Screening> screenings)
//  {
//    for (Screening screening :screenings){
//      this.screenings.add(screening);
//    }
//  }
//
//  public ArrayList<Screening> getScreenings(){
//    ArrayList<Screening> list = new ArrayList<>();
//    for (Screening screening:screenings){
//      list.add(screening);
//    }
//    return list;
//  }
//  public int getSize(){
//    return screenings.size();
//  }
//  public Screening getScreeningById(int id){
//    return getScreenings().get(id);
//  }
//
//  public boolean contains(Screening screening)
//  {
//    return screenings.contains(screening);
//  }
//public void removeScreening(Screening screening){
//    screenings.remove(screening);
//}
//  public void addScreening(Screening screening)
//  {
//    screenings.add(screening);
//  }
//  public Screening getScreening(Screening screening){
//    for (Screening scr:screenings){
//      if (scr.equals(screening)){
//        return scr;
//      }
//    } System.out.println("nULL");
//    return null;
//  }
//}

// Other methods as required


import java.sql.SQLException;
import java.util.ArrayList;

public class ScreeningsList
{
  private ArrayList<Screening> screenings;


  public ScreeningsList() throws SQLException
  {

    screenings = DataBaseHandler.getAllScreenings();

  }
//  public ScreeningsList(){
//    this.screenings = new ArrayList<>();
//  }
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
public Screening getScreeningForView(String time, String date,String title, int room){
    for (Screening screening:screenings){
      if (screening.getTime().equals(time) && screening.getDate().toString().equals(date)&&
      screening.getMovie().getName().equals(title) && screening.getRoom().getRoomID() == room){
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
  System.out.println("not found");
  //TODO
  return null;
}
}

