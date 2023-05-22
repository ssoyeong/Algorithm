import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 합분해

public class BJ2225 {

    static int n, k;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[k+1][n+1];

        solution();
        System.out.println(dp[k][n]);
    }

    private static void solution() {

        for(int i = 0; i <= k; i++) {
            dp[i][0] = 1;
        }

        for(int i = 1; i <= k; i++) {
            for(int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % 1000000000;
            }
        }
    }
    
}
