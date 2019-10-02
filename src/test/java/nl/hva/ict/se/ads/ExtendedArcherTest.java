package nl.hva.ict.se.ads;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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

    @Test
    public void archerResetScore(){
        List<Archer> archers = Archer.generateArchers(1);
        archers.get(0).clearScores();
        assertEquals(0, archers.get(0).getTotalScore());
        assertEquals(0, archers.get(0).getWeightedScore());
    }

    @Test
    public void archerSetScore(){
        List<Archer> archers = Archer.generateArchers(1);
        Archer archer = archers.get(0);
        int oldScore = archer.getTotalScore();
        int oldWeighted = archer.getWeightedScore();
        archer.setTotalScore(501);
        archer.setWeightedScore(605);
        assertTrue(oldScore != archer.getTotalScore());
        assertTrue(oldWeighted != archer.getWeightedScore());
        assertEquals(501, archer.getTotalScore());
        assertEquals(605, archer.getWeightedScore());
    }

    @Test
    public void archerCorrectScores(){
        List<Archer> archers = Archer.generateArchers(1);
        Archer archer = archers.get(0);
        archer.clearScores();
        archerFillPremadeScores(archer);
        assertEquals(55, archer.getTotalScore());
        assertEquals(51, archer.getWeightedScore());
    }

    @Test
    public void archerCorrectChangedScore(){
        List<Archer> archers = Archer.generateArchers(1);
        Archer archer = archers.get(0);
        archer.clearScores();
        archerFillPremadeScores(archer);
        archer.registerScoreForRound(2, new int[] {5,0,0});
        assertEquals(38, archer.getTotalScore());
        assertEquals(18, archer.getWeightedScore());
    }

    @Test
    public void archerCorrectAddedRound(){
        List<Archer> archers = Archer.generateArchers(1);
        Archer archer = archers.get(0);
        archer.clearScores();
        archerFillPremadeScores(archer);
        archer.registerScoreForRound(4, new int[] {5,0,0});
        assertEquals(60, archer.getTotalScore());
        assertEquals(43, archer.getWeightedScore());
    }

    public void archerFillPremadeScores(Archer archer){
        archer.registerScoreForRound(0, new int[] {3,4,5});
        archer.registerScoreForRound(1, new int[] {3,0,0});
        archer.registerScoreForRound(2, new int[] {8,4,10});
        archer.registerScoreForRound(3, new int[] {6,7,5});
    }
}
