package chat.entities;

import java.util.List;

public class SessionParameters {
    private boolean exist;
    private boolean admin;
    private List<Room> rooms;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public SessionParameters(boolean exist, boolean admin,List<Room> rooms, int userId) {
        this.exist = exist;
        this.admin = admin;
        this.rooms = rooms;
        this.userId = userId;
    }
}
