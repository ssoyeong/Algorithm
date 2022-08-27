import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// RGB거리 2

public class BJ17404 {

    static int n;
    static int[][] cost;
    static int[][] dp0, dp1, dp2;       // 0: RED, 1: GREEN, 2: BLUE

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        cost = new int[n+1][3];
        n = Integer.parseInt(br.readLine());

        cost = new int[n+1][3];
        dp0 = new int[n+1][3];
        dp1 = new int[n+1][3];
        dp2 = new int[n+1][3];

        StringTokenizer st;
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        solution();

        int result0 = Math.min(dp0[n][1], dp0[n][2]);
        int result1 = Math.min(dp1[n][0], dp1[n][2]);
        int result2 = Math.min(dp2[n][0], dp2[n][1]);

        int answer = Math.min(result0, Math.min(result1, result2));
        System.out.println(answer);
    }

    static void solution() {

        // fix the first as each color
        dp0[1][0] = cost[1][0];
        dp0[2][0] = Integer.MAX_VALUE;
        dp0[2][1] = dp0[1][0] + cost[2][1];
        dp0[2][2] = dp0[1][0] + cost[2][2];

        dp1[1][1] = cost[1][1];
        dp1[2][1] = Integer.MAX_VALUE;
        dp1[2][0] = dp1[1][1] + cost[2][0];
        dp1[2][2] = dp1[1][1] + cost[2][2];

        dp2[1][2] = cost[1][2];
        dp2[2][2] = Integer.MAX_VALUE;
        dp2[2][0] = dp2[1][2] + cost[2][0];
        dp2[2][1] = dp2[1][2] + cost[2][1];

        for(int i = 3; i <= n; i++) {
            dp0[i][0] = Math.min(dp0[i-1][1], dp0[i-1][2]) + cost[i][0];
            dp0[i][1] = Math.min(dp0[i-1][0], dp0[i-1][2]) + cost[i][1];
            dp0[i][2] = Math.min(dp0[i-1][0], dp0[i-1][1]) + cost[i][2];

            dp1[i][0] = Math.min(dp1[i-1][1], dp1[i-1][2]) + cost[i][0];
            dp1[i][1] = Math.min(dp1[i-1][0], dp1[i-1][2]) + cost[i][1];
            dp1[i][2] = Math.min(dp1[i-1][0], dp1[i-1][1]) + cost[i][2];

            dp2[i][0] = Math.min(dp2[i-1][1], dp2[i-1][2]) + cost[i][0];
            dp2[i][1] = Math.min(dp2[i-1][0], dp2[i-1][2]) + cost[i][1];
            dp2[i][2] = Math.min(dp2[i-1][0], dp2[i-1][1]) + cost[i][2];
        }
    } 
    
}
