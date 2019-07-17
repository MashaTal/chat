package chat.service;

import chat.config.RESTConstants;
import chat.database.dao.room.RoomDao;
import chat.database.dao.room.RoomDaoImpl;
import chat.entities.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService{
    private static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);
    @Autowired
    private RoomDao roomDao;

    public void setRoomDao(RoomDaoImpl roomDao) {
        this.roomDao = roomDao;
    }
    @Override
    @Transactional
    public String addRoom(int userId, Room room) {
        if(!isRoomExist(room)) {
            Date currentDate = new Date();
            room.setDate_of_creation(currentDate);
            room.setState("offline");
            this.roomDao.addRoom(room, userId);
            return RESTConstants.RESPONCE_SUCCSESS;
        }
        return RESTConstants.RESPONCE_ERROR;
    }

    private boolean isRoomExist(Room room) {
        Room newRoom = getRoomById(room.getId());
        if(newRoom == null) return false;
        return true;
    }

    private Room getRoomById(int roomId) {
        return this.roomDao.getRoomById(roomId);
    }

    @Override
    @Transactional
    public List<Room> getListRoomByUserId(String userId) {
        return this.roomDao.getListRoomByUserId(userId);
    }

    @Override
    @Transactional
    public List<Room> getAllListRoom() {
        return this.roomDao.getAllListRoom();
    }
}
