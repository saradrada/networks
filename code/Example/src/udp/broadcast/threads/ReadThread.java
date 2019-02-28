package udp.broadcast.threads;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * 
 * @author Sara Ortiz Drada
 * @version 1.0.0
 */
public class ReadThread extends Thread {
	private DataInputStream in;

	public ReadThread(DataInputStream in) {
		this.in = in;
	}

	@Override
	public void run() {

		System.out.println("Ready to read");
		try {
			while (true) {
				String text = in.readUTF();
				System.out.println(text);
			}

		} catch (IOException e) {

		}

	}
}
