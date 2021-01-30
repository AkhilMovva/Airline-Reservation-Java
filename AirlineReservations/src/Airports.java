import java.util.ArrayList;

public class Airports {
	private String airportName;
	private String city;
	private ArrayList<Flight> arrivalFlights;
	private ArrayList<Flight> departureFlights;
	
	public Airports() {
		
	}
	public Airports(String airportName, String city,ArrayList<Flight> arrivalFlights, ArrayList<Flight> departureFlights) {
		setAirportName(airportName);
		setCity(city);
		setArrivalFlights(arrivalFlights);
		setDepartureFlights(departureFlights);
	}
	
	public ArrayList<Flight> getArrivalFlights() {
		return arrivalFlights;
	}
	public void setArrivalFlights(ArrayList<Flight> arrivalFlights) {
		this.arrivalFlights = arrivalFlights;
	}
	public ArrayList<Flight> getDepartureFlights() {
		return departureFlights;
	}
	public void setDepartureFlights(ArrayList<Flight> departureFlights) {
		this.departureFlights = departureFlights;
	}
	public String getAirportName() {
		return airportName;
	}
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
}
