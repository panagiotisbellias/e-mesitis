package e.mesitis;

import org.junit.Test;
import static org.junit.Assert.*;

public class ResidenceTest {

    @Test
    public void residenceIsAbstract() {
        assertTrue(
                java.lang.reflect.Modifier.isAbstract(Residence.class.getModifiers())
        );
    }

    @Test
    public void shouldHandleZeroValues() {
        Residence r = new Apartment(
                "3rd floor",  // floor as String
                true,         // hasParkingSpot
                false,        // hasWarehouse
                "Athens",     // municipality
                0.0f,        // area
                2010,         // constructionYear
                2,            // bedrooms
                1,            // bathrooms
                90000.0       // rentalPrice
        );
        assertEquals(0, r.getArea(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailOnNegativeSquareMeters() {
        new Apartment(
                "3rd floor",  // floor as String
                true,         // hasParkingSpot
                false,        // hasWarehouse
                "Athens",     // municipality
                -50,        // area
                2010,         // constructionYear
                2,            // bedrooms
                1,            // bathrooms
                90000.0       // rentalPrice
        );
    }

    @Test
    public void shouldHandleNullMunicipality() {
        Residence r = new Apartment(
                "3rd floor",  // floor as String
                true,         // hasParkingSpot
                false,        // hasWarehouse
                "Athens",     // municipality
                80.0f,        // area
                2010,         // constructionYear
                2,            // bedrooms
                1,            // bathrooms
                90000.0       // rentalPrice
        );
        assertNotNull(r.getMunicipality());
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectBlankMunicipality() {
        new Apartment(
                "1st floor", false, false,
                "   ", 80.0f, 2010, 2, 1, 900.0
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectNullMunicipality() {
        new Apartment(
                "1st floor", false, false,
                null, 80.0f, 2010, 2, 1, 900.0
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectNaNArea() {
        new Apartment(
                "1st floor", false, false,
                "Athens", Float.NaN, 2010, 2, 1, 900.0
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectInfiniteArea() {
        new Apartment(
                "1st floor", false, false,
                "Athens", Float.POSITIVE_INFINITY,
                2010, 2, 1, 900.0
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectNegativeBedrooms() {
        new Apartment(
                "1st floor", false, false,
                "Athens", 80.0f, 2010, -1, 1, 900.0
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectNegativeBathrooms() {
        new Apartment(
                "1st floor", false, false,
                "Athens", 80.0f, 2010, 2, -1, 900.0
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectNegativeRentalPrice() {
        new Apartment(
                "1st floor", false, false,
                "Athens", 80.0f, 2010, 2, 1, -900.0
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectNaNRentalPrice() {
        new Apartment(
                "1st floor", false, false,
                "Athens", 80.0f, 2010, 2, 1, Double.NaN
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectInfiniteRentalPrice() {
        new Apartment(
                "1st floor", false, false,
                "Athens", 80.0f, 2010, 2, 1,
                Double.POSITIVE_INFINITY
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectFutureConstructionYear() {
        new Apartment(
                "1st floor", false, false,
                "Athens", 80.0f,
                java.time.Year.now().getValue() + 1,
                2, 1, 900.0
        );
    }
}
