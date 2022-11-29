package chat;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import peer.peer;
import protocols.encode;
import protocols.tag;
import protocols.decode;

public class chat {
    // My variable
    private Socket server;
    private String username;
    private ObjectOutputStream serverOut;
    private ObjectInputStream serverIn;
    private peer myPeer;
    private boolean isConnect;
    public static ArrayList<peer> onlineList;

    public chat(Socket server, String username,ObjectOutputStream serverOut,ObjectInputStream serverIn ) {
        this.server = server;
        this.username = username;
        this.serverIn = serverIn;
        this.serverOut = serverOut;
        isConnect = true;
    }

    public void logout() {
        isConnect = false;
    }

    public void GetFriendFromServer() {
        try {
            // basic se-ci communication
            // serverOut = new ObjectOutputStream(server.getOutputStream());
            // serverIn = new ObjectInputStream(server.getInputStream());

            // tell server to get friend
            serverOut.writeObject(tag.GET_ONLINE);
            serverOut.flush();

            // Listen to update onlineList data
            Thread ListenOnlineListThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    ListenOnlineList();
                }
            });
            ListenOnlineListThread.start();
            // thread running update online list
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            res+= peer.getName() + "/";
        }
        // cut the last /
        res = res.substring(0, res.length() - 1);
        return res.split("/");
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

    public String myFriends() {
        String _myFriend = "";
        for (peer _peer : myPeer.getFriends()) {
            _myFriend += _peer.getName() + "\n";
        }
        ;
        return _myFriend;
    }
}
