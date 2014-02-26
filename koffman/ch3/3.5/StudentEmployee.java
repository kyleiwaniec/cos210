public class StudentEmployee implements EmployeeInterface, StudentInterface, Cloneable{
	private Student student = new Student();
	private Employee employee = new Employee();
	public String name;
	public double hours;
	public double rate;
	public double gpa;
	public String major;
	public Address address = new Address();

	// constructors
	public StudentEmployee(){};
	public StudentEmployee(String name, double hours, double rate, double gpa, String major, Address address){
		this.name = name;
		this.hours = hours;
		this.rate = rate;
		this.gpa = gpa;
		this.major = major;
		this.address = address;
	}

	// public methds
	public String getName(){
		return name;
	};
	public double getHours(){
		return employee.getHours();
	};
	public double getRate(){
		return employee.getRate();
	};
	public double getGPA(){
		return student.getGPA();
	};
	public String getMajor(){
		return student.getMajor();
	};
	public Address getAddress(){
		return address.getAddress();
	};
	
	public String setName(String name){
		return this.name = name;
	};
	public double setHours(double hours){
		return this.hours = employee.setHours(hours);
	};
	public double setRate(double rate){
		return this.rate = employee.setRate(rate);
	};
	public double setGPA(double gpa){
		return this.gpa = student.setGPA(gpa);
	};
	public String setMajor(String major){
		return this.major = student.setMajor(major);
	};
	public Address setAddress(String houseNum, String street, String city){
		return this.address = address.setAddress(houseNum, street, city);
	};
	public Address setAddress(Address address){
		return this.address = address.setAddress(address);
	};


	public Object clone(){
		try{
			StudentEmployee cloned = (StudentEmployee) super.clone();
			cloned.address = (Address) address.clone();
			return cloned;
		}catch(CloneNotSupportedException ex){
			throw new InternalError();
		}
	}

	public String toString(){
			String result = "Name: " + name +
							"\nHours: " + hours +
							"\nRate: " + rate +
							"\nGPA: " + gpa +
							"\nMajor: " + major +
							"\nAddress: " + address.toString();
			return result;				
		}
}