package nl.hva.ict.se.ads;

import org.junit.jupiter.api.Test;

import java.util.*;

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

    @Test
    public void timeQuickSort() {
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
            List<Archer> unsortedArchersForCollection = new ArrayList<>(unsortedArchersForQuick);

            startTime = System.nanoTime();
            List<Archer> sortedArchersQuick = ChampionSelector.quickSort(unsortedArchersForQuick, comparator);
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

}
