package email_smtp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class SMTP{
	
     private static DataOutputStream dos;

     public static void main(String[] args) throws Exception
     {
          int pause = 1200;
          String user = "saraodrada@gmail.com";
          String pass = "xxx";
          String username = Base64.getEncoder().encodeToString(user.getBytes(StandardCharsets.UTF_8));
          String password = Base64.getEncoder().encodeToString(pass.getBytes(StandardCharsets.UTF_8));

          
          SSLSocket sock = (SSLSocket)((SSLSocketFactory)SSLSocketFactory.getDefault()).createSocket("smtp.gmail.com", 465);    
          
          
//        Socket sock = new Socket("smtp.gmail.com", 25);
          final BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
          
          Thread hilo = new Thread(new Runnable() {
			
			@Override
			public void run() {
                try
                {
                     String line;
                     while((line = br.readLine()) != null)
                          System.out.println("SERVER: "+line);
                }
                catch (IOException e)
                {
                     e.printStackTrace();
                }
			}
		}); 
          
          hilo.start();
          
         
          dos = new DataOutputStream(sock.getOutputStream());
          sendMail("EHLO smtp.gmail.com\r\n");
          Thread.sleep(pause);
          sendMail("AUTH LOGIN\r\n");
          Thread.sleep(pause);
          sendMail(username + "\r\n");
          Thread.sleep(pause);
          sendMail(password + "\r\n");
          Thread.sleep(pause);
          sendMail("MAIL FROM:<saraodrada@gmail.com>\r\n");
          Thread.sleep(pause);
          sendMail("RCPT TO:<saraodrada@gmail.com>\r\n");
          Thread.sleep(pause);
          sendMail("DATA\r\n");
          Thread.sleep(pause);
          sendMail("Subject: Reto SMTP\r\n");
          Thread.sleep(pause);
          sendMail("Sara Ortiz Drada - Daniela Llano Lozano\r\n");
          Thread.sleep(pause);
          sendMail(".\r\n");
          Thread.sleep(pause);
          sendMail("QUIT\r\n");
     }

     private static void sendMail(String s) throws Exception
     {
          dos.writeBytes(s);
          System.out.println("CLIENT: "+s);
     }
}