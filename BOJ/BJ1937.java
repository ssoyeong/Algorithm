import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 욕심쟁이 판다

public class BJ1937 {
    
    static int n;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        dp = new int[n][n];

        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                dp[i][j] = dfs(i, j);
            }
        }

        int max = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                max = Integer.max(max, dp[i][j]);
            }
        }
        
        System.out.println(max);
    }

    private static int dfs(int x, int y) {

        if(dp[x][y] != 0) return dp[x][y];
        dp[x][y] = 1;

        for(int i = 0; i < 4; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];

            if(xx < 0 || xx >= n || yy < 0 || yy >= n) continue;
            if(board[xx][yy] > board[x][y]) {
                dp[x][y] = Integer.max(dp[x][y], dfs(xx, yy) + 1);
            }   
        }

        return dp[x][y];
    }

}
