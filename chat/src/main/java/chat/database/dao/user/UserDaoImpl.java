package chat.database.dao.user;

import chat.entities.Connection;
import chat.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user, String roomId) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User successfully saved. User details: " + user);
        User newUser = getUserByName(user.getLogin());
        if(newUser != null) {
            Connection connection = new Connection(new Integer(roomId), newUser.getId());
            session.persist(connection);
            logger.info("Connection successfully saved. Connection details: " + connection);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers(String roomId) {
        Session session = this.sessionFactory.getCurrentSession();
        Object[] rows = session.createSQLQuery("SELECT id_user FROM connections WHERE id_room= " + roomId).list().toArray();
        List<User> userList = session.createSQLQuery("SELECT * FROM users WHERE id IN (:values)")
                .addEntity(User.class)
                .setParameterList("values", rows)
                .list();
        return userList;
    }

    @Override
    public User getUser(String login, String password) {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> list = (List<User>) session.createSQLQuery("SELECT * FROM users WHERE login ='" + login + "' AND password = '"+ password + "'")
                .addEntity(User.class)
                .list();
        if(list.isEmpty()) {
            return null;
        }
        logger.info("User successfully loaded. User details: " + list.get(0));
        return list.get(0);
    }

    public User getUserByName(String login) {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> list = (List<User>) session.createSQLQuery("SELECT * FROM users WHERE login ='" + login + "'")
                .addEntity(User.class)
                .list();
        if(list != null && list.isEmpty()) {
            return null;
        }
        logger.info("User successfully loaded. User details: " + list.get(0));
        return list.get(0);
    }

}
