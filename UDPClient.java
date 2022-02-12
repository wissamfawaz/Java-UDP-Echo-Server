import java.io.*;
import java.net.*;

public class UDPClient {

	public static void main(String[] args) throws Exception {
		String line, modifiedLine;
		byte[] sendData, receiveData = new byte[1024];
		DatagramSocket clientSocket = new DatagramSocket();
		BufferedReader inFromUser = new BufferedReader(
					new InputStreamReader(System.in));
		InetAddress IPAddress;
		DatagramPacket sendPacket, receivePacket;
		int port = 9876;

		System.out.println("Enter a line of text: ");
		line = inFromUser.readLine();

		sendData = line.getBytes();		
		IPAddress = InetAddress.getByName("localhost");

		sendPacket = new DatagramPacket(sendData, sendData.length, 
				IPAddress, port);
		clientSocket.send(sendPacket);

		System.out.println("Packet sent to server...");

		receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);

		modifiedLine = new String(receivePacket.getData(), 0, 
				receivePacket.getLength());
		System.out.println("Received from Server: " + modifiedLine);

		clientSocket.close();				

	}
}