import java.io.*;
import java.util.*;

// 보물섬

public class BJ2589 {
    
    static int n, m;
    static int ans;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] map;
    static int[][] visited;
    static ArrayDeque<int[]> queue = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new boolean[n][m];
        visited = new int[n][m];

        for(int i = 0; i < n; i++) {
            String line = br.readLine();
            for(int j = 0; j < m; j++) {
                if(line.charAt(j) == 'L') map[i][j] = true;
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j]) ans = Integer.max(ans, bfs(i, j));
            }
        }

        System.out.println(ans);
    }

    private static int bfs(int x, int y) {

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                visited[i][j] = -1;
            }
        }

        int max = 0;
        visited[x][y] = 0;
        queue.add(new int[] {x, y});

        while(!queue.isEmpty()) {
            int[] xy = queue.poll();
            for(int d = 0; d < 4; d++) {
                int xx = xy[0] + dx[d];
                int yy = xy[1] + dy[d];

                if(xx < 0 || xx >= n || yy < 0 || yy >= m) continue;
                if(!map[xx][yy] || visited[xx][yy] >= 0) continue;

                visited[xx][yy] = visited[xy[0]][xy[1]] + 1;
                max = Integer.max(max, visited[xx][yy]);
                queue.add(new int[] {xx, yy});
            }
        }

        return max;
    }
}
