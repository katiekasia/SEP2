package model;

import java.io.Serializable;
import java.net.IDN;
import java.time.LocalDate;
import java.util.ArrayList;

public class Screening implements Serializable
{
	private int hour;
	private int minute;
	private SimpleDate date;
	private Movie movie;
	private Room room;
	private int id;
	private ArrayList<Integer> ids = new ArrayList<>();

	public Screening(int hour, int minute, LocalDate date, Movie movie, Room room)
	{
		this.date = new SimpleDate(date);
		setID(generateID());
		this.hour = hour;
		this.minute = minute;
		this.movie = movie;
		this.room = room;
	}

	public Screening(int id,int hour, int minute, LocalDate date, Movie movie, Room room){
			this.date = new SimpleDate(date);
			setID(id);
			this.hour = hour;
			this.minute = minute;
			this.movie = movie;
			this.room = room;
		}

	private int generateID(){
		int id = Integer.parseInt(String.valueOf(ids.size() + Math.floor(Math.random() * 401)));
		return id;
	}
	public void deleteID(){
		ids.remove(id);
	}
	public int getId(){
		return id;
	}
	private void setID(int ID) {
		if (!ids.contains(ID)){
			this.id = ID;
			this.ids.add(ID);
		}else
		{
			int Id = Integer.parseInt(String.valueOf(ID + Math.floor(Math.random() * Integer.MAX_VALUE)));
			setID(Id);
		}
	}
public String getDateString(){
		return date.toString();
}

	//**********************************************Getters and setters*******************************

	@Override public boolean equals(Object obj)
	{
		if (obj == null || getClass() != obj.getClass())
		{
			return false;
		}
		Screening other = (Screening) obj;
		return date.equals(other.getDate()) && getTime().equals(other.getTime()) &&
				movie.getName().equals(other.getMovie().getName()) && room.getRoomID() == other.getRoom().getRoomID();
	}
	public String getMovieTitle(){
		return movie.getName();
	}
	public boolean isAfter(SimpleDate date){
		return date.isAfter(date);
	}
	public boolean isOnTheSameDay(SimpleDate date){
		return this.date.equals(date);
	}
	public boolean isOnTheSameDay(String date){
		return this.date.toString().equals(date);
	}
	public int getRoomID(){
		return room.getRoomID();
	}
	public int getNbOfAvailableSeats(){
		return room.availableSeats();
	}

	public String getTime(){
		return hour + ":" + minute;
	}
	public boolean getSeatAvailabilty(int indx){
		return room.getSeatAvailability(indx);
	}
	public int getNbOfSeats(){
		return room.getNbSeats();
	}
	public Seat getSeatByIndx(int indx){
		return room.getSeat(indx);
	}
	public Seat[] getAvailableSeats()
	{
		ArrayList<Seat> seats = new ArrayList<>();
		for (int i = 0; i < getNbOfSeats(); i++)
		{
			if (getSeatAvailabilty(i))
			{
				seats.add(getSeatByIndx(i));
			}
		}
		return seats.toArray(new Seat[getNbOfSeats()]);
	}
	public Seat getSeatById(String id){
		return room.getSeatByID(id);
	}
	public void bookSeatById(String id, Ticket ticket){
		getSeatById(id).book(ticket);
	}
	public Seat[] getEmptySeats()
	{
		ArrayList<Seat> emptySeats = new ArrayList<>();
		for (int i = 0; i < getNbOfSeats(); i++)
		{
			if (!getSeatAvailabilty(i))
			{
				emptySeats.add(getSeatByIndx(i));
			}
		}
		return emptySeats.toArray(new Seat[getNbOfSeats()]);
	}

	public void setRoom(Room room)
	{
		this.room = room;
	}

	public Room getRoom()
	{
		return room;
	}

	public SimpleDate getDate(){return date;}

	public void setDate(SimpleDate date)
	{
		this.date = date;
	}

	public Movie getMovie()
	{
		return movie;
	}

	public void setMovie(Movie movie)
	{
		this.movie = movie;
	}

	public void setMinute(int minute)
	{
		this.minute = minute;
	}

	public void setHour(int hour)
	{
		this.hour = hour;
	}

	public int getHour()
	{
		return hour;
	}

	public int getMinute()
	{
		return minute;
	}




}
