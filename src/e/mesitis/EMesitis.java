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
                4. Update residence information
                5. Delete residence
                6. Calculate average rent per municipality
                0. Exit
                Choose option:
                """);

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> registerResidence(scanner, registry); // UC1
                case 2 -> registry.listResidences();            // UC2
                case 3 -> searchResidences(scanner, registry);  // UC3
                case 4 -> updateResidence(scanner, registry);  // UC4
                case 5 -> { // UC5
                    registry.listResidences();

                    if (registry.getAllResidences().isEmpty()) {
                        break;
                    }

                    logger.info("Enter residence index to delete:");
                    int index = scanner.nextInt();
                    scanner.nextLine();

                    logger.info("Confirm deletion? (y/n)");
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("y")) {
                        boolean deleted = registry.deleteResidence(index);
                        if (deleted) {
                            logger.info("Residence deleted successfully.");
                        } else {
                            logger.warning("Invalid residence index.");
                        }
                    } else {
                        logger.info("Deletion cancelled.");
                    }
                }
                case 6 -> registry.displayAverageRentPerMunicipality(); // UC6
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

    private static void updateResidence(Scanner scanner, ResidenceRegistry registry) {
        logger.info("=== Update Residence Information ===");

        if (registry.getAllResidences().isEmpty()) {
            logger.info("No residences available to update.");
            return;
        }

        // Show residences with index
        registry.listResidencesWithIndex();
        logger.info("Enter index of residence to update: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Residence r = registry.getResidence(index);
        if (r == null) {
            logger.warning("Invalid selection.");
            return;
        }

        logger.info("Leave empty to keep current value.");

        // Common fields
        logger.info("Municipality (" + r.municipality + "): ");
        String municipality = scanner.nextLine();
        if (!municipality.isBlank()) r.municipality = municipality;

        logger.info("Area (" + r.area + " m²): ");
        String areaStr = scanner.nextLine();
        if (!areaStr.isBlank()) r.area = Float.parseFloat(areaStr);

        logger.info("Construction Year (" + r.constructionYear + "): ");
        String yearStr = scanner.nextLine();
        if (!yearStr.isBlank()) r.constructionYear = Integer.parseInt(yearStr);

        logger.info("Bedrooms (" + r.bedrooms + "): ");
        String bedroomsStr = scanner.nextLine();
        if (!bedroomsStr.isBlank()) r.bedrooms = Integer.parseInt(bedroomsStr);

        logger.info("Bathrooms (" + r.bathrooms + "): ");
        String bathroomsStr = scanner.nextLine();
        if (!bathroomsStr.isBlank()) r.bathrooms = Integer.parseInt(bathroomsStr);

        logger.info("Rental Price (€" + r.rentalPrice + "): ");
        String priceStr = scanner.nextLine();
        if (!priceStr.isBlank()) r.rentalPrice = Double.parseDouble(priceStr);

        // Type-specific fields
        if (r instanceof Apartment) {
            Apartment apt = (Apartment) r;
            logger.info("Floor (" + apt.getFloor() + "): ");
            String floor = scanner.nextLine();
            if (!floor.isBlank()) apt.setFloor(floor);

            logger.info("Has parking (" + apt.hasParkingSpot() + "): ");
            String parking = scanner.nextLine();
            if (!parking.isBlank()) apt.setParkingSpot(Boolean.parseBoolean(parking));

            logger.info("Has warehouse (" + apt.hasWarehouse() + "): ");
            String warehouse = scanner.nextLine();
            if (!warehouse.isBlank()) apt.setWarehouse(Boolean.parseBoolean(warehouse));

        } else if (r instanceof DetachedHouse) {
            DetachedHouse house = (DetachedHouse) r;
            logger.info("Floors (" + house.getFloors() + "): ");
            String floors = scanner.nextLine();
            if (!floors.isBlank()) house.setFloors(Integer.parseInt(floors));

            logger.info("Plot Area (" + house.getPlotArea() + " m²): ");
            String plot = scanner.nextLine();
            if (!plot.isBlank()) house.setPlotArea(Float.parseFloat(plot));

            logger.info("Has fireplace (" + house.hasFireplace() + "): ");
            String fireplace = scanner.nextLine();
            if (!fireplace.isBlank()) house.setFireplace(Boolean.parseBoolean(fireplace));
        }

        logger.info("Residence updated successfully!");
        logger.info(r.basicInfo());
    }

    
}
