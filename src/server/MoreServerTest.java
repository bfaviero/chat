package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import junit.framework.TestCase;

import main.Packet;
import main.User;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class MoreServerTest {
    
    private final int PORT = 1234;
    private Server server;
    private Socket user0;
    private Socket user1;
    private Socket user2;
    private Thread t;
    
    @Test
    void start()
    {
        try {
            server = new Server(PORT, true);
            user0 = new Socket();
            user1 = new Socket();
            user2 = new Socket();
        } catch (Exception e){
            System.out.println(e.getStackTrace());
            assertEquals(1, 0);
        }
        
    }
    
    void end() throws Exception
    {
        server.terminate();
        user0.close();
        user1.close();
    }
    
    /**
     * Check that upon initialization a new server has no users nor channels
     */
    @Test
    void checkStart()
    {
        assertEquals(server.getUserList(), "");
        assertEquals(server.getChannelList(), "");
    }
    
    /**
     * Login with two clients to start, create two channels.
     * Double-check that these are the only objects in the server HashMaps.  
     */
    @Test
    void logIn()
    {
        server.makeUserFromSocket(user0);
        server.makeUserFromSocket(user1);
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
