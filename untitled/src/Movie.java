public class Movie {

	private int length;

	private String description;

	private String name;

	private String genre;



	public Movie(int length, String description, String name, String genre) {
		this.length = length;
		this.description = description;
		this.name = name;
		this.genre = genre;

	}

	//Checks if the room is already in use on the same day

	// ************************************************Getters and setters**************************************

//
//private boolean isRoomInUse(ScreeningDay day){
//	boolean roomInUse = false;
//	for (ScreeningDay days:days){
//		if (day.getDate().equals(days.getDate()) && day.getRoom().equals(days.getRoom())){
//			roomInUse = true;
//		}
//	}
//	return roomInUse;
//}
//	public ScreeningDay getScreeningDay(SimpleDate date) {
//		for (ScreeningDay day : days) {
//			if (day.getDate().equals(date)) {
//				return day;
//			}
//		}
//		return null;
//	}
//	public void setScreeningDays(ArrayList<ScreeningDay> screeningDays)
//	{
//		this.days = screeningDays;
//	} TODO useful for later
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


}
