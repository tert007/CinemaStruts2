package main.dao.factoryimpl;

import main.dao.*;
import main.dao.databaseimpl.*;

/**
 * Created by Alexander on 16.02.2016.
 */
public class DatabaseDaoFactory extends DaoFactory {
    private final static DatabaseDaoFactory instance = new DatabaseDaoFactory();

    private DatabaseDaoFactory() {
    }

    public static DatabaseDaoFactory getInstance() {
        return instance;
    }

    @Override
    public UserDao getUserDao() {
        return UserDatabaseDao.getInstance();
    }

    @Override
    public FilmDao getFilmDao() {
        return FilmDatabaseDao.getInstance();
    }

    @Override
    public SeanceDao getSeanceDao() throws DaoException {
        return SeanceDatabaseDao.getInstance();
    }

    @Override
    public TicketDao getTicketDao() throws DaoException {
        return TicketDatabaseDao.getInstance();
    }

    @Override
    public UserTypeDao getUserTypeDao() throws DaoException {
        return UserTypeDatabaseDao.getInstance();
    }

    @Override
    public FilmGenreDao getFilmGenreDao() throws DaoException {
        return FilmGenreDatabaseDao.getInstance();
    }

    @Override
    public HallDao getHallDao() throws DaoException {
        return HallDatabaseDao.getInstance();
    }

    @Override
    public AgeLimitationDao getAgeLimitationDao() throws DaoException {
        return AgeLimitationDatabaseDao.getInstance();
    }
}
