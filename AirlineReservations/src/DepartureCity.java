import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DepartureCity extends Airports {

	private ArrayList<Flight> flights;
	
	public DepartureCity(String airportName,String cityName, ArrayList<Flight> flights) {
		setFlights(flights);
		setAirportName(airportName);
		setCity(cityName);
	}
	
	public ArrayList<Flight> getFlights() {
		return flights;
	}

	public void setFlights(ArrayList<Flight> flights) {
		this.flights = flights;
	}
	
}
