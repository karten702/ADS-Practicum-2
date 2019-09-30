package nl.hva.ict.se.ads;

import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

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
//        List<Archer> myArchers = new ArrayList<>();
//        //List<Archer> unordered = new ArrayList<>();
//        Archer pivotArcher = archers.next();
//        myArchers.add(pivotArcher);
//        //unordered.add(pivotArcher);
//
//        while (archers.hasNext()){
//            Archer nextArcher = archers.next();
//            //unordered.add(nextArcher);
//            if (scoringScheme.compare(nextArcher, pivotArcher) >= 0)
//                myArchers.add(nextArcher);
//            else
//                myArchers.add(myArchers.indexOf(pivotArcher), nextArcher);
//            //Quicksort.quickSort(myArchers, 0, myArchers.size()-1, scoringScheme);
//            //myArchers.add(nextArcher);
//        }
//
//        int pivotIndex = myArchers.indexOf(pivotArcher);
//        Quicksort.quickSortWiki(myArchers, 0, pivotIndex, scoringScheme);
//        Quicksort.quickSortWiki(myArchers, pivotIndex, myArchers.size()-1, scoringScheme);
//
//        //Quicksort.quickSort(myArchers, 0, myArchers.size()-1, scoringScheme);
//        //Quicksort.quickSort(myArchers, scoringScheme);
//        //Quicksort.quickSortWiki(myArchers, 0, myArchers.size()-1, scoringScheme);

        Stack<Archer> archerStack = new Stack<>();
        Archer pivotArcher = archers.next();
        archerStack.push(pivotArcher);

        while(archers.hasNext()){
            Archer nextArcher = archers.next();
            if (scoringScheme.compare(nextArcher, pivotArcher) >= 0){
                archerStack.push(nextArcher);
            }
            else
                archerStack.insertElementAt(nextArcher, archerStack.indexOf(pivotArcher));
        }
        int indexOfPivot = archerStack.indexOf(pivotArcher);
        //Quicksort.quickSortStack(archerStack, 0, indexOfPivot, scoringScheme);
        Quicksort.quickSortStack(archerStack, 0, archerStack.size()-1, scoringScheme);

//        Stack archerStack = new Stack();
//        archerStack.push(0);
//        archerStack.push((int)archerIterator.max);
//
//        while (!archerStack.isEmpty()){
//            int end = (int) archerStack.pop();
//            int start = (int) archerStack.pop();
//            if (end - start < 2){
//                continue;
//            }
//            int pivot = start + ((end-start)/2);
//            //pivot = Partition.partition(archerIterator, pivot, start, end, scoringScheme);
//            archerStack.push(pivot+1);
//            archerStack.push(end);
//            archerStack.push(start);
//            archerStack.push(pivot);
//        }

        return archers;
    }

}
