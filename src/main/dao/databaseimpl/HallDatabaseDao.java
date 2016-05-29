package main.dao.databaseimpl;

import main.dao.DaoException;
import main.dao.HallDao;
import main.entity.hall.Hall;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 01.05.2016.
 */
public class HallDatabaseDao extends Connector implements HallDao {

    private static final String tableName = "hall";

    private static final String columnId = "id";
    private static final String columnHallCapacity = "capicity";

    private static final String[] columnsName = {
            columnId,
            columnHallCapacity
    };

    private HallDatabaseDao() {
        super();
    }

    private static final HallDatabaseDao instance = new HallDatabaseDao();

    public static HallDatabaseDao getInstance() {
        return instance;
    }

    @Override
    public Hall findHallById(int id) throws DaoException {
        ResultSet resultSet = null;
        try {
            resultSet = databaseController.select(tableName, columnsName, columnId + "='" + id + "'");



            resultSet.next();
            return createHallFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Hall> getHallsCollection() throws DaoException {
        ResultSet resultSet = null;
        try {
            resultSet = databaseController.select(tableName, columnsName, null);

            return createHallsCollectionFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private List<Hall> createHallsCollectionFromResultSet(ResultSet resultSet) throws DaoException {
        List<Hall> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(createHallFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Hall createHallFromResultSet(ResultSet resultSet) throws DaoException {
        try {
            return new Hall(resultSet.getInt(columnId), resultSet.getInt(columnHallCapacity));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
