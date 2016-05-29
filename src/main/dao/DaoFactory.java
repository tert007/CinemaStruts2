package main.dao;

import main.dao.factoryimpl.DatabaseDaoFactory;

public abstract class DaoFactory {

	public static DaoFactory getDaoFactory(){
		switch ("database") {
			case "database":
				return DatabaseDaoFactory.getInstance();
		}

		return null;
	}

	public abstract UserDao getUserDao() throws DaoException;
	public abstract FilmDao getFilmDao() throws DaoException;
	public abstract SeanceDao getSeanceDao() throws DaoException;
	public abstract TicketDao getTicketDao() throws DaoException;
	public abstract UserTypeDao getUserTypeDao() throws DaoException;
	public abstract FilmGenreDao getFilmGenreDao() throws DaoException;
	public abstract HallDao getHallDao() throws DaoException;
	public abstract AgeLimitationDao getAgeLimitationDao() throws DaoException;
}
