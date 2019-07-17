package chat.service;

import chat.entities.SessionParameters;
import chat.entities.User;

import java.util.List;

public interface UserService {
      String addUser(User user, String roomId);
      List<User> getUsers(String roomId);
      User getUser(String login, String password);
      SessionParameters getResponce(String login, String password);
//    public List<User> getActiveUsers();
}
