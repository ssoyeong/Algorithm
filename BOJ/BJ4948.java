import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 베르트랑 공준

public class BJ4948 {

    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            n = Integer.parseInt(br.readLine());
            if(n == 0) break;
            int ans = solution();
            sb.append(ans + "\n");
        }

        System.out.println(sb.toString());
    }

    private static int solution() {

        int cnt = 0;
        for(int i = n+1; i <= 2 * n; i++) { 
            if(isPrime(i)) cnt++;  
        }
        return cnt;
    }

    private static boolean isPrime(int num) {
        if(num == 1) return false;
        for(int i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
    
}
