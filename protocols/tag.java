package protocols;

public class tag {
    public static int INVALID = -1;
    public static String NAME_INVALID = "NAME_INVALID";
    public static String NAME_VALID = "NAME_VALID";
    public static String GET_ONLINE = "GET_ONLINE";
    public static String REFRESH = "REFRESH";
    public static String WHAT_YOUR_NAME = "WHAT_YOUR_NAME";
    public static String FRIEND_REQUEST = "FRIEND_REQUEST";
    public static String CHAT_REQUEST = "CHAT_REQUEST";
    public static String DENY = "DENY";
    public static String ACCEPT = "ACCEPT";

    public static String FILE_HEAD = "FILE_HEAD";
    public static String FILE_TAIL = "FILE_TAIL";

    public static String FRIEND_REQUEST_HEAD = "<FRIEND_REQUEST>";
    public static String FRIEND_REQUEST_TAIL = "</FRIEND_REQUEST>";
    public static String CHAT_REQUEST_HEAD = "<CHAT_REQUEST>";
    public static String CHAT_REQUEST_TAIL = "</CHAT_REQUEST>";

    public static String ROOT_BEGIN = "<ROOT>";
    public static String ROOT_END = "</ROOT>";

    public static String SESSION_HEAD = "<SESSION>";
    public static String SESSION_TAIL = "</SESSION>";

    public static String PEER_HEAD = "<PEER>";
    public static String PEER_TAIL = "</PEER>";

    public static String PEER_NAME_HEAD = "<PEER_NAME>";
    public static String PEER_NAME_TAIL = "</PEER_NAME>";

    public static String PEER_IP_HEAD = "<PEER_IP>";
    public static String PEER_IP_TAIL = "</PEER_IP>";

    public static String PEER_PORT_HEAD = "<PEER_PORT>";
    public static String PEER_PORT_TAIL = "</PEER_PORT>";

}
