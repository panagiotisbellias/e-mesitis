
package e.mesitis;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResidenceCsvExporterTest {

    @Test
    public void shouldExportHeaderAndResidenceData() throws IOException {
        Path output = Files.createTempFile("residences", ".csv");

        try {
            Residence residence = new Apartment(
                    "2nd floor",
                    true,
                    false,
                    "Athens",
                    80.0f,
                    2010,
                    2,
                    1,
                    900.0
            );

            new ResidenceCsvExporter().export(
                    List.of(residence), output);

            List<String> lines = Files.readAllLines(
                    output, StandardCharsets.UTF_8);

            assertEquals(2, lines.size());
            assertTrue(lines.get(0).startsWith(
                    "type,municipality,area_m2"));
            assertTrue(lines.get(1).startsWith(
                    "Apartment,Athens,80.00,2010,2,1,900.00"));
            assertTrue(lines.get(1).contains("2nd floor,true,false"));
        } finally {
            Files.deleteIfExists(output);
        }
    }

    @Test
    public void shouldEscapeCommasAndQuotes() throws IOException {
        Path output = Files.createTempFile("residences", ".csv");

        try {
            Residence residence = new Apartment(
                    "2nd \"upper\" floor",
                    false,
                    true,
                    "Athens, Central",
                    80.0f,
                    2010,
                    2,
                    1,
                    900.0
            );

            new ResidenceCsvExporter().export(
                    List.of(residence), output);

            String csv = Files.readString(output);

            assertTrue(csv.contains("\"Athens, Central\""));
            assertTrue(csv.contains("\"2nd \"\"upper\"\" floor\""));
        } finally {
            Files.deleteIfExists(output);
        }
    }

    @Test
    public void shouldWriteHeaderForEmptyRegistry() throws IOException {
        Path output = Files.createTempFile("residences", ".csv");

        try {
            new ResidenceCsvExporter().export(
                    new ArrayList<>(), output);

            List<String> lines = Files.readAllLines(
                    output, StandardCharsets.UTF_8);

            assertEquals(1, lines.size());
            assertTrue(lines.getFirst().startsWith("type,municipality"));
        } finally {
            Files.deleteIfExists(output);
        }
    }
}
