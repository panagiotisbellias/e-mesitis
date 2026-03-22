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
        if (logger.isLoggable(java.util.logging.Level.INFO)) {
        logger.info("Enter index of residence to update: ");
        }
        int index = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Residence r = registry.getResidence(index);
        if (r == null) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning("Invalid selection.");
            }
            return;
        }
        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("Leave empty to keep current value.");

            // Common fields
            logger.info("Municipality (" + r.municipality + "): ");
        }
        String municipality = scanner.nextLine();
        if (!municipality.isBlank()) r.municipality = municipality;
        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("Area (" + r.area + " m²): ");
        }
        String areaStr = scanner.nextLine();
        if (!areaStr.isBlank()) r.area = Float.parseFloat(areaStr);

        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("Construction Year (" + r.constructionYear + "): ");
        }
        String yearStr = scanner.nextLine();
        if (!yearStr.isBlank()) r.constructionYear = Integer.parseInt(yearStr);

        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("Bedrooms (" + r.bedrooms + "): ");
        }
        String bedroomsStr = scanner.nextLine();
        if (!bedroomsStr.isBlank()) r.bedrooms = Integer.parseInt(bedroomsStr);

        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("Bathrooms (" + r.bathrooms + "): ");
        }
        String bathroomsStr = scanner.nextLine();
        if (!bathroomsStr.isBlank()) r.bathrooms = Integer.parseInt(bathroomsStr);

        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("Rental Price (€" + r.rentalPrice + "): ");
        }
        String priceStr = scanner.nextLine();
        if (!priceStr.isBlank()) r.rentalPrice = Double.parseDouble(priceStr);

        // Type-specific fields
        if (r instanceof Apartment) {
            Apartment apt = (Apartment) r;
            if (logger.isLoggable(java.util.logging.Level.INFO)) {
                logger.info("Floor (" + apt.getFloor() + "): ");
            }
            String floor = scanner.nextLine();
            if (!floor.isBlank()) apt.setFloor(floor);

            if (logger.isLoggable(java.util.logging.Level.INFO)) {
                logger.info("Has parking (" + apt.hasParkingSpot() + "): ");
            }
            String parking = scanner.nextLine();
            if (!parking.isBlank()) apt.setParkingSpot(Boolean.parseBoolean(parking));

            if (logger.isLoggable(java.util.logging.Level.INFO)) {
                logger.info("Has warehouse (" + apt.hasWarehouse() + "): ");
            }
            String warehouse = scanner.nextLine();
            if (!warehouse.isBlank()) apt.setWarehouse(Boolean.parseBoolean(warehouse));

        } else if (r instanceof DetachedHouse) {
            DetachedHouse house = (DetachedHouse) r;
            if (logger.isLoggable(java.util.logging.Level.INFO)) {
                logger.info("Floors (" + house.getFloors() + "): ");
            }
            String floors = scanner.nextLine();
            if (!floors.isBlank()) house.setFloors(Integer.parseInt(floors));

            if (logger.isLoggable(java.util.logging.Level.INFO)) {
                logger.info("Plot Area (" + house.getPlotArea() + " m²): ");
            }
            String plot = scanner.nextLine();
            if (!plot.isBlank()) house.setPlotArea(Float.parseFloat(plot));

            if (logger.isLoggable(java.util.logging.Level.INFO)) {
                logger.info("Has fireplace (" + house.hasFireplace() + "): ");
            }
            String fireplace = scanner.nextLine();
            if (!fireplace.isBlank()) house.setFireplace(Boolean.parseBoolean(fireplace));
        }

        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info("Residence updated successfully!");
            logger.info(r.basicInfo());
        }
    }

    
}
