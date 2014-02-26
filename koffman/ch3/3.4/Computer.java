public abstract class Computer implements Cloneable{
	// Data fields
	private String manufacturer;
	private String processor;
	private int ramSize;
	private int diskSize;

	// Abstarct Methods
	/** returns the costBenefit as double.
		@return The cost benefit double
	*/
	public abstract double costBenefit(double c);

	//Methods
	/** initializes Computer object with all methods initialized
		@param man The computer manuffacturer
		@param processor The processor type
		@param ram The RAM size
		@param disk The disk size
		*/
		public Computer(){

		}
		public Computer(String man, String processor, int ram, int disk){
			this.manufacturer = man;
			this.processor = processor;
			this.ramSize = ram;
			this.diskSize = disk;
		}
		public String getManufacturer() {return manufacturer;}
		public String getProcessor() {return processor;}
		public int getRamSize() {return ramSize;}
		public int getDiskSize() {return diskSize;}

		public Object clone(){
			try{
				Computer cloned = (Computer) super.clone();
				return cloned;
			}catch(CloneNotSupportedException ex){
				throw new InternalError();
			}
		}
		
		// insert other accessor and modifier methods here
		public String toString(){
			String result = "Manufacturer: " + manufacturer +
							"\nCPU: " + processor +
							"\nRAM: " + ramSize +
							"\nDisk: " + diskSize + " gigabytes";
			return result;				
		}
}

