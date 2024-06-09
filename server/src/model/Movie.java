package model;

import java.io.Serializable;
import java.time.LocalDate;
/**
 *  Represents a movie in the cinema booking system.
 *  This class provides methods to access and manipulate movie details.
 *
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */

public class Movie implements Serializable
{

	private String length;

	private String description;

	private String name;

	private String genre;
	private LocalDate releaseDate;


	/**
	 * 5 argument constructor
	 * initialises a new Movie object with the specified parameters.
	 *
	 * @param length       The length of the movie
	 * @param description  The description of the movie
	 * @param name         The name of the movie
	 * @param genre        The genre of the movie
	 * @param releaseDate  The release date of the movie
	 */
	public Movie(String length, String description, String name, String genre, LocalDate releaseDate) {
		this.length = length;
		this.description = description;
		this.name = name;
		this.genre = genre;
		this.releaseDate = releaseDate;
	}
	/**
	 * Returns the release date of the movie.
	 *
	 * @return The release date of the movie
	 */
	public LocalDate getReleaseDate()
	{
		return LocalDate.of(releaseDate.getYear(), releaseDate.getMonthValue(),
				releaseDate.getDayOfMonth());
	}
	/**
	 * Returns a string representation of the release date of the movie.
	 *
	 * @return A string representation of the release date
	 */
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
	/**
	 * getter for length
	 * Returns the length of the movie.
	 *
	 * @return The length of the movie
	 */
	public String getLenghth() {
		return length;
	}
	/**
	 * Returns the description of the movie.
	 *
	 * @return The description of the movie
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Returns the name of the movie.
	 *
	 * @return The name of the movie
	 */
	public String getName() {
		return name;
	}
	/**
	 * Returns the genre of the movie.
	 *
	 * @return The genre of the movie
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * setter
	 * Sets the length of the movie.
	 *
	 * @param length The length of the movie
	 */
	public void setLength(String length) {
		this.length = length;
	}
	/**
	 * Sets the description of the movie.
	 *
	 * @param description The description of the movie
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Sets the name of the movie.
	 *
	 * @param name The name of the movie
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Sets the genre of the movie.
	 *
	 * @param genre The genre of the movie
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}


}
