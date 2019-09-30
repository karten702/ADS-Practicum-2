package nl.hva.ict.se.ads;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Quicksort {

    public static void quickSort(List<Archer> archers, int start, int end, Comparator<Archer> scoringScheme) {
        if (start < end){
            int partIndex = partition(archers, start, end, scoringScheme);

            quickSort(archers, start, partIndex-1, scoringScheme);
            quickSort(archers, partIndex+1, end, scoringScheme);
        }
    }

    public static void quickSortStack(Stack<Archer> archers, int start, int end, Comparator<Archer> scoringScheme) {
        if (start < end){
            int partIndex = partition(archers, start, end, scoringScheme);

            quickSort(archers, start, partIndex-1, scoringScheme);
            quickSort(archers, partIndex+1, end, scoringScheme);
        }
    }

    public static void quickSortWiki(List<Archer> archers, int start, int end, Comparator<Archer> scoringScheme){
//        if (start < end){
//            int partIndex = partition(archers, start, end, scoringScheme);
//
//            quickSort(archers, start, partIndex-1, scoringScheme);
//            quickSort(archers, partIndex+1, end, scoringScheme);
//        }

        int L = start;
        int R = end;
        Archer pivot = archers.get((L+R)/2);

        do{
            while(scoringScheme.compare(archers.get(L), pivot) < 0){
                L++;
            }
            while(scoringScheme.compare(pivot, archers.get(R)) < 0){
                R--;
            }

            if (L <= R){
                swap(archers, L, R);
                L++;
                R--;
            }
        }while(L < R);
        if (start < R)
            quickSortWiki(archers, start, R, scoringScheme);
        if (L < end){
            quickSortWiki(archers, L, end, scoringScheme);
        }
    }

    public static void quickSort(Stack<Archer> archers, Comparator<Archer> scoringScheme){
        Stack archerStack = new Stack();
        archerStack.push(0);
        archerStack.push(archers.size());

        while (!archerStack.isEmpty()){
            int end = (int) archerStack.pop();
            int start = (int) archerStack.pop();
            if (end - start < 2){
                continue;
            }
            int pivot = start + ((end-start)/2);
            pivot = partition(archers, pivot, start, end, scoringScheme);
            archerStack.push(pivot+1);
            archerStack.push(end);
            archerStack.push(start);
            archerStack.push(pivot);
        }
    }

    public static void quickSort(List<Archer> archers, Comparator<Archer> scoringScheme){
        Stack archerStack = new Stack();
        archerStack.push(0);
        archerStack.push(archers.size());

        while (!archerStack.isEmpty()){
            int end = (int) archerStack.pop();
            int start = (int) archerStack.pop();
            if (end - start < 2){
                continue;
            }
            int pivot = start + ((end-start)/2);
            pivot = partition(archers, pivot, start, end, scoringScheme);
            archerStack.push(pivot+1);
            archerStack.push(end);
            archerStack.push(start);
            archerStack.push(pivot);
        }
    }

    private static int partition(List<Archer> archers, int start, int end, Comparator<Archer> scoringScheme){
        Archer pivot = archers.get(end);
        int index = (start-1);

        for (int j = start; j < end; j++){
            if (scoringScheme.compare(archers.get(j), pivot) < 0){
                index++;
                swap(archers, index, j);
            }
        }
        swap(archers, index+1, end);
        return index+1;
    }

    private static int partition(Stack<Archer> archers, int start, int end, Comparator<Archer> scoringScheme){
        Archer pivot = archers.get(end);
        int index = (start-1);

        for (int j = start; j < end; j++){
            if (scoringScheme.compare(archers.get(j), pivot) < 0){
                index++;
                swap(archers, index, j);
            }
        }
        swap(archers, index+1, end);
        return index+1;
    }

    public static int partition(Archer[] input, int pivot, int start, int end, Comparator<Archer> scoringScheme){

        int left = start;
        int right = end-2;
        Archer piv = input[pivot];
        swap(input, pivot, end-1);

        while(left < right){
            if (scoringScheme.compare(input[left], piv) < 0){
                left++;
            }
            else if (scoringScheme.compare(input[right], piv) >= 0){
                right--;
            }
            else
                swap(input, left, right);
        }
        int index = right;
        if (scoringScheme.compare(input[right], piv) < 0)
            index++;
        swap(input, end -1, index);

        return index;
    }

    public static int partition(Stack<Archer> archers, int pivot, int start, int end, Comparator<Archer> scoringScheme){
        int left = start;
        int right = end-2;
        Archer piv = archers.get(pivot);
        swap(archers, pivot, end-1);

        while(left < right){
            if (scoringScheme.compare(archers.get(left), piv) < 0){
                left++;
            }
            else if (scoringScheme.compare(archers.get(right), piv) >= 0){
                right--;
            }
            else
                swap(archers, left, right);
        }
        int index = right;
        if (scoringScheme.compare(archers.get(right), piv) < 0)
            index++;
        swap(archers, end -1, index);

        return index;
    }

    public static int partition(List<Archer> archers, int pivot, int start, int end, Comparator<Archer> scoringScheme){

        int left = start;
        int right = end-2;
        Archer piv = archers.get(pivot);
        swap(archers, pivot, end-1);

        while(left < right){
            if (scoringScheme.compare(archers.get(left), piv) < 0){
                left++;
            }
            else if (scoringScheme.compare(archers.get(right), piv) >= 0){
                right--;
            }
            else
                swap(archers, left, right);
        }
        int index = right;
        if (scoringScheme.compare(archers.get(right), piv) < 0)
            index++;
        swap(archers, end -1, index);

        return index;
    }

    public static void swap(Archer[] archers,int archer1, int archer2){
        Archer tempArcher = archers[archer1];
        archers[archer1] = archers[archer2];
        archers[archer2] = tempArcher;
    }

    public static void swap(List<Archer> archers, int archer1, int archer2){
        Archer tempArcher = archers.get(archer1);
        archers.set(archer1, archers.get(archer2));
        archers.set(archer2, tempArcher);
    }

    public static void swap(Stack<Archer> archers, int archer1, int archer2){
        Archer tempArcher = archers.get(archer1);
        archers.set(archer1, archers.get(archer2));
        archers.set(archer2, tempArcher);
    }
}
