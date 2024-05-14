package model;

public abstract class Ticket
{
	private String ticketID;
	private double ticketPrice;
	private String ticketType;
	private Seat seat;
	private Screening screening;
	private User customer;

	public Ticket(String ticketID, double ticketPrice, Seat seat, Screening screening, User customer, String ticketType) {
		this.ticketID = ticketID;
		this.ticketPrice = ticketPrice;
		this.seat = seat;
		this.screening = screening;
		this.customer = customer;
		this.ticketType= ticketType;
	}
	public String getTicketType()
	{
		return ticketType;
	}
	public void setTicketType(String ticketType)
	{
		this.ticketType= ticketType;
	}

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
		this.ticketID = ticketID;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public void cancelTicket() {
		seat.cancelBooking();
	}

}
