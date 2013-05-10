package client;

import java.awt.Dimension;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import main.Packet;


public class Client {
    private MainApp gui;
    private Client client;
    private List<String> currentRooms;
    protected ConcurrentHashMap<String, List<String>> roomMessages; // Maps usernames to users.
    protected ClientConnection conn;
    private String user;
    private Signin signin;

    public Client(){
        signin = new Signin(this);
        signin.init();
            
    }
    /**
     *  Listen for connections on the port specified in the Server constructor
     * @throws IOException 
     * @throws UnknownHostException 
     */
    public void setUser(String user) {
        this.user = user;
        
    }
    public String getUser() {
        return user;
    }
    public void login(String user){
        this.user = user;
        Socket socket = null;
        try {
            socket = new Socket("localhost", 1234);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        conn = new ClientConnection(socket, this);
        conn.login(user);
        MainApp gui = new MainApp(conn, this);
        signin.frame.setVisible(false);
        gui.init();
        
    }
 
}
