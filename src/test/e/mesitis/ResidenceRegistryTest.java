package e.mesitis;

import org.junit.Test;
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
}
