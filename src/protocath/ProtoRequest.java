package protocath;

import java.util.*;

public class ProtoRequest {
    
    public enum Type {
        LOGIN,
        LOGOUT,
        MESSAGE,
        UNKNOWN,
        BLANK
    }
    private int userID;
    private String text;
    private Type reqType;
    
    public ProtoRequest(int id, String textline)
    {
        this.userID = id;
        this.text = textline;
        this.reqType = getType();
    }
    
    public String getText()
    {
        return this.text;
    }
    
    public Type getType()
    {
        if ((this.text == null) || (this.text == ""))
        {
            return Type.BLANK;
        }
        else if (this.text.startsWith("login"))
        {
            return Type.LOGIN;
        }
        else if (this.text.startsWith("logout"))
        {
            return Type.LOGOUT;
        }
        else if (this.text.startsWith("message"))
        {
            return Type.MESSAGE;
        }
        else
        {
            return Type.UNKNOWN;
        }
    }
    
    
}


