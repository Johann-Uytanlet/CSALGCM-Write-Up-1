import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TribonacciSequence {
    public static final long MOD = 1000000007L;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().trim().split(" ");
        long a = Long.parseLong(parts[0]);
        long b = Long.parseLong(parts[1]);
        long c = Long.parseLong(parts[2]);
        long i = Long.parseLong(parts[3]);
        System.out.println(compute_ans(a,b,c,i));
    }

    public static long compute_ans(long a, long b, long c, long i){
        // compute for and return answer here
        long temp;
        
        if (i > 2) {
            temp = a + b + c;
            
            a = b;
            b = c;
            c = temp % MOD;
            
            return compute_ans(a, b, c, i - 1);
        }
        
        return c;
    }
}
