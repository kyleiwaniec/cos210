import java.io.*;
public class Wump {
	private BufferedReader br;
	private PrintStream ps;
	public Wump() throws Exception {
		Process p = Runtime.getRuntime().exec("./wump -d");
		this.br = new BufferedReader(
			new InputStreamReader(
				p.getErrorStream()
			)
		);
		this.ps = new PrintStream(
			p.getOutputStream()
		);
	}
	public static void main(String[] sa) throws Exception {
		new Wump().doIt();
	}
	private void doIt() throws Exception {
		while (true) {
			StringBuilder sb = new StringBuilder();
			String s = "";

			while ((s = this.br.readLine()) != null) {
				if (s.length() == 0) {
					break;
				}
				sb.append(s + "\n");
			}
			// here do the UI thing instead of println:
			System.out.println(sb.toString());

			Thread.sleep(5000);
			
			String reply = parseComputeAndReply(sb.toString());
			this.ps.println(reply);
			this.ps.flush();
		}
	}
	private String parseComputeAndReply(String s) {
		//if ("Instructions? (y-n) \n".equals(s)) {
		//	return "n";
		//} else {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String ans = "";
			try{
				ans = br.readLine();
			}
	        catch(IOException e){
	            System.out.println("input error");
	        }
			return ans;
		//}
	}
}

