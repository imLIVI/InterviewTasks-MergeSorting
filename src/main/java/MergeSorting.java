import java.util.Arrays;

public class MergeSorting {
    /**
     * Program invariant: the array after "merge" (function) - sorted
     * <p>
     * Program complexity:
     * ---- SIZE OF ARRAY after each recursion call ----
     * 1-st level of recursion:                        (0...n)                                   sum = n
     * /            \
     * 2-nd level of recursion:            (0...n/2)              (n/2...n)                      sum = n
     * /    \               /          \
     * 3-nd level of recursion:    (0...n/4)    (n/4...n/2)  (n/2...3n/4)    (3n/4...n)          sum = n
     * ...
     * On each level the complexity is const = O(n)
     * <p>
     * Number of levels = log2(n)
     * <p>
     * => So program complexity = O (n*log2(n))
     * <p>
     * But using memory = O(n) (for function "merge")
     */
    public static void mergeSorting(int[] array, int len) {

        // Basic case
        if (len < 2) {
            return;
        }

        // Looking for a middle of array
        int middle = len / 2;

        // Divide array on two arrays
        int[] left = new int[middle];
        int[] right = new int[len - middle];

        // Filling arrays
        for (int i = 0; i < middle; i++) {
            left[i] = array[i];
        }
        for (int j = middle; j < len; j++) {
            right[j - middle] = array[j];
        }

        // Recursion
        mergeSorting(left, middle);
        mergeSorting(right, len - middle);

        // The most difficult operation
        merge(array, left, right, middle, len - middle);
    }

    private static void merge(int[] array, int[] left, int[] right, int lenL, int lenR) {
        int l = 0, r = 0, m = 0;

        while (l < lenL && r < lenR) {
            if (left[l] <= right[r])
                array[m++] = left[l++];
            else
                array[m++] = right[r++];
        }

        while (l < lenL)
            array[m++] = left[l++];

        while (r < lenR)
            array[m++] = right[r++];

    }
}