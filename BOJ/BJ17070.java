import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 파이프 옮기기 1

public class BJ17070 {

    static int n;
    static int cnt;
    static boolean[][] board;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new boolean[n+1][n+1];

        StringTokenizer st;
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                if(st.nextToken().equals("1")) board[i][j] = true; 
            }
        }

        dfs(1, 2, 0);
        System.out.println(cnt);
    }

    private static void dfs(int x, int y, int direction) {

        if(x == n && y == n) {
            cnt++;
            return;
        }

        switch(direction) {
            case 0:
                if(y + 1 <= n && !board[x][y + 1]) {
                    dfs(x, y + 1, 0);
                }
                break;
            case 1:
                if(x + 1 <= n && !board[x + 1][y]) {
                    dfs(x + 1, y, 1);
                }
                break;
            case 2:
                if(y + 1 <= n && !board[x][y + 1]) {
                    dfs(x, y + 1, 0);
                }
                if(x + 1 <= n && !board[x + 1][y]) {
                    dfs(x + 1, y, 1);
                }
                break;
        }

        if(y + 1 <= n && x + 1 <= n && !board[x][y + 1] && !board[x + 1][y] && !board[x + 1][y + 1]) {
            dfs(x + 1, y + 1, 2);
        }
    }
    
}
