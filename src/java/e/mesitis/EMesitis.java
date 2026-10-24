package e.mesitis;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Level;
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
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        ResidenceRegistry registry = new ResidenceRegistry();

        boolean running = true;

        while (running) {
            if (logger.isLoggable(java.util.logging.Level.INFO)) {
                logger.info("""
                        === e-Mesitis ===
                        1. Register a new residence
                        2. View available residences
                        3. Search residence by criteria
                        4. Update residence information
                        5. Delete residence
                        6. Calculate average rent per municipality
                        7. View residence details
                        0. Exit
                        Choose option:
                        """);
            }

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

                    if (logger.isLoggable(java.util.logging.Level.INFO)) {
                        logger.info("Enter residence index to delete:");
                    }
                    int index = scanner.nextInt();
                    scanner.nextLine();

                    if (logger.isLoggable(java.util.logging.Level.INFO)) {
                        logger.info("Confirm deletion? (y/n)");
                    }
                    String confirm = scanner.nextLine();

                    if ("y".equalsIgnoreCase(confirm)) {
                        boolean deleted = registry.deleteResidence(index);
                        if (deleted) {
                            if (logger.isLoggable(java.util.logging.Level.INFO)) {
                                logger.info("Residence deleted successfully.");
                            }
                        } else {
                            if (logger.isLoggable(Level.WARNING)) {
                                logger.warning("Invalid residence index.");
                            }
                        }
                    } else {
                        if (logger.isLoggable(java.util.logging.Level.INFO)) {
                            logger.info("Deletion cancelled.");
                        }
                    }
                }
                case 6 -> registry.displayAverageRentPerMunicipality(); // UC6
                case 0 -> {
                    if (logger.isLoggable(java.util.logging.Level.INFO)) {
                        logger.info("Exiting application.");
                    }
                    running = false;
                }
                case 7 -> {
                    if (registry.getAllResidences().isEmpty()) {
                        if (logger.isLoggable(java.util.logging.Level.INFO)) {
                            logger.info("No residences available.");
                        }
                        break;
                    }

                    registry.listResidencesWithIndex();
                    if (logger.isLoggable(java.util.logging.Level.INFO)) {
                        logger.info("Enter residence index to view details:");
                    }
                    int index = scanner.nextInt();
                    scanner.nextLine();

                    registry.showResidenceDetails(index);
                }
                default -> {
                    if (logger.isLoggable(Level.WARNING)) {
                        logger.warning("Invalid option.");
                    }
                }
            }
        }

        scanner.close();
    }

    private static void registerResidence(Scanner scanner, ResidenceRegistry registry) {

        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("=== Register a New Residence ===");
            logger.info("Enter type (1 = Apartment, 2 = Detached House): ");
        }
        int type = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("Municipality: ");
        }
        String municipality = scanner.nextLine();

        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("Area (m²): ");
        }
        float area = scanner.nextFloat();

        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("Construction Year: ");
        }
        int year = scanner.nextInt();

        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("Bedrooms: ");
        }
        int bedrooms = scanner.nextInt();

        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("Bathrooms: ");
        }
        int bathrooms = scanner.nextInt();

        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("Rental Price (€): ");
        }
        double price = scanner.nextDouble();

        Residence residence;

        if (type == 1) {
            scanner.nextLine();
            if (logger.isLoggable(java.util.logging.Level.INFO)) {
                logger.info("Floor: ");
            }
            String floor = scanner.nextLine();

            if (logger.isLoggable(java.util.logging.Level.INFO)) {
                logger.info("Has parking spot (true/false): ");
            }
            boolean parking = scanner.nextBoolean();

            if (logger.isLoggable(java.util.logging.Level.INFO)) {
                logger.info("Has warehouse (true/false): ");
            }
            boolean warehouse = scanner.nextBoolean();

            residence = new Apartment(floor, parking, warehouse, municipality, area, year, bedrooms, bathrooms, price);

        } else {
            if (logger.isLoggable(java.util.logging.Level.INFO)) {
                logger.info("Floors: ");
            }
            int floors = scanner.nextInt();

            if (logger.isLoggable(java.util.logging.Level.INFO)) {
                logger.info("Plot area (m²): ");
            }
            float plotArea = scanner.nextFloat();

            if (logger.isLoggable(java.util.logging.Level.INFO)) {
                logger.info("Has fireplace (true/false): ");
            }
            boolean fireplace = scanner.nextBoolean();

            residence = new DetachedHouse(
                    floors,
                    plotArea,
                    fireplace,
                    municipality,
                    area,
                    year,
                    bedrooms,
                    bathrooms,
                    price
            );
        }

        registry.addResidence(residence);
        registry.listResidences();
        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("Residence registered successfully.");
        }
    }

    private static void searchResidences(Scanner scanner, ResidenceRegistry registry) {

        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("=== Search Residence by Criteria ===");

            logger.info("Municipality (leave empty to ignore): ");
        }
        String municipality = scanner.nextLine();

        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("Max rental price (0 to ignore): ");
        }
        double priceInput = scanner.nextDouble();

        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("Minimum bedrooms (0 to ignore): ");
        }
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
            if (logger.isLoggable(java.util.logging.Level.INFO)) {
                logger.info("No results found.");
            }
            return;
        }

        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("=== Matching Residences ===");
            for (Residence r : results) {
                logger.info(r.basicInfo());
            }
        }
    }

    private static void updateResidence(Scanner scanner, ResidenceRegistry registry) {

        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("=== Update Residence Information ===");
        }

        if (registry.getAllResidences().isEmpty()) {
            if (logger.isLoggable(java.util.logging.Level.INFO)) {
                logger.info("No residences available to update.");
            }
            return;
        }

        // Show residences with index
        registry.listResidencesWithIndex();

        int index = readInteger(
                scanner
        );

        Residence residence = registry.getResidence(index);

        if (residence == null) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning("Invalid selection.");
            }
            return;
        }

        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("Leave empty to keep current value.");
        }

        updateMunicipality(scanner, residence);
        updateArea(scanner, residence);
        updateConstructionYear(scanner, residence);
        updateBedrooms(scanner, residence);
        updateBathrooms(scanner, residence);
        updateRentalPrice(scanner, residence);

        String municipality = scanner.nextLine();
        if (!municipality.isBlank()) residence.municipality = municipality;
        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("Area (" + residence.area + " m²): ");
        }
        String areaStr = scanner.nextLine();
        if (!areaStr.isBlank()) {
            try {
                residence.setArea(Float.parseFloat(areaStr));
            } catch (NumberFormatException ex) {
                if (logger.isLoggable(Level.WARNING)) {
                    logger.warning("Invalid area: enter a numeric value.");
                }
            } catch (IllegalArgumentException ex) {
                if (logger.isLoggable(Level.WARNING)) {
                    logger.warning(ex.getMessage());
                }
            }
        }

        // Type-specific fields
        if (residence instanceof Apartment) {
            updateApartment(scanner, (Apartment) residence);
        } else if (residence instanceof DetachedHouse) {
            updateDetachedHouse(scanner, (DetachedHouse) residence);
        }

        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("Residence updated successfully!");
            logger.info(residence.basicInfo());
        }
    }

    private static void updateMunicipality(
            Scanner scanner,
            Residence residence
    ) {
        String input = readOptionalInput(
                scanner,
                "Municipality (" + residence.municipality + "): "
        );

        if (input == null) {
            return;
        }

        try {
            residence.setMunicipality(input);
        } catch (IllegalArgumentException ex) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning("Municipality not updated: " + ex.getMessage());
            }
        }
    }

    private static void updateArea(
            Scanner scanner,
            Residence residence
    ) {
        String input = readOptionalInput(
                scanner,
                "Area (" + residence.area + " m²): "
        );

        if (input == null) {
            return;
        }

        try {
            residence.setArea(Float.parseFloat(input));
        } catch (NumberFormatException ex) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning("Area not updated: enter a valid number.");
            }
        } catch (IllegalArgumentException ex) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning("Area not updated: " + ex.getMessage());
            }
        }
    }

    private static void updateConstructionYear(
            Scanner scanner,
            Residence residence
    ) {
        String input = readOptionalInput(
                scanner,
                "Construction year (" + residence.constructionYear + "): "
        );

        if (input == null) {
            return;
        }

        try {
            residence.setConstructionYear(Integer.parseInt(input));
        } catch (NumberFormatException ex) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning("Construction year not updated: enter a valid integer.");
            }
        } catch (IllegalArgumentException ex) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning("Construction year not updated: " + ex.getMessage());
            }
        }
    }

    private static void updateBedrooms(
            Scanner scanner,
            Residence residence
    ) {
        String input = readOptionalInput(
                scanner,
                "Bedrooms (" + residence.bedrooms + "): "
        );

        if (input == null) {
            return;
        }

        try {
            residence.setBedrooms(Integer.parseInt(input));
        } catch (NumberFormatException ex) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning("Bedrooms not updated: enter a valid integer.");
            }
        } catch (IllegalArgumentException ex) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning("Bedrooms not updated: " + ex.getMessage());
            }
        }
    }

    private static void updateBathrooms(
            Scanner scanner,
            Residence residence
    ) {
        String input = readOptionalInput(
                scanner,
                "Bathrooms (" + residence.bathrooms + "): "
        );

        if (input == null) {
            return;
        }

        try {
            residence.setBathrooms(Integer.parseInt(input));
        } catch (NumberFormatException ex) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning("Bathrooms not updated: enter a valid integer.");
            }
        } catch (IllegalArgumentException ex) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning("Bathrooms not updated: " + ex.getMessage());
            }
        }
    }

    private static void updateRentalPrice(
            Scanner scanner,
            Residence residence
    ) {
        String input = readOptionalInput(
                scanner,
                "Rental price (€" + residence.rentalPrice + "): "
        );

        if (input == null) {
            return;
        }

        try {
            residence.setRentalPrice(Double.parseDouble(input));
        } catch (NumberFormatException ex) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning("Rental price not updated: enter a valid number.");
            }
        } catch (IllegalArgumentException ex) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning("Rental price not updated: " + ex.getMessage());
            }
        }
    }

    private static void updateApartment(
            Scanner scanner,
            Apartment apartment
    ) {
        String floor = readOptionalInput(
                scanner,
                "Floor (" + apartment.getFloor() + "): "
        );

        if (floor != null) {
            try {
                apartment.setFloor(floor);
            } catch (IllegalArgumentException ex) {
                if (logger.isLoggable(Level.WARNING)) {
                    logger.warning("Floor not updated: " + ex.getMessage());
                }
            }
        }

        Boolean parking = readOptionalBoolean(
                scanner,
                "Has parking (" + apartment.hasParkingSpot() + "): "
        );

        if (parking != null) {
            apartment.setParkingSpot(parking);
        }

        Boolean warehouse = readOptionalBoolean(
                scanner,
                "Has warehouse (" + apartment.hasWarehouse() + "): "
        );

        if (warehouse != null) {
            apartment.setWarehouse(warehouse);
        }
    }

    private static void updateDetachedHouse(
            Scanner scanner,
            DetachedHouse house
    ) {
        String floors = readOptionalInput(
                scanner,
                "Floors (" + house.getFloors() + "): "
        );

        if (floors != null) {
            try {
                house.setFloors(Integer.parseInt(floors));
            } catch (NumberFormatException ex) {
                if (logger.isLoggable(Level.WARNING)) {
                    logger.warning("Floors not updated: enter a valid integer.");
                }
            } catch (IllegalArgumentException ex) {
                if (logger.isLoggable(Level.WARNING)) {
                    logger.warning("Floors not updated: " + ex.getMessage());
                }
            }
        }

        String plotArea = readOptionalInput(
                scanner,
                "Plot area (" + house.getPlotArea() + " m²): "
        );

        if (plotArea != null) {
            try {
                house.setPlotArea(Float.parseFloat(plotArea));
            } catch (NumberFormatException ex) {
                if (logger.isLoggable(Level.WARNING)) {
                    logger.warning("Plot area not updated: enter a valid number.");
                }
            } catch (IllegalArgumentException ex) {
                if (logger.isLoggable(Level.WARNING)) {
                    logger.warning("Plot area not updated: " + ex.getMessage());
                }
            }
        }

        Boolean fireplace = readOptionalBoolean(
                scanner,
                "Has fireplace (" + house.hasFireplace() + "): "
        );

        if (fireplace != null) {
            house.setFireplace(fireplace);
        }
    }

    private static String readOptionalInput(
            Scanner scanner,
            String prompt
    ) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(prompt);
        }
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? null : input;
    }

    private static Boolean readOptionalBoolean(
            Scanner scanner,
            String prompt
    ) {
        while (true) {
            String input = readOptionalInput(scanner, prompt);

            if (input == null) {
                return null;
            }

            if ("true".equalsIgnoreCase(input)) {
                return Boolean.TRUE;
            }

            if ("false".equalsIgnoreCase(input)) {
                return Boolean.FALSE;
            }

            if (logger.isLoggable(Level.WARNING)) {
                logger.warning("Enter true or false, or leave empty to keep the current value.");
            }
        }
    }

    private static int readInteger(Scanner scanner) {
        while (true) {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Enter index of residence to update: ");
            }
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                if (logger.isLoggable(Level.WARNING)) {
                    logger.warning("Invalid input: enter a valid integer.");
                }
            }
        }
    }
    
}
