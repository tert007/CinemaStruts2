package main.dao.databaseimpl;

import main.dao.DaoException;
import main.dao.UserTypeDao;
import main.entity.user.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 01.05.2016.
 */
public class UserTypeDatabaseDao extends Connector implements UserTypeDao {
    private static final String tableName = "user_type";

    private static final String columnId = "id";
    private static final String columnType = "type";

    private static final String[] columnsName = {
            columnId,
            columnType
    };

    private UserTypeDatabaseDao() {
        super();
    }

    private static final UserTypeDatabaseDao instance = new UserTypeDatabaseDao();

    public static UserTypeDatabaseDao getInstance() {
        return instance;
    }

    @Override
    public UserType findUserTypeById(int id) throws DaoException {
        ResultSet resultSet = null;
        try {
            resultSet = databaseController.select(tableName, columnType, columnId + "='" + id + "'");
            resultSet.next();

            return createUserTypeFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int findIdByUserTypeValue(UserType userType) throws DaoException {
        ResultSet resultSet = null;
        try {
            resultSet = databaseController.select(tableName, columnId, columnType + "='" + userType + "'");
            resultSet.next();

            return resultSet.getInt(columnId);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<UserType> getUserTypesCollection() throws DaoException {
        ResultSet resultSet = null;
        try {
            resultSet = databaseController.select(tableName, columnsName, null);

            return createUserTypesCollectionFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private List<UserType> createUserTypesCollectionFromResultSet(ResultSet resultSet) throws DaoException {
        List<UserType> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(createUserTypeFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private UserType createUserTypeFromResultSet(ResultSet resultSet) throws DaoException {
        try {
            return UserType.valueOf(resultSet.getString(columnType));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}

