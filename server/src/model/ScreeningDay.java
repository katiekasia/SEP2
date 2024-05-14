package model;

public class ScreeningDay {
	private SimpleDate date;
	private Screening[] screenings;
	private Room room;

	public ScreeningDay(Screening morningScreening, Screening afternoonScreening, Screening eveningScreening, SimpleDate date, Room room) {
		screenings = new Screening[3];
		setScreenings(morningScreening, afternoonScreening, eveningScreening);
		this.date = date;
		this.room = room;
	}
	//Removes one of the screenings from the day
	public void removeScreening(int index) {
		screenings[index] = null;
	}

	//Adds a new screaning to the day. Type is the morning(1), afternoon(2), evening(3), it checks if such screening already exists if no it adds a new one
	public void addScreening(int type, Screening screening){
		for (Screening screen:screenings){
			if (screen.getTime().equals(screening.getTime())){
				throw new IllegalArgumentException("A screaning for this hour is already scheduled");
			}
		}
		switch (type){
			case 1:
				if (screenings[0] == null){
					screenings[0] = screening;
				}else {
					throw new IllegalArgumentException("Morning screening is already scheduled");
				}
			case 2:
				if (screenings[1] == null){
					screenings[1] = screening;
				}else {
					throw new IllegalArgumentException("Afternoon screening is already scheduled");
				}
			case 3:
				if (screenings[2] == null){
					screenings[2] = screening;
				}else {
					throw new IllegalArgumentException("Evening screening is already scheduled");
				}
		}
	}

	//****************************************************Getters and setters*******************************
	public Screening[] getScreenings() {
		return screenings;
	}

	public Screening getScreening(int index) {
		return screenings[index];
	}

	public SimpleDate getDate() {
		return date;
	}

	public Room getRoom()
	{
		return room;
	}
//Sets the screenings to their respective index -> morning(1), afternoon(2), evening(3)
	public void setScreenings(
      Screening morningScreening, Screening afternoonScreening, Screening eveningScreening) {
		screenings[0] = morningScreening;
		screenings[1] = afternoonScreening;
		screenings[2] = eveningScreening;
	}
	public void setScreenings(Screening[] screenings){
		this.screenings = screenings;
	}

	public void setRoom(Room room)
	{
		this.room = room;
	}

	public void setDate(SimpleDate date)
	{
		this.date = date;
	}
}
