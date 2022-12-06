package chat;

import java.io.*;
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
    private peer myPeer;
    private Socket server;
    private ServerSocket peerServerChat;
    private ServerSocket peerServerFile;
    private ObjectOutputStream serverOut;
    private ObjectInputStream serverIn;
    private boolean isConnect;
    private static String username;
    public static ArrayList<peer> onlineList;
    public static ArrayList<peer> friendList;

    public menu(Socket server, String username, ObjectOutputStream serverOut, ObjectInputStream serverIn) {
        this.server = server;
        this.username = username;
        this.serverIn = serverIn;
        this.serverOut = serverOut;
        isConnect = true;
    }

    // call server to get newest online user
    public void refreshOnlineList() {
        try {
            serverOut.writeObject(tag.REFRESH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // return list of Online/Friend ->String for UI render
    public static String[] getList(ArrayList<peer> List) {
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

    // tell server to logout and close UI
    public void logout() throws IOException {
        serverOut.writeObject(tag.LOGOUT);
        isConnect = false;
        peerServerFile.close();
        peerServerChat.close();
        serverOut.close();
        serverIn.close();
        server.close();
    }

    // get friend list on peer -> will remove?
    public String myFriends() {
        String _myFriend = "";
        for (peer _peer : myPeer.getFriends()) {
            _myFriend += _peer.getName() + "\n";
        }
        ;
        return _myFriend;
    }

    // return my peer
    public static peer getMyPeer() {
        return FindPeer(username);
    }

    // find peer on online List
    public static peer FindPeer(String name) {
        for (peer peer : onlineList) {
            if (name.equals(peer.getName())) {
                return peer;
            }
        }
        System.out.println("Error: can not find peer " + name);
        return null;
    }

    // check if it is myPeer
    public boolean isMypeer(String name) {
        if (name == null)
            return true;
        if (name.equals(username)) {
            return true;
        }
        return false;
    }

    // add new friend
    public static void AddFriend(String requestName) {
        if (friendList == null) {
            friendList = new ArrayList<>();
        }
        friendList.add(FindPeer(requestName));
        return;
    }

    // send friend request and wait for answer
    public static void SendFriendRequest(peer peerServerChat)
            throws UnknownHostException, IOException, ClassNotFoundException {
        Socket peerServerSocket = new Socket(peerServerChat.getHost(), peerServerChat.getPort());
        Socket peerServerSocketFile = new Socket(peerServerChat.getHost(), peerServerChat.getPort() + 1);

        ObjectOutputStream serverPeerOut = new ObjectOutputStream(peerServerSocket.getOutputStream());

        serverPeerOut.writeObject(encode.FriendRequest(getMyPeer()));

        ObjectInputStream serverPeerIn = new ObjectInputStream(peerServerSocket.getInputStream());
        String respone = (String) serverPeerIn.readObject();

        if (respone.equals(tag.DENY)) {
            peerServerSocket.close();
            return;
        } else {
            // add friend
            AddFriend(peerServerChat.getName());
            menuUI.addNewFriend(getList(friendList));
        }
        serverPeerOut.close();
        serverPeerIn.close();
        peerServerSocketFile.close();
        peerServerSocket.close();
    }

    // send chat request and wait for answer
    public static void SendChatRequest(peer peerServerChat)
            throws UnknownHostException, IOException, ClassNotFoundException {
        Socket peerServerSocket = new Socket(peerServerChat.getHost(), peerServerChat.getPort());
        Socket peerServerSocketFile = new Socket(peerServerChat.getHost(), peerServerChat.getPort() + 1);
        ObjectOutputStream serverPeerOut = new ObjectOutputStream(peerServerSocket.getOutputStream());

        serverPeerOut.writeObject(encode.ChatRequest(getMyPeer()));

        ObjectInputStream serverPeerIn = new ObjectInputStream(peerServerSocket.getInputStream());
        String respone = (String) serverPeerIn.readObject();

        if (respone.equals(tag.DENY)) {
            peerServerSocket.close();
            return;
        } else {
            // establish chat
            chatUI chat = new chatUI(peerServerSocket, peerServerSocketFile, username, peerServerChat.getName(),
                    serverPeerIn, serverPeerOut);
        }
    }

    // initial tell server to get online list
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

    // loop listen new online list
    public void ListenOnlineList() {
        while (true) {
            try {
                String onlineListXML = (String) serverIn.readObject();

                // logout
                if (onlineListXML == null) {
                    return;
                }

                onlineList = decode.getOnlineList(onlineListXML);
                System.out.println("Online list update");

                // CLOSE SOCKET
                if (isConnect == false) {
                    serverOut.close();
                    serverIn.close();
                    server.close();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    // loop listen on peer
    public void ListenPeer(int port) throws IOException, ClassNotFoundException {

        System.out.println("listenning on " + port);
        peerServerChat = new ServerSocket(port);
        peerServerFile = new ServerSocket(port + 1);
        while (true) {
            Socket otherPeer = peerServerChat.accept();
            Socket otherPeerFile = peerServerFile.accept();

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
                otherPeerOut.writeObject(tag.ACCEPT);
                AddFriend(requestName);
                menuUI.addNewFriend(getList(friendList));
                otherPeer.close();

            }

            if (requestType.equals(tag.CHAT_REQUEST)) {
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
                // Start to chat
                otherPeerOut.writeObject(tag.ACCEPT);
                chatUI Chat = new chatUI(otherPeer, otherPeerFile, username, requestName, otherPeerIn, otherPeerOut);
            }

        }

    }

}
