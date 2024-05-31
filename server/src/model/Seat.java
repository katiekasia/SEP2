package model;

import java.io.Serializable;
/**
 *
 * A class to represent a seat in the cinema
 *
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class Seat implements Serializable
{
	private String ID;
	private boolean isBooked;
	private Ticket ticket;
	/**
	 *  3 argument constructor
	 *  Constructor to initialize a Seat object.
	 * @param ID The unique identifier for the seat.
	 * @param isBooked A boolean indicating whether the seat is booked or not.
	 */
	public Seat(String ID, boolean isBooked)
	{
		this.ID = ID;
		this.isBooked = isBooked;
		this.ticket = null; // Initially no ticket associated with the seat
	}
	/**
	 * Cancels the booking of the seat.
	 */
	//Cancels the booking of the seat
	public void cancelBooking()
	{
		setTicket(null);
		setBooked(false);
	}

	/**
	 * Books the seat with the specified ticket.
	 * @param ticket The ticket to associate with the seat.
	 */
	//Books the seat
	public void book(Ticket ticket)
	{
		setTicket(ticket);
		setBooked(true);
	}

	//*********************************Getters and setters***********************************************

	/**
	 * Retrieves the ID of the seat.
	 * @return The ID of the seat.
	 */
	public String getID()
	{
		return ID;
	}

	/**
	 * Retrieves the ticket associated with the seat.
	 * @return The ticket associated with the seat.
	 */
	public Ticket getTicket()
	{
		return ticket;
	}
	/**
	 * Checks if the seat is available (not booked).
	 * @return true if the seat is available, false otherwise.
	 */
	public boolean isAvailable()
	{
		return !isBooked;
	}

	/**
	 * Sets the ID of the seat.
	 * @param ID The ID to set for the seat.
	 */
	public void setID(String ID)
	{
		this.ID = ID;
	}
	/**
	 * Sets the booked status of the seat.
	 * @param booked A boolean indicating whether the seat is booked or not.
	 */
	public void setBooked(boolean booked)
	{
		isBooked = booked;
	}
	/**
	 * Sets the ticket associated with the seat.
	 * @param ticket The ticket to associate with the seat.
	 */
	public void setTicket(Ticket ticket)
	{
		this.ticket = ticket;
	}
}
