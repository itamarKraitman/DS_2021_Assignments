package Q1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MaxHeap {

    /**
     * @author Asaly Saed Maximum heap class - Data structures course, Ariel
     * University - 2020.
     */

    int[] arr;
    int last;
    int size;

    public MaxHeap(int size) {
        this.size = size;
        arr = new int[size];
        last = -1;
    }

    public boolean Add(int data) {
        if (last == size - 1)
            return false;
        arr[++last] = data;
        Heapify_Up(last);
        return true;
    }

    private void Heapify_Up(int pos) {
        if (pos == 0)
            return;
        int parent = (int) (Math.floor((pos - 1) / 2));
        if (arr[pos] > arr[parent]) {
            swap(arr, pos, parent);
            Heapify_Up(parent);
        }

    }

    private void swap(int[] arr, int from, int to) {
        int tmp = arr[from];
        arr[from] = arr[to];
        arr[to] = tmp;
    }

    public int Delete() {
        if (last != 0) {
            int tmp = arr[0];
            swap(arr, 0, last--);
            Heapify_down(0);
            return tmp;
        } else if (last == 0) {
            int tmp = arr[0];
            last = -1;
            return tmp;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    private void Heapify_down(int pos) {
        int left, right;
        left = 2 * pos + 1;
        right = 2 * pos + 2;
        if ((left == last) && ((arr[pos] < arr[left]))) {
            swap(arr, pos, left);
            return;
        }
        if ((right == last) && (arr[right] > arr[left]) && (arr[pos] < arr[right])) {
            swap(arr, pos, right);
            return;
        } else if ((right == last) && (arr[right] < arr[left]) && (arr[pos] < arr[left])) {
            swap(arr, pos, left);
            return;
        }
        if (left >= last || right >= last) {
            return;
        }
        if ((arr[left] > arr[right]) && (arr[pos] <= arr[left])) {
            swap(arr, pos, left);
            Heapify_down(left);
        } else if (arr[pos] < arr[right]) {
            swap(arr, pos, right);
            Heapify_down(right);
        }
    }

    public static void HeapSort(int[] arr) {
        MaxHeap h = new MaxHeap(arr.length);
        for (int i = 0; i < arr.length; i++) {
            h.Add(arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {
            h.Delete();
            System.out.println(Arrays.toString(h.arr));
        }
        for (int i = 0; i < arr.length; i++)
            arr[i] = h.arr[i];
    }

    //**Q1b**
    //O(n+k) + O(n) + O(k), O(n)
    public static MaxHeap mergeTwoHeaps(MaxHeap h1, MaxHeap h2) {
        MaxHeap ansHeap = new MaxHeap(h1.size + h2.size);//array represent the new heap
//        pointers to run threw h1 and h2, starting from 0
        int h1Iterator = 0;
        int h2Iterator = h1.size;
//        adding elements from both max heaps arrays to one array
        while (h1Iterator < h1.size) {//O(n)
            ansHeap.arr[h1Iterator] = h1.arr[h1Iterator];
            h1Iterator++;
            ansHeap.last++;
        }
        h1Iterator = 0;
        while (h2Iterator < ansHeap.size) {//O(k)
            ansHeap.arr[h2Iterator] = h2.arr[h1Iterator];
            h2Iterator++;
            h1Iterator++;
            ansHeap.last++;
        }
//        building max heap from the array, we proved in the lecture it takes O(arr.length)
        for (int i = ansHeap.arr.length / 2 - 1 ; i >= 0; i--) {//O(n+k)
            ansHeap.Heapify_down(i);
        }
        return ansHeap;
    }

    //**Q1c**
    //O(nlogn)
    public static int[] getMinHeap(MaxHeap h) {
        Arrays.sort(h.arr);//O(nlogn)
        int[] minHeap = Arrays.copyOf(h.arr, h.arr.length);//O(n)
        return minHeap;
    }


    public int[] getArr() {
        return this.arr;
    }

}

