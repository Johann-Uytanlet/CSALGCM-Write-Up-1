import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.*;
import java.util.*;

public class FriendlyRhythmGame2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String[] parts = br.readLine().trim().split(" ");
        int n = Integer.parseInt(parts[0]);
        int r = Integer.parseInt(parts[1]);
        
        long[][] times = new long[n][2];

        for(int i = 0; i < n; i++){
            parts = br.readLine().trim().split(" ");
            for(int j = 0; j < 2; j++) {
                times[i][j] = Long.parseLong(parts[j]);
            }
        }
        solve(n,r,times,sb);
    }
      
    /**
     * Parameters:
     * n : int - number of notes
     * r : int - the target accuracy
     * times : array-like of size (n,2) - list of button inputs. Each contains a list with (t_i,p_i)
     */
      public static void solve(int n, int r, long[][] times, StringBuilder sb){
          // compute and append answer to StringBuilder here
          sort(times, 0, n-1);
        double N = n;
        double R = (double)r/100;
        int index = (int)Math.round(N*R) - 1;
        /*
        for(int i = 0; i < n; i++) {
            System.out.println("{} " + Math.abs(times[i][1] - times[i][0]));
        }
        */
        if(R == 0)
            sb.append("It's impossible!");
        else if(times[0][1] == -1 || times[index][1] == -1)
            sb.append("It's impossible!");
        else{
            long margin = Math.abs(times[index][1] - times[index][0]);
            sb.append(margin);
        }
        System.out.println(sb);
    }
    
    
    public static boolean lessThan(long arr1[], long arr2[]){
        if(arr1[1] == -1)
            return false;
        else if(arr2[1] == -1)
            return true;
        else if(Math.abs(arr1[1]-arr1[0]) <= Math.abs(arr2[1]-arr2[0]))
            return true;
        else
            return false;
    }


    public static void merge(long arr[][], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        long L[][] = new long[n1][2];
        long R[][] = new long[n2][2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i){
            L[i][0] = arr[l + i][0];
            L[i][1] = arr[l + i][1];
        }
        for (int j = 0; j < n2; ++j){
            R[j][0] = arr[m + 1 + j][0];
            R[j][1] = arr[m + 1 + j][1];
        }


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            //L[i] <= R[j]
            if (lessThan(L[i], R[j])) {
                arr[k][0] = L[i][0];
                arr[k][1] = L[i][1];
                i++;
            }
            else {
                arr[k][0] = R[j][0];
                arr[k][1] = R[j][1];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k][0] = L[i][0];
            arr[k][1] = L[i][1];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k][0] = R[j][0];
            arr[k][1] = R[j][1];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    public static void sort(long arr[][], int l, int r)
    {
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
}
