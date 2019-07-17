package chat.entities;

public class Message {
    private String author;
    private String message;

    Message(String author, String message) {
        this.author = author;
        this.message = message;
    }

    Message() {}

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "author='" + author + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
