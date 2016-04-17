package MergeAndHeap;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Prior {



    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int inputSize = sc.nextInt();
        ArrayList<Integer> array = new ArrayList<>();

        for (int i = inputSize; i > 0; i--) {
            array.add(i);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(array);
        int[] output = new int[inputSize];
        long startTime = System.currentTimeMillis();
        int i=0;
        while (!pq.isEmpty()) {
            output[i] = pq.remove();
            i++;
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Time for Heap sort is " + duration);
        boolean isProper = true;
        for (i = 0; i < output.length - 1; i++) {
            if (!(output[i] <= output[i + 1])) {
                isProper = false;
                break;
            }
        }
        System.out.println(isProper);
        sc.close();

    }

}