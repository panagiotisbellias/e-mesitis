
package e.mesitis;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

public class ResidenceCsvExporter {

    private static final String[] HEADERS = {
            "type",
            "municipality",
            "area_m2",
            "construction_year",
            "bedrooms",
            "bathrooms",
            "rental_price",
            "floor",
            "has_parking_spot",
            "has_warehouse",
            "floors",
            "plot_area_m2",
            "has_fireplace"
    };

    public void export(List<Residence> residences, Path outputPath)
            throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(
                outputPath,
                StandardCharsets.UTF_8)) {
            writeRow(writer, HEADERS);

            for (Residence residence : residences) {
                writeRow(writer, getResidenceFields(residence));
            }
        }
    }

    private String[] getResidenceFields(Residence residence) {
        String floor = "";
        String parking = "";
        String warehouse = "";
        String floors = "";
        String plotArea = "";
        String fireplace = "";

        if (residence instanceof Apartment) {
            Apartment apartment = (Apartment) residence;
            floor = apartment.getFloor();
            parking = Boolean.toString(apartment.hasParkingSpot());
            warehouse = Boolean.toString(apartment.hasWarehouse());
        } else if (residence instanceof DetachedHouse) {
            DetachedHouse house = (DetachedHouse) residence;
            floors = Integer.toString(house.getFloors());
            plotArea = String.format(
                    Locale.ROOT, "%.2f", house.getPlotArea());
            fireplace = Boolean.toString(house.hasFireplace());
        }

        return new String[] {
                residence.getType(),
                residence.getMunicipality(),
                String.format(Locale.ROOT, "%.2f", residence.getArea()),
                Integer.toString(residence.getConstructionYear()),
                Integer.toString(residence.getBedrooms()),
                Integer.toString(residence.getBathrooms()),
                String.format(Locale.ROOT, "%.2f", residence.getRentalPrice()),
                floor,
                parking,
                warehouse,
                floors,
                plotArea,
                fireplace
        };
    }

    private void writeRow(BufferedWriter writer, String... fields)
            throws IOException {
        for (int i = 0; i < fields.length; i++) {
            if (i > 0) {
                writer.write(',');
            }
            writer.write(escapeCsv(fields[i]));
        }
        writer.newLine();
    }

    private String escapeCsv(String value) {
        if (value == null) {
            return "";
        }

        if (value.contains(",")
                || value.contains("\"")
                || value.contains("\n")
                || value.contains("\r")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }

        return value;
    }
}
