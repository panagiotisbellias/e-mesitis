package e.mesitis;

/**
 *
 * @author Panagiotis Bellias
 */
public class Apartment extends Residence {
    
    private String floor;
    private boolean hasParkingSpot;
    private boolean hasWarehouse;

    public Apartment(
            String floor,
            boolean hasParkingSpot,
            boolean hasWarehouse,
            String municipality,
            float area,
            int constructionYear,
            int bedrooms,
            int bathrooms,
            double rentalPrice
    ) {
        super(municipality, area, constructionYear, bedrooms, bathrooms, rentalPrice);
        this.floor = floor;
        this.hasParkingSpot = hasParkingSpot;
        this.hasWarehouse = hasWarehouse;
    }

    public Apartment(String floor, boolean hasParkingSpot, boolean hasWarehouse) {
        this.floor = floor;
        this.hasParkingSpot = hasParkingSpot;
        this.hasWarehouse = hasWarehouse;
    }

    public Apartment() {}

    @Override
    public String getType() {
        return "Apartment";
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public boolean hasParkingSpot() {
        return hasParkingSpot;
    }

    public void setParkingSpot(boolean hasParkingSpot) {
        this.hasParkingSpot = hasParkingSpot;
    }

    public boolean hasWarehouse() {
        return hasWarehouse;
    }

    public void setWarehouse(boolean hasWarehouse) {
        this.hasWarehouse = hasWarehouse;
    }

    @Override
    public String toString() {
        return "Apartment in " + getMunicipality() + " (" + getArea() + " m², " +
                getBedrooms() + " beds, " + getBathrooms() + " baths, " +
                "floor: " + floor + ", parking: " + hasParkingSpot + ", warehouse: " + hasWarehouse + ")";
    }

    @Override
    public String basicInfo() {
        return super.basicInfo() +
                String.format(" | Floor: %-5s | Parking: %-5s | Warehouse: %-5s",
                        floor,
                        hasParkingSpot,
                        hasWarehouse);
    }

    @Override
    public String detailedInfo() {
        return """
        === Apartment ===
        Municipality: %s
        Area: %.2f m²
        Construction Year: %d
        Bedrooms: %d
        Bathrooms: %d
        Rental Price: €%.2f
        Floor: %s
        Parking Spot: %s
        Warehouse: %s
        """.formatted(
                municipality,
                area,
                constructionYear,
                bedrooms,
                bathrooms,
                rentalPrice,
                floor,
                hasParkingSpot() ? "Yes" : "No",
                hasWarehouse() ? "Yes" : "No"
        );
    }

}
