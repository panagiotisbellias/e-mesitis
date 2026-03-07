package e.mesitis;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResidenceDAO {

    private Connection connection;

    public ResidenceDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertResidence(Residence r) throws SQLException {
        String sql = "INSERT INTO residences (municipality, price, bedrooms) VALUES (?, ?, ?)";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, r.municipality);
        stmt.setDouble(2, r.rentalPrice);
        stmt.setInt(3, r.bedrooms);

        stmt.executeUpdate();
    }

    public List<Residence> getAllResidences() throws SQLException {

        List<Residence> list = new ArrayList<>();

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM residences");

        while (rs.next()) {

            Residence r = new Apartment(
                    "test", true, true, rs.getString("municipality"), 70, 2, rs.getInt("bedrooms"), 1, rs.getDouble("price"));

            list.add(r);
        }

        return list;
    }
}