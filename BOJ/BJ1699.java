import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 제곱수의 합

public class BJ1699 {

    static int n;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n+1];
        for(int i = 0; i <= n; i++) {
            dp[i] = i;
        }
        solution();
        System.out.println(dp[n]);
    }

    private static void solution() {
        for(int i = 0; i <= n; i++) {
            for(int j = 1; j * j <= i; j++) {
                dp[i] = Integer.min(dp[i], dp[i- j*j] + 1);
            }
        }
    }
    
}
