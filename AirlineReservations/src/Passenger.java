import java.util.ArrayList;

public class Passenger extends PersonDetails {

	private ArrayList<Booking> passBooking;
	private int classID;
	private String passportNo;
	
	public Passenger(String passportNo) {
		setPassportNo(passportNo);
	}

	public Passenger (int classID,String name, String passportNo, String address, String tel, String email, Booking booking){
		passBooking=new ArrayList<Booking>(10);
		setClassID(classID);
	    setName(name);
	    setPassportNo(passportNo);
	    setAddress(address);
	    setTel(tel);
	    setEmail(email);
	    setPassBooking(booking);  
	}

	public ArrayList<Booking> getPassBooking() {
		return passBooking;
	}

	public void setPassBooking(Booking passBooking) {
		this.passBooking.add(passBooking);
	}
	
	public int getClassID() {
		return classID;
	}

	public void setClassID(int classID) {
		this.classID = classID;
	}
	
	public String toString() {
		String passengerDetails="###############\n";
		for(Booking b: this.passBooking) {
			passengerDetails=passengerDetails.concat("Ticket: " + this.getName() + " with passport No: "+this.getPassportNo()+" and flight no: "+b.getPassFlight().getFlightNo() + " from " + b.getPassFlight().getDepartureAirport().getCity()+ " " + b.getPassFlight().getDepartureDate() + " " + b.getPassFlight().getDepartureTime() + " to "  + b.getPassFlight().getArrivalAirport().getCity() + " " +b.getPassFlight().getArrivalDate()+ " "+ b.getPassFlight().getArrivalTime()+ " in seat " + b.getSeatNo()+ " for the duration "+b.getPassFlight().getDuration()+"\n");
		}
		passengerDetails=passengerDetails.concat("###############\n");
		return passengerDetails;
	}
	
	@Override
    public boolean equals(Object o) { 
     
        if (o == this) { 
            return true; 
        } 
  
        if (!(o instanceof Passenger)) { 
            return false; 
        } 
          
        Passenger p = (Passenger) o; 
          
        // Compare the data members and return accordingly  
        if(this.passportNo.equals(p.passportNo)) {
        	return true;
        }
        return false;
    }

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	} 
	
	public boolean oneBookingPerFlightConstraint(Flight f) {
		if(!this.passBooking.isEmpty()) {
			for(Booking b: this.passBooking) {
				if(b.getPassFlight().getFlightNo()!=f.getFlightNo()) {
					return true;
				}
				else if(b.getPassFlight().getFlightNo()==f.getFlightNo()) {
					if(!(f.getDepartureDate().equals(b.getPassFlight().getDepartureDate())) && !(f.getDepartureTime().equals(b.getPassFlight().getDepartureTime()))) {
						return true;
					}
					else {
						System.out.println("A passenger cannot have more than one booking for the same flight!");
						return false;
					}
						
				}
				else {
					System.out.println("A passenger cannot have more than one booking for the same flight!");
					return false;
				}
					
			}
		}
		return true;
	}
}
