package server_model;

import java.time.LocalDate;

/**
 * A class representing a date.
 * @author Florina Mitigus
 * @author Michael Barczuk
 */

public class SimpleDate implements Comparable<SimpleDate>
{
  private int day, month, year;
  private LocalDate date;
  private int index;

  /**
   * A four-argument constructor creating the new client_model.SimpleDate object for the date.
   * @param day the day of the month of the screening as int
   * @param month the month of the screening as int
   * @param year the year of the screening as int
   */
  public SimpleDate(int day, int month,int year)
  {
    this.day=day;
    this.month=month;
    this.year = year;
    index = 0;
  }

  /**
   * A one-argument constructor creating a new client_model.SimpleDate object.
   * @param date the date of the screening as LocalDate
   */
  public SimpleDate(LocalDate date){
    this.day = date.getDayOfMonth();
    this.month = date.getMonthValue();
    this.year = date.getYear();
  }

  /**
   * A no argument constructor creating the new client_model.SimpleDate object based on today's date.
   */
  public SimpleDate()
  {
    this.day=LocalDate.now().getDayOfMonth();
    this.month=LocalDate.now().getMonthValue();
    this.year=LocalDate.now().getYear();
  }

  /**
   * A method returning the day.
   * @return an integer representing day.
   */
  public int getDay()
  {
    return day;
  }
  /**
   * A method returning the month.
   * @return an integer representing month.
   */
  public int getMonth()
  {
    return month;
  }
  /**
   * A method returning the year.
   * @return an integer representing year.
   */
  public int getYear()
  {
    return year;
  }

/**
 * A method returning the LocalDate object with same values as the client_model.SimpleDate.
 * @return an integer representing day.
 */
public LocalDate getDate()
{
  return date = LocalDate.of(year,month,day);
}
  /**
   * A method responsible for comparing this date to another and returning if this one is after it.
   * @param date
   *        a date we are comparing
   * @return a boolean representing if this date is after the parameter one.
   */
  public boolean isAfter(SimpleDate date)
  {
    if (this.year > date.year)//1 comparison
      return true;//1 return
    else if (this.year < date.year)//1 comparison
      return false;//1 return

    if(this.month > date.month)//1 comparison
      return true;//1 return
    else if (this.month < date.month)//1 comparison
      return false;//1 return
    return this.day >= date.day;//1 comparison and 1 return
    // We will take the most complicated structure to determine Big O
    // T(n)=1+1+2=4 => O(1)
  }
  /**
   * A method responsible for comparing this date to another and returning if this one is before it.
   * @param date
   *        a date we are comparing
   * @return a boolean representing if this date is before the parameter one.
   */
  public boolean isBefore(SimpleDate date)
  {

    if (this.year < date.year)//1 comparison
      return true;//1 return
    else if (this.year > date.year)//1 comparison
      return false;//1 return
    //=> At most 3 steps for this if-else structure

    if (this.month < date.month)//1 comparison
      return true;//1 return
    else if (this.month > date.month)//1 comparison
      return false;//1 return
    else
      return this.day <= date.day;//1 return and 1 comparison
    //=> At most 4 steps for this structure, with 3 comparisons and 1 return
    // We will take the most complicated structure to determine Big O
    // T(n)=1+1+2=4 => O(1)
  }

  /**
   * A method used to compare this client_model.SimpleDate class to another Object and return if they are the same.
   * @param obj
   *        object we are comparing
   * @return a boolean representing if this client_model.SimpleDate and the parameter are the same.
   */
  public boolean equals(Object obj)
  {
    if(obj==null || obj.getClass()!=this.getClass())
      return false;
    SimpleDate other=(SimpleDate)obj;
    return other.getYear()==this.getYear() && other.getMonth()==this.getMonth() && other.getDay()==this.getDay();
  }

  /**
   * A to String method that returns client_model.SimpleDate as String, without the minutes and hours if they are set to -1.
   * @return returns this object formated to a string
   */
  public String toString()
  {
    String s=getDay()+"/"+getMonth()+"/" + getYear();
    return s;
  }
  /**
   * A method use to create a copy of the date.
   * @return a copy of date as new client_model.SimpleDate class.
   */
  public SimpleDate copy()  { return new SimpleDate(getDay(),getMonth(),getYear());}

  /**
   *A method comparing two dates but returning an integer instead of boolean.
   * @param date
   *        the object to be compared.
   * @return the integer representing if this date is after the compared one.
   */
  @Override public int compareTo(SimpleDate date)
  {
    if(this.isAfter(date))
      return 1;
    else return -1;
  }

}
