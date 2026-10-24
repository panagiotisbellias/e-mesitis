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

    @Test
    public void shouldFormatDetailedInfoCorrectly() {
        Apartment a = new Apartment(
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
        String info = a.detailedInfo();

        assertTrue(info.contains("Floor"));
        assertTrue(info.contains("Parking"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectBlankFloor() {
        new Apartment(
                "   ", false, false,
                "Athens", 80.0f, 2010, 2, 1, 900.0
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectNullFloor() {
        new Apartment(
                null, false, false,
                "Athens", 80.0f, 2010, 2, 1, 900.0
        );
    }

    @Test
    public void shouldTrimFloor() {
        Apartment apartment = new Apartment(
                "  1st floor  ", false, false,
                "Athens", 80.0f, 2010, 2, 1, 900.0
        );

        assertEquals("1st floor", apartment.getFloor());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectBlankFloorWhenUpdated() {
        Apartment apartment = new Apartment(
                "1st floor", false, false,
                "Athens", 80.0f, 2010, 2, 1, 900.0
        );

        apartment.setFloor(" ");
    }
}
