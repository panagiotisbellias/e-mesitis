package e.mesitis;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ResidenceRegistry {

    private static final Logger logger = Logger.getLogger(ResidenceRegistry.class.getName());

    private final List<Residence> residences = new ArrayList<>();

    // === UC1 ===
    public void addResidence(Residence residence) {
        residences.add(residence);
        System.out.println("Residence successfully registered: " + residence);
    }

    // === UC2 ===
    public void listResidences() {
        logger.info("=== Available Residences ===");

        if (residences.isEmpty()) {
            logger.info("No residences available.");
            return;
        }

        logger.info(String.format(
                "%-15s | %-12s | %-8s | %-6s | %-6s | %-10s | Details",
                "Type", "Municipality", "Area", "Beds", "Baths", "Rent"
        ));

        logger.info("--------------------------------------------------------------------------------");

        for (Residence r : residences) {
            logger.info(r.basicInfo());
        }
    }

    // === UC3 – Search Residence by Criteria ===
    public List<Residence> search(
            String municipality,
            Double maxPrice,
            Integer minBedrooms) {

        List<Residence> results = new ArrayList<>();

        for (Residence r : residences) {

            boolean matches = true;

            if (municipality != null && !municipality.isBlank()) {
                matches &= r.municipality.equalsIgnoreCase(municipality);
            }

            if (maxPrice != null) {
                matches &= r.rentalPrice <= maxPrice;
            }

            if (minBedrooms != null) {
                matches &= r.bedrooms >= minBedrooms;
            }

            if (matches) {
                results.add(r);
            }
        }

        return results;
    }

}
