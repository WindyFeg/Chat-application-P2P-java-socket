package protocols;

public class fillter {
    public String ipAddress(String connectionInfo) {
        String[] cutPcName = connectionInfo.split("/");
        String[] userItems = cutPcName[1].split(",");
        return userItems[0];
    }

    public int port(String connectionInfo) {
        String[] cutPcName = connectionInfo.split("/");
        String[] userItems = cutPcName[1].split(",");
        return Integer.parseInt(userItems[1]);
    }

    public String username(String connectionInfo) {
        String[] cutPcName = connectionInfo.split("/");
        String[] userItems = cutPcName[1].split(",");
        return userItems[2];
    }

}