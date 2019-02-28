package udp.multicast;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;
import java.net.*;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * 
 * @author Sara Ortiz Drada
 * @version 1.0.0
 */
public class MulticastingServer {

	public static void main(String[] args) throws IOException {

		System.out.println("Inicio del servidor multicast...\n" + "Por favor, digite su mensaje:");

//Creamos el MulticastSocket sin especificar puerto.
		MulticastSocket s = new MulticastSocket();

// Creamos el grupo multicast:
		InetAddress group = InetAddress.getByName("231.0.0.1");

// Creamos un datagrama vacío en principio:
		byte[] vacio = new byte[0];
		DatagramPacket dgp = new DatagramPacket(vacio, 0, group, 10000);

//Cogemos los datos a encapsular de la entrada 
//estándar (el teclado)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String linea = br.readLine();

//El servidor enviará los datos que lea por teclado hasta que
//se escriba "salir":
		while (!linea.equals("salir")) {

//Creamos el buffer a enviar
			byte[] buffer = linea.getBytes();

			// byte[] buffer;
			// System.out.println(new File("/images/multicast.jpg").getCanonicalPath());
			// BufferedImage image = ImageIO.read(new File("C:\\images\\multicast.jpg"));

//			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//			ImageIO.write(image, "jpg", outputStream);
//			String encodedImage = Base64.encode(outputStream.toByteArray());
//			
//			buffer = encodedImage.getBytes();
//			System.out.println(encodedImage);

//Pasamos los datos al datagrama
			dgp.setData(buffer);

//Establecemos la longitud
			dgp.setLength(buffer.length);

//Y por último enviamos:
			s.send(dgp);

//Leemos de la entrada estandar para evitar bucles infinitos
			linea = br.readLine();

		}

// Cerramos el socket.
		s.close();
	}

	/**
	 * Converts an image to byte array.
	 * 
	 * @param ImageName Name of the image.
	 * @return Byte array of an image.
	 * @throws IOException
	 */
	private static byte[] extractBytes(String ImageName) throws IOException {
		// open image
		File imgPath = new File(ImageName);
		BufferedImage bufferedImage = ImageIO.read(imgPath);

		// get DataBufferBytes from Raster
		WritableRaster raster = bufferedImage.getRaster();
		DataBufferByte data = (DataBufferByte) raster.getDataBuffer();

		return (data.getData());
	}
}
