package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class representing a room in the system
 *
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */

public class Room implements Serializable
{
  private int roomID;
  private int nbSeats;
  private Seat[] seats;
  /**
   *  2 argument constructor
   *   to initialize a Room object with the given room ID and number of seats.
   * Initializes the seats array and sets the seats for the room.
   *
   * @param roomID   The ID of the room.
   * @param nbSeats  The number of seats in the room.
   */
  public Room(int roomID, int nbSeats)
  {
    this.roomID = roomID;
    this.nbSeats = nbSeats;
    seats = new Seat[nbSeats];
    setSeats();
  }

  /**
   * Method to set the seats for the room.
   * Initializes the seats array and assigns Seat objects to it based on the room layout.
   */
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
  /**
   * Method to get the IDs of the booked seats in the room.
   *
   * @return An ArrayList containing the IDs of the booked seats.
   */
  public ArrayList<String> getBookedSeatsIDs(){
    ArrayList<String> result = new ArrayList<>();

    for (Seat seat : seats){
      if (!seat.isAvailable()){
        result.add(seat.getID());
      }
    }
    return result;
  }
  /**
   * Method to calculate the number of available seats in the room.
   *
   * @return The number of available seats in the room.
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
  /**
   * getter for room id
   * @return The room identifier.
   */
  public int getRoomID()
  {
    return roomID;
  }
  /**
   * retrieves a list  of seats
   * @return The list of seats
   */
  public Seat[] getSeats() {
    return seats;
  }
  public Seat getSeatByID(String ID){
    for (Seat seat :seats){
      if (seat.getID().equals(ID)){
        return seat;
      }
    }
    throw new IllegalArgumentException("SEAT NOT FOUND.");
  }
  /**
   * Checks the availability of a seat in the room based on its identifier.
   * @param id The identifier of the seat to check availability for.
   * @return true if the seat with the specified identifier is available, false otherwise.
   */
  public boolean getAvailabilityByID(String id){
    return getSeatByID(id).isAvailable();
  }
  /**
   * Books a seat in the room with the provided identifier for the given ticket.
   * @param id The identifier of the seat to be booked.
   * @param ticket The ticket associated with the booking.
   */
  public void bookSeatById(String id, Ticket ticket){
    getSeatByID(id).book(ticket);
  }
  /**
   *
   * Retrieves the seat at the specified index in the room.
   * @param index The index of the seat to retrieve.
   * @return The Seat object at the specified index.
   */
  public Seat getSeat(int index) {
    return seats[index];
  }
  /**
   * Checks the availability of the seat at the specified index in the room.
   * @param indx The index of the seat to check.
   * @return True if the seat is available, false otherwise.
   */
  public boolean getSeatAvailability(int indx){
    return getSeat(indx).isAvailable();
  }
  /**
   * Sets the array of seats for the room.
   * @param seats The array of Seat objects to set.
   */
  public void setSeats(Seat[] seats){
    this.seats = seats;
  }

  public void setRoomID(int roomID)
  {
    this.roomID = roomID;
  }
  /**
   * getter for number of setas
   * @return nbSeats the number of seats
   */
  public int getNbSeats()
  {
    return nbSeats;
  }

  public void setNbSeats(int nbSeats)
  {
    this.nbSeats = nbSeats;
  }

}
