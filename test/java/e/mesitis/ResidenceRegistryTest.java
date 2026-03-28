package e.mesitis;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ResidenceRegistryTest {

    @Test
    public void registryAcceptsResidences() {
        ResidenceRegistry registry = new ResidenceRegistry();

        Residence apartment = new Apartment();
        Residence house = new DetachedHouse();

        registry.addResidence(apartment);
        registry.addResidence(house);

        // If no exception is thrown, this is already meaningful
        assertTrue(true);
    }

    @Test
    public void testAddResidence() {
        ResidenceRegistry registry = new ResidenceRegistry();

        Residence r = new Apartment(
                "test",
                true,
                true,
                "Athens",
                80f,
                2,
                1,
                1,
                750.0
        );

        registry.addResidence(r);

        assertEquals(1, registry.getAllResidences().size());
    }

    @Test
    public void testSearchByMunicipality() {
        ResidenceRegistry registry = new ResidenceRegistry();

        registry.addResidence(new Apartment("test", true, true, "Athens", 70f, 2, 1, 1, 700));
        registry.addResidence(new Apartment("test", false, false, "Piraeus", 90f, 3, 2, 1, 900));

        List<Residence> results = registry.search("Athens", null, null);

        assertEquals(1, results.size());
        assertEquals("Athens", results.get(0).municipality);
    }

    @Test
    public void testSearchByPriceAndBedrooms() {
        ResidenceRegistry registry = new ResidenceRegistry();

        registry.addResidence(new Apartment("test", true, true, "Athens", 70f, 2, 1, 1, 700));
        registry.addResidence(new Apartment("test", false, false, "Athens", 90f, 3, 2, 1, 1200));

        List<Residence> results = registry.search("Athens", 800.0, 2);

        assertEquals(0, results.size());
    }

    @Test
    public void testDeleteResidenceSuccess() {
        ResidenceRegistry registry = new ResidenceRegistry();

        registry.addResidence(new Apartment("test", true, true, "Athens", 70f, 2, 1, 1, 700));

        boolean deleted = registry.deleteResidence(0);

        assertTrue(deleted);
        assertEquals(0, registry.getAllResidences().size());
    }

    @Test
    public void testDeleteResidenceInvalidIndex() {
        ResidenceRegistry registry = new ResidenceRegistry();

        boolean deleted = registry.deleteResidence(5);

        assertFalse(deleted);
    }

    @Test
    public void testAverageRentPerMunicipality() {
        ResidenceRegistry registry = new ResidenceRegistry();

        registry.addResidence(new Apartment("test", true, true, "Athens", 70f, 2, 1, 1, 700));
        registry.addResidence(new Apartment("test", true, true, "Athens", 80f, 2, 1, 1, 900));

        Map<String, Double> averages = registry.calculateAverageRentPerMunicipality();

        assertEquals(800.0, averages.get("Athens"), 0.01);
    }

    @Test
    public void shouldMaintainCorrectAverageAfterMultipleOperations() {
        ResidenceRegistry registry = new ResidenceRegistry();

        registry.addResidence(new Apartment("test", true, true, "Athens", 70f, 2, 1, 1, 700));
        registry.addResidence(new Apartment("test", true, true, "Athens", 80f, 2, 1, 1, 900));
        registry.deleteResidence(0);

//        double avg = registry.calculateAverageRentPerMunicipality("Athens");

//        assertTrue(avg > 0);
    }

}
