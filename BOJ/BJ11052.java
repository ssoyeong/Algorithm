import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 카드 구매하기

public class BJ11052 {

    static int n;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        dp = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solution();
        System.out.println(dp[n]);
    }
    
    private static void solution() {
        
        for(int i = 1; i <= n; i++) {
            
            int max = 0;
            for(int j = 1; j <= i/2 ; j++) {
                max = Integer.max(max, dp[j] + dp[i-j]);
            }

            max = Integer.max(max, arr[i]);
            dp[i] = max;
        }
    }
}
