package e.mesitis;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * @author Panagiotis Bellias
 */
public class EMesitis {

    private static final Logger logger = Logger.getLogger(EMesitis.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ResidenceRegistry registry = new ResidenceRegistry();

        boolean running = true;

        while (running) {
            logger.info("""
                === e-Mesitis ===
                1. Register a new residence
                2. View available residences
                3. Search residence by criteria
                0. Exit
                Choose option:
                """);

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> registerResidence(scanner, registry); // UC1
                case 2 -> registry.listResidences();            // UC2
                case 3 -> searchResidences(scanner, registry);  // UC3
                case 0 -> {
                    logger.info("Exiting application.");
                    running = false;
                }
                default -> logger.warning("Invalid option.");
            }
        }

        scanner.close();
    }

    private static void registerResidence(Scanner scanner, ResidenceRegistry registry) {

        logger.info("=== Register a New Residence ===");
        logger.info("Enter type (1 = Apartment, 2 = Detached House): ");
        int type = scanner.nextInt();
        scanner.nextLine(); // consume newline

        logger.info("Municipality: ");
        String municipality = scanner.nextLine();

        logger.info("Area (m²): ");
        float area = scanner.nextFloat();

        logger.info("Construction Year: ");
        int year = scanner.nextInt();

        logger.info("Bedrooms: ");
        int bedrooms = scanner.nextInt();

        logger.info("Bathrooms: ");
        int bathrooms = scanner.nextInt();

        logger.info("Rental Price (€): ");
        double price = scanner.nextDouble();

        Residence residence;

        if (type == 1) {
            scanner.nextLine();
            logger.info("Floor: ");
            String floor = scanner.nextLine();

            logger.info("Has parking spot (true/false): ");
            boolean parking = scanner.nextBoolean();

            logger.info("Has warehouse (true/false): ");
            boolean warehouse = scanner.nextBoolean();

            residence = new Apartment(floor, parking, warehouse, municipality, area, year, bedrooms, bathrooms, price);

        } else {
            logger.info("Floors: ");
            int floors = scanner.nextInt();

            logger.info("Plot area (m²): ");
            float plotArea = scanner.nextFloat();

            logger.info("Has fireplace (true/false): ");
            boolean fireplace = scanner.nextBoolean();

            residence = new DetachedHouse(floors, plotArea, fireplace, municipality, area, year, bedrooms, bathrooms, price);
        }

        registry.addResidence(residence);
        registry.listResidences();
        logger.info("Residence registered successfully.");
    }

    private static void searchResidences(Scanner scanner, ResidenceRegistry registry) {

        logger.info("=== Search Residence by Criteria ===");

        logger.info("Municipality (leave empty to ignore): ");
        String municipality = scanner.nextLine();

        logger.info("Max rental price (0 to ignore): ");
        double priceInput = scanner.nextDouble();

        logger.info("Minimum bedrooms (0 to ignore): ");
        int bedroomsInput = scanner.nextInt();
        scanner.nextLine();

        Double maxPrice = priceInput > 0 ? priceInput : null;
        Integer minBedrooms = bedroomsInput > 0 ? bedroomsInput : null;

        var results = registry.search(
                municipality.isBlank() ? null : municipality,
                maxPrice,
                minBedrooms
        );

        if (results.isEmpty()) {
            logger.info("No results found.");
            return;
        }

        logger.info("=== Matching Residences ===");
        for (Residence r : results) {
            logger.info(r.basicInfo());
        }
    }
    
}
