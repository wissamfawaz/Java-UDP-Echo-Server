import java.io.*;
import java.net.*;

public class UDPServer {
	public static void main(String[] args) throws Exception {
		String line, modifiedLine;
		DatagramSocket serverSocket = new DatagramSocket(9876);
		byte[] sendData, receiveData = new byte[1024];
		DatagramPacket sendPacket, receivePacket;
		InetAddress IPAddress;
		int port;
		while(true) {
			System.out.println("Waiting for data...");
			receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);

			line = new String(receivePacket.getData(), 0, 
					receivePacket.getLength());
			modifiedLine = line.toUpperCase();
			sendData = modifiedLine.getBytes();
			IPAddress = receivePacket.getAddress();
			port = receivePacket.getPort();

			sendPacket = new DatagramPacket(sendData, sendData.length, 
					IPAddress, port);
			
			serverSocket.send(sendPacket);
			System.out.println("Message sent back to client...");			


		}
	}
}