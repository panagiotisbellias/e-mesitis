package e.mesitis;

/**
 *
 * @author Panagiotis Bellias
 */
public abstract class Residence {

    protected String municipality;
    protected float area;
    protected int constructionYear;
    protected int bedrooms;
    protected int bathrooms;
    protected double rentalPrice;

    protected Residence(String municipality, float area, int constructionYear, int bedrooms, int bathrooms, double rentalPrice) {
        this.municipality = municipality;
        this.area = area;
        this.constructionYear = constructionYear;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.rentalPrice = rentalPrice;
    }

    protected Residence() {}

    public abstract String getType();

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

    public String basicInfo() {
        return String.format(
                "%-15s | %-12s | %6.1f m² | %2d bd | %2d ba | €%8.2f",
                getType(),
                municipality,
                area,
                bedrooms,
                bathrooms,
                rentalPrice
        );
    }

    public abstract String detailedInfo();
    
}
