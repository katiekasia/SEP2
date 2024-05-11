package server_model;

public class Seat
{
	private String ID;
	private boolean isBooked;
	private Ticket ticket;

	public Seat(String ID, boolean isBooked)
	{
		this.ID = ID;
		this.isBooked = isBooked;
		this.ticket = null; // Initially no ticket associated with the seat
	}
//Cancels the booking of the seat
	public void cancelBooking()
	{
		setTicket(null);
		setBooked(false);
	}
//Books the seat
	public void book(Ticket ticket)
	{
		setTicket(ticket);
		setBooked(true);
	}

	//*********************************Getters and setters***********************************************
	public String getID()
	{
		return ID;
	}


	public Ticket getTicket()
	{
		return ticket;
	}

	public boolean isAvailable()
	{
		return !isBooked;
	}
	public void setID(String ID)
	{
		this.ID = ID;
	}

	public void setBooked(boolean booked)
	{
		isBooked = booked;
	}

	public void setTicket(Ticket ticket)
	{
		this.ticket = ticket;
	}
}



