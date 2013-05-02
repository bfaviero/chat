package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

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
        String channel = "default";
        String server = "localhost";
        Socket socket = new Socket(server, 1234);
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream( )));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream( )));
        while (true) {
            String line = in.readLine();
            if (line != null) {
                if (line.indexOf("100") >= 0) {
                    break;
                }
                else if (line.indexOf("101") >= 0) {
                    System.out.println("Nickname taken.");
                    return;
                }
                else if (line.toLowerCase().startsWith("PING ")) {
                    // We must respond to PINGs to avoid being disconnected.
                    out.write("PONG " + line.substring(5) + "\r\n");
                    out.write("PRIVMSG " + channel + " :I got pinged!\r\n");
                    out.flush( );
                }
                else {
                    // Print the raw line received by the bot.
                    System.out.println(line);
                }
            }
                
        }

    }

}
