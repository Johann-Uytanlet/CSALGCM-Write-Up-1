
//Monkey's Paw
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    // the code for MergeSort was taken from GeeksforGeeks and was partially
    // modified to fit the problem.
    static void merge(int arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /* Copy data to temp arrays */
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] >= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    static void sort(int arr[], int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine().trim());
        int[][] movies = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().trim().split("\\s+");
            movies[i][0] = Integer.parseInt(parts[0]);
            movies[i][1] = Integer.parseInt(parts[1]);
        }
        int q = Integer.parseInt(br.readLine().trim());
        for (int cc = 0; cc < q; cc++) {
            String[] parts = br.readLine().trim().split("\\s+");
            int s = Integer.parseInt(parts[0]);
            int e = Integer.parseInt(parts[1]);
            int a = Integer.parseInt(parts[2]);
            int k = Integer.parseInt(parts[3]);
            // compute for answer and append to StringBuilder to prevent TLE
            // check if Richie has enough money to buy a ticket
            if (a < k) {
                sb.append(-1);
                sb.append(System.getProperty("line.separator"));
            } else {
                // create temp array
                int temp[] = new int[(e - s) + 1]; // add 1 because 0-based indices

                for (int j = 0; j < temp.length; j++) {
                    // get the true runtime of each
                    // System.out.print(">>" + (s+j) + "\n");
                    temp[j] = movies[s + j][0] - movies[s + j][1];
                }
                sort(temp, 0, temp.length - 1);

                // debug: show temp
                /*
                 * for(int l = 0; l < temp.length; l++) {
                 * System.out.print("$$ " + temp[l] + "\n");
                 * }
                 */
                // get up until which index can be seen
                int last_index = a / k;

                if (last_index <= temp.length) {
                    sb.append(temp[last_index - 1]);
                    sb.append(System.getProperty("line.separator"));

                } else {
                    sb.append(temp[temp.length - 1]);
                    sb.append(System.getProperty("line.separator"));
                }

            }

        }
        System.out.print(sb);
    }
}
