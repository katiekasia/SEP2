package model;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * abstract class representing a ticket in the cinema booking system.
 *
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public abstract class Ticket implements Serializable
{
	private String ticketID;
	private double ticketPrice;
	private String ticketType;
	private Seat seat;
	private Screening screening;
	private ArrayList<String > ids = new ArrayList<>();
	/**
	 *5 argument constructor
	 * initialises  a Ticket object with the given parameters.
	 * @param ticketID The unique ID of the ticket.
	 * @param ticketPrice The price of the ticket.
	 * @param seat The seat associated with the ticket.
	 * @param screening The screening associated with the ticket.
	 * @param ticketType The type of the ticket.
	 */
	public Ticket(String ticketID, double ticketPrice, Seat seat, Screening screening, String ticketType) {
		setTicketID(ticketID);
		this.ticketPrice = ticketPrice;
		this.seat = seat;
		this.screening = screening;
		this.ticketType= ticketType;
		ids.add(ticketID);
	}

	/**
	 * Retrieves the type of the ticket.
	 * @return The type of the ticket.
	 */
	public String getTicketType()
	{
		return ticketType;
	}
	/**
	 * Sets the type of the ticket.
	 * @param ticketType The type of the ticket to set.
	 */
	public void setTicketType(String ticketType)
	{
		this.ticketType= ticketType;
	}
	/**
	 * Retrieves the ID of the seat associated with the ticket.
	 * @return The ID of the seat.
	 */
	public String getSeatID(){return seat.getID();}
	/**
	 * Retrieves the hour of the screening associated with the ticket.
	 * @return The hour of the screening.
	 */
	public int getHour(){return screening.getHour();}
	/**
	 * Retrieves the minute of the screening associated with the ticket.
	 * @return The minute of the screening.
	 */
	public int getMinute(){return screening.getMinute();}

	/**
	 * Retrieves the date of the screening associated with the ticket.
	 * @return The date of the screening.
	 */
	public SimpleDate getDate(){return screening.getDate();}


	/**
	 * Retrieves the ID of the ticket.
	 * @return The ID of the ticket.
	 */
	public String getTicketID() {
		return ticketID;
	}

	/**
	 * Retrieves the screening associated with the ticket.
	 * @return The screening associated with the ticket.
	 */
	/**
	 * Retrieves the seat associated with the ticket.
	 * @return The seat associated with the ticket.
	 */
	public Seat getSeat() {
		return seat;
	}

	/**
	 * Sets the screening associated with the ticket.
	 * @param screening The screening to set.
	 */
	public void setScreening(Screening screening)
	{
		this.screening = screening;
	}


	//THIS METHOD i almost deleted by mistake while comenting//
	public Screening getScreening()
	{
		return screening;
	}

	/**
	 * Sets the seat associated with the ticket.
	 * @param seat The seat to set.
	 */
	public void setSeat(Seat seat)
	{
		this.seat = seat;
	}
	/**
	 * Retrieves the price of the ticket.
	 * @return The price of the ticket.
	 */
	public double getTicketPrice() {
		return ticketPrice;
	}
	/**
	 * Sets the ID of the ticket.
	 * If the ID is already present, generates a new ID.
	 * @param ticketID The ID to set for the ticket.
	 */
	public void setTicketID(String ticketID) {
		if (!ids.contains(ticketID)){
			this.ticketID = ticketID;
		}else
		{
			String ID = ticketID + Math.floor(Math.random() * Integer.MAX_VALUE);
			setTicketID(ID);
		}
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	/**
	 * Cancels the booking associated with the ticket by canceling the seat booking.
	 */
	public void cancelTicket() {
		seat.cancelBooking();
	}

}
