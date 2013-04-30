package main;

import static org.junit.Assert.*;

import java.net.Socket;

import org.junit.Test;

public class ServerTest {

	@Test(expected=RuntimeException.class)
	public void testInvalidSocket() {
		// An invalid socket should cause an exception to be thrown in getNickname()
		Server s = new Server(1234);
		s.getNickname(new Socket());
	}

}
