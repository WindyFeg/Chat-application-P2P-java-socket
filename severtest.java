
import java.io.Console;
import java.io.IOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import peer.peer;

public class severtest {

    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static PrintWriter writer;

    public void UpdateOnlineList() {
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
                listening(testSocket, writer);
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
        return true;
    }

    public static peer getOldPeer(String connectionInfo) {
        return null;
    }

    public static peer saveNewPeer(String connectionInfo) {
        writer.println(connectionInfo);
        return null;
    }

    public static void listening(ServerSocket testSocket, PrintWriter writer) {

        while (true) {
            try {
                // create fake user
                peer peerInfo = new peer("Tuan", "192.168.1.1", 8888);

                // accept connection
                Socket client = testSocket.accept();

                // get basic info of user
                in = new ObjectInputStream(client.getInputStream());
                String connectionInfo = (String) in.readObject();
                System.out.println(connectionInfo + " server nhan duoc thong tin cua user");

                // Check user
                if (checkConnection(connectionInfo)) {
                    // old user
                    peerInfo = getOldPeer(connectionInfo);
                } else {
                    // new user
                    peerInfo = saveNewPeer(connectionInfo);
                }

                // Send peer data and friend list to connection
                out = new ObjectOutputStream(client.getOutputStream());
                out.writeObject(peerInfo);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
