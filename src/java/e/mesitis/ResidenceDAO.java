package e.mesitis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ResidenceDAO {

    private Connection connection;

    public ResidenceDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertResidence(Residence r) throws SQLException {
        if (r.municipality == null || r.municipality.isBlank()) {
            throw new IllegalArgumentException("Municipality cannot be empty");
        }
        if (r.rentalPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        String sql = "INSERT INTO residences (municipality, price, bedrooms) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, r.municipality);
            stmt.setDouble(2, r.rentalPrice);
            stmt.setInt(3, r.bedrooms);
            stmt.executeUpdate();
        }
    }

    public List<Residence> getAllResidences() throws SQLException {

        List<Residence> list = new ArrayList<>();

        String sql = "SELECT municipality, price, bedrooms FROM residences";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Residence r = new Apartment(
                        "test",
                        true,
                        true,
                        rs.getString("municipality"),
                        70,
                        2,
                        rs.getInt("bedrooms"),
                        1,
                        rs.getDouble("price")
                );

                list.add(r);
            }
        }

        return list;
    }
}