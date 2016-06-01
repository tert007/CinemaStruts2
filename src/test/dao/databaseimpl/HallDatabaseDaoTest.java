package test.dao.databaseimpl;

import main.dao.HallDao;
import main.dao.databaseimpl.HallDatabaseDao;
import main.entity.hall.Hall;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Vadim on 01.06.2016.
 */
public class HallDatabaseDaoTest {
    private int halId = 1;
    private HallDatabaseDao hallDatabaseDao = HallDatabaseDao.getInstance();

    @Test
    public void getInstance() throws Exception {
        assertTrue(hallDatabaseDao instanceof HallDao);
    }

    @Test
    public void findHallById() throws Exception {
        Hall hall = hallDatabaseDao.findHallById(halId);
        assertTrue(hall.getCapacity() == 125);
    }

    @Test
    public void getHallsCollection() throws Exception {
        List<Hall> halls = hallDatabaseDao.getHallsCollection();
        assertTrue(halls.size() > 0);
    }
}