import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 2×n 타일링 2

public class BJ11727 {

    static int n;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n+1];

        solution();
        System.out.println(dp[n]);
    }

    private static void solution() {

        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = (dp[i-1] + 2 * dp[i-2]) % 10007;
        }
    }
    
}
