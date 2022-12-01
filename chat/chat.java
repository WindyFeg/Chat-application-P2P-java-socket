package chat;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import peer.peer;
import protocols.encode;
import protocols.tag;
import protocols.decode;

public class chat {
    // My variable
    private Socket server;
    private ServerSocket peerServer;
    private String username;
    private ObjectOutputStream serverOut;
    private ObjectInputStream serverIn;
    private peer myPeer;
    private boolean isConnect;
    public static ArrayList<peer> onlineList;

    public chat(Socket server, String username, ObjectOutputStream serverOut, ObjectInputStream serverIn) {
        this.server = server;
        this.username = username;
        this.serverIn = serverIn;
        this.serverOut = serverOut;
        isConnect = true;
    }

    public void refreshOnlineList() {
        try {
            serverOut.writeObject(tag.REFRESH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] getOnlineList() {
        String res = "";
        if (onlineList.size() == 0) {
            return null;
        }
        for (peer peer : onlineList) {
            res += peer.getName() + "/";
        }
        // cut the last /
        res = res.substring(0, res.length() - 1);
        return res.split("/");
    }

    public void logout() {
        isConnect = false;
    }

    
    public String myFriends() {
        String _myFriend = "";
        for (peer _peer : myPeer.getFriends()) {
            _myFriend += _peer.getName() + "\n";
        }
        ;
        return _myFriend;
    }

    public peer getMyPeer() {
       return FindPeer(username);
    }

    public peer FindPeer(String name)
    {
        for (peer peer : onlineList) {
            if (name.equals(peer.getName())) {
                return peer;
            }
        }
        System.out.println("Error: can not find peer " + name);
        return null;
    }

    public boolean isMypeer(String name)
    {
        if(name.equals(username))
        {
            return true;
        }
        return false;
    }

    public void GetFriendFromServer() throws ClassNotFoundException {
        try {

            // tell server to get friend
            serverOut.writeObject(tag.GET_ONLINE);
            serverOut.flush();
            String onlineListXML = (String) serverIn.readObject();
            onlineList = decode.getOnlineList(onlineListXML);

            // Listen to update onlineList data
            Thread ListenOnlineListThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    ListenOnlineList();
                }
            });
            ListenOnlineListThread.start();

            Thread listenPeerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // listen on port that server assign
                        ListenPeer(getMyPeer().getPort());
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
            listenPeerThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ListenOnlineList() {
        while (true) {
            try {
                String onlineListXML = (String) serverIn.readObject();
                onlineList = decode.getOnlineList(onlineListXML);
                System.out.println("Online list update");

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

    public void ListenPeer(int port) throws IOException, ClassNotFoundException {

        System.out.println("listenning on " + port);
        peerServer = new ServerSocket(port);

        Socket otherPeer = peerServer.accept();

        ObjectInputStream otherPeerIn = new ObjectInputStream(otherPeer.getInputStream());
        ObjectOutputStream otherPeerOut = new ObjectOutputStream(otherPeer.getOutputStream());

        // What is your name
        otherPeerOut.writeObject(tag.WHAT_YOUR_NAME);
        // my name is
        String requestName = (String) otherPeerIn.readObject();

        chatUI.FriendRequest(requestName);
    }

}
