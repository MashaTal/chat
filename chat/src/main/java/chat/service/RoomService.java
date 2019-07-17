package chat.service;

import chat.entities.Room;

import java.util.List;

public interface RoomService {
    String addRoom(int userId, Room room);
    List<Room> getListRoomByUserId(String userId);
    List<Room> getAllListRoom();
}
