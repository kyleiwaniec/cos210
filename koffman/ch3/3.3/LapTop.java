import java.lang.reflect.Field;

public class LapTop extends Computer{
	// Data fields
	private static final String DEFUALT_LT_MAN = "my brand";
	private double screenSize;
	private double weight;
	public LapTop(String man, String proc, int ram, int disk, double screen, double wei){
		super(man, proc, ram, disk);
		this.screenSize = screen;
		this.weight = wei;
	}
	public LapTop(String proc, int ram, int disk, double screen, double wei){
		this(DEFUALT_LT_MAN, proc, ram, disk, screen, wei);
	}
	public LapTop(){
		super();
	}
	public LapTop(String man, String proc, int ram, int disk){
		super(man, proc, ram, disk);
	}

	// implenetation of abstract method in Computer
	public double costBenefit(double c){
		return c;
	};

	public double getScreenSize() {return screenSize;}
	public double getWeight() {return weight;}
	public double setScreenSize(double size) {return this.screenSize = size;}
	public double setWeight(double weight) {return this.weight = weight;}

	public static boolean compareLapTops(LapTop a, LapTop b){
		if( a.getWeight() == b.getWeight() && a.getScreenSize() == b.getScreenSize()){
			return true;
		}else{
			return false;
		}
	}
	public String toString(){
		String result = super.toString() +
						"\nScreen size: " + screenSize + " inches" +
						"\nWeight: " + weight + " pounds";
		return result;				
	}
	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException{
		Computer c1 = new LapTop();
		Computer c2 = new LapTop("Ace", "AMD 2000", 512, 60);
		LapTop c3 = new LapTop("Ace", "AMD 2000", 512, 60, 17.5, 9.5);
		LapTop c4 = new LapTop("Ace", "AMD 2000", 512, 60, 17.5, 9.5);

		// System.out.println(compareLapTops(c3, c4));
		// System.out.println(c2.getManufacturer() + ", "+c4.getProcessor());
		// System.out.println(c2.getDiskSize() + ", "+c4.getRamSize());
		// System.out.println(c2.toString() + ", "+c4.toString());
		c4.setWeight(12.2);
		System.out.println(c4.getWeight());

		Class c3Class = Class.forName("LapTop");
         
        Field[] fields = c3Class.getDeclaredFields(); //does not include fields declared in Shape getFields should return inherited fields.
        for (Field f : fields) {
            //System.out.println("field name = " + f.getName()); //prints only 'sideLength'           
        }

        //System.out.println(c3.toString());
    }
}


