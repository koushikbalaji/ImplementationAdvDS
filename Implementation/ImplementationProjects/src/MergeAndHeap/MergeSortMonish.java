package MergeAndHeap;
import java.util.ArrayList;


public class MergeSortMonish {


/**
 * Created by Monish Kumar on 1/14/16.
 * FileName - MergeSort.java
 * Class - MergeSort
 * Description - Has a method to sort an arrayList in-place
 *
 */




    /**
     * Method - Sort
     * Parameters - arrayList
     * Description - Uses merge sort algorithm to sort the given array in-place
     *
     */
    public static<T extends Comparable<? super T>> void Sort(ArrayList<T> array)
    {
        long arraySize = array.size();
        if(arraySize > 1) {
            ArrayList<T> leftHalf = new ArrayList<>();
            ArrayList<T> rightHalf = new ArrayList<>();

            for(int i=0; i< arraySize/2; i++)
                leftHalf.add(array.get(i));
            for(int j=0; j< (arraySize - (arraySize/2)); j++)
                rightHalf.add(array.get((int)(j + arraySize/2)));

            Sort(leftHalf);
            Sort(rightHalf);

            Merge(array, leftHalf, rightHalf);
        }
    }

    /**
     * Method - Merge
     * Parameters - result, left, right
     * Description - The "Merge" part of the MergeSort algorithm
     *
     */
    public static <T extends Comparable<? super T>> void Merge(ArrayList<T> result, ArrayList<T> left, ArrayList<T> right) {
        int leftIndex = 0, rightIndex = 0;
        for(int resultIndex = 0; resultIndex < result.size(); resultIndex++) {
            if( rightIndex >= right.size() || (( leftIndex < left.size() && (left.get(leftIndex).compareTo(right.get(rightIndex)) == -1)))){
                result.set(resultIndex, left.get(leftIndex));
                leftIndex++;
            }
            else {
                result.set(resultIndex, right.get(rightIndex));
                rightIndex++;
            }
        }
    }

    /**
     * Method - Test Method
     * Description - Test the basic functionality of the class
     *
     */
    public static void TestMergeSort() {
        ArrayList<Long> arrayList = new ArrayList<>();
        for(int i=1000000; i<0; i--)
            arrayList.add((long)(i));
        System.out.println("MergeSort");
        System.out.println("Before Sorting");
        System.out.println(arrayList);
        Sort(arrayList);
        System.out.println("After Sorting");
        System.out.println(arrayList);
    }

    public static void main(String[] args) {
        TestMergeSort();
    }

}
