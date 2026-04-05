package e.mesitis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    public static Connection getConnection() throws SQLException {
        String url = System.getenv("DB_URL"); // e.g. jdbc:postgresql://localhost:5432/db?ssl=true
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        return DriverManager.getConnection(url, user, password);
    }
}