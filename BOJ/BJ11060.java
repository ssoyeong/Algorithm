import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

// 점프 점프

public class BJ11060 {

    static int n;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        dp = new int[n+1];
        Arrays.fill(dp, 999999999);
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solution();
        if(dp[n] == 999999999) System.out.println(-1);
        else System.out.println(dp[n]);
    }

    private static void solution() {

        dp[1] = 0;

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= arr[i]; j++) {
                int idx = i + j;
                if(i+j > n) continue;
                dp[idx] = Integer.min(dp[idx], dp[i] + 1);
            }
        }

    }
    
}
