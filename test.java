import java.util.Arrays;
import java.util.List;

public class test {
    public static void main(String[] args) {
        String connectionInfo = "WindyFeng/172.30.0.1,8888,User65";
        String[] cutPcName = connectionInfo.split("/");
        String[] userItems = cutPcName[1].split(",");
        for (String item : userItems) {
            System.out.println(item);
        }
    }
}
