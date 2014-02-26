public class Computer{
	// Data fields
	private String manufacturer;
	private String processor;
	private int ramSize;
	private int diskSize;

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
		
		// insert other accessor and modifier methods here
		public String toString(){
			String result = "Manufacturer: " + manufacturer +
							"\nCPU: " + processor +
							"\nRAM: " + ramSize +
							"\nDisk: " + diskSize + " gigabytes";
			return result;				
		}
}

