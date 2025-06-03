package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/bus_reservation";
                String user = "root";  
                String password = "Hemaj@22";  
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new SQLException("Connection failed.");
            }
        }
        return connection;
    }
}
