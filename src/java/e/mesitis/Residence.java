package e.mesitis;

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
        this.municipality = municipality;
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
        this.municipality = municipality;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        if (area < 0) {
            throw new IllegalArgumentException("Area (square meters) cannot be negative.");
        }
        this.area = area;
    }

    public int getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(int constructionYear) {
        if (constructionYear < 0) {
            throw new IllegalArgumentException("Construction year cannot be negative.");
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
        if (rentalPrice < 0) {
            throw new IllegalArgumentException("Rental price cannot be negative.");
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
