//package server;
//
//
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.Socket;
//
//import junit.framework.TestCase;
//
//import main.Packet;
//import main.User;
//
//import org.junit.Test;
//
//public class ServerTest extends TestCase{
//	
//	private final int PORT = 1234;
//	private Server server;
//	private Socket user0;
//	private Socket user1;
//	private Thread t;
//	
//	/**
//	 * Set up a server with two clients connected to it,
//	 * Guest_1 and Guest_2
//	 */
//	@Override
//    protected void setUp() throws Exception
//    {
//     	server = new Server(PORT, true);
//     	
//        user0 = new Socket();
//        user1 = new Socket();
//        
//        server.makeUserFromSocket(user0);
//        server.makeUserFromSocket(user1);
//    }
//	
//	@Override
//    protected void tearDown() throws Exception
//    {
//        server.terminate();
//        user0.close();
//        user1.close();
//    }
//	
//	/********************************************************
//	 * 						Unit Tests						*
//	 *********************************************************/
//	
//	// Verify that both guests are shown in the user list
//	@Test
//	public void testGetAllUsers(){
//		System.out.println(server.getUserList());
//		assertTrue(server.getUserList().contains("Guest_0"));
//		assertTrue(server.getUserList().contains("Guest_1"));
//	}
//	
//	@Test
//	public void testChannelCreateAndList(){
//		server.createChannel("chess", 0);
//		assertEquals("chess", server.getChannelList());
//		server.createChannel("rabbits", 0);
//		assertTrue(server.getChannelList().contains("rabbits"));
//	}
//	
//
//	// Test that adding two users to a channel results in two users being
//	// in that channel.
//	@Test
//	public void testAddUserToChannel() {
//		server.createChannel("chess", 0);
//		server.addUserToChannel(1, "chess");
//		assertEquals(server.getChannel("chess").getUserCount(), 2);
//	}
//	
//	@Test
//	public void testUserRename(){
//		
//	}
//	
//	
//	
//
//	
//	
//	
//	/**
//	 * Helper method: Get the server's response to a specific packet
//	 * 
//	 * @param s A socket over which to send the query
//	 * @param query A packet representing the packet to send to the server
//	 * @return Server response, in the form of a packet
//	 */
//	public Packet getServerResponse(Socket s, Packet query){
//		Packet response = null;
//		try{
//			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
//			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
//			
//			oos.writeObject(query);
//			response = (Packet) ois.readObject();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if(response == null)
//			throw new RuntimeException("NO RESPONSE");
//		else
//			return response;
//	}
//
//}
