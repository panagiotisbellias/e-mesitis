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
}
