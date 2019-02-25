package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EchoClient {

	private DatagramSocket socket;
	private InetAddress address;

	private byte[] buffer;

	public EchoClient() {
		try {
			socket = new DatagramSocket();
			address = InetAddress.getByName("localhost");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String sendEcho(String message) {
		DatagramPacket packet = null;
		try {

			buffer = message.getBytes();
			packet = new DatagramPacket(buffer, buffer.length, address, 4445);
			socket.send(packet);

			packet = new DatagramPacket(buffer, buffer.length);
			socket.receive(packet);

		} catch (IOException e) {
			e.printStackTrace();
		}

		String received = new String(packet.getData(), 0, packet.getLength());

		return received;
	}

	public void close() {
		socket.close();
	}
}
