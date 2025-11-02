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
    }
    
}
