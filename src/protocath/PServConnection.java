package protocath;

import java.io.*;
import java.net.*;
import java.util.*;

public class PServConnection implements Runnable {

    private ProtoServer server;
    private Socket socket;
    private int thisID;
    private String username;
    public PServConnection(ProtoServer s, int acceptid, Socket sock)
    {
        this.thisID = acceptid;
        this.server = s;
        this.socket = sock;
    }
    
    @Override
    public void run()
    {
        System.out.println("For testing purposes, here is my ID " + this.thisID);
        try {
            server.handleConnection(this.thisID, this.socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
