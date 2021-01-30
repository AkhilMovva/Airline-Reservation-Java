import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Flight implements Comparable<Flight>{
	
	// Fields
		private int classID;
		private int flightNo;
		private int flightSeats;
		private LocalDate departureDate;
		private LocalTime departureTime;
		private LocalDate arrivalDate;
		private LocalTime arrivalTime;
		private String duration;
		private ArrayList<Booking> bookings;
		private ArrayList<Employee> employees;
		private Delays delays;
		private Airports arrivalAirport;
		private Airports departureAirport;
		private Set<Booking> bookingSet;
		private String objectState;
		
		public Flight(int flightNo) {
			setFlightNo(flightNo);
		}

		public Flight(int classID, int flightNo, int flightSeats, Airports departureAirport, LocalDate departureDate, LocalTime departureTime, Airports arrivalAirport, LocalDate arrivalDate, LocalTime arrivalTime, String duration) {
			setFlightSeats(flightSeats);
			bookings = new ArrayList<Booking>(getFlightSeats());
			employees= new ArrayList<Employee>(10);
			setClassID(classID);
			setFlightNo(flightNo);
			
			setDepartureAirport(departureAirport);
			setDepartureDate(departureDate);
			setDepartureTime(departureTime);
			setArrivalAirport(arrivalAirport);
			setArrivalDate(arrivalDate);
			setArrivalTime(arrivalTime);
			setDuration(duration);
			
		}
		
		
		//toString
		public String toString() {
			return "Flight: " + this.getFlightNo() + " [" + this.getDepartureAirport().getCity() + "] " + this.departureDate + " " + this.departureTime + " - [" + this.getArrivalAirport().getCity() + "] " + this.arrivalDate + " " + this.arrivalTime + " " +this.duration;
			
		}
		
		
		public Delays getDelays() {
			return delays;
		}

		public void setDelays(Delays delays) {
			this.delays = delays;
		}
		
		public int getClassID() {
			return classID;
		}

		public void setClassID(int classID) {
			this.classID = classID;
		}
		
		////////////////////////////////////////
		
		public ArrayList<Booking> getBookings() {
			return bookings;
		}
		public void setBookings(int seats) {
			this.bookings.add(new Booking(seats));
		}
		
		
		//////////////////////////////////////		
		public ArrayList<Employee> getEmployees() {
			return employees;
		}

		public void setEmployees(Employee employee) {
			this.employees.add(employee);
		}
		
		
		
		//getters and setters
		public int getFlightNo() {
			return flightNo;
		}

		public void setFlightNo(int flightNo) {
			this.flightNo = flightNo;
		}
		
		
		public LocalDate getDepartureDate() {
			return departureDate;
		}

		public void setDepartureDate(LocalDate departureDate) {
			this.departureDate = departureDate;
		}
		
		public LocalTime getDepartureTime() {
			return departureTime;
		}

		public void setDepartureTime(LocalTime departureTime) {
			this.departureTime = departureTime;
		}
		
		public LocalDate getArrivalDate() {
			return arrivalDate;
		}

		public void setArrivalDate(LocalDate arrivalDate) {
			this.arrivalDate = arrivalDate;
		}
		
		public LocalTime getArrivalTime() {
			return arrivalTime;
		}

		public void setArrivalTime(LocalTime arrivalTime) {
			this.arrivalTime = arrivalTime;
		}
		
		public String getDuration() {
			return duration;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}

		public int add(Booking booking) {
			if(!this.bookings.contains(booking)) {
				this.bookings.add(booking);
				return 1;
				
			}
			return 0;
		}
		
		public Booking cancel(Booking booking) {
			if(this.bookings.contains(booking)) {
				Booking cancelledBooking=this.bookings.get(this.bookings.indexOf(booking));
				
				this.bookings.remove(booking);
			    return cancelledBooking;
			}
			return null;
		}

		public void cancelAllBookings() {
			if (!this.bookings.isEmpty()) {
				   for(Booking item1: this.bookings)
				   {
					   item1.deletePassenger();
				   }
				   this.bookings.clear();
				   this.setObjectState(" #### All bookings removed, Event: cancelAllBookings() #### \n");
				   this.printStatus();
			   }
			else {
				System.out.println("Bookings empty for "+this+" \n Cannot cancel according to constraint for cancel all bookings");
			}
		}
		
		public Airports getArrivalAirport() {
			return arrivalAirport;
		}

		public void setArrivalAirport(Airports arrivalAirport) {
			this.arrivalAirport = arrivalAirport;
		}

		public Airports getDepartureAirport() {
			return departureAirport;
		}

		public void setDepartureAirport(Airports departureAirport) {
			this.departureAirport = departureAirport;
		}
		
		@Override
	    public boolean equals(Object o) { 
	     
	        if (o == this) { 
	            return true; 
	        } 
	  
	        if (!(o instanceof Flight)) { 
	            return false; 
	        } 
	          
	        Flight f = (Flight) o; 
	          
	        // Compare the data members and return accordingly  
	        if(this.getFlightNo()==f.getFlightNo()) {
	        	return true;
	        }
	        return false;
	    }

		public int getFlightSeats() {
			return flightSeats;
		}

		public void setFlightSeats(int flightSeats) {
			this.flightSeats = flightSeats;
		}

		@Override
		public int compareTo(Flight f) {
			if(this.getFlightNo()==f.getFlightNo()) {
	        	return 0;
	        }
			else if(this.getFlightNo()<f.getFlightNo()) {
				return -1;
			}
			else {
				return 1;
			}
			
		}

		public boolean flightCrewMembersConstraint() {
			
			boolean pilotExists=false;
			
			for(Employee e: this.getEmployees()) {
				if(e.getTitle()=="Pilot")
				{
					pilotExists=true;
					break;
				}
			}
			
			if(pilotExists) {
				int dura=Integer.parseInt(this.getDuration());
				if(dura<3) {
					if(this.getEmployees().size()<=3)
					{
						return true;
					}
				}
				else {
					if(this.getEmployees().size()>=4 && this.getEmployees().size()<=6)
					{
						return true;
					}
				}
			}
			
			return false;
		}
		
		public boolean differentSeatConstraint() {
			bookingSet= new TreeSet<Booking>(this.bookings);
			if(bookingSet.size()==this.bookings.size())
			{
				return true;
			}
			return false;
		}

		public String getObjectState() {
			return objectState;
		}

		public void setObjectState(String objectState) {
			this.objectState = objectState;
		}
		
		public void printStatus() {
			System.out.println("\n Flight "+this.getFlightNo()+": "+this.getObjectState());
		}
		
		
		

	
}
