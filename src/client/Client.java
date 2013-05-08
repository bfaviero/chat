package client;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

//TODO: Separate Model and Runnable aspects of code

/**
 * GUI chat client runner.
 */
public class Client {
    private Socket socket;
    private String server;
    private final String username;
    BufferedWriter out;
    BufferedReader in;
    
    public Client(String server, String username, Socket socket)
    {
        this.socket = socket;
        this.username = username;
    }
    public void joinConversation(int id)
    {
        //Join the Conversation with ID id.  If this is already a member, do nothing.
    }
    public void leaveConversation(int id)
    {
        //Leave the Conversation with ID id.  If this is not a member, do nothing.
    }
    public boolean isMemberOf(int id)
    {
    	return false;
        //See if this Client is a member of the Conversation with ID id.
    }
    public void join(String channel) throws IOException{
        send("JOIN " + channel + "\r\n");
    }
    public void connect(String username) {
        
    }
    public void send(String message) throws IOException {
        
        out.write(message);
        out.flush();
    }
    /**
     * Start a GUI chat client.
     */
    public void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainApp gui = new MainApp();
            }
        });
        

    }

}
