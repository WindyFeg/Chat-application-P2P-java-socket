
import java.io.Console;
import java.io.IOException;
import java.lang.reflect.Array;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import peer.peer;
import protocols.tag;
import protocols.encode;
import protocols.fillter;

public class server {

    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static PrintWriter writer;
    public static ArrayList<peer> pOnlineList = new ArrayList();
    public static Vector<ClientHandler> cOnlineList = new Vector<>();

    public void UpdateMyFriendList() {
    }

    public static void main(String[] args) throws IOException {

        System.out.println("This is server open on port: 7777");
        ServerSocket testSocket = new ServerSocket(7777);

        // LISTENING
        Thread listenThread = new Thread(new Runnable() {
            @Override
            public void run() {
                listenOnConnection(testSocket, writer);
            }
        });
        listenThread.start();
    }

    // CLOSE
    public void serverClose() {
        writer.close();
        System.out.println("server close");
    }

    // TODO
    public static boolean checkConnection(String connectionInfo) {
        return false;
    }

    public static peer getOldPeer(String connectionInfo) {
        return null;
    }

    public static peer saveNewPeer(String connectionInfo, Socket client) {
        writer.println(connectionInfo);
        String[] cutPcName = connectionInfo.split("/");
        String[] userItems = cutPcName[1].split(",");
        peer newPeerConnection = new peer(userItems[2], userItems[0], Integer.parseInt(userItems[1]));
        return newPeerConnection;
    }

    public static boolean checkClientName(String clientName) {
        for (ClientHandler name : cOnlineList) {
            if (clientName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static void listenOnConnection(ServerSocket testSocket, PrintWriter writer) {
        while (true) {
            try {
                // accept connection
                Socket client = testSocket.accept();

                // get basic info of user 1
                in = new ObjectInputStream(client.getInputStream());
                out = new ObjectOutputStream(client.getOutputStream());

                peer temp;
                // Client login & check username
                String clientName = (String) in.readObject();
                if (checkClientName(clientName)) {
                    out.writeObject(tag.NAME_INVALID);
                    break;
                } else {
                    out.writeObject(tag.NAME_VALID);
                    // Make a random port for peer
                    Random rd = new Random();
                    int peer_port = 10000 + rd.nextInt() % 1000;
                    // Add to pOnline pls
                    temp = new peer(clientName, fillter.getIpAdress(client.getInetAddress()), peer_port);
                    pOnlineList.add(temp);
                }

                // return online list to client
                String getOnline = (String) in.readObject();
                if (getOnline.equals(tag.GET_ONLINE)) {
                    out.writeObject(encode.OnlineList(pOnlineList));
                }

                // create thread listen for client request
                ClientHandler pHandler = new ClientHandler(client, in, out, clientName, temp);
                Thread t = new Thread(pHandler);
                cOnlineList.add(pHandler);
                t.start();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

class ClientHandler implements Runnable {

    ObjectOutputStream clientOut;
    ObjectInputStream clientIn;
    Socket clientSocket;
    String username;
    peer peer;
    boolean isLogin;

    public ClientHandler(Socket clientSocket, ObjectInputStream clientIn, ObjectOutputStream clientOut,
            String username, peer temppeer) {
        this.clientSocket = clientSocket;
        this.clientIn = clientIn;
        this.clientOut = clientOut;
        this.isLogin = true;
        this.username = username;
        this.peer = temppeer;
    }

    public String getName() {
        return this.username;
    }

    public String[] listFriend(Vector<peer> peers) {
        String res = "";
        if (server.pOnlineList.size() == 0) {
            return null;
        }
        for (peer peer : server.pOnlineList) {
            res += peer.getName() + "/";
        }
        // cut last /
        res = res.substring(0, res.length() - 1);
        return res.split("/");
    }

    @Override
    public void run() {
        String request;
        while (true) {
            try {
                // receive the string
                request = (String) clientIn.readObject();

                System.out.println(request);

                if (request.equals(tag.LOGOUT)) {
                    clientSocket.close();
                    server.pOnlineList.remove(this.peer);
                    server.cOnlineList.remove(this);
                }

                if (request.equals(tag.REFRESH)) {
                    clientOut.writeObject(encode.OnlineList(server.pOnlineList));
                    // clientOut.flush();
                }

            } catch (IOException | ClassNotFoundException e) {

                e.printStackTrace();
                this.isLogin = false;
                try {
                    this.clientSocket.close();
                    server.pOnlineList.remove(this.peer);
                    server.cOnlineList.remove(this);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            }
        }
        try {
            // closing resources
            this.clientIn.close();
            this.clientOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
