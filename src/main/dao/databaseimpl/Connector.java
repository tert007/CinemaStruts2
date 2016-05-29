package main.dao.databaseimpl;


import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

/**
 * Created by Alexander on 20.02.2016.
 */
public class Connector {
    private static final String className = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/cinema";
    private static final String login = "root";
    private static final String password = "root";

    protected static Connection connection = null;

    protected Statement statement = null;
    protected DatabaseController databaseController;

    protected Connector() {
        try {
            if (connection == null){
                Class.forName(className);

                Driver driver = new FabricMySQLDriver();
                DriverManager.registerDriver(driver);

                connection = DriverManager.getConnection(url, login, password);
            }

            statement = connection.createStatement();
            databaseController = new DatabaseController(statement);
        }
        catch (ClassNotFoundException | SQLException e) {
            connection = null;
            statement = null;
        }
    }

    protected void closeConnection() throws SQLException {
         if (connection != null) {
            connection.close();
            connection = null;
        }
    }

    protected void closeStatement(Statement statement) throws SQLException {
            if (statement != null) {
                statement.close();
            }
    }

    protected void closeResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
    }


}
