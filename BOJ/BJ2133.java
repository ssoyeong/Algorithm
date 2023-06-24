import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 타일 채우기

public class BJ2133 {

    static int n;
    static int ans;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        if(n % 2 == 0) {
            dp = new int[n/2 + 1];
            solution();
        }  

        System.out.println(ans);
    }

    private static void solution() {

        dp[1] = 3;
        if(n >=  4) dp[2] = 11;

        int total = dp[1];
        for(int i = 3; i <= n/2; i++) {
            dp[i] = total * 2 + dp[i-1] * 3 + 2;
            total += dp[i-1];
        }

        ans = dp[n/2];
    }
    
}
