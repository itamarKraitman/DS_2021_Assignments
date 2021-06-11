package Q2;

import Q2.Ex4;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class Ex4_test {
    @Test
    void q2Test(){

        //case 1: unique partitions of 12
        int []a = {1,2,3,4,5,6,7,8,9,10,11,12};
        int t = 12;
        int[][] exp ={{1,2,9},{1,3,8},{1,4,7},{1,5,6},{2,3,7},{2,4,6},{3,4,5}};

        List<int[]> l = Ex4.ThreeSum(a,t);
        int[][] temp = new int[l.size()][3];
        int counter = 0;

        for(int[] arg: l)
            temp[counter++] = arg;

        assertTrue(isIn(exp,temp));

        //case 2: negative numbers

        int []a1 = {-3,2,7,-300,13};
        int t1 = 12;
        int[][] exp1 ={{-3,2,13}};
        counter = 0;
        List<int[]> l1 = Ex4.ThreeSum(a1,t1);
        int[][] temp1 = new int[l1.size()][3];
        for(int[] arg: l1)
            temp1[counter++] = arg;

        assertTrue(isIn(exp1,temp1));

        //case 3: no results

        int []a2 = {9,2,1,5,-4,11};
        int t2 = 92;
        List<int[]> l2 = Ex4.ThreeSum(a2,t2);
        assertTrue(l2.size() == 0);

        //case 4: zeros - another no results case
        int []a3 = {0,0,0,0,0,0,0};
        int t3 = 92;
        List<int[]> l3 = Ex4.ThreeSum(a3,t3);
        assertTrue(l3.size() == 0);

        //case 5: target = 8

        int []a4 = {2,7,-1,-3,4,3,1};
        int t4 = 8;
        int exp4[][] = {{-1,2,7},{1,3,4},{-3,4,7}};
        List<int[]> l4 = Ex4.ThreeSum(a4,t4);
        counter = 0;
        int[][] temp4 = new int[l4.size()][3];
        for(int[] arg: l4)
            temp4[counter++] = arg;

        assertTrue(isIn(exp4,temp4));

        //case 6: duplicate values
        int []a5 = {2,7,-1,-3,4,3,1,2,7,-1,4,3,1,4,3,1};
        int t5 = 8;
        int exp5[][] = {{-1,2,7},{1,3,4},{-3,4,7}};

        List<int[]> l5 = Ex4.ThreeSum(a4,t4);
        counter = 0;
        int[][] temp5 = new int[l4.size()][3];
        for(int[] arg: l5)
            temp5[counter++] = arg;

        assertTrue(isIn(exp5,temp5));



    }
    static boolean isIn(int exp[][], int result[][]){

        //check if the two groups of arrays are identical

        for(int[] arg: exp){
            if(!(Arrays.stream(result).anyMatch(line -> Arrays.equals(line,arg))))
                return false;
        }

        for(int[] arg: result){
            if(!(Arrays.stream(exp).anyMatch(line -> Arrays.equals(line,arg))))
                return false;
        }

        return true;
    }
}
