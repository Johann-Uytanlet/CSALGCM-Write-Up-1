import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//---------
//Version 2: Used Quicksort from GeeksforGeeks
//Usage of sb now
//-----------------

public class DPSCheck {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] parts = br.readLine().trim().split(" ");
        int k = Integer.parseInt(parts[0]);
        int h = Integer.parseInt(parts[1]);
        String[] names = new String[k];
        int[] atks = new int[k];
        int[][] scaling = new int[k][4];

        for (int i = 0; i < k; i++) {
            names[i] = br.readLine().trim();
            parts = br.readLine().trim().split(" ");
            atks[i] = Integer.parseInt(parts[0]);
            for (int j = 1; j < 5; j++) {
                scaling[i][j - 1] = Integer.parseInt(parts[j]);
            }
        }

        int[] hillichurls = new int[h];

        for (int i = 0; i < h; i++) {
            hillichurls[i] = Integer.parseInt(br.readLine().trim());
        }
        // study how to append to StringBuilder to optimize output runtime
        solve(k, h, names, atks, scaling, hillichurls, sb);
        System.out.print(sb);
    }

    static void swap(int[] arr, String[] arr2, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        String temp2 = arr2[i];
        arr2[i] = arr2[j];
        arr2[j] = temp2;
    }

    /*
     * This function takes last element as pivot, places
     * the pivot element at its correct position in sorted
     * array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right
     * of pivot
     */
    static int partition(int[] arr, String[] arr2, int low, int high) {

        // pivot
        int pivot = arr[high];

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is larger
            // than the pivot
            if (arr[j] > pivot) {

                // Increment index of
                // smaller element
                i++;
                swap(arr, arr2, i, j);
            }
        }
        swap(arr, arr2, i + 1, high);
        return (i + 1);
    }

    /*
     * The main function that implements QuickSort
     * arr[] --> Array to be sorted,
     * low --> Starting index,
     * high --> Ending index
     */
    static void quickSort(int[] arr, String[] arr2, int low, int high) {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, arr2, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, arr2, low, pi - 1);
            quickSort(arr, arr2, pi + 1, high);
        }
    }

    /**
     * Parameters:
     * k : int - number of knights
     * h : int - number of hillichurls
     * names : array-like of shape (k,) - list of knight names in order
     * given in input file
     * atks : array-like of shape (k,) - list of atk stats in order given
     * in input file
     * scalings : array-like of shape (k,4) - matrix of scaling values in
     * order given in input file. Each
     * row contains (NA,CA,ES,EB)
     * hillichurls : array-like of shape (h,) - list of hillichurl hit points in
     * order given in input file
     * sb : StringBuilder - StringBuilder to store output
     */
    public static void solve(int k, int h, String[] names, int[] atks,
            int[][] scaling, int[] hillichurls,
            StringBuilder sb) {
        // compute and build output in StringBuilder sb here
        // Pick Character -> Calculate Attack with Multiplier -> Get character killcount
        // -> sort everything -> sort by lexicographical

        int[] Iresult = new int[k];
        String[] Sresult = new String[k];

        for (int i = 0; i < k; i++) {
            // search for the best multiplier
            double multiplier = 0;
            for (int j = 0; j < 4; j++) {
                if (scaling[i][j] > multiplier)
                    multiplier = scaling[i][j];
            }
            // calculate the dmg using multiplier
            int att = (int) (atks[i] * (multiplier / 100));

            // go through the list of hilichurls
            int killcount = 0;

            for (int l = 0; l < h; l++) {
                if (att > hillichurls[l]) {
                    // System.out.println("Damage [" + att + "]" + "--" + "Hillichurl hp:" +
                    // hillichurls[l]);
                    killcount++;
                }

            }

            Iresult[i] = killcount;
            Sresult[i] = names[i];
        }

        // sorting
        // Sorting: descending order first, lexical order 2nd (do so only if a "counter"
        // has been activated)
        quickSort(Iresult, Sresult, 0, k - 1);

    }
}