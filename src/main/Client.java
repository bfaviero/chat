package main;

/**
 * GUI chat client runner.
 */
public class Client {
    
    //The Server this Client is connected to
    private final Server myServer;
    //The username of this Client
    private final String username;
    
    public Client(Server s, String username)
    {
        this.myServer = s;
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
        //See if this Client is a member of the Conversation with ID id.
    }
    /**
     * Start a GUI chat client.
     */
    public static void main(String[] args) {
        // YOUR CODE HERE
        // It is not required (or recommended) to implement the client in
        // this runner class.
    }
}
