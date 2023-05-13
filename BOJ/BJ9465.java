import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 스티커

public class BJ9465 {

    static int t;
    static int n;
    static int[][] arr;
    static int[][] dp;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < t; tc++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[2][n+1];
            dp = new int[2][n+1];

            StringTokenizer st;
            for(int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 1; j <= n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            solution();
            int answer = Integer.max(dp[0][n], dp[1][n]);
            sb.append(answer + "\n");
        }

        System.out.println(sb.toString());
    }

    private static void solution() {

        dp[0][1] = arr[0][1];
        dp[1][1] = arr[1][1];

        for(int i = 2; i <= n; i++) {

            dp[0][i] = Integer.max(dp[1][i-2], dp[1][i-1]) + arr[0][i];
            dp[1][i] = Integer.max(dp[0][i-2], dp[0][i-1]) + arr[1][i];
        }
    }
    
}
