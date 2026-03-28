package e.mesitis;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ResidenceDAOTest {

    ResidenceDAO dao;

    @Before
    public void setUp() throws Exception {
        Connection conn = DriverManager.getConnection(
                "jdbc:h2:mem:testdb_" + System.nanoTime(),
                "sa",
                ""
        );
        Statement stmt = conn.createStatement();
        stmt.execute("""
        CREATE TABLE IF NOT EXISTS residences(
            id INT AUTO_INCREMENT PRIMARY KEY,
            municipality VARCHAR(255),
            price DOUBLE,
            bedrooms INT
        )
    """);
        dao = new ResidenceDAO(conn);
    }

    @Test
    public void testDatabaseInsertAndRetrieve() throws Exception {

        Connection conn = DriverManager.getConnection(
                "jdbc:h2:mem:testdb_" + System.nanoTime(),
                "sa",
                "sa"
        );

        Statement stmt = conn.createStatement();

        stmt.execute("""
        CREATE TABLE IF NOT EXISTS residences(
            id INT AUTO_INCREMENT PRIMARY KEY,
            municipality VARCHAR(255),
            price DOUBLE,
            bedrooms INT
        )
    """);

        ResidenceDAO dao = new ResidenceDAO(conn);

        Residence r = new Apartment(
                "test", true, true, "Athens", 70, 2, 1, 1, 700);

        dao.insertResidence(r);

        List<Residence> results = dao.getAllResidences();

        assertEquals(1, results.size());
        assertEquals("Athens", results.get(0).municipality);
    }

    @Test
    public void shouldInsertUpdateDeleteResidence() throws Exception {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:h2:mem:testdb_" + System.nanoTime(),
                "sa",
                ""
        );

        Statement stmt = conn.createStatement();
        stmt.execute("""
        CREATE TABLE IF NOT EXISTS residences(
            id INT AUTO_INCREMENT PRIMARY KEY,
            municipality VARCHAR(255),
            price DOUBLE,
            bedrooms INT
        )
    """);

        ResidenceDAO dao = new ResidenceDAO(conn);

        Residence r = new Apartment(
                "test", true, true, "Athens", 70, 2, 1, 1, 700);

        dao.insertResidence(r);
        assertEquals(1, dao.getAllResidences().size());
    }

}
