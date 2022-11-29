
import java.io.Console;
import java.io.IOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import peer.peer;
import protocols.*;

public class severtest {

    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static PrintWriter writer;
    public static Vector<peer> pOnlineList = new Vector<>();
    public static Vector<Socket> sOnlineList = new Vector<>();
    public static Vector<ClientHandler> cOnlineList = new Vector<>();

    public static void UpdateOnlineList() {
        for (Socket item : sOnlineList) {
            try {
                out = new ObjectOutputStream(item.getOutputStream());
                out.writeObject(pOnlineList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void UpdateMyFriendList() {
    }

    public static void main(String[] args) throws IOException {

        writer = new PrintWriter("userData.txt", "UTF-8");

        System.out.println("This is server");
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

    public static void listenOnConnection(ServerSocket testSocket, PrintWriter writer) {
        peer peerInfo;
        while (true) {
            try {
                // accept connection
                Socket client = testSocket.accept();

                // get basic info of user 1
                in = new ObjectInputStream(client.getInputStream());
                String connectionInfo = (String) in.readObject();
                System.out.println(connectionInfo + " server nhan duoc thong tin cua user");

                // Check user
                if (checkConnection(connectionInfo)) {
                    // old user
                    peerInfo = getOldPeer(connectionInfo);
                } else {
                    // new user
                    peerInfo = saveNewPeer(connectionInfo, client);
                    System.out.println(peerInfo);
                }
                out = new ObjectOutputStream(client.getOutputStream());
                out.writeObject(peerInfo);

                // Add new peer to online list
                pOnlineList.add(peerInfo);
                sOnlineList.add(client);

                // create thread listen for client request
                ClientHandler pHandler = new ClientHandler(client, in, out);
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
    boolean isLogin;

    public ClientHandler(Socket clientSocket, ObjectInputStream clientIn, ObjectOutputStream clientOut) {
        this.clientSocket = clientSocket;
        this.clientIn = clientIn;
        this.clientOut = clientOut;
        this.isLogin = true;
    }

    public String[] listFriend(Vector<peer> peers) {
        String res = "";
        if (severtest.pOnlineList.size() == 0) {
            return null;
        }
        for (peer peer : severtest.pOnlineList) {
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

                if (request.equals("refresh")) {
                    clientOut.writeObject(listFriend(severtest.pOnlineList));
                    clientOut.flush();
                }

            } catch (IOException | ClassNotFoundException e) {

                e.printStackTrace();
                this.isLogin = false;
                try {
                    this.clientSocket.close();
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
