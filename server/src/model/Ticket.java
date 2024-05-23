package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public abstract class Ticket implements Serializable
{
	private String ticketID;
	private double ticketPrice;
	private String ticketType;
	private Seat seat;
	private Screening screening;
	private ArrayList<String > ids = new ArrayList<>();

	public Ticket(String ticketID, double ticketPrice, Seat seat, Screening screening, String ticketType) {
		setTicketID(ticketID);
		this.ticketPrice = ticketPrice;
		this.seat = seat;
		this.screening = screening;
		this.ticketType= ticketType;
		ids.add(ticketID);
	}
	public String getTicketType()
	{
		return ticketType;
	}
	public void setTicketType(String ticketType)
	{
		this.ticketType= ticketType;
	}
	public String getSeatID(){return seat.getID();}
	public int getHour(){return screening.getHour();}
	public int getMinute(){return screening.getMinute();}
	public SimpleDate getDate(){return screening.getDate();}

	public String getTicketID() {
		return ticketID;
	}

	public Screening getScreening()
	{
		return screening;
	}

	public Seat getSeat()
	{
		return seat;
	}

	public void setScreening(Screening screening)
	{
		this.screening = screening;
	}


	public void setSeat(Seat seat)
	{
		this.seat = seat;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

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
	public void cancelTicket() {
		seat.cancelBooking();
	}

}
