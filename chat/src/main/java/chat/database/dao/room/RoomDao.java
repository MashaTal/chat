package chat.database.dao.room;

import chat.entities.Room;

import java.util.List;

public interface RoomDao {
    void addRoom(Room room, int userId);
    List<Room> getListRoomByUserId(String userId);
    List<Room> getAllListRoom();
    Room getRoomById(int roomId);
}
