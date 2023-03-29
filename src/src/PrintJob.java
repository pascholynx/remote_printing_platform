
package src;

public class PrintJob {
    private int id;
    private String content;

    public PrintJob(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PrintJob{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
