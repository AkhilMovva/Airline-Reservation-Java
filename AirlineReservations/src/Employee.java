import java.util.ArrayList;

public class Employee extends PersonDetails{
	
	private int employeeNo;
	private String title;
	private ArrayList<String> status;
	private ArrayList<Flight> flights;
	private ArrayList<Employee> supervises;

	
	public Employee(int employeeNo) {
		this.employeeNo=employeeNo;
	}
	public Employee (int employeeNo, String EmpName, String EmpAddress, String EmpTel, String EmpEmail, String title, ArrayList<String> status, ArrayList<Flight> flights){
		this.flights=flights;
		supervises=new ArrayList<Employee>(10);
		setemployeeNo(employeeNo);
		setName(EmpName);
	    setAddress(EmpAddress);
	    setTel(EmpTel);
	    setEmail(EmpEmail);
	    setTitle(title);
	    setStatus(status);
	}
	
	public int getemployeeNo() {
		return employeeNo;
	}

	public void setemployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}
	
	public String getTitle(){
		return title;
	}
	public void setTitle(String newTitle){
		title= newTitle;
	}
	
	public ArrayList<String> getStatus(){
		return status;
	}
	public void setStatus(ArrayList<String> newStatus){
		status= newStatus;
	}
	
	public ArrayList<Flight> getFlights() {
		return flights;
	}

	public void setFlights(int index,Flight flight) {
		
			this.flights.add(index,flight);
	}
	
	public String getStatusVal(int index){
		return this.status.get(index);
	}
	public void setStatusVal(int index,String val){
		this.status.set(index, val);
	}
	
	@Override
    public boolean equals(Object o) { 
     
        if (o == this) { 
            return true; 
        } 
  
        if (!(o instanceof Employee)) { 
            return false; 
        } 
          
        Employee e = (Employee) o; 
          
        // Compare the data members and return accordingly  
        if(this.getemployeeNo()==e.getemployeeNo()) {
        	return true;
        }
        return false;
    } 
	
	
	//toString
	public String toString() {
		String employeeDetails="###############\n";
		int count=0;
		for(String stat: this.getStatus()) {
			Flight flight=this.getFlights().get(count);
			if(flight!=null) {
				employeeDetails=employeeDetails.concat(this.getemployeeNo() +" Employee: " +this.getName() + ", Title: " + this.getTitle() + ", Status: " +stat+", flight: "+flight.getFlightNo()+", Slot: "+count+"\n");
			}
			else
			{
				employeeDetails=employeeDetails.concat(this.getemployeeNo() +" Employee: " +this.getName() + ", Title: " + this.getTitle() + ", Status: " +stat+", flight: N/A"+", Slot: "+count+"\n");
			}
			
			++count;
		}
		employeeDetails=employeeDetails.concat("###############\n");
		return employeeDetails;
		 
	}
	
	public int managerSupervisesConstraint() {
		if(this.title=="Manager")
		{
			if(this.supervises.size()<=10)
			{
				//System.out.println(this.getTitle()+" "+this.getemployeeNo()+" satisfies the constraint of supervising less than 10 people");
				return 1;
			}
			else {
				System.out.println(this.getTitle()+" "+this.getemployeeNo()+" doesnt satisfy the constraint of supervising less than 10 people");
				System.exit(0);
			}
		}
		
		return 2;
	}
	public ArrayList<Employee> getSupervises() {
		return supervises;
	}
	public void setSupervises(Employee e) {
		if(this.title=="Manager")
		{
			this.supervises.add(e);
		}
		else {
			System.out.println("supervisees can only be added to a manager!");
			System.exit(0);
		}
	}
}
