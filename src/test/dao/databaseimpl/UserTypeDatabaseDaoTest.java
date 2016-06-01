package test.dao.databaseimpl;

import main.dao.UserTypeDao;
import main.dao.databaseimpl.UserDatabaseDao;
import main.dao.databaseimpl.UserTypeDatabaseDao;
import main.entity.user.UserType;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Vadim on 01.06.2016.
 */
public class UserTypeDatabaseDaoTest {
    private UserTypeDatabaseDao userTypeDatabaseDao = UserTypeDatabaseDao.getInstance();
    private UserType userType = UserType.USER;

    @Test
    public void getInstance() throws Exception {
        assertTrue(userTypeDatabaseDao instanceof UserTypeDao);
    }

    @Test
    public void findUserTypeById() throws Exception {
        UserType newUserType = userTypeDatabaseDao.findUserTypeById(1);
        assertTrue(newUserType.equals(userType));
    }

    @Test
    public void findIdByUserTypeValue() throws Exception {
        int id = userTypeDatabaseDao.findIdByUserTypeValue(userType);
        assertTrue(id == 1);
    }

    @Test
    public void getUserTypesCollection() throws Exception {
        List<UserType> userTypes = userTypeDatabaseDao.getUserTypesCollection();
        assertTrue(userTypes.size() > 0);
    }
}