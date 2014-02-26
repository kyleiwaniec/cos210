public class Point  implements Cloneable{
		private double xCoor;
		private double yCoor;
		private int leastSig = 0;
		private int lastRemoved = 0;
		private int idx = 0;

		public Point(){};
		public Point(double xCoor, double yCoor){
			this.xCoor = xCoor;
			this.yCoor = yCoor;
		}

		public double getXCoor(){
			return this.xCoor;
		}

		public double getYCoor(){
			return this.yCoor;
		}

		public double setXCoor(double xCoor){
			return this.xCoor = xCoor;
		}

		public double setYCoor(double yCoor){
			return this.yCoor = yCoor;
		}

		public int getLeastSig(){
			return this.leastSig;
		}
		public int setLeastSig(int ls){
			return this.leastSig = ls;
		}
		public int getLastRemoved(){
			return this.lastRemoved;
		}
		public int setLastRemoved(int lr){
			return this.lastRemoved = lr;
		}

		public int getIdx(){
			return this.idx;
		}
		public int setIdx(int idx){
			return this.idx = idx;
		}

		public Object clone(){
			try{
				Point cloned = (Point) super.clone();
				return cloned;
			}catch(CloneNotSupportedException ex){
				throw new InternalError();
			}
		}
		public String toString(){
			String result = "";
			result += 
					  "\n ls: "+this.leastSig+
					  
					  " idx: "+this.idx;
			return result;				
		}

	}