package e.mesitis;

/**
 *
 * @author Panagiotis Bellias
 */
public class DetachedHouse extends Residence {
    
    private int floors;
    private float plotArea;
    private boolean hasFireplace;

    public DetachedHouse(
            int floors,
            float plotArea,
            boolean hasFireplace,
            String municipality,
            float area,
            int constructionYear,
            int bedrooms,
            int bathrooms,
            double rentalPrice
    ) {
        super(municipality, area, constructionYear, bedrooms, bathrooms, rentalPrice);
        setFloors(floors);
        setPlotArea(plotArea);
        this.hasFireplace = hasFireplace;
    }

    public DetachedHouse(int floors, float plotArea, boolean hasFireplace) {
        setFloors(floors);
        setPlotArea(plotArea);
        this.hasFireplace = hasFireplace;
    }

    public DetachedHouse() {}

    @Override
    public String getType() {
        return "DetachedHouse";
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        if (floors <= 0) {
            throw new IllegalArgumentException("Number of floors must be greater than zero.");
        }
        this.floors = floors;
    }

    public float getPlotArea() {
        return plotArea;
    }

    public void setPlotArea(float plotArea) {
        if (!Float.isFinite(plotArea) || plotArea <= 0) {
            throw new IllegalArgumentException("Plot area must be a finite value greater than zero.");
        }
        this.plotArea = plotArea;
    }

    public boolean hasFireplace() {
        return hasFireplace;
    }

    public void setFireplace(boolean hasFireplace) {
        this.hasFireplace = hasFireplace;
    }

    @Override
    public String toString() {
        return "Detached House in " + getMunicipality() + " (" + getArea() + " m², " +
                getBedrooms() + " beds, " + getBathrooms() + " baths, " +
                "floors: " + floors + ", plot area: " + plotArea + ", fireplace: " + hasFireplace + ")";
    }

    @Override
    public String basicInfo() {
        return super.basicInfo() +
                String.format(" | Floors: %d | Plot: %.1f m² | Fireplace: %-5s",
                        floors,
                        plotArea,
                        hasFireplace);
    }

    @Override
    public String detailedInfo() {
        return """
        === Detached House ===
        Municipality: %s
        Area: %.2f m²
        Construction Year: %d
        Bedrooms: %d
        Bathrooms: %d
        Rental Price: €%.2f
        Floors: %d
        Plot Area: %.2f m²
        Fireplace: %s
        """.formatted(
                municipality,
                area,
                constructionYear,
                bedrooms,
                bathrooms,
                rentalPrice,
                floors,
                plotArea,
                hasFireplace() ? "Yes" : "No"
        );
    }

}
