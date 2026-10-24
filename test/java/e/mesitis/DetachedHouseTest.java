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


    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectZeroFloors() {
        new DetachedHouse(
                0, 500.0f, false,
                "Patras", 150.0f, 1995, 3, 2, 200000.0
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectNegativeFloors() {
        new DetachedHouse(
                -1, 500.0f, false,
                "Patras", 150.0f, 1995, 3, 2, 200000.0
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectNegativePlotArea() {
        new DetachedHouse(
                2, -500.0f, false,
                "Patras", 150.0f, 1995, 3, 2, 200000.0
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectNaNPlotArea() {
        new DetachedHouse(
                2, Float.NaN, false,
                "Patras", 150.0f, 1995, 3, 2, 200000.0
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectInfinitePlotArea() {
        new DetachedHouse(
                2, Float.POSITIVE_INFINITY, false,
                "Patras", 150.0f, 1995, 3, 2, 200000.0
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectInvalidFloorsWhenUpdated() {
        DetachedHouse house = new DetachedHouse(
                2, 500.0f, false,
                "Patras", 150.0f, 1995, 3, 2, 200000.0
        );

        house.setFloors(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectInvalidPlotAreaWhenUpdated() {
        DetachedHouse house = new DetachedHouse(
                2, 500.0f, false,
                "Patras", 150.0f, 1995, 3, 2, 200000.0
        );

        house.setPlotArea(0.0f);
    }
}
