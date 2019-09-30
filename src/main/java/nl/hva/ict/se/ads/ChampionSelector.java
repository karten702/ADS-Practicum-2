package nl.hva.ict.se.ads;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a list of Archer's this class can be used to sort the list using one of three sorting algorithms.
 */
public class ChampionSelector {

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

        for (int i = 0; i < archers.size(); i++){
            int pos = i;
            for (int j = i+1; j < archers.size(); j++){
                if (scoringScheme.compare(archers.get(j), archers.get(pos)) < 0){
                    pos = j;
                }
            }
            Archer smallerArcher = archers.get(pos);
            archers.set(pos, archers.get(i));
            archers.set(i, smallerArcher);
        }

        return archers;
    }

    /**
     * This method uses quick sort for sorting the archers.
     */
    @SuppressWarnings("UnusedReturnValue")
    public static List<Archer> quickSort(List<Archer> archers, Comparator<Archer> scoringScheme) {
        quick(archers, 0, archers.size() - 1, scoringScheme);
        return archers;
    }

    private static <T extends Comparable<T>> void quick(List<T> array, int from, int to, Comparator<T> scoringScheme) {
        if (from < to) {
            int pivot = partition(array, from, to, scoringScheme);

            // repeat for left and right of pivot
            quick(array, from, pivot - 1, scoringScheme);
            quick(array, pivot + 1, to, scoringScheme);
        }
    }

    private static <T extends Comparable<T>> int partition(List<T> array, int from, int to, Comparator<T> scoringScheme) {
        // pick pivot at end of array
        T pivot = array.get(to);
        // setup counter to find swap position
        int i = (from - 1);

        // loop through elements starting at 'from' ending before 'to'
        for (int j = from; j < to; j++) {
            T current = array.get(j);
            // if the current iteration is smaller than pivot, we must swap
            if (scoringScheme.compare(current, pivot) < 0) {
                // increase swap location to be at correct position in array
                i++;
                // swap found swap position and current element
                swap(array, i, j);
            }
        }

        // swap last swap position with pivot
        swap(array, i + 1, to);
        return i + 1;
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
        List<Archer> list = new ArrayList<>();
        archers.forEachRemaining(list::add);
        quick(list, 0, list.size() - 1, scoringScheme);
        return list.iterator();
    }

}
