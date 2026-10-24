package e.mesitis;

import java.time.Year;

/**
 *
 * @author Panagiotis Bellias
 */
public abstract class Residence {

    private int id;
    protected String municipality;
    protected float area;
    protected int constructionYear;
    protected int bedrooms;
    protected int bathrooms;
    protected double rentalPrice;

    protected Residence(
            String municipality,
            float area,
            int constructionYear,
            int bedrooms,
            int bathrooms,
            double rentalPrice
    ) {
        setMunicipality(municipality);
        setArea(area);
        setConstructionYear(constructionYear);
        setBedrooms(bedrooms);
        setBathrooms(bathrooms);
        setRentalPrice(rentalPrice);
    }

    protected Residence() {}

    public abstract String getType();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        if (municipality == null || municipality.isBlank()) {
            throw new IllegalArgumentException("Municipality cannot be null or blank.");
        }
        this.municipality = municipality.trim();
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        if (!Float.isFinite(area) || area < 0) {
            throw new IllegalArgumentException("Area must be a finite, non-negative value.");
        }
        this.area = area;
    }

    public int getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(int constructionYear) {
        int currentYear = Year.now().getValue();

        if (constructionYear <= 0 || constructionYear > currentYear) {
            throw new IllegalArgumentException("Construction year must be between 1 and " + currentYear + ".");
        }
        this.constructionYear = constructionYear;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        if (bedrooms < 0) {
            throw new IllegalArgumentException("Number of bedrooms cannot be negative.");
        }
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        if (bathrooms < 0) {
            throw new IllegalArgumentException("Number of bathrooms cannot be negative.");
        }
        this.bathrooms = bathrooms;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        if (!Double.isFinite(rentalPrice) || rentalPrice < 0) {
            throw new IllegalArgumentException("Rental price must be a finite, non-negative value.");
        }
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
