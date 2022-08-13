import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 1로 만들기 2

public class BJ12852 {

    static int n;
    static int[] dp;
    static int[] pre;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dp = new int[n+1];
        pre = new int[n+1];
        dp[1] = 0;
        
        solution();
        print();
    }
    
    private static void solution() {

        for(int i = 2; i <= n; i++) {

            dp[i] = dp[i-1] + 1;
            pre[i] = i-1;

            if(i%3 == 0 && dp[i/3] + 1 < dp[i]) {
                dp[i] = dp[i/3] + 1;
                pre[i] = i/3;
            }

            if(i%2 == 0 && dp[i/2] + 1 < dp[i]) {
                dp[i] = dp[i/2] + 1;
                pre[i] = i/2;
            }
        }
    }

    private static void print() {

        StringBuilder sb = new StringBuilder();

        sb.append(dp[n]).append("\n");
        while(n > 0) {
            sb.append(n + " ");
            n = pre[n];
        }
        sb.append("\n");
        System.out.println(sb.toString());
    }
}
