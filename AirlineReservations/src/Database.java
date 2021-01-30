import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.time.LocalDate;
import java.time.LocalTime;

public class Database {
	
	private ArrayList<Passenger> passenger;
	private ArrayList<Flight> flight;
	//private ArrayList<Booking> seat;
	//private ArrayList<Booking> ReservedSeats;
	private ArrayList<Employee> employee;
	private ArrayList<Airports> airportsInfo;
	private ArrayList<Delays> delaysInfo;
	private boolean flightNoConstraintSatisfied=false;
	private boolean flightCrewMembersConstraintSatisfied=false;
	private Set<Flight> flightSet;
	
	
	public Database() {
		passenger=new ArrayList<Passenger>();
		flight=new ArrayList<Flight>();
		//seat=new ArrayList<Booking>();
		employee=new ArrayList<Employee>();
		airportsInfo = new ArrayList<Airports>();
		delaysInfo = new ArrayList<Delays>();
	}
	
	
	public ArrayList<Passenger> getPassenger() {
		return passenger;
	}
	public void setPassenger(int classID,String name, String passportNo, String address, String tel, String email, Booking booking) {
		
		passenger.add(new Passenger(classID,name, passportNo, address, tel, email, booking));
			
	}
	
	
	public ArrayList<Flight> getFlight() {
		return flight;
	}
	public void setFlight(int classID, int flightNo, int flightSeats, Airports departureAirport, LocalDate departureDate, LocalTime departureTime, Airports arrivalAirport, LocalDate arrivalDate, LocalTime arrivalTime, String duration) {
		flight.add(new Flight(classID,flightNo, flightSeats, departureAirport, departureDate, departureTime, arrivalAirport, arrivalDate, arrivalTime, duration));
		
	}
	
	
	public ArrayList<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(int EmpNo, String EmpName, String EmpAddress, String EmpTel, String EmpEmail, String title, ArrayList<String> status, ArrayList<Flight> flights) {
		employee.add(new Employee(EmpNo, EmpName, EmpAddress, EmpTel, EmpEmail, title, status, flights));
	}


	public ArrayList<Airports> getAirportsInfo() {
		return airportsInfo;
	}
	public void setAirportsInfo(String airportName, String city,ArrayList<Flight> arrivalFlights, ArrayList<Flight> departureFlights) {
		airportsInfo.add(new Airports(airportName, city,arrivalFlights,departureFlights));
	}


	public ArrayList<Delays> getDelaysInfo() {
		return delaysInfo;
	}
	public void setDelaysInfo(Flight flight, LocalTime delayedDepartureTime, LocalTime delayedArrivalTime) {
		delaysInfo.add(new Delays(flight, delayedDepartureTime, delayedArrivalTime));
	}
	
	private boolean flightNoConstraint() {
		flightSet= new TreeSet<Flight>(this.flight);
		if(flightSet.size()==this.flight.size())
		{
			//System.out.println("Unique flight numbers constraint has been satisfied!");
			return true;
		}
		return false;
	}
	
