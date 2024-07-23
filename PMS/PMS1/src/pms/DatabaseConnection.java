package pms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides a connection to the PMS database.
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/PMS";
    private static final String USER = "root";
    private static final String PASSWORD = "new_password";

    /**
     * Gets a connection to the database.
     * 
     * @return a Connection object
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
