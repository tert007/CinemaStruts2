package test.dao.factoryimpl;

import main.dao.DaoFactory;
import main.dao.databaseimpl.*;
import main.dao.factoryimpl.DatabaseDaoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alexander on 01.06.2016.
 */
public class DatabaseDaoFactoryTest {

    DatabaseDaoFactory databaseDaoFactory = DatabaseDaoFactory.getInstance();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetInstance() throws Exception {
        assertTrue(databaseDaoFactory instanceof DaoFactory);
    }

    @Test
    public void testGetUserDao() throws Exception {
        assertTrue(databaseDaoFactory.getUserDao() instanceof UserDatabaseDao);

    }

    @Test
    public void testGetFilmDao() throws Exception {
        assertTrue(databaseDaoFactory.getFilmDao() instanceof FilmDatabaseDao);

    }

    @Test
    public void testGetSeanceDao() throws Exception {
        assertTrue(databaseDaoFactory.getSeanceDao() instanceof SeanceDatabaseDao);
    }

    @Test
    public void testGetTicketDao() throws Exception {
        assertTrue(databaseDaoFactory.getTicketDao() instanceof TicketDatabaseDao);
    }

    @Test
    public void testGetUserTypeDao() throws Exception {
        assertTrue(databaseDaoFactory.getUserTypeDao() instanceof UserTypeDatabaseDao);
    }

    @Test
    public void testGetFilmGenreDao() throws Exception {
        assertTrue(databaseDaoFactory.getFilmGenreDao() instanceof FilmGenreDatabaseDao);
    }

    @Test
    public void testGetHallDao() throws Exception {
        assertTrue(databaseDaoFactory.getHallDao() instanceof HallDatabaseDao);
    }

    @Test
    public void testGetAgeLimitationDao() throws Exception {
        assertTrue(databaseDaoFactory.getAgeLimitationDao() instanceof AgeLimitationDatabaseDao);

    }
}