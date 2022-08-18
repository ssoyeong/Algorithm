import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 동전

public class BJ9084 {

    static int t, n, m;
    static int[] coin;
    static long[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int test = 0; test < t; test++) {

            n = Integer.parseInt(br.readLine());
            coin = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
            }
            m = Integer.parseInt(br.readLine());

            long result = solution();
            sb.append(result + "\n");
        }

        System.out.println(sb.toString());
    }
    
    private static long solution() {

        dp = new long[2][m+1];
        dp[0][0] = 1;
        dp[1][0] = 1;

        for(int i = 0; i < n; i++) {
            for(int j = coin[0]; j <= m; j++) {

                int pre = (i%2 == 0) ? 1 : 0;

                if(j >= coin[i]) dp[i%2][j] = dp[pre][j] + dp[i%2][j-coin[i]];
                else dp[i%2][j] = dp[pre][j];
            }
        }

        int row = (n%2 == 0) ? 1 : 0;
        return dp[row][m];
    }

}
