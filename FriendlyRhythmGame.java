import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.*;
import java.util.*;

public class Solution {
    static void merge(long arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
  
        /* Create temp arrays */
        long L[] = new long[n1];
        long R[] = new long[n2];
  
        /*Copy data to temp arrays*/
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
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
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
    static void sort(long arr[], int l, int r)
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
        //Find out how many nodes you need to actually get
        int i = 0;
        int j = 0;
        int required = (int)Math.round(n * (r/100.0)); 
        int misscounter = 0;
        //System.out.println("Accuracy: " + required + "%\n");
        //create the array of error margins
      	long[] margins = new long[n];
        
        for(i = 0; i < n; i++) {
            if (times[i][1] != -1) {//if not miss
                margins[j] = Math.abs(times[i][1] - times[i][0]);
                //System.out.println(">> " + margins[j]);
                j++;
            }
            else {
                misscounter++;
            }
        }
        //order the margins in ascending order
        sort(margins, 0, j-1);
        
        //Checking
        //Case 1: the number of acquired margins is less than the required (too many misses)
        //Case 2: the number of acquired margins is greated than the required [success]
        //Case 3: all misses [fail]
        //Case 4: everything is too close together accuracy wise (failure), method: just check the value directly after the required one to see if it's also under the accuracy
        /*
        for (int k = 0; k < j; k++){
            System.out.println("$$ " + margins[k]);
        }
        */
        
        /*
        System.out.println("[[ Arr Length: " + margins.length);
        System.out.println("-- Required: " + required);
        System.out.println("-- Final Margins: " + margins[required]);
        System.out.println("== j: " + j);
        */
        if (misscounter == i) {
            System.out.println("It's impossible!");
        }
        else if (margins.length >= required && required == j) {
            System.out.println(margins[required-1]);
            
        }
        else if (margins.length >= required && (required-1) <= margins.length && margins[required] > margins[required-1]) {
            System.out.println(margins[required-1]);
        }
        else {
            System.out.println("It's impossible!");
        }
        
        
    }
}
