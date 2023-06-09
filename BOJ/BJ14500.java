import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 테트로미노

public class BJ14500 {

    static int n, m;
    static int max;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(1, i, j, board[i][j]);
                visited[i][j] = false;

                exception(i, j);        // ㅜ 모양 테트로미노인 경우
            }
        }

        System.out.println(max);
    }

    private static void dfs(int depth, int x, int y, int total) {

        if(depth == 4) {
            max = Integer.max(max, total);
            return;
        }

        for(int i = 0; i < 4; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];

            if(xx <  0 || xx >= n || yy < 0 || yy >= m) continue;
            if(visited[xx][yy]) continue;

            visited[xx][yy] = true;
            dfs(depth + 1, xx, yy, total + board[xx][yy]);
            visited[xx][yy] = false;
        }
    }
    
    private static void exception(int r, int c) {

        int[] arr = new int[4];     // r, c 기준 상우하좌 값
        int sum = 0;                // 상우하좌 값의 합
                
        for(int i = 0; i < 4; i++) {
            int xx = r + dx[i];
            int yy = c + dy[i];

            if(xx < 0 || xx >= n || yy < 0 || yy >= m) arr[i] = 0;
            else arr[i] = board[xx][yy];

            sum += arr[i];
        }

        for(int i = 0; i < 4; i++) {
            max = Integer.max(max, sum + board[r][c] - arr[i]);
        }
    }
}
