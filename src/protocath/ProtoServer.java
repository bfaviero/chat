package protocath;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;  //some reason this needs to be imported separately


public class ProtoServer {

    private int port;  //default?
    private ServerSocket serverSocket;
    private HashMap<String, Integer> userMap;  //maps username to client
    //indicates who has actually 'logged on'
    
    private HashMap<String, ProtoRoom> roomMap;  //maps roomname to room
    
    private HashMap<Integer, String> socketUserMap;  //maps socket/client ID to username
    
    private HashMap<Integer, Socket> clientSocketMap;  //maps socket/client ID to socket
    private List<PrintWriter> outputStreams;   
    private HashMap<Integer, PrintWriter> clientOutputMap;
    //synchronize for concurrency when returning responses
    private LinkedBlockingQueue<ProtoRequest> queue;  //holds incoming requests
    
    public ProtoServer (int givenport) throws IOException {
        this.port = givenport;
        this.serverSocket = new ServerSocket(this.port);
        this.userMap = new HashMap<String, Integer>();
        this.roomMap = new HashMap<String, ProtoRoom>();
        this.socketUserMap = new HashMap<Integer, String>();
        this.clientSocketMap = new HashMap<Integer, Socket>();
        this.outputStreams = new ArrayList<PrintWriter>();
        this.clientOutputMap = new HashMap<Integer, PrintWriter>();
        this.queue = new LinkedBlockingQueue<ProtoRequest>();
        
    }
    
    public void listen()
    {
        int socketID = 0;
        try{     
            synchronized(this)
            {
                socketID++;
            }
            while(true){
                Socket socket = this.serverSocket.accept();
                synchronized(this)
                {
                    this.clientSocketMap.put(socketID, socket);
                }
                //String username = new String("Guest_" + String.valueOf(userMap.size() + 1));
                
                Thread t = new Thread(new PServConnection(this, socketID, socket));
                //why shouldn't we make ProtoConnection implement Runnable?
                //this is what we did for minesweeper...
                t.start();              
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * Take in requests from a connected client.  
     * This will add this Client's further requests to the server queue.  
     */
    public void handleConnection (int socketID, Socket socket) throws IOException 
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        this.clientOutputMap.put(socketID, out);
        this.outputStreams.add(out);
        out.println("Connected with socket ID " + socketID);
        try {
            for (String line =in.readLine(); line!=null; line=in.readLine()) 
            {
                ProtoRequest newRequest = new ProtoRequest(socketID, line);
                this.queue.add(newRequest);
            }
        } finally {
            out.close();
            in.close();
        }
    }
    
    /**
     * Takes Requests from the queue.  
     */
    public void processRequest () throws InterruptedException 
    {
        //For testing purposes, assume all lines formatted correctly.  
        try {
            ProtoRequest request = this.queue.take();
            //get appropriate socket out stream for this request's client
            //- this might be troublesome
            String[] args = request.getText().split(" ");
            int userID = Integer.parseInt(args[args.length-1]);
            Socket socket = this.clientSocketMap.get(userID);
            PrintWriter tempOut = this.clientOutputMap.get(userID);
            //return response
            String response = handleRequest(request);
            tempOut.println(response);
            tempOut.close();
            
        } finally {
            //This should NOT happen
            System.out.println("Somehow done with requests????");
        }
    }
    /**
     * Handles a Request from a client.  
     */
    public String handleRequest(ProtoRequest r)
    {
        String response = "This was not processed";
        ProtoRequest.Type thisType = r.getType();
        //Perhaps have a Request parsing method to pre-parse arguments ahead of time?
        //Store in a dictionary?
        String[] args = r.getText().split(" ");
        String username;
        int userID;
        switch (thisType) {        
        case LOGIN:
            username = args[1];
            userID = Integer.parseInt(args[2]);
            return login(username, userID);
        case LOGOUT:
            userID = Integer.parseInt(args[1]);
            return logout(userID);
        case MESSAGE:
            userID = Integer.parseInt(args[3]);
            return sendMessage(args[1], args[2], userID);            
        case UNKNOWN:
            return "Unknown command";
        case BLANK:
            return "Blank command";
        }
        return "Now something's really wrong";
    }
    
    public synchronized String login(String username, int userID)
    {
        if (!this.socketUserMap.containsKey(userID))
        {
            this.socketUserMap.put(userID, username);
            this.userMap.put(username, userID);
            return "success";
        }
        return "failure";
    }
    
    public synchronized String logout(int userID)
    {
        if (this.socketUserMap.containsKey(userID))
        {
            String username = this.socketUserMap.get(userID);
            if (this.userMap.containsKey(username))
            {
                this.userMap.remove(username);
                this.socketUserMap.remove(userID);
                return "success";
            }
        }
        return "failure";
    }
    
    public String sendMessage(String roomname, String text, int userID)
    {
        //TODO: Implement
        
        return "Sending " + text + "to Channel " + roomname + " by username " 
    + this.socketUserMap.get(userID);
    }
    
}
