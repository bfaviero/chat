package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import junit.framework.TestCase;

import main.Packet;
import main.User;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class MoreServerTest{
/**    
    private final int PORT = 1234;
    private Server server;
    private Socket user0;
    private Socket user1;
    private Socket user2;
    private Thread t;
    
    @Before
    public void setUp() throws Exception
    {
        server = new Server(PORT, true);
        user0 = new Socket();
        user1 = new Socket();
        user2 = new Socket();
        server.makeUserFromSocket(user0);
        server.makeUserFromSocket(user1);
        
    }
    
*/    
    /**
     * Check that upon initialization a new server has no users nor channels
     
    @Test
    public void checkStart()
    {
        
        assertEquals(server.getUserList(), "");
        assertEquals(server.getChannelList(), "");
        
    }
    */
    
    /**
     * Login with two clients to start, create two channels.
     * Double-check that these are the only objects in the server HashMaps.  
     
    @Test
    public void logIn()
    {
        
        server.createChannel("chess", 0);
        //System.out.println(server.getUserList());
        assertEquals(server.getUserList().split(" ").length, 2);
        assertEquals(server.getChannelList().split(" ").length, 1);
    }
    
    /**
     * Attempt to add a user to Channel not yet created.
     */
    
    /**
     * Attempt to send a message to a Channel not yet created.  
     */
    
    /**
     * Attempt to create a Channel with the same name as a prexisting one.
     */
    
    /**
     * Display messages from a Channel.  
     */

}
