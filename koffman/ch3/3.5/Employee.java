public class Employee implements EmployeeInterface{
	public String name;
	public double hours;
	public double rate;
	public Address address = new Address();

	// constructors
	public Employee(){};
	public Employee(String name, double hours, double rate, Address address){
		this.name = name;
		this.hours = hours;
		this.rate = rate;
		this.address = address;
	}

	// public methds
	public String getName(){
		return name;
	};
	public double getHours(){
		return hours;
	};
	public double getRate(){
		return rate;
	};
	public Address getAddress(){
		return address.getAddress();
	};
	
	public String setName(String name){
		return this.name = name;
	};
	public double setHours(double hours){
		return this.hours = hours;
	};
	public double setRate(double rate){
		return this.rate = rate;
	};
	public Address setAddress(String houseNum, String street, String city){
		return this.address = address.setAddress(houseNum, street, city);
	};
	public Address setAddress(Address address){
		return this.address = address.setAddress(address);
	};

	public String toString(){
			String result = "name: " + name +
							"\nHours: " + hours +
							"\nRate: " + rate +
							"\nAddress: " + address.toString();
			return result;				
		}
}