package nl.hva.ict.se.ads;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Place all your own tests for ChampionSelector in this class. Tests in any other class will be ignored!
 */
public class ExtendedChampionSelectorTest extends ChampionSelectorTest {

    @Test
    public void timeSelectionSort() {
        Map<Integer, Long> results = new HashMap<>();
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

            results.put(numberOfArchers, elapsedSelIns/1000000);
            System.out.println("Sorted " + numberOfArchers + " archers.");
            System.out.println("Selection sort time: " + elapsedSelIns + " nanoseconds or " + elapsedSelIns/1000000 + " milliseconds");
            System.out.println("Collection sort time: " + elapsedCollection + " nanoseconds or " + elapsedCollection/1000000 + " milliseconds");
            System.out.println("=======================================================================================================");
            if (elapsedSelIns > elapsedCollection)
                highestElapsedTime = elapsedSelIns/1000000;
            else
                highestElapsedTime = elapsedCollection/1000000;
            numberOfArchers = numberOfArchers*2;

            assertEquals(sortedArchersCollection, sortedArchersSelIns);
        }while (highestElapsedTime < maxElapsedTime && numberOfArchers <= maxNumberOfArchers);
        List<Integer> list = new ArrayList<>(results.keySet());
        list.sort(Comparator.comparingInt(o -> o));
        list.forEach(key -> System.out.printf("%d%n", key));
        System.out.println("");
        list.forEach(key -> System.out.printf("%d%n", results.get(key)));
    }

    @Test
    public void timeQuickSort() {
        Map<Integer, Long> results = new HashMap<>();
        int numberOfArchers = 100;
        int maxNumberOfArchers = 5000000;

        long highestElapsedTime = 0;
        long maxElapsedTime = 20000;

        do {
            long elapsedQuick = 0;
            long elapsedCollection = 0;
            long startTime = 0;
            long endTime = 0;

            List<Archer> unsortedArchersForQuick = Archer.generateArchers(numberOfArchers);
            List<Archer> unsortedArchersForCollection = new ArrayList<>(unsortedArchersForQuick);

            startTime = System.nanoTime();
            List<Archer> sortedArchersQuick = ChampionSelector.quickSort(unsortedArchersForQuick, comparator);
            endTime = System.nanoTime();
            elapsedQuick = endTime - startTime;

            startTime = System.nanoTime();
            List<Archer> sortedArchersCollection = ChampionSelector.collectionSort(unsortedArchersForCollection, comparator);
            endTime = System.nanoTime();
            elapsedCollection = endTime - startTime;

            results.put(numberOfArchers, elapsedQuick/1000000);
            System.out.println("Sorted " + numberOfArchers + " archers.");
            System.out.println("Quick sort time: " + elapsedQuick + " nanoseconds or " + elapsedQuick/1000000 + " milliseconds");
            System.out.println("Collection sort time: " + elapsedCollection + " nanoseconds or " + elapsedCollection/1000000 + " milliseconds");
            System.out.println("=======================================================================================================");
            if (elapsedQuick > elapsedCollection)
                highestElapsedTime = elapsedQuick/1000000;
            else
                highestElapsedTime = elapsedCollection/1000000;
            numberOfArchers = numberOfArchers*2;

            assertEquals(sortedArchersCollection, sortedArchersQuick);
        } while (highestElapsedTime < maxElapsedTime && numberOfArchers <= maxNumberOfArchers);
        List<Integer> list = new ArrayList<>(results.keySet());
        list.sort(Comparator.comparingInt(o -> o));
        list.forEach(key -> System.out.printf("%d%n", key));
        System.out.println("");
        list.forEach(key -> System.out.printf("%d%n", results.get(key)));
    }

    @Test
    public void timeQuickSortIterator() {
        Map<Integer, Long> results = new HashMap<>();
        int numberOfArchers = 100;
        int maxNumberOfArchers = 5000000;

        long highestElapsedTime = 0;
        long maxElapsedTime = 20000;

        do {
            long elapsedSelIns = 0;
            long elapsedCollection = 0;
            long startTime = 0;
            long endTime = 0;

            List<Archer> unsortedArchersForQuick = Archer.generateArchers(numberOfArchers);
            Iterator<Archer> unsortedArchersIterator = unsortedArchersForQuick.iterator();
            List<Archer> unsortedArchersForCollection = new ArrayList<>(unsortedArchersForQuick);

            startTime = System.nanoTime();
            Iterator<Archer> sortedArchersQuick = ChampionSelector.quickSort(unsortedArchersIterator, comparator);
            endTime = System.nanoTime();
            elapsedSelIns = endTime - startTime;

            startTime = System.nanoTime();
            List<Archer> sortedArchersCollection = ChampionSelector.collectionSort(unsortedArchersForCollection, comparator);
            endTime = System.nanoTime();
            elapsedCollection = endTime - startTime;

            results.put(numberOfArchers, elapsedSelIns/1000000);
            System.out.println("Sorted " + numberOfArchers + " archers.");
            System.out.println("Quick sort time: " + elapsedSelIns + " nanoseconds or " + elapsedSelIns/1000000 + " milliseconds");
            System.out.println("Collection sort time: " + elapsedCollection + " nanoseconds or " + elapsedCollection/1000000 + " milliseconds");
            System.out.println("=======================================================================================================");
            if (elapsedSelIns > elapsedCollection)
                highestElapsedTime = elapsedSelIns/1000000;
            else
                highestElapsedTime = elapsedCollection/1000000;
            numberOfArchers = numberOfArchers*2;
        } while (highestElapsedTime < maxElapsedTime && numberOfArchers <= maxNumberOfArchers);
        List<Integer> list = new ArrayList<>(results.keySet());
        list.sort(Comparator.comparingInt(o -> o));
        list.forEach(key -> System.out.printf("%d%n", key));
        System.out.println("");
        list.forEach(key -> System.out.printf("%d%n", results.get(key)));
    }


    @Test
    public void timeAllSorts(){
        int numberOfArchers = 100;
        int maxNumberOfArchers = 5000000;

        long highestElapsedTimeSelIns = 0;
        long highestElapsedTimeQuick = 0;
        long highestElapsedTimeCollection = 0;
        long maxElapsedTime = 20000;

        do{
            List<Archer> unsortedArchersForSelect = Archer.generateArchers(numberOfArchers);
            List<Archer> unsortedArchersQuick = new ArrayList<>(unsortedArchersForSelect);
            List<Archer> unsortedArchersCollect = new ArrayList<>(unsortedArchersForSelect);

            System.out.println("Sorted " + numberOfArchers + " archers.");

            if (highestElapsedTimeSelIns/1000000 < maxElapsedTime) {
                highestElapsedTimeSelIns = sort(unsortedArchersForSelect, 1);
                System.out.println("Selection sort time: " + highestElapsedTimeSelIns + " nanoseconds or " + highestElapsedTimeSelIns/1000000 + " milliseconds");
            }
            else
                System.out.println("No selection sort, too much time");
            if (highestElapsedTimeQuick/1000000 < maxElapsedTime) {
                highestElapsedTimeQuick = sort(unsortedArchersQuick, 2);
                System.out.println("Quick sort time: " + highestElapsedTimeQuick + " nanoseconds or " + highestElapsedTimeQuick/1000000 + " milliseconds");
            }
            else
                System.out.println("No quick sort, too much time");
            if (highestElapsedTimeCollection/1000000 < maxElapsedTime) {
                highestElapsedTimeCollection = sort(unsortedArchersCollect, 3);
                System.out.println("Collection sort time: " + highestElapsedTimeCollection + " nanoseconds or " + highestElapsedTimeCollection/1000000 + " milliseconds");
            }
            else
                System.out.println("No collection sort, too much time");

            System.out.println("=======================================================================================================");

            assertEquals(unsortedArchersForSelect, unsortedArchersCollect);
            assertEquals(unsortedArchersQuick, unsortedArchersCollect);

            numberOfArchers*=2;
        }while(numberOfArchers < maxNumberOfArchers);
    }

    public long sort(List<Archer> archers, int sort){

        long start = 0;
        long end = 0;

        switch (sort){
            case 1:
                start = System.nanoTime();
                ChampionSelector.selInsSort(archers, comparator);
                end = System.nanoTime();
                break;
            case 2:
                start = System.nanoTime();
                ChampionSelector.quickSort(archers, comparator);
                end = System.nanoTime();
                break;
            case 3:
                start = System.nanoTime();
                ChampionSelector.collectionSort(archers, comparator);
                end = System.nanoTime();
                break;
        }

        return end-start;
    }

    @Test
    public void archerIteratorTest(){
        long nrOfArchers = 20;
        Iterator<Archer> archerIterator = Archer.generateArchers(nrOfArchers);
        ChampionSelector.quickSort(archerIterator, comparator);
        assertFalse(archerIterator.hasNext());
    }
}
