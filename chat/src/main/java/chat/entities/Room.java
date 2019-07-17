package chat.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="rooms")
public class Room {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "state")
    private String state;

    @Column(name = "date_of_creation")
    private Date date_of_creation;

    public Date getDate_of_creation() {
        return date_of_creation;
    }

    public void setDate_of_creation(Date date_of_creation) {
        this.date_of_creation = date_of_creation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", date_of_creation=" + date_of_creation +
                '}';
    }
}
