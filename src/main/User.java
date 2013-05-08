package main;

import java.net.Socket;

public class User {
    private int id;
    private String nickname;
    
    public User(Server server, Socket socket, String nickname, int id) {
        this.id = id;
        this.nickname = nickname;
    }
    
    
}
