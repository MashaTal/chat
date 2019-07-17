package chat.database.dao.user;

import chat.entities.User;

import java.util.List;

public interface UserDao {
      void addUser(User user, String roomId);
      List<User> getUsers(String roomId);
      User getUser(String login, String password);
      User getUserByName(String login);
}
