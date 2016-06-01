package test.dao.databaseimpl;

import main.dao.UserDao;
import main.dao.databaseimpl.UserDatabaseDao;
import main.entity.user.User;
import main.entity.user.UserType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Vadim on 01.06.2016.
 */
public class UserDatabaseDaoTest {
    User user = new User();
    UserDatabaseDao userDatabaseDao = UserDatabaseDao.getInstance();
    int user_id;

    @Before
    public void setUp() throws Exception {
        user.setLogin("test");
        user.setPassword("test");
        user.setEmail("test");
        user.setBonusCount(10000);
        user.setUserType(UserType.USER);
        user_id = userDatabaseDao.addUser(user);
    }

    @After
    public void tearDown() throws Exception {
        userDatabaseDao.removeUser(user_id);
    }

    @Test
    public void getInstance() throws Exception {
        assertTrue(userDatabaseDao instanceof UserDao);
    }

    @Test
    public void findUserById() throws Exception {
        User buff = userDatabaseDao.findUserById(user_id);
        assertTrue(buff.getId() == user_id);
    }

    @Test
    public void findUser() throws Exception {
        User buff = userDatabaseDao.findUser(user.getLogin());
        assertTrue(buff.getId() == user_id);
    }

    @Test
    public void getUsersCollection() throws Exception {
        List<User> users = userDatabaseDao.getUsersCollection();
        assertTrue(users.size() > 0);
    }

    @Test
    public void addUser() throws Exception {
        User newUser = new User();
        newUser.setLogin("test");
        newUser.setPassword("test");
        newUser.setEmail("test");
        newUser.setBonusCount(10000);
        newUser.setUserType(UserType.USER);
        int new_user_id = userDatabaseDao.addUser(newUser);
        boolean flag = userDatabaseDao.findUserById(new_user_id).getId() == new_user_id;
        userDatabaseDao.removeUser(new_user_id);
        assertTrue(flag);
    }

    @Test
    public void updateUser() throws Exception {
        User newUser = new User();
        newUser.setLogin("test2");
        newUser.setPassword("test2");
        newUser.setEmail("test2");
        newUser.setBonusCount(20000);
        newUser.setUserType(UserType.USER);
        newUser.setId(user_id);
        userDatabaseDao.updateUser(newUser);
        assertTrue(userDatabaseDao.findUserById(user_id).getLogin().equals("test2"));
    }

    @Test
    public void removeUser() throws Exception {
        assertTrue(userDatabaseDao.removeUser(user_id));
    }
}