import java.io.*;
import java.util.*;

// 세 번 이내에 사과를 먹자

public class BJ26169 {

    static final int N = 5;
    static int[][] map = new int[N][N];
    static boolean[][] visited = new boolean[N][N];
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        visited[r][c] = true;
        dfs(r, c, 0, 0);
        System.out.println(0);
    }
    
    private static void dfs(int r, int c, int depth, int cnt) {
           
        if (cnt == 2) {
            System.out.println(1);
            System.exit(0);
        }
        if (depth >= 3) return;

        for (int d = 0; d < 4; d++) {
            int xx = r + dx[d];
            int yy = c + dy[d];

            if (xx < 0 || xx >= N || yy < 0 || yy >= N) continue;
            if (visited[xx][yy] || map[xx][yy] == -1) continue;

            visited[xx][yy] = true;
            if (map[xx][yy] == 1) {
                dfs(xx, yy, depth + 1, cnt + 1);
            }
            else {
                dfs(xx, yy, depth + 1, cnt);
            }
            visited[xx][yy] = false;
        }
    }
}