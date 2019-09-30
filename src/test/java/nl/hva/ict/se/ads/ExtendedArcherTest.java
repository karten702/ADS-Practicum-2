package nl.hva.ict.se.ads;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Place all your own tests for Archer in this class. Tests in any other class will be ignored!
 */
public class ExtendedArcherTest extends ArcherTest {

    @Test
    public void archerIdsIncreaseCorrectly() {
        List<Archer> archers = Archer.generateArchers(200);
        for(Archer archer : archers){
            System.out.println(archer.getId());
        }
        assertTrue(archers.get(1).getId() == archers.get(0).getId()+ 1);
        assertTrue(archers.get(2).getId() == archers.get(1).getId()+ 1);
    }

    @Test
    public void archerWeightedScores(){
        List<Archer> archers = Archer.generateArchers(1);
        assertEquals(-7, archers.get(0).weighted(0));
        assertEquals(4, archers.get(0).weighted(3));
    }
}
