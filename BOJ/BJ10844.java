import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 쉬운 계단 수

public class BJ10844 {

    static int n;
    static int[][] dp;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dp = new int[n][11];
        for(int i = 1; i <= 9; i++) {
            dp[0][i] = 1;
        }

        for(int i = 1; i < n; i++) {
            
            dp[i][0] = dp[i-1][1] % 1000000000;
            for(int j = 1; j <= 9; j++) {
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000;
            }
        }

        long answer = 0;
        for(int i = 0; i <= 9; i++) {
            answer = answer + dp[n-1][i];
        }

        answer = answer % 1000000000;
        System.out.println(answer);
    }
    
}
