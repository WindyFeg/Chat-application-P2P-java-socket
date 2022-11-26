
import java.io.Console;
import java.io.IOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import javafx.util.converter.PercentageStringConverter;
import peer.peer;
import protocols.*;
public class severtest {

    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static PrintWriter writer;
    public static Vector<peer> onlineList = new Vector<>();

    // public static void UpdateOnlineList() {
    //     for (peer item : onlineList) {
    //         try {
    //             out = new ObjectOutputStream(item.getSocket().getOutputStream());
    //         } catch (IOException e) {
    //             // TODO Auto-generated catch block
    //             e.printStackTrace();
    //         }
    //     }
    // }

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
        peer newPeerConnection = new peer(userItems[2],userItems[0],Integer.parseInt(userItems[1]));
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
                onlineList.add(peerInfo);
                // UpdateOnlineList();

                // Send peer data and friend list to connection

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
