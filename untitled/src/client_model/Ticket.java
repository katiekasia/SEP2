package model;

import model.Screening;
import model.ScreeningDay;
import model.Seat;

public abstract class Ticket {
	private int ticketID;
	private double ticketPrice;
	private Seat seat;
	private Screening screening;
	private ScreeningDay screeningDay;
	private User customer;

	public Ticket(int ticketID, double ticketPrice, Seat seat, ScreeningDay screeningDay, Screening screening, User customer) {
		this.ticketID = ticketID;
		this.ticketPrice = ticketPrice;
		this.seat = seat;
		this.screening = screening;
		this.screeningDay = screeningDay;
		this.customer = customer;
	}

	public int getTicketID() {
		return ticketID;
	}

	public Screening getScreening()
	{
		return screening;
	}

	public ScreeningDay getScreeningDay()
	{
		return screeningDay;
	}

	public Seat getSeat()
	{
		return seat;
	}

	public void setScreening(Screening screening)
	{
		this.screening = screening;
	}

	public void setScreeningDay(ScreeningDay screeningDay)
	{
		this.screeningDay = screeningDay;
	}

	public void setSeat(Seat seat)
	{
		this.seat = seat;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public void cancelTicket() {
		seat.cancelBooking();
	}

}