	public void bootstrap() {
		
		String airportNames[]= {"MTL International Airport","NY International Airport","TRO International Airport","DHL International Airport","LDN International Airport","STL International Airport","CHG International Airport"};
		String cityNames[]= {"Montreal","New York","Toronto","Delhi","London","Seattle","Chicago"};
		String dates[]= {"2020-02-20","2020-03-04","2020-02-23","2020-02-16"};
		String times[]= {"06:30","21:00","15:20","11:40"};
		String durations[]= {"4","3","5","7"};
		int flightNos[]={1000,2001,3000,4001};
		int flightSeats[]={10,10,10,10};
	
		
		for(int i=0;i<4;i++)
		{
			Airports arrivalAirport=new Airports();
			Airports departureAirport=new Airports();
			arrivalAirport.setAirportName(airportNames[cityNames.length-1-i]);
			arrivalAirport.setCity(cityNames[cityNames.length-1-i]);
			departureAirport.setAirportName(airportNames[i]);
			departureAirport.setCity(cityNames[i]);
			setFlight(i,flightNos[i], flightSeats[i], departureAirport, LocalDate.parse(dates[i]), LocalTime.parse(times[i]), arrivalAirport, LocalDate.parse(dates[i]), LocalTime.parse(times[i]), durations[i]);	
		}
		
		for(Flight f: getFlight()) {
			f.setObjectState(" #### Flight Created. #### \n");
			f.printStatus();
		}
		flightNoConstraintSatisfied=flightNoConstraint();
		
		if(flightNoConstraintSatisfied==false) {
			System.out.println("The flight Nos are not unique in the collection flights!!");
			System.exit(0);
		}
		
		for(int i=0;i<cityNames.length;i++)
		{
			ArrayList<Flight> arrivalFlights=new ArrayList<Flight>(10);
			ArrayList<Flight> departureFlights=new ArrayList<Flight>(10);
			for(Flight flight: getFlight()) {
				if(flight.getDepartureAirport().getCity().equals(cityNames[i])) {
					departureFlights.add(flight);
				}
				if(flight.getArrivalAirport().getCity().equals(cityNames[i])) {
					arrivalFlights.add(flight);
				}
			}
			setAirportsInfo(airportNames[i], cityNames[i],arrivalFlights,departureFlights);
		}
		
		
		
		

		
		setDelaysInfo(getFlight().get(0), LocalTime.parse("06:30"), LocalTime.parse("07:40"));
		setDelaysInfo(getFlight().get(1), LocalTime.parse("21:30"), LocalTime.parse("22:50"));
		setDelaysInfo(getFlight().get(2), LocalTime.parse("15:40"), LocalTime.parse("02:50"));
		setDelaysInfo(getFlight().get(3), LocalTime.parse("11:40"), LocalTime.parse("01:30"));
		
		String employeeNames[]= {"Eric","Sam","Tony","Pony","Mony","Gony","Lony","Rony"};
		String titles[]= {"Pilot","Pilot","Pilot","Pilot","Crew member","Crew member","Crew member","Manager"};
		String status[][]= {{"Occupied","Occupied","Occupied","Occupied"},{"Occupied","Occupied","Occupied","Occupied"},{"Occupied","Occupied","Occupied","Occupied"},{"Occupied","Occupied","Occupied","Occupied"},{"Free","Free","Free","Free"},{"Free","Free","Free","Free"},{"Free","Free","Free","Free"},{"Free","Free","Free","Free"}};
		
		
		for(int i=0;i<employeeNames.length;i++)
		{
			ArrayList<Flight> employeeAssignedFlights=new ArrayList<Flight>(10);
			ArrayList<String> employeeStatusAssigned=new ArrayList<String>(10);
			String statusAssigned[]=status[i];
			for(int j=0;j<statusAssigned.length;j++) {
				
				int flightIndex=this.flight.indexOf(new Flight(flightNos[j]));	
				employeeStatusAssigned.add(statusAssigned[j]);
				String stat=employeeStatusAssigned.get(j);
				
				if(stat=="Occupied") {
					employeeAssignedFlights.add(this.flight.get(flightIndex));
					
				}
				else{
					employeeAssignedFlights.add(null);
				}
			}
				
			setEmployee(i,employeeNames[i],"101 author st montreal"," 012347589","eric@mail.com" ,titles[i], employeeStatusAssigned, employeeAssignedFlights);
			
			
		}
		
		for(int i=0;i<getFlight().size();i++) 
		{
			for(int j=0;j<getEmployee().size();j++)
			{
				Employee employee=getEmployee().get(j);
				if(employee.getStatus().get(i)=="Occupied")
				{
					this.flight.get(i).setEmployees(employee);
				}
				
			}
		}
		
		for(Flight f: getFlight()) {
			f.setObjectState(" #### Employees added default. Event: setEmployee() #### \n");
			f.printStatus();
		}
		
		for(Flight f: getFlight()) {
			flightCrewMembersConstraintSatisfied=f.flightCrewMembersConstraint();
			if(flightCrewMembersConstraintSatisfied==false) {
				System.out.println("The employees assigned for Flight: "+f+" doesnt satisfy crew member constraints!!");
				System.exit(0);
			}
			
		}
		
		//System.out.println("Crew members constraint satisfied for the flights");
		
		Employee manager=this.employee.get(this.employee.indexOf(new Employee(7)));
		
		for(Employee e: this.employee) {
			if(!e.getTitle().equals("Manager")) {
				manager.setSupervises(e);
			}
		}
		
		for(Employee e: getEmployee()) {
			if(e.getTitle().equals("Manager")) {
			e.managerSupervisesConstraint();
		}
		
		}
		
		
	}



}
