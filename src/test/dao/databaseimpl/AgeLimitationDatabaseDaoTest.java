package test.dao.databaseimpl;

import main.dao.databaseimpl.AgeLimitationDatabaseDao;
import main.entity.film.AgeLimitation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alexander on 01.06.2016.
 */
public class AgeLimitationDatabaseDaoTest {
    AgeLimitationDatabaseDao ageLimitationDatabaseDao = AgeLimitationDatabaseDao.getInstance();

    AgeLimitation ageLimitation;
    int ageLimitationId;

    @Before
    public void setUp() throws Exception {
        ageLimitation = AgeLimitation.PG18;
        ageLimitationId = 2;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetInstance() throws Exception {
        assertTrue(ageLimitationDatabaseDao instanceof AgeLimitationDatabaseDao);
    }

    @Test
    public void testFindAgeLimitationById() throws Exception {
        assertTrue(ageLimitationDatabaseDao.findAgeLimitationById(ageLimitationId).equals(ageLimitation));
    }

    @Test
    public void testFindIdByAgeLimitationValue() throws Exception {
        assertTrue(ageLimitationDatabaseDao.findIdByAgeLimitationValue(ageLimitation) == ageLimitationId);
    }

    @Test
    public void testGetAgeLimitationsCollection() throws Exception {
        assertTrue(ageLimitationDatabaseDao.getAgeLimitationsCollection().size() > 0);
    }
}