package main.dao.databaseimpl;

import main.dao.DaoException;
import main.dao.FilmGenreDao;
import main.entity.film.FilmGenre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 01.05.2016.
 */
public class FilmGenreDatabaseDao extends Connector implements FilmGenreDao {

    private static final String tableName = "film_genre";

    private static final String columnId = "id";
    private static final String columnGenre = "film_genre_value";

    private static final String[] columnsName = {
            columnId,
            columnGenre
    };

    private FilmGenreDatabaseDao() {
        super();
    }

    private static final FilmGenreDatabaseDao instance = new FilmGenreDatabaseDao();

    public static FilmGenreDatabaseDao getInstance() {
        return instance;
    }

    @Override
    public FilmGenre findFilmGenreById(int id) throws DaoException {
        ResultSet resultSet = null;
        try {
            resultSet = databaseController.select(tableName, columnGenre, columnId + "=" + id);
            resultSet.next();

            return createFilmGenreFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int findIdByFilmGenreValue(FilmGenre filmGenre) throws DaoException {
        ResultSet resultSet = null;
        try {
            resultSet = databaseController.select(tableName, columnId, columnGenre + "='" + filmGenre + "'");
            resultSet.next();
            return resultSet.getInt(columnId);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<FilmGenre> getFilmGenresCollection() throws DaoException {
        ResultSet resultSet = null;
        try {
            resultSet = databaseController.select(tableName, columnsName, null);

            return createFilmGenresCollectionFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private List<FilmGenre> createFilmGenresCollectionFromResultSet(ResultSet resultSet) throws DaoException {
        List<FilmGenre> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(createFilmGenreFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private FilmGenre createFilmGenreFromResultSet(ResultSet resultSet) throws DaoException {
        try {
            return FilmGenre.valueOf(resultSet.getString(columnGenre));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
