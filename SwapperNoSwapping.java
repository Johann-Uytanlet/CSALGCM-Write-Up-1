import java.io.BufferedReader;
import java.io.InputStreamReader;

public class c {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String[] parts = br.readLine().trim().split(" ");
        int n = Integer.parseInt(parts[0]);
        int s = Integer.parseInt(parts[1]);
        int[] books = new int[n];
        parts = br.readLine().trim().split(" ");

        for(int i = 0; i < n; i++) {
            books[i] = Integer.parseInt(parts[i]);
        }
        solve(books,n,s);
    }

    public static void solve(int[] books, int n, int s){
        // TODO: Compute and print answer here
        
        int[] books2 = new int[n];
        for(int i=0;i<n;i++){
            books2[i] = books[i];
        }
        
        int asc = ascSort(books, 0, n-1);
        int dsc = dscSort(books2, 0, n-1);
        
        double minSwaps;
        double time = s;
        if(asc < dsc)
            minSwaps = asc;
        else
            minSwaps = dsc;
        
        double answer = Math.ceil(time/minSwaps);
        
        if(minSwaps == 0)
            System.out.println("Butz loses!");
        else
            System.out.println(String.format("%.0f", answer));
    }
    
    public static int dscMerge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
        int count = 0;
        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];
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
            if (L[i] >= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
                count += n1 - i;
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
        return count;
    }
 
    // Main function that sorts arr[l..r] using
    // merge()
    public static int dscSort(int arr[], int l, int r)
    {
        int count = 0;
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;
 
            // Sort first and second halves
            count += dscSort(arr, l, m);
            count += dscSort(arr, m + 1, r);
 
            // Merge the sorted halves
            count += dscMerge(arr, l, m, r);
        }
        return count;
    }
    
    public static int ascMerge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
        int count = 0;
        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];
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
                count += n1 - i;
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
        return count;
    }
 
    // Main function that sorts arr[l..r] using
    // merge()
    public static int ascSort(int arr[], int l, int r)
    {
        int count = 0;
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;
 
            // Sort first and second halves
            count += ascSort(arr, l, m);
            count += ascSort(arr, m + 1, r);
 
            // Merge the sorted halves
            count += ascMerge(arr, l, m, r);
        }
        return count;
    }   
}
