import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 정사각형 방

public class SWEA1861 {
    
    static int tc;
    static int n;
    static int maxCount, maxRoomNum;
    static int[][] board;
    static int[][] dp;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int t = 1; t <= tc; t++) {
            n = Integer.parseInt(br.readLine());
            board = new int[n][n];
            dp = new int[n][n];

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            solution();
            sb.append("#" + t + " " + maxRoomNum + " " + maxCount + "\n");
        }

        System.out.println(sb.toString());
    }

    private static void solution() {

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(dp[i][j] == 0) {
                    dp[i][j] = dfs(i, j) + 1;
                }
            }
        }

        findAnswer();
    }

    private static int dfs(int x, int y) {

        for(int i = 0; i < 4; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];

            if(xx < 0 || xx >= n || yy < 0 || yy >= n) continue;
            if(board[xx][yy] == board[x][y] + 1) {
                if(dp[xx][yy] == 0) {
                    dp[xx][yy] = dfs(xx, yy) + 1;
                }
                return dp[xx][yy];
            }
        }

        return dp[x][y];
    }

    private static void findAnswer() {

        int cnt = 0;
        int num = n + 1;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(dp[i][j] >= cnt) {
                    if(dp[i][j] == cnt) {
                        num = Integer.min(num, board[i][j]);
                    }
                    else {
                        num = board[i][j];
                    }
                    cnt = dp[i][j];
                }
            }
        }

        maxCount = cnt;
        maxRoomNum = num;
    }
}
