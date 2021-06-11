package Q2;

import java.util.*;

public class Ex4 {
    public static List<int[]> ThreeSum(int[] arr, int target) {
        HashSet<HashSet<Integer>> triples = new HashSet<>();
        LinkedList<int[]> answer = new LinkedList<>();
        HashSet<Integer> arrAsHash = new HashSet<>();
        Arrays.sort(arr);//O(nlogn) average
        for (int p = 0; p < arr.length; p++) {//O(n)
            arrAsHash.add(arr[p]);
        }
        for (int i = 0; i <= arr.length - 3; i++) {//O(n)
            for (int j = i + 1; j < arr.length - 1; j++) {//O(n)
                if (arrAsHash.contains(target - arr[i] - arr[j])) {//O(1)
                    int temp = target - arr[i] - arr[j];
                    if (temp != arr[i] && temp != arr[j] && arr[i] != arr[j]) {
                        HashSet<Integer> matchTriple = new HashSet<>();
                        matchTriple.add(arr[i]);//O(1)
                        matchTriple.add(arr[j]);//O(1)
                        matchTriple.add(temp);//O(1)
                        if (!triples.contains(matchTriple)) {//O(1)
                            triples.add(matchTriple);//O(1)
                            answer.addLast(new int[]{arr[i], arr[j], temp});//O(1)
                        }
                    }
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {//main to test myself
        int[] a = {0, 8, 10, 0, 8, 10, 5, 2, 5, 6, 7};
        List<int[]> answer = ThreeSum(a, 18);
        System.out.println(Arrays.deepToString(answer.toArray()));
        HashSet<Integer> set1 = new HashSet<>();
    }
}
