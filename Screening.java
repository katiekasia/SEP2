public class Screening {
	private int hour;
	private int minute;
	private Seat[] seats;
	public Screening(int nbSeats, int hour, int minute){
	seats = new Seat[nbSeats];
	this.hour = hour;
	this.minute = minute;
	setSeats();
	}
	//Returns the amount of available seats
	public int availableSeats(){
		int count = 0;
		for (Seat seat: seats){
			if (seat.isAvailable()){
				count++;
			}
		}
		return count;
	}
	//**********************************************Getters and setters*******************************


//generate new array of free seats for new screaning
	public void setSeats() {
		for (int i = 0; i < seats.length; i++)
		{
			Seat temp = new Seat(i+1,false);
			seats[i] = temp;
		}
	}
	public void setSeats(Seat[] seats){
		this.seats = seats;
	}
	public String getTime(){
		return hour + ":" + minute;
	}

	public Seat[] getSeats() {
		return seats;
	}

	public int getHour()
	{
		return hour;
	}

	public int getMinute()
	{
		return minute;
	}

	public Seat getSeat(int index) {
		return seats[index];
	}


}
