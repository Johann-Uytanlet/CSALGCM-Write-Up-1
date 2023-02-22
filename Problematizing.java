import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problematizing {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] parts = br.readLine().trim().split(" ");
		int n = Integer.parseInt(parts[0]);
		int t = Integer.parseInt(parts[1]);
		int[][] problems = new int[n][2];

		for(int i = 0; i < n; i++) {
			parts = br.readLine().trim().split(" ");
			for(int j = 0; j < 2; j++){	
          		problems[i][j] = Integer.parseInt(parts[j]);
            }
		}
		System.out.println(solve(n,t,problems));
	}
  
  	public static String solve(int n, int t, int[][] problems){
        // compute and return answer here
        //int[] temp = new int[2];
        sort(problems, 0, n-1);
        if(t < problems[0][0])
            return "This exam is impossible!";
        else{
            int count = 0;
            while(t > 0){
                t = t - problems[count][0];
                count++;
            }
            
            if(t < 0)
                count--;
            
            return String.valueOf(count);
        }
    }
    
    
    public static boolean lessThan(int arr1[], int arr2[]){
        if(arr1[0] == -1)
            return false;
        else if(arr1[0] < arr2[0])
            return true;
        else if (arr1[0] == arr2[0] && arr1[1] <= arr2[1])
            return true;
        else if(arr2[0] == -1)
            return true;
        else
            return false;
    }


    public static void merge(int arr[][], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[][] = new int[n1][2];
        int R[][] = new int[n2][2];

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
    public static void sort(int arr[][], int l, int r)
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
