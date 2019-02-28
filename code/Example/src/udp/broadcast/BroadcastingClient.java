package udp.broadcast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import udp.broadcast.threads.ReadThread;
import udp.broadcast.threads.WriteThread;

/**
 * 
 * @author Sara Ortiz Drada
 * @version 1.0.0
 */
public class BroadcastingClient extends Thread {
	private Socket socket;
	private String Server_Ip;
	private String port;
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;

	public BroadcastingClient(String Server_Ip, int port) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		DataInputStream in;
		DataOutputStream out;

		System.out.println("Establishing connection. Please wait ...");

		try {
			socket = new Socket(Server_Ip, port);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			System.out.println("Connected");

			ReadThread read = new ReadThread(in);
			WriteThread write = new WriteThread(out);

			read.start();
			write.start();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void main(String[] args) {
		BroadcastingClient client = new BroadcastingClient("localhost", 8080);
	}
}
