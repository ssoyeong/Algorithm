import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 구간 합 구하기 5

public class BJ11660 {
    
    static int n, m;
    static int x1, x2, y1, y2;
    static int[][] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n+1][n+1];
        dp = new int[n+1][n+1];

        for(int i = 1; i <= n; i++) {

            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makeDpMatrix();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            
            int result = dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1];
            sb.append(result + "\n");   
        }
        
        System.out.println(sb.toString());
    }

    private static void makeDpMatrix() {

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {

                if(i == 1) {
                    dp[i][j] = dp[i][j-1] + arr[i][j];
                }
                else if(j == 1) {
                    dp[i][j] = dp[i-1][j] + arr[i][j];
                }
                else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1] + arr[i][j] - dp[i-1][j-1];
                }
            }
        }
    }
    
}
