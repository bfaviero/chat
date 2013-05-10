package protocath;

import java.util.ArrayList;
import java.util.List;

import main.Packet;

public class ProtoRoom {
    private String title;
    //private List<UserConnection> users = new ArrayList<UserConnection>();
    private List<String> users = new ArrayList<String>();
    private List<Packet> messages = new ArrayList<Packet>();

    public ProtoRoom(String title, String owner)
    {
        this.title = title;
        this.users.add(owner);
    }
    public String getTitle()
    {
        return this.title;
    }
    public void addMessage(Packet m)
    {
        this.messages.add(m);
    }
    public void addUser(String user)
    {
        //if !this.users.has(user) { users.add(user) }
    }
    public void removeUser(String user)
    {
        //if this.users.has(user) { users.remove(user) }
        //if this.users.size() == 0 { //delete Conversation }
    }
    public void hasUser(String user)
    {
        //if this.users.has(user)
    }
}
