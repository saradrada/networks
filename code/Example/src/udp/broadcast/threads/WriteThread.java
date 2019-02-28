package udp.broadcast.threads;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author Sara Ortiz Drada
 * @version 1.0.0
 */
public class WriteThread extends Thread {
	private DataOutputStream out;

	public WriteThread(DataOutputStream out) {
		this.out = out;
	}

	@Override
	public void run() {

		System.out.println("Ready to write");

		try {
			while (true) {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String text = br.readLine();
				//System.out.println("-");
				out.writeUTF(text);
			}

		} catch (IOException e) {

		}

	}
}
