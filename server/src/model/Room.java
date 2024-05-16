package model;

import java.io.Serializable;

public class Room implements Serializable
{
  private int roomID;
  private int nbSeats;
  private Seat[] seats;

  public Room(int roomID, int nbSeats)
  {
    this.roomID = roomID;
    this.nbSeats = nbSeats;
    seats = new Seat[nbSeats];
    setSeats();
  }


  public void setSeats() {
    for (int i = 0; i < 4; i++)
    {
      String id = "";
      switch (i){
        case 0:
          id ="A";
          break;
        case 1:
          id = "B";
          break;
        case 2:
          id = "C";
          break;
        case 3:
          id = "D";
          break;
      }
      for (int j = 0; j < 11; j++)
      {
        Seat seat = new Seat(id + (j+1),false);
        for (int k = 0; k < seats.length; k++)
        {
          seats[k] = seat;
        }
      }
    }
  }

  //Returns the amount of available seats
  public int availableSeats(){
    int count = 0;
    for (Seat seat: seats){
      if (seat.isAvailable()){
        count++;
      }
    }
    return count;
  }

//**********************************************Getters and Setters******************************
  public int getRoomID()
  {
    return roomID;
  }
  public Seat[] getSeats() {
    return seats;
  }
  public Seat getSeat(int index) {
    return seats[index];
  }
  public void setSeats(Seat[] seats){
    this.seats = seats;
  }

  public void setRoomID(int roomID)
  {
    this.roomID = roomID;
  }

  public int getNbSeats()
  {
    return nbSeats;
  }

  public void setNbSeats(int nbSeats)
  {
    this.nbSeats = nbSeats;
  }

}
