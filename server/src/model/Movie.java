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
