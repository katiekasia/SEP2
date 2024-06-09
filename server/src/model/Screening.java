package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * Represents a screening of a movie in a specific room at a specific date and time.
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class Screening implements Serializable
{
	private int hour;
	private int minute;
	private SimpleDate date;
	private Movie movie;
	private Room room;
	private int id;
	private ArrayList<Integer> ids = new ArrayList<>();
	/**
	 * 5 argument constructor
	 * Constructs a Screening object with the specified parameters.
	 * @param hour The hour of the screening.
	 * @param minute The minute of the screening.
	 * @param date The date of the screening.
	 * @param movie The movie being screened.
	 * @param room The room where the screening takes place.
	 */
	public Screening(int hour, int minute, LocalDate date, Movie movie, Room room)
	{
		this.date = new SimpleDate(date);
		setID(generateID());
		this.hour = hour;
		this.minute = minute;
		this.movie = movie;
		this.room = room;
	}
	/**
	 * 6 argument constructor
	 * Constructs a Screening object with the specified parameters, including a custom ID.
	 * @param id The custom ID for the screening.
	 * @param hour The hour of the screening.
	 * @param minute The minute of the screening.
	 * @param date The date of the screening.
	 * @param movie The movie being screened.
	 * @param room The room where the screening takes place.
	 */
	public Screening(int id,int hour, int minute, LocalDate date, Movie movie, Room room){
		this.date = new SimpleDate(date);
		setID(id);
		this.hour = hour;
		this.minute = minute;
		this.movie = movie;
		this.room = room;
	}

	/**
	 * Generates a unique ID for the screening.
	 * @return The generated ID.
	 */
	public int generateID(){
		Random random = new Random();
		int randomPart = random.nextInt(401);

		int id = Integer.parseInt(ids.size() + "" + randomPart);
		return id;
	}
	public void deleteID(){}
	//		for (int s : ids){
	//			if (s == id){
	//				ids.remove(s);
	//			}
	//		}
	//	}

	/**
	 * Retrieves id for screening
	 * @return  id for screening
	 */
	public int getId(){
		return id;
	}

	/**
	 * sets id for screening
	 * @param ID  type int, sets id for screening
	 */
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
	/**
	 * Compares this Screening object to the specified object for equality.
	 * Returns true if the specified object is also a Screening object and has the same date, time, movie, and room.
	 * @param obj The object to compare with.
	 * @return true if the objects are equal, false otherwise.
	 */
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
	/**
	 * getter for name of the movie
	 * @return  the name of the movie
	 */
	public String getMovieTitle(){
		return movie.getName();
	}
	/**
	 * Checks if this Screening is scheduled after the specified date.
	 * @param date The date to compare against.
	 * @return true if this Screening is scheduled after the specified date, false otherwise.
	 */
	public boolean isAfter(SimpleDate date){
		return date.isAfter(date);
	}
	/**
	 * Checks if this Screening is scheduled on the same day as the specified date.
	 * @param date The date to compare against.
	 * @return true if this Screening is scheduled on the same day as the specified date, false otherwise.
	 */
	public boolean isOnTheSameDay(SimpleDate date){
		return this.date.equals(date);
	}
	/**
	 * Checks if this Screening is scheduled on the same day as the specified date string.
	 * @param date The date string to compare against (in the format "YYYY-MM-DD").
	 * @return true if this Screening is scheduled on the same day as the specified date string, false otherwise.
	 */
	public boolean isOnTheSameDay(String date){
		return this.date.toString().equals(date);
	}
	/**
	 * getter for room id
	 * @return  id for room
	 */
	public int getRoomID(){
		return room.getRoomID();
	}
	/**
	 * getter for the nr of available seats
	 * @return  nr of available seats
	 */
	public int getNbOfAvailableSeats(){
		return room.availableSeats();
	}
	/**
	 * getter for time: hour and minute of the screening
	 * @return  time of the screening
	 */
	public String getTime(){
		return hour + ":" + minute;
	}

	/**
	 * Checks the availability of a seat in the room at the specified index.
	 * @param indx The index of the seat to check.
	 * @return true if the seat at the specified index is available, false otherwise.
	 */
	public boolean getSeatAvailabilty(int indx){
		return room.getSeatAvailability(indx);
	}
	/**
	 * getter for the nr of  seats
	 * @return  nr of seats
	 */
	public int getNbOfSeats(){
		return room.getNbSeats();
	}

	/**
	 * Retrieves the seat at the specified index in the room.
	 * @param indx The index of the seat to retrieve.
	 * @return The Seat object located at the specified index in the room.
	 */
	public Seat getSeatByIndx(int indx){
		return room.getSeat(indx);
	}
	/**
	 * Retrieves an array of available seats in the screening room.
	 * @return An array of Seat objects representing the available seats in the screening room.
	 */
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
	/**
	 * Checks the availability of a seat in the room by id
	 * @param id The id of the seat to check.
	 * @return true if the seat with specified id is available, false otherwise.
	 */
	public boolean getAvailabilityById(String id){
		return room.getAvailabilityByID(id);
	}
	/**
	 * Books a seat in the screening room using the provided seat ID and associates it with the given ticket.
	 * @param id The ID of the seat to be booked.
	 * @param ticket The ticket to be associated with the booked seat.
	 */
	public void bookSeatById(String id, Ticket ticket){
		room.bookSeatById(id,ticket);
	}
	/**
	 * Retrieves an array of empty seats in the screening room.
	 * @return An array of empty Seat objects.
	 */
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
	/**
	 * setter for the room of screening
	 * @param room  set room for screening
	 */
	public void setRoom(Room room)
	{
		this.room = room;
	}
	/**
	 * getter for room
	 * @return room for screening
	 */
	public Room getRoom()
	{
		return room;
	}
	/**
	 * getter for date of screening
	 * @return  date for screening
	 */
	public SimpleDate getDate(){return date.copy();}
	/**
	 * setter for the date
	 * @date  sets   the date of screening
	 */
	public void setDate(SimpleDate date)
	{
		this.date = date;
	}
	/**
	 * getter for movie
	 * @return  movie for screening
	 */
	public Movie getMovie()
	{
		return movie;
	}
	/**
	 * setter  for movie
	 * @param movie sets movie to be in the screening
	 */
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
	/**
	 * getter for the hour of screening
	 * @return  the hour
	 */
	public int getHour()
	{
		return hour;
	}
	/**
	 * getter for the minutes of screening
	 * @return  the minute
	 */
	public int getMinute()
	{
		return minute;
	}




}
