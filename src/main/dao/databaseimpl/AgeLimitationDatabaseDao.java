package main.dao.databaseimpl;

import main.dao.AgeLimitationDao;
import main.dao.DaoException;
import main.entity.film.AgeLimitation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 02.05.2016.
 */
public class AgeLimitationDatabaseDao extends Connector implements AgeLimitationDao {
    private static final String tableName = "age_limitation";

    private static final String columnId = "id";
    private static final String columnAgeLimitationValue = "value";

    private static final String[] columnsName = {
            columnId,
            columnAgeLimitationValue
    };

    private AgeLimitationDatabaseDao() {
        super();
    }

    private static final AgeLimitationDatabaseDao instance = new AgeLimitationDatabaseDao();

    public static AgeLimitationDatabaseDao getInstance() {
        return instance;
    }


    @Override
    public AgeLimitation findAgeLimitationById(int id) throws DaoException {
        ResultSet resultSet = null;
        try {
            resultSet = databaseController.select(tableName, columnAgeLimitationValue, columnId + "='" + id + "'");
            resultSet.next();

            return createAgeLimitationFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int findIdByAgeLimitationValue(AgeLimitation ageLimitation) throws DaoException {
        ResultSet resultSet = null;
        try {
            resultSet = databaseController.select(tableName, columnId, columnAgeLimitationValue + "='" + ageLimitation + "'");
            resultSet.next();

            return resultSet.getInt(columnId);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<AgeLimitation> getAgeLimitationsCollection() throws DaoException {
        ResultSet resultSet = null;
        try {
            resultSet = databaseController.select(tableName, columnsName, null);

            return createAgeLimitationsCollectionFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    private List<AgeLimitation> createAgeLimitationsCollectionFromResultSet(ResultSet resultSet) throws DaoException {
        List<AgeLimitation> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(createAgeLimitationFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private AgeLimitation createAgeLimitationFromResultSet(ResultSet resultSet) throws DaoException {
        try {
            return AgeLimitation.valueOf(resultSet.getString(columnAgeLimitationValue));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
