import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 팰린드롬?

public class BJ10942 {
    public static int n, m;
    public static int num[];
    public static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        num = new int[n+1];
        dp = new boolean[n+1][n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= n; i++) {
            dp[i][i] = true;
        }

        for(int i = 1; i < n; i++) {
            for(int j = 1; j <= n-i; j++) {
                solution(j, j+i);
            }
        }

        m = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if(dp[s][e]) sb.append("1\n");
            else sb.append("0\n");
        }

        System.out.println(sb.toString());
    }

    private static void solution(int s, int e) {

        if(e - s == 1){
            if(num[s] == num[e]) dp[s][e] = true;
        }
        else{
            if(dp[s+1][e-1] && (num[s] == num[e])) dp[s][e] = true;
        }
    }
}
