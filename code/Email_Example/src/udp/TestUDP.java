package udp;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class TestUDP {
	EchoClient client;

	public void setup() throws IOException {
		new EchoServer().start();
		client = new EchoClient();
	}

	@Test
	public void whenCanSendAndReceivePacket_thenCorrect() throws IOException {
		setup();
		String echo = client.sendEcho("hello server");
		assertEquals("hello server", echo);
		echo = client.sendEcho("server is working");
		assertFalse(echo.equals("hello server"));
		tearDown();
	}

	public void tearDown() {
		client.sendEcho("end");
		client.close();
	}

}
