package nl.hva.ict.se.ads;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a list of Archer's this class can be used to sort the list using one of three sorting algorithms.
 */
public class ChampionSelector {

    public static void main(String[] args) {
        List<Archer> archers = Archer.generateArchers(20);
        System.out.println(archers.stream().map(Archer::getTotalScore).collect(Collectors.toList()));
        quickSort(archers, (o1, o2) -> {
            if (o1.getTotalScore() == o2.getTotalScore()) {
                if (o1.getWeightedScore() == o2.getWeightedScore()) {
                    return o1.getId() - o2.getId();
                }
                return o1.getWeightedScore() - o2.getWeightedScore();
            }
            return o1.getTotalScore() - o2.getTotalScore();
        });
        System.out.println(archers.stream().map(Archer::getTotalScore).collect(Collectors.toList()));
    }

    private static <T> void swap(List<T> array, int index1, int index2) {
        if (index1 < 0 || index2 < 0 || index1 == index2 || array.size() <= index1 || array.size() <= index2) {
            return;
        }
        T temp = array.get(index1);
        array.set(index1, array.get(index2));
        array.set(index2, temp);
    }
    /**
     * This method uses either selection sort or insertion sort for sorting the archers.
     */
    public static List<Archer> selInsSort(List<Archer> archers, Comparator<Archer> scoringScheme) {
        return archers;
    }

    /**
     * This method uses quick sort for sorting the archers.
     */
    @SuppressWarnings("UnusedReturnValue")
    public static List<Archer> quickSort(List<Archer> archers, Comparator<Archer> scoringScheme) {
        return quick(archers, 0, archers.size() - 1, scoringScheme);
    }

    private static <T extends Comparable<T>> List<T> quick(List<T> array, int from, int to, Comparator<T> scoringScheme) {
        if (from < to) {
            // pick pivot at end of array
            T pivot = array.get(to);
            // setup counter to find swap position
            int i = (from - 1);

            // loop through elements starting at 'from' ending before 'to'
            for (int j = from; j < to; j++) {
                T current = array.get(j);
                // if the current iteration is smaller than pivot, we must swap
                if (scoringScheme == null ? current.compareTo(pivot) < 0 : scoringScheme.compare(current, pivot) < 0) {
                    // increase swap location to be at correct position in array
                    i++;
                    // swap found swap position and current element
                    swap(array, i, j);
                }
            }

            // swap last swap position with pivot
            i++;
            swap(array, i, to);

            // repeat for left and right of pivot
            quick(array, from, i - 1, scoringScheme);
            quick(array, i + 1, to, scoringScheme);
        }
        return array;
    }

    /**
     * This method uses the Java collections sort algorithm for sorting the archers.
     */
    public static List<Archer> collectionSort(List<Archer> archers, Comparator<Archer> scoringScheme) {
        archers.sort(scoringScheme);
        return archers;
    }

    /**
     * This method uses quick sort for sorting the archers in such a way that it is able to cope with an Iterator.
     *
     * <b>THIS METHOD IS OPTIONAL</b>
     */
    public static Iterator<Archer> quickSort(Iterator<Archer> archers, Comparator<Archer> scoringScheme) {
        return null;
    }

}
