package e.mesitis;

import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.Assert.assertNotNull;

public class DatabaseIntegrationTest {

    @Test
    public void testH2Connection() throws Exception {

        Connection conn = DriverManager.getConnection(
                "jdbc:h2:mem:testdb",
                "sa",
                ""
        );

        Statement stmt = conn.createStatement();

        stmt.execute(
                "CREATE TABLE residences(id INT PRIMARY KEY, name VARCHAR(255))"
        );

        assertNotNull(conn);
    }
}