import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

// 동전 2

public class BJ2294 {

    static int n, k;
    static int answer;
    static int[] dp;
    static Integer[] coin;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        coin = new Integer[n];

        int maxCoin = 0;
        for(int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            coin[i] = x;
            maxCoin = Math.max(maxCoin, x);
        }

        if(maxCoin > k) dp = new int[maxCoin+1];
        else dp = new int[k+1];

        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;

        solution();

        if(dp[k] == Integer.MAX_VALUE - 1) answer = -1;
        else answer = dp[k];
        System.out.println(answer);
    }

    private static void solution() {

        for(int i = 0; i < n; i++) {
            for(int j = coin[i]; j <= k; j++) {
                dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);
            }
        }
    }

}
