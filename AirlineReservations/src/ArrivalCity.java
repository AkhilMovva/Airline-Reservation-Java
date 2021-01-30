
import java.util.ArrayList;

public class ArrivalCity extends Airports {

	private ArrayList<Flight> flights;
	
	public ArrivalCity(String airportName,String cityName, ArrayList<Flight> flights) {
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
