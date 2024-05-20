package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Screening implements Serializable
{
	private int hour;
	private int minute;
	private SimpleDate date;
	private Movie movie;
	private Room room;

	public Screening(int hour, int minute, LocalDate date, Movie movie, Room room){
		this.date = new SimpleDate(date);
	this.hour = hour;
	this.minute = minute;
	this.movie = movie;
	this.room = room;
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

	public String getTime(){
		return hour + ":" + minute;
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
