package e.mesitis;

import org.junit.Test;
import static org.junit.Assert.*;

public class ApartmentTest {

    @Test
    public void apartmentCanBeCreated() {
        Apartment apartment = new Apartment(
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

        assertNotNull(apartment);

        // Optional extra checks
        assertEquals("3rd floor", apartment.getFloor());
        assertTrue(apartment.hasParkingSpot());
        assertFalse(apartment.hasWarehouse());
        assertEquals("Apartment", apartment.getType());
    }
}
