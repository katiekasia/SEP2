package model;

public class Seat
{
	private int ID;
	private boolean isBooked;
	private Ticket ticket;

	public Seat(int ID, boolean isBooked)
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
	public int getID()
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
	public void setID(int ID)
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



