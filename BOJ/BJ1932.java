import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

// 정수 삼각형

public class BJ1932 {

    static int n;
    static int[][] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        dp = new int[n][n];

        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j <= i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = solution();
        System.out.println(ans);
    }

    private static int solution() {

        // 1열 및 대각선
        dp[0][0] = arr[0][0];
        for(int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0] + arr[i][0];
            dp[i][i] = dp[i-1][i-1] + arr[i][i];
        }

        // 나머지
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < n; j++) {
                if(i <= j) continue;
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + arr[i][j];
            }
        }

        // n-1 행의 최댓값 찾기
        Arrays.sort(dp[n-1]);
        return dp[n-1][n-1];
    }
     
}
