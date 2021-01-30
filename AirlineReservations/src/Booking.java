
public class Booking implements Comparable<Booking>{
	
	private int seatNo;
	private Flight passFlight;
	private Passenger passenger;
	private String objectState;
	
	//Constructors
	public  Booking() {
		seatNo=0;
	}
	public Booking(int seatNo){
		setSeatNo(seatNo);
	}

	//getters and setters
	public int getSeatNo() {
		// TODO Auto-generated method stub
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		// TODO Auto-generated method stub
		this.seatNo= seatNo;
	}
	
	public Flight getPassFlight() {
		return passFlight;
	}

	public void setPassFlight(Flight passFlight) {
		this.passFlight = passFlight;
	}
	
	public Passenger getPassenger() {
		return passenger;
	}
	
	public void deletePassenger() {
		this.passenger=null;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	
	//toString
	public String toString() {
		return "Seat: " + this.getSeatNo();
	}
	
	
	@Override
    public boolean equals(Object o) { 
     
        if (o == this) { 
            return true; 
        } 
  
        if (!(o instanceof Booking)) { 
            return false; 
        } 
          
        Booking b = (Booking) o; 
          
        // Compare the data members and return accordingly  
        if(this.getSeatNo()==b.getSeatNo()) {
        	return true;
        }
        return false;
    } 

	@Override
	public int compareTo(Booking b) {
		if(this.getSeatNo()==b.getSeatNo()) {
        	return 0;
        }
		else if(this.getSeatNo()<b.getSeatNo()) {
			return -1;
		}
		else {
			return 1;
		}
		
	}
	public String getObjectState() {
		return objectState;
	}
	public void setObjectState(String objectState) {
		this.objectState = objectState;
	}
	
	public void printStatus() {
		System.out.println("\n Booking seat No:  "+this.getSeatNo()+": "+this.getObjectState());
	}
	
	
}
