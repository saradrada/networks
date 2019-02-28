package udp.broadcast.threads;

import java.io.*;
import java.net.*;

/**
 * 
 * @author Sara Ortiz Drada
 * @version 1.0.0
 */
public class CatcherThread extends Thread {
	private Socket s;
	private Socket[] Sockets;
	private DataOutputStream out;
	private DataInputStream in;

	public CatcherThread(Socket s, Socket[] sockets, DataInputStream in, DataOutputStream out) {
		this.s = s;
		this.Sockets = sockets;
		this.out = out;
		this.in = in;
	}

	@Override
	public void run() {

		boolean space = false;
		int x = -1;

		for (int i = 0; i < Sockets.length && !space; i++) {
			if (Sockets[i] == null) {
				Sockets[i] = s;
				space = true;
				x = i;
			}
		}

		while (true) {
			try {

				for (int i = 0; i < Sockets.length && space; i++) {
					if (i != x) {
						String text = in.readUTF();
						out = new DataOutputStream(Sockets[i].getOutputStream());
						out.writeUTF(text);
						System.out.println("Message sent by client : " + text);
					}

				}

				if (!space) {
					out.writeUTF("Sorry the server is full");
				}

			} catch (Exception e) {

			}
		}
	}
}
