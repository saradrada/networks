package udp.broadcast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import udp.broadcast.threads.CatcherThread;

/**
 * 
 * @author Sara Ortiz Drada
 * @version 1.0.0
 */
public class BroadcastingServer {
	private static ServerSocket serverSocket;
	private static Socket[] sockets;
	private DataInputStream in;
	private DataOutputStream out;

	public BroadcastingServer(int port) {

		sockets = new Socket[10];
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Initializing server... [Ok].");

			while (true) {

				Socket socket = serverSocket.accept();
				System.out.println("Nueva conexion entrante");

				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				CatcherThread sct = new CatcherThread(socket, sockets, in, out);
				sct.start();

				String client_Request = in.readUTF();

				System.out.println("El mensaje enviado por el cliente fue : " + client_Request);

				// out.writeUTF("okay");
			}

		} catch (IOException e) {
			System.out.println("error");
		}

	}

	public static void main(String[] args) {
		BroadcastingServer server = new BroadcastingServer(8080);
	}
}
