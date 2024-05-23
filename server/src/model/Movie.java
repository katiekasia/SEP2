package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Movie implements Serializable
{

	private String length;

	private String description;

	private String name;

	private String genre;
	private LocalDate releaseDate;



	public Movie(String length, String description, String name, String genre, LocalDate releaseDate) {
		this.length = length;
		this.description = description;
		this.name = name;
		this.genre = genre;
		this.releaseDate = releaseDate;
	}

	public LocalDate getReleaseDate()
	{
		return releaseDate;
	}
	public String getReleaseString(){
		return releaseDate.toString();
	}
	//Checks if the room is already in use on the same day

	// ************************************************Getters and setters**************************************

//
//private boolean isRoomInUse(client_model.ScreeningDay day){
//	boolean roomInUse = false;
//	for (client_model.ScreeningDay days:days){
//		if (day.getDate().equals(days.getDate()) && day.getRoom().equals(days.getRoom())){
//			roomInUse = true;
//		}
//	}
//	return roomInUse;
//}
//	public client_model.ScreeningDay getScreeningDay(client_model.SimpleDate date) {
//		for (client_model.ScreeningDay day : days) {
//			if (day.getDate().equals(date)) {
//				return day;
//			}
//		}
//		return null;
//	}
//	public void setScreeningDays(ArrayList<client_model.ScreeningDay> screeningDays)
//	{
//		this.days = screeningDays;
//	} TODO useful for later
	public String getLenghth() {
		return length;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public String getGenre() {
		return genre;
	}


	public void setLength(String length) {
this.length = length;
	}

	public void setDescription(String description) {
this.description = description;
	}

	public void setName(String name) {
this.name = name;
	}

	public void setGenre(String genre) {
this.genre = genre;
	}


}
