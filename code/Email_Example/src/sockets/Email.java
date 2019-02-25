package sockets;
import java.net.*;
import java.io.*;
public class Email {

	public static void main(String[] args) {

		
		try {
			Socket socket =  new Socket("smtp-relay.gmail.com", 25);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream writer = new PrintStream(socket.getOutputStream());
			
			writer.println("mail from: saraodrada@gmail.com");
			System.out.println(reader.readLine());
			
			String address = "saraodrada@gmail.com";
			writer.println("rcpt to:" + address);
			
			System.out.println(reader.readLine());
			
			writer.println("data");
			System.out.println(reader.readLine());
			
			writer.println("This is the message \n that Java sent");
			writer.println(".");
			System.out.println(reader.readLine());
			
			writer.flush();
			socket.close();
			writer.close();
			reader.close();
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}