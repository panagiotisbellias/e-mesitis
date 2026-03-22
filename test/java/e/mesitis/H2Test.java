package e.mesitis;

import java.sql.Connection;
import java.sql.DriverManager;

public class H2Test {

    public static void main(String[] args) throws Exception {

        Connection conn = DriverManager.getConnection(
                "jdbc:h2:mem:testdb",
                "sa",
                "sa"
        );

        System.out.println("Connected to H2!");
    }
}
