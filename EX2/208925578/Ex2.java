import java.util.LinkedList;

//Assignment 2 DS semester 2021B
//Itamar Kraitman
//I.D 208925578
public class Ex2 {
    //Question 3.1- iterative mergeSort - O(nlogn)
    public static void mergeSort2(int[] arr) {
		if (arr.length > 0) {
			int mid;
			int end;
			for (int subArrLength = 1; subArrLength <= arr.length; subArrLength *= 2) {//O(logn)
				for (int start = 0; start <= arr.length; start += subArrLength * 2) {//O(n/2)
					if (arr.length - 1 < subArrLength * 2 + start - 1)
						end = arr.length - 1;
					else
						end = subArrLength * 2 + start - 1;
					if (arr.length - 1 < subArrLength + start - 1)
						mid = arr.length - 1;
					else
						mid = subArrLength + start - 1;
					Merge(arr, start, mid, end, (int) Math.floor(Math.sqrt(Integer.MAX_VALUE)));//O(n)
				}
			}
		}
		else
			throw new IllegalArgumentException("Length 0 is not allowed");
	}

    //Question 3.2
    //assuming values are lower than sqrt Integer.MAX_VALUE
    //assuming max_num is the highest value in array and all values are lower than sqrt
    //complexity O(n) with space complexity of O(1), without temporary array
    //for this question i was helped with geeksforgeeks site, only for calculation help.
    private static void Merge(int[] arr, int start, int middle, int end, int max_num) {
        max_num = (int) Math.floor(Math.sqrt(Integer.MAX_VALUE));
        int i = start;
        int j = middle + 1;
        int k = start; // running pointer over the array
        while (i <= middle && j <= end) {
            if (arr[i] % max_num < arr[j] % max_num) {
                arr[k] = (int) (arr[k] + (arr[i] % max_num) * max_num);
                i++;
            } else {
                arr[k] = (int) (arr[k] + (arr[j] % max_num) * max_num);
                j++;
            }
            k++;
        }
        //inserting rest values
        while (i <= middle) {
            arr[k] = (int) (arr[k] + (arr[i] % max_num) * max_num);
            i++;
            k++;
        }
        while (j <= end) {
            arr[k] = (int) (arr[k] + (arr[j] % max_num) * max_num);
            j++;
            k++;
        }
        for (i = start; i <= end; i++)//returning to original value
            arr[i] = (int) (arr[i] / max_num);

    }
    //Question 3.3
	//Merging LinkedList array into one sorted LinkedList. complexity O(NMlogM)
    public static LinkedList<Integer> join(LinkedList<Integer>[] arr) {
        if (arr.length == 0)
            throw new IllegalArgumentException("length 0 is not allowed");
        LinkedList<Integer> answer = new LinkedList<>();
        for (int i = 0; i < Math.log(arr.length); i++) {//O(log M)
			for (int j = 0; j < arr.length - 1; j++) {//O(M)
				LinkedList<Integer> temp = mergeLists(arr[j], arr[j + 1]);//O(N)
				answer = mergeLists(answer, temp);//O(c*N)<=O(N)
			}
		}
        return  answer;
    }
    //merging two LinkedLists. O(N)
    private static LinkedList<Integer> mergeLists(LinkedList<Integer> first, LinkedList<Integer> second) {
		LinkedList<Integer> answer = new LinkedList<>();
		if (first.size()==0)
		    return second;
		if (second.size()==0)
		    return  first;
		while (first.size() != 0 && second.size() != 0) {
            if (first.getFirst() < second.getFirst()) {
                answer.addLast(first.getFirst());
                first.removeFirst();
            }
            else {
            	answer.addLast(second.getFirst());
            	second.removeFirst();
			}
        }
        while (first.size() != 0){
			answer.addLast(first.getFirst());
			first.removeFirst();
		}
        while (second.size() != 0){
			answer.addLast(second.getFirst());
			second.removeFirst();
		}
        return answer;
    }


    //Question 4. required complexity - O(n)
    public static int diff(int[] arr) {
		if (arr.length > 0) {
			countingSort(arr);//O(n+k), worst case O(2n)
			int maxDiff = Math.abs(arr[0] - arr[1]);
			for (int i = 1; i < arr.length; i++) {//O(n)
				if (Math.abs(arr[i] - arr[i - 1]) > maxDiff)
					maxDiff = Math.abs(arr[i] - arr[i - 1]);
			}
			return maxDiff;
		}
		else
			throw new IllegalArgumentException("length 0 is not allowed");
	}
    //Sorting the array using countingSort - O(n+k), worst case O(2n)
    private static void countingSort(int[] arr) {
        int[] counter = new int[findMax(arr) + 1];
        for (int i = 0; i < arr.length; i++) {
            counter[arr[i]]++;
        }
        int index = 0;
        for (int i = 0; i < counter.length; i++) {
            for (int j = 0; j < counter[i]; j++)
                arr[index++] = i;
        }
    }

    //finding max value in arr, will be length of counter
    private static int findMax(int[] arr) {//O(n)
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        return max;
    }
}

