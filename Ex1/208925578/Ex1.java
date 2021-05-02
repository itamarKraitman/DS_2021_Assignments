public class Ex1 {
    /*
        Itamar Kraitman I.D 208925578
    Ex 4
    finding if there is a pair of integers in a which its sum equals to s -complexity O(n)
    */
    public static boolean pairs(int[] a, int s) {
        int i = 0;
        int j = a.length - 1;
        while (i < j) {
            if (a[i] + a[j] == s)
                return true;
            else if (a[i] + a[j] > s) {
                j--;
            } else
                i++;
        }
        return false;
    }


    /*
        Ex 5
    finding if there is a triple in a which its sum equals to s - complexity O(n^2)
    */
    public static boolean triples(int[] a, int s) {
        int[] withoutCurrent = new int[a.length - 1];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (j != i)
                    withoutCurrent[j - 1] = a[j];
            }
            boolean ans = pairs(withoutCurrent, s - a[i]);
            if (ans)
                return true;
        }
        return false;
    }

    /* finding if there is a triple in a which its sum equals to s without temporary array- complexity O(n^2) */
    public static boolean Triples(int[] a, int s) {
        for (int i = 0; i < a.length; i++) {
            int j = i + 1;
            int w = a.length - 1;
            while (j < w){
                if(a[i] + a[j] + a[w] == s)
                    return true;
                else if (a[i] + a[j] + a[w] > s){
                    w--;
                }
                else
                    j++;
            }
        }
        return false;
    }
}
