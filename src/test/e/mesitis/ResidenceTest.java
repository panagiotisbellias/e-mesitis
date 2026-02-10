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
}
