package test.action.generator;

import main.dao.DaoFactory;
import main.dao.HallDao;
import main.dao.databaseimpl.HallDatabaseDao;
import main.entity.hall.Hall;
import main.entity.seance.Seance;
import main.entity.user.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Vadim on 01.06.2016.
 */
public class HallGenerationTest {
    private int halId = 1;
    private HallDatabaseDao hallDatabaseDao = HallDatabaseDao.getInstance();

    @Before
    public void setUp() throws Exception {
        assertTrue(hallDatabaseDao instanceof HallDao);
    }

    @After
    public void tearDown() throws Exception {
        Hall hall = hallDatabaseDao.findHallById(halId);
        assertTrue(hall.getCapacity() == 125);
    }

    @Test
    public void execute() throws Exception {
        List<Hall> halls = hallDatabaseDao.getHallsCollection();
        assertTrue(halls.size() > 0);
    }

    @Test
    public void getHall_id() throws Exception {
        List<User> users = DaoFactory.getDaoFactory().getUserDao().getUsersCollection();
        assertTrue(users.size() > 0);
    }

    @Test
    public void setHall_id() throws Exception {
        List<Hall> halls = DaoFactory.getDaoFactory().getHallDao().getHallsCollection();
        assertTrue(halls.size() > 0);
    }

    @Test
    public void getDoc_type() throws Exception {
        List<Seance> seances = DaoFactory.getDaoFactory().getSeanceDao().getTodaySeances();
        assertTrue(true);
    }

    @Test
    public void setDoc_type() throws Exception {
        List<Hall> halls = DaoFactory.getDaoFactory().getHallDao().getHallsCollection();
        assertTrue(halls.size() > 0);
    }
}