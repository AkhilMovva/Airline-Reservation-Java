import java.time.LocalTime;


public class Delays {
	private LocalTime delayedDepartureTime;
	private LocalTime delayedArrivalTime;
	private Flight flight;

	public Delays(Flight flight, LocalTime delayedDepartureTime, LocalTime delayedArrivalTime) {
		setFlight(flight);
		setDelayedDepartureTime(delayedDepartureTime);
		setDelayedArrivalTime(delayedArrivalTime);
		
	}
	
	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public int getFlightNo() {
		return flight.getFlightNo();
	}
	public void setFlightNo(int flightNo) {
		flight.setFlightNo(flightNo);
	}
	public LocalTime getDelayedArrivalTime() {
		return delayedArrivalTime;
	}
	public void setDelayedArrivalTime(LocalTime delayedArrivalTime) {
		this.delayedArrivalTime = delayedArrivalTime;
	}
	public LocalTime getDelayedDepartureTime() {
		return delayedDepartureTime;
	}
	public void setDelayedDepartureTime(LocalTime delayedDepartureTime) {
		this.delayedDepartureTime = delayedDepartureTime;
	}
}
