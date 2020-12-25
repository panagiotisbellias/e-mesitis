package e.mesitis;

/**
 *
 * @author Panagiotis Bellias
 */
public class Apartment extends Residence {
    
    private String floor;
    private boolean parkingSpot, warehouse;

    public Apartment(String floor, boolean parkingSpot, boolean warehouse, String municipality, float area, int constructionYear, int bedrooms, int bathrooms, double rentalPrice) {
        super(municipality, area, constructionYear, bedrooms, bathrooms, rentalPrice);
        this.floor = floor;
        this.parkingSpot = parkingSpot;
        this.warehouse = warehouse;
    }

    public Apartment(String floor, boolean parkingSpot, boolean warehouse) {
        this.floor = floor;
        this.parkingSpot = parkingSpot;
        this.warehouse = warehouse;
    }

    public Apartment() {
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public boolean isParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(boolean parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public boolean isWarehouse() {
        return warehouse;
    }

    public void setWarehouse(boolean warehouse) {
        this.warehouse = warehouse;
    }
    
}
