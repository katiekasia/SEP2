package model;

import java.io.Serializable;
import java.util.ArrayList;

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
    int index = 0;  // Index to track the position in the seats array
    seats = new Seat[4 * 11];  // Assuming seats array is a class variable and initializing it here

    for (int i = 0; i < 4; i++) {
      String id = "";
      switch (i) {
        case 0:
          id = "A";
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
///////
      for (int j = 0; j < 11; j++) {
        Seat seat = new Seat(id + (j + 1), false);
        seats[index++] = seat;  // Assign the seat to the next position in the array
      }
    }
  }
  public ArrayList<String> getBookedSeatsIDs(){
    ArrayList<String> result = new ArrayList<>();

    for (Seat seat : seats){
      if (!seat.isAvailable()){
        result.add(seat.getID());
      }
    }
    return result;
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
  public Seat getSeatByID(String ID){
    System.out.println(ID);
    for (Seat seat :seats){
      if (seat.getID().equals(ID)){
        System.out.println(seat.getID());
        return seat;
      }
    }
    throw new IllegalArgumentException("SEAT NOT FOUND.");
  }
  public Seat getSeat(int index) {
    return seats[index];
  }
  public boolean getSeatAvailability(int indx){
    return getSeat(indx).isAvailable();
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
