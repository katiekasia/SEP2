package client_model;

import java.util.ArrayList;

public class Room
{
  private int roomID;
  private int nbSeats;

  public Room(int roomID, int nbSeats)
  {
    this.roomID = roomID;
    this.nbSeats = nbSeats;
  }


//**********************************************Getters and Setters******************************
  public int getRoomID()
  {
    return roomID;
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
