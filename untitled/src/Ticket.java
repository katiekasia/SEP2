public abstract class Ticket {
	private int ticketID;
	private double ticketPrice;
	private Seat seat;
	private Screening screening;
	private User customer;

	public Ticket(int ticketID, double ticketPrice, Seat seat, Screening screening, User customer) {
		this.ticketID = ticketID;
		this.ticketPrice = ticketPrice;
		this.seat = seat;
		this.screening = screening;
		this.customer = customer;
	}

	public int getTicketID() {
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
