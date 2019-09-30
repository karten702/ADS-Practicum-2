package nl.hva.ict.se.ads;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Place all your own tests for ChampionSelector in this class. Tests in any other class will be ignored!
 */
public class ExtendedChampionSelectorTest extends ChampionSelectorTest {

    @Test
    public void timeSelectionSort() {

        int numberOfArchers = 100;
        int maxNumberOfArchers = 5000000;

        long highestElapsedTime = 0;
        long maxElapsedTime = 20000;

        do {
            long elapsedSelIns = 0;
            long elapsedCollection = 0;
            long startTime = 0;
            long endTime = 0;

            List<Archer> unsortedArchersForSelIns = Archer.generateArchers(numberOfArchers);
            List<Archer> unsortedArchersForCollection = new ArrayList<>(unsortedArchersForSelIns);

            startTime = System.nanoTime();
            List<Archer> sortedArchersSelIns = ChampionSelector.selInsSort(unsortedArchersForSelIns, comparator);
            endTime = System.nanoTime();
            elapsedSelIns = endTime - startTime;

            startTime = System.nanoTime();
            List<Archer> sortedArchersCollection = ChampionSelector.collectionSort(unsortedArchersForCollection, comparator);
            endTime = System.nanoTime();
            elapsedCollection = endTime - startTime;

            System.out.println("Sorted " + numberOfArchers + " archers.");
            System.out.println("Selection sort time: " + elapsedSelIns + " nanoseconds or " + elapsedSelIns/1000000 + " milliseconds");
            System.out.println("Collection sort time: " + elapsedCollection + " nanoseconds or " + elapsedCollection/1000000 + " milliseconds");
            System.out.println("=======================================================================================================");
            if (elapsedSelIns > elapsedCollection)
                highestElapsedTime = elapsedSelIns/1000000;
            else
                highestElapsedTime = elapsedCollection/1000000;
            numberOfArchers = numberOfArchers*2;
        }while (highestElapsedTime < maxElapsedTime && numberOfArchers <= maxNumberOfArchers);
    }

//    @Test
//    public void archerIteratorTest(){
//        long nrOfArchers = 20;
//        Iterator<Archer> archerIterator = Archer.generateArchers(nrOfArchers);
//        if (archerIterator != null){
//            Iterator<Archer> sorted = ChampionSelector.quickSort(archerIterator, comparator);
//        }
//    }
//
//    @Test
//    public void timeIteratorSort() {
//
//        long numberOfArchers = 100;
//        int maxNumberOfArchers = 5000000;
//
//        long highestElapsedTime = 0;
//        long maxElapsedTime = 20000;
//
//        do {
//            long elapsedQuickIterator = 0;
//            long startTime = 0;
//            long endTime = 0;
//
//            Iterator<Archer> archerIterator = Archer.generateArchers(numberOfArchers);
//
//            startTime = System.nanoTime();
//            Iterator<Archer> sorted = ChampionSelector.quickSort(archerIterator, comparator);
//            endTime = System.nanoTime();
//            elapsedQuickIterator = endTime - startTime;
//
//            System.out.println("Sorted " + numberOfArchers + " archers.");
//            System.out.println("Quick sort time: " + elapsedQuickIterator + " nanoseconds or " + elapsedQuickIterator/1000000 + " milliseconds");
//            System.out.println("=======================================================================================================");
//
//            highestElapsedTime = elapsedQuickIterator/1000000;
//            numberOfArchers = numberOfArchers*2;
//        }while (highestElapsedTime < maxElapsedTime && numberOfArchers <= maxNumberOfArchers);
//    }
}
