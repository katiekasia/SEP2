import java.util.ArrayList;

public class Movie {

	private int length;

	private String description;

	private String name;

	private String genre;
	private ArrayList<ScreeningDay> days;


	public Movie(int length, String description, String name, String genre) {
		this.length = length;
		this.description = description;
		this.name = name;
		this.genre = genre;
		this.days = new ArrayList<>();
	}

	//Adds a new screening day for the movie unless the same screening day already exists or a room is already in use for that day
	//Possible for future either allow or not screening days on same date but different rooms
	public void addScreeningDay(ScreeningDay day){
		if (days.contains(day)){
			throw new IllegalArgumentException("Such screening day is already scheduled");
		}
		else if (isRoomInUse(day))
		{
			throw new IllegalArgumentException("Room is already booked for this day");
		}
		else
		{
			days.add(day);
		}
	}
	//Checks if the room is already in use on the same day
	private boolean isRoomInUse(ScreeningDay day){
		boolean roomInUse = false;
		for (ScreeningDay days:days){
			if (day.getDate().equals(days.getDate()) && day.getRoom().equals(days.getRoom())){
				roomInUse = true;
			}
		}
		return roomInUse;
	}
	// ************************************************Getters and setters**************************************
	public ArrayList<ScreeningDay> getScreeningDays()
	{
		return days;
	}

	public ScreeningDay getScreeningDay(SimpleDate date) {
		for (ScreeningDay day : days) {
			if (day.getDate().equals(date)) {
				return day;
			}
		}
		return null;
	}
	public int getLenghth() {
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

	public void setLength(int length) {
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
	public void setScreeningDays(ArrayList<ScreeningDay> screeningDays)
	{
		this.days = screeningDays;
	}

}
