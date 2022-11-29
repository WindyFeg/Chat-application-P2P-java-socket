package chat;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Vector;
import peer.peer;

public class chat {
    // My variable
    private Socket server;
    private String serverIp;
    private int serverPort;
    private String username;
    private ObjectOutputStream serverOut;
    private ObjectInputStream serverIn;
    private peer myPeer;
    private boolean isConnect;
    public static String[] onlineList;

    public chat(Socket server, String serverIp, int serverPort, String username) {
        this.server = server;
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.username = username;
        isConnect = true;
    }

    public void logout() {
        isConnect = false;
    }

    public void GetFriendFromServer() {
        try {
            // get our ear and mouse
            // Tell server our information 2
            serverOut = new ObjectOutputStream(server.getOutputStream());
            serverOut.writeObject(InetAddress.getLocalHost() + "," + 8888 + "," + username);

            // Get our data from server
            serverIn = new ObjectInputStream(server.getInputStream());
            myPeer = (peer) serverIn.readObject();
            System.out.println("Peer info has been received");

            // Communicate with server thread
            Thread ListenOnlineListThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    ListenOnlineList();
                }
            });
            ListenOnlineListThread.start();
            // thread running update online list
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void refreshOnlineList() {
        try {
            serverOut.writeObject("refresh");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] getOnlineList()
    {

        return onlineList;
    }

    public void ListenOnlineList() {
        while (true) {
            try {
                onlineList = (String[]) serverIn.readObject();
                System.out.println(onlineList);

                // CLOSE SOCKET
                if (isConnect == false) {
                    serverOut.close();
                    serverIn.close();
                    server.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public String myFriends() {
        String _myFriend = "";
        for (peer _peer : myPeer.getFriends()) {
            _myFriend += _peer.getName() + "\n";
        }
        ;
        return _myFriend;
    }
}
