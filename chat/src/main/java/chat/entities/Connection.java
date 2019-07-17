package chat.entities;

import javax.persistence.*;

@Entity
@Table(name="connections")
public class Connection {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_room")
    private int id_room;

    @Column(name = "id_user")
    private int id_user;

    public Connection() {}

    @Override
    public String toString() {
        return "Connection{" +
                "id=" + id +
                ", id_room=" + id_room +
                ", id_user=" + id_user +
                '}';
    }

    public Connection(int id_room, int id_user) {
        this.id_room = id_room;
        this.id_user = id_user;
    }
}
