public class TestStudent{
	public static void main(String[] args){
		Student st = new Student();
			st.setName("Kyle");
			st.setGPA(4.0);
			st.setMajor("CompSci");
			st.setAddress("3727", "Nottingham Way", "Hamilton");

		Employee emp = new Employee();
			emp.setName("Kyle");
			emp.setRate(50.0);
			emp.setHours(40);
			emp.setAddress("23", "Green Lane", "Dublin");


		StudentEmployee stEmp = new StudentEmployee();
			stEmp.setName(st.getName());
			stEmp.setRate(emp.getRate());
			stEmp.setHours(emp.getHours());
			stEmp.setGPA(st.getGPA());
			stEmp.setMajor(st.getMajor());
			stEmp.setAddress(emp.getAddress());


		StudentEmployee stEmp2 = (StudentEmployee) stEmp.clone();	

		stEmp2.setAddress("1", "stupid street", "ny");

		System.out.println(stEmp.toString());
		System.out.println(stEmp2.toString());
	}
}