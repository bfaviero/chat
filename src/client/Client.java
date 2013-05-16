package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * The Client represents the program that runs on the user's machine. It stores information about the session, 
 * and stores the information about the rooms the user is in, and the messages in those rooms. 
 *
 */
public class Client {
    //The main GUI for the app
    private MainApp gui;
    //
    //The rooms the user is currently in
    protected List<String> currentRooms;
    //The messages from each room the user is in
    protected HashMap<String, List<String>> roomMessages; // Maps usernames to users.
    //The connection that communicates with the socket
    protected ClientConnection conn;
    private String user;
    private Signin signin;

    public Client(){
        this.roomMessages = new HashMap<String, List<String>>();
        this.currentRooms = new ArrayList<String>();
        signin = new Signin(this);     
    }
    //Opens the sign-in screen
    public void start(){
    	signin.init();
    }
    /**
     * 
     * @return currentRooms The rooms the user is in
     */
    public List<String> getRooms() {
        return currentRooms;
    }
    public List<String> getMessages(String room) {
        return roomMessages.get(room);
    }

    
    public void setUser(String user) {
        this.user = user;
        
    }
    public String getUser() {
        return user;
    }
    /**
     * Logs into the system, and serves up the main chat GUI.
     * @param user
     */
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
        MainApp gui = new MainApp(conn);
        conn.setGUI(gui);
        signin.frame.setVisible(false);
        gui.init();
        conn.listUsers();
    }
    
    public static void main(String[] args) throws UnknownHostException, IOException {
        Client client = new Client();
        client.start();
    }
}
