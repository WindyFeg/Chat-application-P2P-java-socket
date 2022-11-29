package protocols;

import java.net.InetAddress;

public class fillter {
    public static String getIpAdress(InetAddress add){
        String[] nameAndAdd = add.getHostAddress().split("/");
        return nameAndAdd[0];
    }

}