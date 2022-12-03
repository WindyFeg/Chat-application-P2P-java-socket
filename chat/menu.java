package chat;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import peer.peer;
import protocols.encode;
import protocols.tag;
import protocols.decode;

public class menu {
    // My variable
    private Socket server;
    private ServerSocket peerServer;
    private static String username;
    private ObjectOutputStream serverOut;
    private ObjectInputStream serverIn;
    private peer myPeer;
    private boolean isConnect;
    public static ArrayList<peer> onlineList;
    public static ArrayList<peer> friendList;

    public menu(Socket server, String username, ObjectOutputStream serverOut, ObjectInputStream serverIn) {
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

    public String[] getList(ArrayList<peer> List) {
        String res = "";
        if (List.size() == 0) {
            return null;
        }
        for (peer peer : List) {
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

    public static peer getMyPeer() {
        return FindPeer(username);
    }

    public static peer FindPeer(String name) {
        for (peer peer : onlineList) {
            if (name.equals(peer.getName())) {
                return peer;
            }
        }
        System.out.println("Error: can not find peer " + name);
        return null;
    }

    public boolean isMypeer(String name) {
        if (name == null)
            return true;
        if (name.equals(username)) {
            return true;
        }
        return false;
    }

    public static void ConnectToPeer(peer peerServer) throws UnknownHostException, IOException, ClassNotFoundException {
        Socket peerServerSocket = new Socket(peerServer.getHost(), peerServer.getPort());

        // ObjectInputStream serverPeerIn = new
        // ObjectInputStream(peerServerSocket.getInputStream());
        ObjectOutputStream serverPeerOut = new ObjectOutputStream(peerServerSocket.getOutputStream());

        serverPeerOut.writeObject(encode.FriendRequest(getMyPeer()));

        ObjectInputStream serverPeerIn = new ObjectInputStream(peerServerSocket.getInputStream());
        String respone = (String) serverPeerIn.readObject();

        if (respone.equals(tag.DENY)) {
            peerServerSocket.close();
            return;
        } else {
            // Show chat
        }

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

        // waiting for who want to chat with us
        String request = (String) otherPeerIn.readObject();

        String requestType = decode.requestType(request);

        if (requestType.equals(tag.FRIEND_REQUEST)) {
            // Friend request
            String requestName = decode.peerRequest(request, tag.FRIEND_REQUEST);
            System.out.println(requestName);

            // Show requestUI
            int respone = menuUI.showDialog(requestName + " send a friend request!", true);
            // Deny
            if (respone == -1) {
                otherPeerOut.writeObject(tag.DENY);
                otherPeer.close();
            }
            // Accept & add to Friend list
            peer connectPeer = FindPeer(requestName);
            // why thread die?
            friendList = onlineList;
            menuUI.addNewFriend(getList(friendList));

        } else {
            // Chat request
            String requestName = decode.peerRequest(request, tag.CHAT_REQUEST);
            System.out.println(requestName);
            // Show requestUI
            int respone = menuUI.showDialog(requestName + " want to chat with you", true);
            // Deny
            if (respone == -1) {
                otherPeerOut.writeObject(tag.DENY);
                otherPeer.close();
            }
            //
            chatUI newChatUI = new chatUI(otherPeer, username, requestName);
        }

    }

}
