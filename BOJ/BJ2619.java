import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 로봇 조종하기

public class BJ2619 {

    static int n, m;
    static int[][] board;
    static int[][] dp;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        board = new int[n+1][m+1];
        dp = new int[3][m+1];
        
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        solution();
        System.out.println(dp[0][m - 1]);
    }

    private static void solution() {

        // 첫번째 행
        dp[0][0] = board[0][0];
        for(int i = 1; i < m; i++) {
            dp[0][i] += dp[0][i - 1] + board[0][i];
        }

        // 나머지 행
        for(int i = 1; i < n; i++) {

            // 오른쪽, 아래 방향 채우기
            dp[1][0] = dp[0][0] + board[i][0];
            for(int j = 1; j < m; j++) {
                dp[1][j] = Integer.max(dp[0][j], dp[1][j - 1]) + board[i][j];
            }

            // 왼쪽, 아래 방향 채우기
            dp[2][m - 1] = dp[0][m - 1] + board[i][m - 1];
            for(int j = m - 2; j >= 0; j--) {
                dp[2][j] = Integer.max(dp[0][j], dp[2][j + 1]) + board[i][j];
            }

            // dp[0][j]에 최댓값 넣기
            for(int j = 0; j < m; j++) {
                dp[0][j] = Integer.max(dp[1][j], dp[2][j]);
            }
        }
    }

}

