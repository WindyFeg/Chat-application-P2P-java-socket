

import java.io.Console;
import java.io.IOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class severtest {

    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    
    public void UpdateOnlineList() {
    }

    public void UpdateMyFriendList() {
    }

    public static void main(String[] args) throws IOException {
        System.out.println("This is server");

        ServerSocket testSocket = new ServerSocket(7777);
        Socket client = testSocket.accept();


        try {
            // listenning
            in = new ObjectInputStream(client.getInputStream()); 
            String message = (String) in.readObject();

            System.out.println(message);
            // Talk
            out = new ObjectOutputStream(client.getOutputStream());
            out.writeObject("username" + message + "\n has n Friend");
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("server close");
        // Server get init message (contain all information)from client

    }

    // Thread loop on waiting for client connect
}
