package main.dao.databaseimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Vadim on 04.04.2016.
 */
public class DatabaseController {
    private Statement statement;

    public DatabaseController(Statement statement){
        this.statement = statement;
    }

    public ResultSet select(String tableName, String column, String where) throws SQLException{
        String query = "SELECT " + column + " FROM " + tableName;

        if(where != null){
            query += " WHERE " + where;
        }

        return statement.executeQuery(query);
    }

    public ResultSet select(String tableName, String[] columns, String where) throws SQLException{
        String query = "SELECT ";

        for (int i=0; i < columns.length - 1; i++){
            query += columns[i] + ",";
        }
        query += columns[columns.length - 1];
        query += " FROM " + tableName;

        if(where != null){
            query += " WHERE " + where;
        }

        return statement.executeQuery(query);
    }

    public int insert(String tableName, String[] columns, String[] values) throws SQLException{
        String query = "INSERT INTO " + tableName + " (";
        for (int i = 0; i < columns.length - 1; i++){
            query += columns[i] + ",";
        }
        query += columns[columns.length - 1] + ") VALUES (";
        for (int i = 0; i < values.length - 1; i++) {
            query += "'" + values[i] + "',";
        }
        query += "'" +  values[values.length - 1] + "'";
        query += ")";

        statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();

        return resultSet.getInt(1);
    }


    public boolean update(String tableName, String[] columns, String[] newValues, String where) throws SQLException{
        String query = "UPDATE " + tableName + " SET ";
        for (int i = 0; i < columns.length - 1; i++){
            query += columns[i] + "='" + newValues[i] + "',";
        }
        query += columns[columns.length - 1] + "='" + newValues[columns.length - 1] + "'";
        query += " WHERE " + where;

        statement.executeUpdate(query);
        return true;
    }

    public boolean remove(String tableName, String where) throws SQLException{
        String query = "DELETE FROM " + tableName;
        query += " WHERE " + where;

        statement.executeUpdate(query);
        return true;
    }
}
