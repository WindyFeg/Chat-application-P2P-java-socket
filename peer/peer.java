package peer;
// make this object can turn to byte and vice versa
import java.io.Serializable;
import java.net.Socket;
import java.io.*;
import java.util.Vector;

public class peer implements Serializable{

    // private ObjectOutputStream out;		
	// private ObjectInputStream in;	
    private String pName;
    private String pHost;
    private int pPort;
    // can not send a socket to a client
    // private Socket client;
    private Vector<peer> FriendLists = new Vector();

    public peer (String pName, String pHost, int pPort, Socket client)
    {
        this.pName = pName;
        this.pHost = pHost;
        this.pPort = pPort;
        // this.client = client;
    }
    
    public void set(String pName, String pHost, int pPort)
    {
        this.pName = pName;
        this.pHost = pHost;
        this.pPort = pPort;
    }

    // public Socket getSocket()
    // {
    //     return this.client;
    // }

    public void addFriend(peer peer)
    {
        this.FriendLists.add(peer);
    }

    public Vector<peer> getFriends()
    {
        return FriendLists;
    }

    public peer getFriend(int i)
    {
        return FriendLists.get(i);
    }

    public int getNumberOfFriends()
    {
        return FriendLists.size();
    }

    public void setName(String name)
    {
        this.pName = name;
    }
    public void setHost(String host)
    {
        this.pHost = host;
    }public void setPort(int port)
    {
        this.pPort = port;
    }
    public String getName()
    {
        return this.pName;
    };
    public String getHost()
    {
        return this.pHost;
    };
    public int getPort()
    {
        return this.pPort;
    };
}
