package e.mesitis;

/**
 *
 * @author Panagiotis Bellias
 */
public abstract class Residence {
    
    private String municipality;
    private float area;
    private int constructionYear, bedrooms, bathrooms;
    private double rentalPrice;

    public Residence(String municipality, float area, int constructionYear, int bedrooms, int bathrooms, double rentalPrice) {
        this.municipality = municipality;
        this.area = area;
        this.constructionYear = constructionYear;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.rentalPrice = rentalPrice;
    }

    public Residence() {
    }

    
    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public int getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(int constructionYear) {
        this.constructionYear = constructionYear;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
    
}
