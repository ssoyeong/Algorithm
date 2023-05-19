import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 구구단 걷기

public class SWEA16800 {

    static int tc;
    static long n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= tc; i++) {
            n = Long.parseLong(br.readLine());

            long ans = solution();
            sb.append("#" + i + " " + ans + "\n");
        }

        System.out.println(sb.toString());
    }

    public static long solution() {

        long min = Long.MAX_VALUE;

        for(long i = 1; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                long a = i;
                long b = n/i;
                
                min = Long.min(min, a+b);
            }
        }

        return min - 2;
    }
    
}
