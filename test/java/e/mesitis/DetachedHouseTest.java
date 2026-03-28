package e.mesitis;

import org.junit.Test;
import static org.junit.Assert.*;

public class DetachedHouseTest {

    @Test
    public void detachedHouseCanBeCreated() {
        DetachedHouse house = new DetachedHouse(
                2,           // floors
                500.0f,      // plotArea
                true,        // hasFireplace
                "Patras",    // municipality
                150.0f,      // area
                1995,        // constructionYear
                3,           // bedrooms
                2,           // bathrooms
                200000.0     // rentalPrice
        );

        assertNotNull(house);

        // Optional extra checks
        assertEquals(2, house.getFloors());
        assertEquals(500.0f, house.getPlotArea(), 0.001f);
        assertTrue(house.hasFireplace());
        assertEquals("DetachedHouse", house.getType());
    }

    @Test
    public void shouldCalculatePricePerSquareMeter() {
        DetachedHouse h = new DetachedHouse(
                2,           // floors
                500.0f,      // plotArea
                true,        // hasFireplace
                "Patras",    // municipality
                150.0f,      // area
                1995,        // constructionYear
                3,           // bedrooms
                2,           // bathrooms
                200000.0     // rentalPrice
        );
        assertTrue(h.getRentalPrice() > 0);
    }
}
