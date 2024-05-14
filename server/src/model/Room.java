package model;

public class Room
{
  private int roomID;
  private int nbSeats;
  private Seat[] seats;

  public Room(int roomID, int nbSeats)
  {
    this.roomID = roomID;
    this.nbSeats = nbSeats;
    seats = new Seat[nbSeats];
    //setSeats();
  }
/*
  public void setSeats() {
    for (int i = 0; i < seats.length; i++)
    {
      Seat temp = new Seat(i+1,false);
      seats[i] = temp;
    }
  }

 */
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
