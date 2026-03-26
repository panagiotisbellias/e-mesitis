package e.mesitis;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.Assert.assertNotNull;

public class H2Test {

    @Test
    public void shouldConnectToH2() throws Exception {
        Class.forName("org.h2.Driver"); // important for CI

        Connection conn = DriverManager.getConnection(
                "jdbc:h2:mem:testdb",
                "sa",
                "sa"
        );

        assertNotNull(conn);
    }
}
