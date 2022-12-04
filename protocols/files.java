package protocols;

public class files {
    private int id;
    private String name;
    private byte[] data;
    private String fileEx;

    public files(int id, String name, String fileE, byte[] data) {
        this.data = data;
        this.id = id;
        this.fileEx = fileE;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setFileEx(String FE) {
        this.fileEx = FE;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public byte[] getData() {
        return this.data;
    }

    public String getFileEx() {
        return this.fileEx;
    }
}
