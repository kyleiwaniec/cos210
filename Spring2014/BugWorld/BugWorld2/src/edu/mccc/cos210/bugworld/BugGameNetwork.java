package edu.mccc.cos210.bugworld;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BugGameNetwork {
	private BlockingQueue<String> sendQueue = new LinkedBlockingQueue<>();
	private List<String> recvQueue = Collections.synchronizedList(new LinkedList<String>());
	public BugGameNetwork(String hostName, int port) {
		new BugWorldReceiveThread(hostName, port).start();
	}
	public BlockingQueue<String> getSendQueue() {
		return this.sendQueue;
	}
	public List<String> getRecvQueue() {
		return this.recvQueue;
	}
	class BugWorldReceiveThread extends Thread {
		int port;
		public BugWorldReceiveThread(String hostName, int port) {
			this.port = port;
			new BugWorldSendThread(
				hostName,
				port == 5975 ? 5976 : 5975
			).start();
		}
		@Override
		public void run() {
			DatagramSocket socket = null;
			try {
				socket = new DatagramSocket(port);
				while (true) {
					byte[] buf = new byte[256];
					DatagramPacket packet = new DatagramPacket(buf, buf.length);
					socket.receive(packet);
					recvQueue.add(new String(packet.getData()));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				socket.close();
			}
		}
	}
	public class BugWorldSendThread extends Thread {
		private String hostName;
		private int port;
		public BugWorldSendThread(String hostName, int port) {
			this.hostName = hostName;
			this.port = port;
		}
		@Override
		public void run() {
			DatagramSocket socket = null;
			InetAddress address = null;
			try {
				socket = new DatagramSocket();
				address = InetAddress.getByName(this.hostName);
				while (true) {
					byte[] buf = sendQueue.take().getBytes();
					DatagramPacket packet = new DatagramPacket(buf, buf.length,	address, port);
					socket.send(packet);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				socket.close();
			}
		}
	}
}
