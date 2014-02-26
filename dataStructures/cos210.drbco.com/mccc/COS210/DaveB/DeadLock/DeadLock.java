public class DeadLock {
	private Object dead = new Object();
	private Object head = new Object();
	public static void main(String[] sa) {
		DeadLock dl = new DeadLock();
		dl.new DeadHeadThread().start();
		dl.new HeadDeadThread().start();
	}
	private class DeadHeadThread extends Thread {
		private long n = 0;
		public void run() {
			while (true) {
				synchronized (dead) {
					synchronized (head) {
						System.out.println("+ " + (n++) + " DeadHead");
					}
				}
			}
		}
	}
	private class HeadDeadThread extends Thread {
		private long n = 0;
		public void run() {
			while (true) {
				synchronized (head) {
					synchronized (dead) {
						System.out.println("- " + (n++) + " HeadDead");
					}
				}
			}
		}
	}
}
