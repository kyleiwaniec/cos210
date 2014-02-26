public class Student implements StudentInterface{
	public String name;
	public double gpa;
	public String major;
	public Address address = new Address();

	// constructors
	public Student(){};
	public Student(String name, double gpa, String major, Address address){
		this.name = name;
		this.gpa = gpa;
		this.major = major;
		this.address = address;
	}

	// public methds
	public String getName(){
		return name;
	};
	public double getGPA(){
		return gpa;
	};
	public String getMajor(){
		return major;
	};
	public Address getAddress(){
		return address.getAddress();
	};
	public String setName(String name){
		return this.name = name;
	};
	public double setGPA(double gpa){
		return this.gpa = gpa;
	};
	public String setMajor(String major){
		return this.major = major;
	};
	public Address setAddress(String houseNum, String street, String city){
		return this.address = address.setAddress(houseNum, street, city);
	};
	public Address setAddress(Address address){
		return this.address = address.setAddress(address);
	};

	public String toString(){
			String result = "name: " + name +
							"\ngpa: " + gpa +
							"\nmajor: " + major +
							"\n" + address.toString();
			return result;				
		}
}