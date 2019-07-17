package chat.service;

import chat.config.RESTConstants;
import chat.database.dao.user.UserDao;
import chat.database.dao.user.UserDaoImpl;
import chat.entities.Room;
import chat.entities.SessionParameters;
import chat.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import chat.database.dao.room.RoomDao;
import chat.database.dao.room.RoomDaoImpl;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl  implements UserService{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoomDao roomDao;

    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public void setRoomDao(RoomDaoImpl roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    @Transactional
    public String addUser(User user, String roomId) {
        logger.info("login: " + user.getLogin());
        logger.info(this.userDao.getUserByName(user.getLogin())+ "");
        if(this.userDao.getUserByName(user.getLogin())!= null) {
            return RESTConstants.RESPONCE_ERROR;
        }
        this.userDao.addUser(user, roomId);
        return RESTConstants.RESPONCE_SUCCSESS;
    }

    @Override
    @Transactional
    public List<User> getUsers(String roomId) {
        List<User> users = this.userDao.getUsers(roomId);
        List<User> result = new ArrayList<>();
        for(User user: users) {
             User newUser = new User(user.getId(),user.getLogin(),"",user.getState());
             result.add(newUser);
        }
        return result;
    }

    @Override
    @Transactional
    public User getUser(String login, String password) {
        return this.userDao.getUser(login,password);
    }

    @Transactional
    private boolean isUserExist(User user) {
        if(user == null) {
            return false;
        }
        return true;
    }

    @Transactional
    private boolean isUserAdmin(User user) {
        if(user == null) {
            return false;
        }
        return (user.getLogin()).equals("Admin");
    }

    @Override
    @Transactional
    public SessionParameters getResponce(String login, String password) {
        User user = getUser(login,password);
        boolean exist = isUserExist(user);
        if(exist) {
            int userId = user.getId();
            boolean admin = isUserAdmin(user);
            List<Room> rooms = null;
                if(admin) {
                    rooms = this.roomDao.getAllListRoom();
                } else {
                    rooms = this.roomDao.getListRoomByUserId(user.getId() + "");
                }
            return new SessionParameters(true, admin, rooms, userId);
        }
        return new SessionParameters(false,false, null,0);
    }

}
