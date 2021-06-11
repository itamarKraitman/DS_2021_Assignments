package Q1;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class maxHeap_test {

    static boolean isMaxHeap(int arr[]) {
        int left = 0, right = 0;
        for (int i = 0; i <= (arr.length / 2) - 1; i++) {
            left = 2 * i + 1;
            right = 2 * i + 2;
            if (left <= arr.length - 1 && arr[left] > arr[i])
                return false;
            if (right <= arr.length - 1 && arr[right] > arr[i])
                return false;

        }
        return true;
    }

    //return true if the array represent a min heap
    static boolean isMinHeap(int arr[]) {

        int left = 0, right = 0;
        for (int i = 0; i <= (arr.length / 2) - 1; i++) {
            left = 2 * i + 1;
            right = 2 * i + 2;
            if (left <= arr.length - 1 && arr[left] < arr[i])
                return false;
            if (right <= arr.length - 1 && arr[right] < arr[i])
                return false;
        }
        return true;

    }

    @Test
    void testMergeTwoHeaps() {
        Random rnd = new Random();
        MaxHeap m1 = new MaxHeap(rnd.nextInt(20));
        for (int j = 0; j < 30; j++) {
            for (int i = 0; i < m1.size; i++) {
                m1.Add(rnd.nextInt(60));
            }
            MaxHeap m2 = new MaxHeap(rnd.nextInt(20));
            for (int it = 0; it < m2.size; it++) {
                m2.Add(rnd.nextInt(60));
            }
            assertTrue(isMaxHeap(MaxHeap.mergeTwoHeaps(m1, m2).getArr()));
        }
    }

    @Test
    void testGetMinHeap() {
        Random rnd = new Random();
        MaxHeap m1 = new MaxHeap(rnd.nextInt(20));
        for (int j = 0; j < rnd.nextInt(50); j++) {
            for (int i = 0; i < m1.size; i++) {
                m1.Add(rnd.nextInt(60));
            }
            assertTrue(isMinHeap(MaxHeap.getMinHeap(m1)));
        }
    }

    @Test
    void isMaxheapTest() {

        int a1[] = {1, 2, 3, 4, 5, 6};
        int a2[] = {1, 2};
        assertFalse(isMaxHeap(a1));
        assertFalse(isMaxHeap(a2));

        int[] a3 = {12, 10, 9, 2, 5, 7, 6};
        assertTrue(isMaxHeap(a3));
        int[] a4 = {20, 15, 8, 10, 5, 7, 6, 2, 9, 1};
        assertTrue(isMaxHeap(a4));
    }

    @Test
    void isMinHeapTest() {

        int[] m1 = {1, 2, 3, 4, 5, 6};
        int[] m2 = {2, 3, 2};
        int[] m3 = {1, 1, 1, 4, 5, 2, 1};

        assertTrue(isMinHeap(m1));
        assertTrue(isMinHeap(m2));
        assertTrue(isMinHeap(m3));

        int[] n1 = {4, 3, 2, 1};
        int[] n2 = {4, 2, 11, 0};
        assertFalse(isMinHeap(n1));
        assertFalse(isMinHeap(n2));
    }

    @Test
    void mergeTest() {

        MaxHeap m1 = new MaxHeap(5);
        m1.Add(1);
        m1.Add(2);
        m1.Add(3);
        m1.Add(4);
        m1.Add(5);

        MaxHeap m2 = new MaxHeap(3);
        m2.Add(6);
        m2.Add(7);
        m2.Add(8);


        MaxHeap m3 = MaxHeap.mergeTwoHeaps(m1, m2);
        MaxHeap m4 = MaxHeap.mergeTwoHeaps(m1, m3);
        MaxHeap m5 = MaxHeap.mergeTwoHeaps(m4, m3);

        assertTrue(isMaxHeap(m3.arr));
        assertTrue(isMaxHeap(m4.arr));
        assertTrue(isMaxHeap(m5.arr));

        MaxHeap zero1 = new MaxHeap(2);
        zero1.Add(0);
        zero1.Add(0);
        MaxHeap zero2 = new MaxHeap(1);
        zero2.Add(0);

        MaxHeap z = MaxHeap.mergeTwoHeaps(zero1, zero2);
        assertTrue(isMaxHeap(z.arr));

        //semi-full heaps
        MaxHeap sm = new MaxHeap(8);
        sm.Add(6);
        sm.Add(7);
        sm.Add(8);

        MaxHeap sm1 = new MaxHeap(7);
        sm1.Add(7);
        sm1.Add(3);
        sm1.Add(2);

        MaxHeap sm2 = MaxHeap.mergeTwoHeaps(sm, sm1);
        assertTrue(isMaxHeap(sm2.arr));


    }

    @Test
    void getMinHeapTests() {

        MaxHeap m1 = new MaxHeap(5);
        m1.Add(1);
        m1.Add(2);
        m1.Add(0);
        m1.Add(11);
        m1.Add(2);

        MaxHeap m2 = new MaxHeap(3);
        m2.Add(6);
        m2.Add(7);
        m2.Add(1);


        MaxHeap.getMinHeap(m2);

        MaxHeap m3 = new MaxHeap(1);
        m3.Add(6);

        MaxHeap m4 = new MaxHeap(5);
        m4.Add(0);
        m4.Add(0);
        m4.Add(0);
        m4.Add(0);
        m4.Add(0);
        MaxHeap m5 = MaxHeap.mergeTwoHeaps(m2, m4);


        assertTrue(isMinHeap(MaxHeap.getMinHeap(m1)));
        assertTrue(isMinHeap(MaxHeap.getMinHeap(m2)));
        assertTrue(isMinHeap(MaxHeap.getMinHeap(m3)));
        assertTrue(isMinHeap(MaxHeap.getMinHeap(m4)));
        assertTrue(isMinHeap(MaxHeap.getMinHeap(m5)));

    }
}

