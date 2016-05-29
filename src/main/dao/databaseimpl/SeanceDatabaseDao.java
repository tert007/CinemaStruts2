package main.dao.databaseimpl;

import main.dao.DaoException;
import main.dao.SeanceDao;
import main.entity.film.Film;
import main.entity.hall.Hall;
import main.entity.seance.Seance;
import main.entity.ticket.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexander on 30.04.2016.
 */
public class SeanceDatabaseDao extends Connector implements SeanceDao {

    private static final String tableName = "seance";

    private static final String columnId = "id";
    private static final String columnHallId = "id_hall";
    private static final String columnFilmId = "id_film";
    private static final String columnUnixTime = "unix_time";
    private static final String columnPrice = "price";

    private static final String[] columnsName = {
            columnId,
            columnHallId,
            columnFilmId,
            columnUnixTime,
            columnPrice
    };

    private SeanceDatabaseDao(){
        super();
    }

    private static final SeanceDatabaseDao instance = new SeanceDatabaseDao();

    public static SeanceDatabaseDao getInstance() {
        return instance;
    }

    private static String[] getValues(Seance seance) {
        String[] result = {
                String.valueOf(seance.getId()),
                String.valueOf(seance.getHall().getId()),
                String.valueOf(seance.getFilm().getId()),
                String.valueOf(seance.getDate().getTime() / 1000),
                String.valueOf(seance.getPrice())
        };

        return result;
    }

    public static long removeTimeFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime().getTime() / 1000;
    }

    public static long roundTimeToFullDay(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime().getTime() / 1000;
    }

    @Override
    public Seance findSeanceById(int id) throws DaoException {
        ResultSet resultSet = null;
        try {
            resultSet = databaseController.select(tableName, columnsName, columnId + "='" + id + "'");

            resultSet.next();
            return createSeanceFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Seance> findSeancesByDate(Date startDate, Date finishDate) throws DaoException {
        ResultSet resultSet = null;
        try {
            resultSet = databaseController.select(tableName, columnsName, columnUnixTime + " >= " + removeTimeFromDate(startDate) + " AND " + columnUnixTime + " < " + roundTimeToFullDay(finishDate));

            return createSeanceCollectionFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Seance> findSeancesByFilm(Film film) throws DaoException {
        ResultSet resultSet = null;
        try {
            resultSet = databaseController.select(tableName, columnsName, columnFilmId + " = " + film.getId());

            return createSeanceCollectionFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Integer> getBusyPlaces(int seanceId) throws DaoException {
        List<Ticket> tickets = TicketDatabaseDao.getInstance().findTicketsBySeanceId(seanceId);
        List<Integer> busyPlaces = new ArrayList<>();

        for (Ticket ticket : tickets) {
            busyPlaces.add(ticket.getPlace());
        }

        return busyPlaces;
    }

    @Override
    public List<Seance> getTodaySeances() throws DaoException {
        return findSeancesByDate(Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
    }

    @Override
    public int addNewSeance(Seance seance) throws DaoException {
        try {
            return databaseController.insert(tableName, columnsName, getValues(seance));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean removeSeanceById(int id) throws DaoException {
        try {
            return databaseController.remove(tableName, columnId + "=" + id);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateSeance(Seance newSeance) throws DaoException {
        try {
            return databaseController.update(tableName, columnsName, getValues(newSeance), columnId + "=" + newSeance.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private List<Seance> createSeanceCollectionFromResultSet(ResultSet resultSet) throws DaoException {
        List<Seance> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(createSeanceFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Seance createSeanceFromResultSet(ResultSet resultSet) throws DaoException {
        Seance seance = new Seance();

        try {
            seance.setId(resultSet.getInt(columnId));
            seance.setDate(resultSet.getLong(columnUnixTime) * 1000);
            seance.setPrice(resultSet.getInt(columnPrice));

            int hallId = resultSet.getInt(columnHallId);
            Hall hall = HallDatabaseDao.getInstance().findHallById(hallId);
            seance.setHall(hall);

            int filmId = resultSet.getInt(columnFilmId);
            Film film = FilmDatabaseDao.getInstance().findFilmById(filmId);
            seance.setFilm(film);

            return seance;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
