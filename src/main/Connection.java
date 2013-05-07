package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import server.Server;

/**
 * Start a new connection for the given user and
 *
 */

public class Connection {
    protected String nickname;
    protected Socket socket;
    protected BufferedReader in;
    protected PrintWriter out;

    public Connection(String nickname, Socket sock){
        if(!sock.isConnected())
            throw new RuntimeException("Socket not connected");

        this.nickname = nickname;
        this.socket = sock;
        try{
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));  
        }
        catch(Exception e){
            System.err.println("Error opening reader / writer on socket in Connection constructor");
            e.printStackTrace();
        }
    }
}
