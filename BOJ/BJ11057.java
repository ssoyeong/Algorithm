import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.IOException;

// 오르막 수

public class BJ11057 {

    static int n;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n+1][11];

        int ans = solution();
        System.out.println(ans);
    }

    private static int solution() {
        
        Arrays.fill(dp[1], 1);
        
        for(int i = 2; i <= n; i++) {
            for(int j = 9; j >= 0; j--) {
                dp[i][j] = dp[i-1][j] % 10007 + dp[i][j+1] % 10007;
            }
        }

        int total = 0;
        for(int i = 0; i <= 9; i++) {
            total += dp[n][i];
        }

        return total % 10007;
    }
    
}
