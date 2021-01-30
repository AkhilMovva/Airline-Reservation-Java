import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
//import java.util.Scanner;


/* ------------------------------------- Details: -------------------------------------------------
 *                                    Name: Akhil Movva
 *                                       ID: 40106477
 * -----------------------------------------------------------------------------------------------------*/

public class Airline {

	public static void main(String[] args) {
		
		Database airlineDB = new Database();
		
		
		
		airlineDB.bootstrap();
		
		boolean always = true;
		int PassengerID=0;
		String passengerName = null;
		String passengerPassportNo=null;
		String passengerAddress = null;
		String passengerTel = null;
		String passengerEmail = null;
		
		int flightNumber = 0;
		int seatNumber = 0;
		
		BufferedReader screenInput = new BufferedReader(new InputStreamReader(System.in));
		
		String tCheckPE = null;
		int tCheckReser = 0;
		int tCheckEmp = 0; 
		
		
			Flight tFlightSelected = null;
			System.out.println("----------------------------------------------------------------");
			System.out.println("Choose Passenger (p) or Employee (e) or Track Delayed flights (t) :  (Type p/e/t)");
			System.out.print("===> ");
			try {
				tCheckPE = screenInput.readLine();
			} catch (IOException e) {
				System.out.println("Sorry, i didn't get it.");
			}
			
			switch(tCheckPE)
			{
			   case "p":
					while(always)
					{
						System.out.println("----------------------------------------------------------------");
						System.out.println("                   Welcome to Airline !!! ");
						System.out.println("----------------------------------------------------------------");
						System.out.println("1. Reservation: ");
						System.out.println("2. Cancel a Booking: ");
						System.out.println("3. Cancel all Bookings: ");
						System.out.println("4. Airports and Cities List: ");
						System.out.println("5. Quit:");
						System.out.println("-->(1-5)");
						System.out.print("===> ");
						try{
							tCheckReser = Integer.parseInt(screenInput.readLine());		
						}
						catch (IOException e) {
							System.out.println("Please enter a valid No");
						}
						catch (NumberFormatException e) {
							System.out.println("That was not a number");
						}
						
						
						Booking bookingNew = null;
						
						switch(tCheckReser)
						{
						   case 1:
							   	
						        /////////////////////// Passenger Details /////////////////////////
							    
							   	boolean oneBookingPerFlightConstraintSatisfied=true;
							   	
							    System.out.println("----------------------------------------------------------------");
								System.out.println("Enter Passenger's Name: ");
								System.out.print("===> ");
								
								try {
									passengerName = screenInput.readLine();
								} catch (IOException e) {
									System.out.println("Sorry, i dont understand.");
								}
								
								
								
								try {
									System.out.println("----------------------------------------------------------------");
									System.out.println("Enter Passenger's passport number: ");
									System.out.print("===> ");
								passengerPassportNo = screenInput.readLine();
								} catch (IOException e) {
									System.out.println("Sorry, i dont understand.");
								}
								
								int ind=airlineDB.getPassenger().indexOf(new Passenger(passengerPassportNo));
								Passenger passengerExisting=null;
								if(ind!=-1) {
									passengerExisting=airlineDB.getPassenger().get(ind);
								}
									
									
								System.out.println("Enter Passenger's Address: ");
								System.out.print("===> ");
								
								try {
									passengerAddress = screenInput.readLine();
								} catch (IOException e) {
									System.out.println("Sorry, i dont understand.");
								}
								System.out.println("Enter Passenger's Phone Number: ");
								System.out.print("===> ");
								
								try {
									passengerTel = screenInput.readLine();
								} catch (IOException e) {
									System.out.println("Sorry, i dont understand.");
								}
								System.out.println("Enter Passenger's mail: ");
								System.out.print("===> ");
								
								try {
									passengerEmail = screenInput.readLine();
								} catch (IOException e) {
									System.out.println("Sorry, i dont understand.");
								}
								
								
								//////////////Flight Selection/////////////
								System.out.println("\nHere are available flights: ");
								for(Flight item: airlineDB.getFlight()){
									System.out.println(item);
								}
								
								do
								{
									System.out.println("----------------------------------------------------------------");
									System.out.println("Enter the valid flight number to book: ");
									System.out.println("----------------------------------------------------------------");
									System.out.print("===> ");
									try{
									flightNumber = Integer.parseInt(screenInput.readLine());
									} catch (IOException e) {
										System.out.println("Please enter flight no");
										System.out.print("===> ");
								    }
									catch (NumberFormatException e) {
										System.out.println("That was not a number");
									}
									
									ArrayList<Flight> flights=airlineDB.getFlight();
									int indflight=flights.indexOf(new Flight(flightNumber));
									if(indflight==-1)
									{
										continue;
									}
									tFlightSelected=flights.get(indflight);
									
									if(passengerExisting!=null) {
										oneBookingPerFlightConstraintSatisfied=passengerExisting.oneBookingPerFlightConstraint(tFlightSelected);
									}
									
									
								}while(!oneBookingPerFlightConstraintSatisfied);
								
								/////////////////Seat Selection/////////////
								System.out.println("Here are available seats on that flight: ");
								
								ArrayList<Booking> bookings=tFlightSelected.getBookings();
								
								for(int i=0;i<tFlightSelected.getFlightSeats();i++) {
									
									if(!bookings.contains(new Booking(i))) {
										System.out.print("[" + i + "] ");
									}
									else {
										System.out.print("[ x ] ");
									}
								}
								System.out.println(" ");
										
								int flagSeat=0;
								
								while(flagSeat==0)
								{
									System.out.println("----------------------------------------------------------------");
									System.out.println("Enter valid seat number to book: ");
									System.out.println("----------------------------------------------------------------");
									System.out.print("===> ");
									try{
										seatNumber = Integer.parseInt(screenInput.readLine());
										
									}
									catch (IOException e) {
										System.out.println("Please enter a seat number to book");
								    }
									
									Booking booking=new Booking(seatNumber);
									booking.setObjectState(" #### Booking created. #### \n");
									booking.printStatus();
									
									flagSeat=tFlightSelected.add(booking);
									if(flagSeat==1) {
										tFlightSelected.setObjectState(" #### Passenger Booking added, Event: add() #### \n");
										tFlightSelected.printStatus();
										bookingNew=booking;
										bookingNew.setPassFlight(tFlightSelected);
										booking.setObjectState(" #### Booking flight added, Event: setPassFlight() #### \n");
										booking.printStatus();
										break;
									}	
									else {
										System.out.println("Doesn't satisfy the constraints:\n 1. Passengers on the same flight must be assigned different seats!\n 2. The booking is only added if it doesn't already exists!\n");
									}
								
								}
								
								///////////////Details passing/////////////
								ArrayList<Passenger> passengers=airlineDB.getPassenger();
								if(passengerExisting==null) {
									airlineDB.setPassenger(PassengerID,passengerName, passengerPassportNo, passengerAddress, passengerTel, passengerEmail, bookingNew);
								}
								else {
									Passenger passenger=passengers.get(passengers.indexOf(new Passenger(passengerPassportNo)));
									passenger.setPassBooking(bookingNew);
								}
							    bookingNew.setPassenger(passengers.get(passengers.indexOf(new Passenger(passengerPassportNo))));
							    bookingNew.setObjectState(" #### Booking Passenger added, Event: setPassenger() #### \n");
							    bookingNew.printStatus();
							    PassengerID=PassengerID+1;
							    //ticketInfo = "Ticket: '" + passengerName + "' departing on flight No. [" + flightNumber + "] from <" + tFlightSelected.getDepartureAirport().getCity()+ "> on " + tFlightSelected.getDepartureDate() + " [" + tFlightSelected.getDepartureTime() + "] to <"  + tFlightSelected.getArrivalAirport().getCity() + "> " +tFlightSelected.getArrivalDate()+ " ["+ tFlightSelected.getArrivalTime()+ "] in seat <<" + bookingNew.getSeatNo()+ ">> for the duration "+tFlightSelected.getDuration();
							    
							    System.out.println("----------------------------------------------------------------");
								System.out.println("\n                   Your Booking is Successful!! ");
								System.out.println("\nHere are your ticket details :");
								System.out.println(passengers.get(passengers.indexOf(new Passenger(passengerPassportNo))) + "\n");
								break;
								
							///////////////////////////Cancel a booking///////////////////////	
						   case 2:
							   do
							   {
								   System.out.println("----------------------------------------------------------------");
								   System.out.println("\nHere are available flights: ");
								   for(Flight item: airlineDB.getFlight()){
									   System.out.println(item);
								   }
								   System.out.println("----------------------------------------------------------------");
								   System.out.println("\nEnter the Flight Number to Cancel : ");
								   System.out.print("===> ");
								   try{
									flightNumber = Integer.parseInt(screenInput.readLine());
								   } catch (IOException e) {
										System.out.println("Please enter flight no");
										System.out.print("===> ");
								   } catch (NumberFormatException e) {
									System.out.println("That was not a number");
								   }
							 
								   System.out.println("These are the Reserved Seats for this Flight : \n");
							   
								   ArrayList<Flight> flights=airlineDB.getFlight();
								   tFlightSelected=flights.get(flights.indexOf(new Flight(flightNumber)));
								   
								   if(tFlightSelected==null) {
									   System.out.println("Flight Not Found!!");
								   }
							   }while(tFlightSelected==null);
							   
							   ArrayList<Booking> bookings1=tFlightSelected.getBookings();
							   
							   	if(bookings1.isEmpty())
							   {
								   System.out.println("\nSorry, There were No Reservations on this flight.\n");
							   }
							   else {
								   System.out.print(bookings1);
							   
								   System.out.println("\nEnter the Seat Number to Cancel : ");
								   System.out.print("===> ");
								   try{
										seatNumber = Integer.parseInt(screenInput.readLine());
									} catch (IOException e) {
											System.out.println("Please enter seat number");
											System.out.print("===> ");
									} catch (NumberFormatException e) {
										System.out.println("That was not a number");
									}
								   
								   Booking cancelledBooking =null;
								   cancelledBooking=tFlightSelected.cancel(new Booking(seatNumber));
								   if(cancelledBooking!=null) {
									   tFlightSelected.setObjectState(" #### Passenger Booking removed, Event: cancel() #### \n");
									   tFlightSelected.printStatus();
									   Passenger pass=cancelledBooking.getPassenger();
									   pass.getPassBooking().remove(cancelledBooking);
									   
									   cancelledBooking.setObjectState(" #### Booking removed, Event: cancel() #### \n");
									   cancelledBooking.printStatus();
										
										System.out.println("----------------------------------------------------------------");
										System.out.println("\n                    Successful.....!!!!! ");
									   System.out.println(" Your Booking has been Canceled...!!!  \n");
									   System.out.println("Flight bookings left for this passenger: \n");
									   System.out.println(pass);
									   if(pass.getPassBooking().isEmpty())
									   {
										   airlineDB.getPassenger().remove(pass);
									   }
										PassengerID=PassengerID-1;
								   } else {
									   System.out.println("----------------------------------------------------------------");
									   System.out.println("\n      Sorry, The seat you entered is not valid......!!!!! ");
									   System.out.println("\n Doesn't satisfy the constraint: The booking can only be cancelled if it already exists!");
									   System.out.println("\n                Please Try Again...!!!!! ");
									   
								   }
							   
							   }
							   break;	
							   
							   /////////////////////////////Cancel all bookings////////////////////////////
						   case 3:
							   System.out.println("----------------------------------------------------------------");
							   for(Flight item: airlineDB.getFlight())
								{ 
								   item.cancelAllBookings();		
								   
								}
							   
							   for(Passenger p: airlineDB.getPassenger()) {
								   for(Booking b: p.getPassBooking()) {
									   b.setObjectState(" #### Booking removed, Event: cancelAllBookings() #### \n");
									   b.printStatus();
								   }
							   }
							   airlineDB.getPassenger().clear();
							   
							   
							   PassengerID=0;
							   System.out.println("----------------------------------------------------------------");
							   System.out.println("\n                    Successful......!!!!! ");
							   System.out.println("\n         All Bookings have been Canceled...!!! \n");
							   
							   break;
							   //////////////////////////// Delayed Flights //////////////////////////////
						   
						   case 4:
							   System.out.println("----------------------------------------------------------------");
							   System.out.println("Here are the Airports and cities List : ");
							   System.out.println("List Departure Airports and cities List : ");
							   for(Airports item: airlineDB.getAirportsInfo())
							   {	
								   for(Flight item1: item.getDepartureFlights()) {
									   System.out.println("Airport Name: "+item.getAirportName()+", City: "+item.getCity()+", Flight No: "+item1.getFlightNo()+", Departure Date: "+item1.getDepartureDate()+", Departure Time: "+item1.getDepartureTime());
								   }
							
							   }
							   System.out.println("List of Arrival Airports and cities: ");
							   for(Airports item: airlineDB.getAirportsInfo())
							   {
								   for(Flight item1: item.getArrivalFlights()) {
										   System.out.println("Airport Name: "+item.getAirportName()+", City: "+item.getCity()+", Flight No: "+item1.getFlightNo()+", Arrival Date: "+item1.getArrivalDate()+", Arrival Time: "+item1.getArrivalTime());
									   }
							   }
							   System.out.println("----------------------------------------------------------------");
							   break;
						   case 5:
							   quit();
						   default:
							   System.out.println("           << < Please type only 1-6 numbers > >>  ");
							   System.out.println("----------------------------------------------------------------");
							   	   
						}
					
					}
					break;
					
			   case "e":
				   
					   while(always) {
						   System.out.println("----------------------------------------------------------------");
						   System.out.println("                   Welcome to Airline !!! ");
						   System.out.println("----------------------------------------------------------------");
						   System.out.println("\n1. Display Employee List");
						   System.out.println("2. Assign Status");
						   System.out.println("3. Quit");
						   System.out.print("===> ");
						   
						   try{
								tCheckEmp = Integer.parseInt(screenInput.readLine());
								
							}
							catch (IOException e) {
								System.out.println("Please enter a number");
							}
							catch (NumberFormatException e) {
								System.out.println("That was not a number");
							}
						   switch(tCheckEmp) {
						   
						   case 1:
							   System.out.println("----------------------------------------------------------------");
							   System.out.println("\nEmployee List : \n");
							   for(Employee item: airlineDB.getEmployee()){
									System.out.println(item);
									//i=i+1;
								}
							   System.out.println("----------------------------------------------------------------");
							   break;
							   
						   case 2:
							   int employeeNo = 0;
							   int flagEmp = 0;
							   int slotNumber=0;
				
							   while(flagEmp==0) 
							   {
								   System.out.println("----------------------------------------------------------------");
								   System.out.println("Enter the valid Employee Number to change the Status :");
								   System.out.print("===> ");
								   try{
									    employeeNo = Integer.parseInt(screenInput.readLine());	
									}
									catch (IOException e) {
										System.out.println("Please enter a employee number");
									}
									catch (NumberFormatException e) {
										System.out.println("That was not a number");
									}
								   
								   System.out.println("\nHere are available flights: ");
									for(Flight item: airlineDB.getFlight()){
										System.out.println(item);
									}
								   
									System.out.println("Enter the valid flight number to Assign for the employee:");
									System.out.print("===> ");
									try{
									flightNumber = Integer.parseInt(screenInput.readLine());
									} catch (IOException e) {
										System.out.println("Please enter flight no");
										System.out.print("===> ");
								    }
									catch (NumberFormatException e) {
										System.out.println("That was not a number");
									}
									
									
									ArrayList<Flight> flights=airlineDB.getFlight();
									tFlightSelected=flights.get(flights.indexOf(new Flight(flightNumber)));
									slotNumber=flights.indexOf(new Flight(flightNumber));
									ArrayList<Employee> employees=airlineDB.getEmployee();
									
									Employee employee=employees.get(employees.indexOf(new Employee(employeeNo)));
									
									String stat=employee.getStatusVal(slotNumber);
									if(stat.equals("Free")) {
										stat="Occupied";
										employee.setStatusVal(slotNumber,stat);
										employee.setFlights(slotNumber,tFlightSelected);
										tFlightSelected.setEmployees(employee);
										tFlightSelected.setObjectState(" #### Employee added, Event: setEmployees() ####\n");
										tFlightSelected.printStatus();
										flagEmp = 1;
										break;
									}
									
									  
							   }
							   System.out.println("----------------------------------------------------------------");
							   System.out.println("\n               Successfully Changed....!!! \n");
							   System.out.println("\nEmployee List : \n");
							   for(Employee item: airlineDB.getEmployee()){
									System.out.println(item);
								}
							   System.out.println("----------------------------------------------------------------");
							   break;
							   
						   case 3:
							   System.out.println("----------------------------------------------------------------");
							   quit();
						   default:
								   
							   System.out.println("           << < Please type only 1-3 numbers > >>  ");
							   System.out.println("----------------------------------------------------------------");
						   
						   }
							   
					   }
					   break;
			   case "t": 
				   System.out.println("----------------------------------------------------------------");
				   System.out.println("Here are the Delayed Flights : ");
				   
				   
						
				   for(Delays item1: airlineDB.getDelaysInfo()) 
				   {
					   	ArrayList<Flight> flights=airlineDB.getFlight();
						tFlightSelected=flights.get(flights.indexOf(new Flight(item1.getFlight().getFlightNo())));
					    LocalTime delDep = item1.getDelayedDepartureTime();
						LocalTime delArl = item1.getDelayedArrivalTime();
						LocalTime sehDep = tFlightSelected.getDepartureTime();
						LocalTime sehArl = tFlightSelected.getArrivalTime();
						int value1 = sehDep.compareTo(delDep);
						int value2 = sehArl.compareTo(delArl);
							
						if(value1 != 0 || value2 !=0) {
		
							tFlightSelected.setDepartureTime(delDep);
							tFlightSelected.setObjectState(" #### Delayed departure time set, Event: setDepartureTime() #### \n");
							tFlightSelected.printStatus();
							tFlightSelected.setArrivalTime(delArl);
							tFlightSelected.setObjectState(" #### Delayed arrival time set, Event: setArrivalTime() #### \n");
							tFlightSelected.printStatus();
							tFlightSelected.setDelays(item1);
							tFlightSelected.setObjectState(" #### Delay added, Event: setDelays() #### \n");
							tFlightSelected.printStatus();
							System.out.println(tFlightSelected);
							
							}
							
					}
					
				   System.out.println(" ");
				   System.out.println("----------------------------------------------------------------");
				   quit();
				 default:
					 System.out.println("           << < Please type only p or e or t chars > >>  ");
					 System.out.println("----------------------------------------------------------------");
				   
			
			}
		
			
			for(Passenger p: airlineDB.getPassenger()) {
				   for(Booking b: p.getPassBooking()) {
					   b.setObjectState(" #### object deleted, Event: quit() #### \n");
					   b.printStatus();
				   }
			   }
		for(Flight f: airlineDB.getFlight()) {
			f.setObjectState("\n #### object deleted. Event: quit() #### \n");
			f.printStatus();
		}
		
  }

	

	private static void quit() {
		System.exit(0);;
		
	}

}
