package client;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import main.Message;

import server.Server;
import server.User;


public class Client {
    private MainApp gui;
    private Client client;
    private List<String> currentRooms;
    private HashMap<String, List<Message>> roomMessages; // Maps usernames to users.
    private ClientConnection conn;

    public Client(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gui = new MainApp(conn);
                gui.init();
            }
        });
    }
    /**
     *  Listen for connections on the port specified in the Server constructor
     * @throws IOException 
     * @throws UnknownHostException 
     */
    public void createConnection(String user) throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 1234);
        conn = new ClientConnection(user, socket, gui);
    }
 
}
