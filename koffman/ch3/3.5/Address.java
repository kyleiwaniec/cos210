public class Address implements Cloneable{
	private String houseNum;
	private String street;
	private String city;	

	// public methds
	public String getHouseNum(){
		return houseNum;
	};
	public String getStreet(){
		return street;
	};
	public String getCity(){
		return city;
	};
	
	public String setHouseNum(String houseNum){
		return this.houseNum = houseNum;
	};
	public String setStreet(String street){
		return this.street = street;
	};
	public String setCity(String city){
		return this.city = city;
	};
	
	public Address getAddress(){
		this.getHouseNum();
		this.getStreet();
		this.getCity();
		return this;
	};

	public Address setAddress(String houseNum, String street, String city){
		this.setHouseNum(houseNum);
		this.setStreet(street);
		this.setCity(city);
		return this;
	};
	public Address setAddress(Address address){
		this.houseNum = address.getHouseNum();
		this.street = address.getStreet();
		this.city = address.getCity();
		return this;
	};

	public Object clone(){
		try{
			Address cloned = (Address) super.clone();
			return cloned;
		}catch(CloneNotSupportedException ex){
			throw new InternalError();
		}
	}

	public String toString(){
			String result = houseNum +" "+ street +" "+ city;
			return result;				
		}

}