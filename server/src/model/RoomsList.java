package model;

import java.util.ArrayList;

public class RoomsList
{
  private Room[] rooms;
  public RoomsList(ArrayList<Room> roomArray){
    rooms = roomArray.toArray(roomArray.toArray(new Room[0]));
  }

  public Room[] getRooms()
  {
    return rooms;
  }
  public Room getRoomById(int id){
    for (Room room:rooms){
      if (room.getRoomID() == id){
        return room;
      }
    }
    throw new IllegalArgumentException("No such room found.");
  }
}
