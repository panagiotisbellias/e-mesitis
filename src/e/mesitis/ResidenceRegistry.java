package e.mesitis;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ResidenceRegistry {

    private static final Logger logger = Logger.getLogger(ResidenceRegistry.class.getName());

    private final List<Residence> residences = new ArrayList<>();

    public void addResidence(Residence residence) {
        residences.add(residence);
        System.out.println("Residence successfully registered: " + residence);
    }

    public List<Residence> getResidences() {
        return residences;
    }

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

    public List<Residence> getAllResidences() {
        return new ArrayList<>(residences);
    }

}
