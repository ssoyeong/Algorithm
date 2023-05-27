import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

// 앱

public class BJ7579 {

    static int n, m;
    static int[] memory;
    static int[] cost;
    static int[] dp = new int[10001];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        memory = new int[n];
        cost = new int[n];
        Arrays.fill(dp, -1);
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }
        
        int ans = solution();
        System.out.println(ans);
    }

    private static int solution() {

        for(int i = 0; i < n; i++) {
            for(int j = 10000; j >= cost[i]; j--) {
                if(dp[j - cost[i]] != -1) {
                    dp[j] = Integer.max(dp[j], dp[j - cost[i]] + memory[i]);
                }
            }

            if(dp[cost[i]] < memory[i]) dp[cost[i]] = memory[i];
        }

        for(int i = 0; i < 10001; i++) {
            if(dp[i] >= m) return i;
        }

        return -1;
    }
    
}
