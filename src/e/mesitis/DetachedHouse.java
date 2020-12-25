package e.mesitis;

/**
 *
 * @author Panagiotis Bellias
 */
public class DetachedHouse extends Residence {
    
    private int floors;
    private float plotArea;
    private boolean fireplace;

    public DetachedHouse(int floors, float plotArea, boolean fireplace, String municipality, float area, int constructionYear, int bedrooms, int bathrooms, double rentalPrice) {
        super(municipality, area, constructionYear, bedrooms, bathrooms, rentalPrice);
        this.floors = floors;
        this.plotArea = plotArea;
        this.fireplace = fireplace;
    }

    public DetachedHouse(int floors, float plotArea, boolean fireplace) {
        this.floors = floors;
        this.plotArea = plotArea;
        this.fireplace = fireplace;
    }

    public DetachedHouse() {
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public float getPlotArea() {
        return plotArea;
    }

    public void setPlotArea(float plotArea) {
        this.plotArea = plotArea;
    }

    public boolean isFireplace() {
        return fireplace;
    }

    public void setFireplace(boolean fireplace) {
        this.fireplace = fireplace;
    }
    
}
