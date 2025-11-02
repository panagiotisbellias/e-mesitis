package e.mesitis;

import java.util.ArrayList;
import java.util.List;

public class ResidenceRegistry {

    private final List<Residence> residences = new ArrayList<>();

    public void addResidence(Residence residence) {
        residences.add(residence);
        System.out.println("Residence successfully registered: " + residence);
    }

    public List<Residence> getResidences() {
        return residences;
    }

    public void listResidences() {
        if (residences.isEmpty()) {
            System.out.println("No residences registered yet.");
        } else {
            residences.forEach(System.out::println);
        }
    }

}
