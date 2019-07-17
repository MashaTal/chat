package chat.database.dao.room;

import chat.entities.Connection;
import chat.entities.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class RoomDaoImpl implements RoomDao {
    private static final Logger logger = LoggerFactory.getLogger(RoomDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void addRoom(Room room ,int userId) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(room);
        logger.info("Room successfully saved. Room details: " + room);
        Connection connection = new Connection(room.getId(), userId);
        session.persist(connection);
        logger.info("Connection successfully saved. Connection details: " + room);
    }

    @Override
    public List<Room> getListRoomByUserId(String userId) {
        Session session = this.sessionFactory.getCurrentSession();
        Object[] rows = session.createSQLQuery("SELECT id_room FROM connections WHERE id_user= " + userId).list().toArray();
        if(rows.length == 0) return null;
        List<Room> roomList = (List<Room>) session.createSQLQuery("SELECT * FROM rooms WHERE id IN (:values)")
                .addEntity(Room.class)
                .setParameterList("values", rows)
                .list();
        logger.info(roomList.size() + "roomDao");
        return roomList;
    }

    @Override
    public Room getRoomById(int roomId) {
        Session session = this.sessionFactory.getCurrentSession();
        Room room = (Room) session.get(Room.class, new Integer(roomId));
        logger.info("Room by id " + room);
        return room;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Room> getAllListRoom() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Room> roomList = session.createSQLQuery("SELECT * FROM rooms")
                .addEntity(Room.class)
                .list();
        return roomList;
    }
}
