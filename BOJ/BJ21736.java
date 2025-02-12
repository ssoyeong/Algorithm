import java.io.*;
import java.util.*;

// 헌내기는 친구가 필요해

public class BJ21736 {

    static int n, m;
    static char[][] map;
    static boolean[][] visited;
    static ArrayDeque<int[]> queue = new ArrayDeque<>();
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'I') {
                    visited[i][j] = true;
                    queue.add(new int[] { i, j });
                }
            }
        }

        int count = bfs();
        if (count == 0)
            System.out.println("TT");
        else
            System.out.println(count);
    }

    private static int bfs() {

        int count = 0;
        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            for (int d = 0; d < 4; d++) {
                int xx = xy[0] + dx[d];
                int yy = xy[1] + dy[d];

                if (xx < 0 || xx >= n || yy < 0 || yy >= m) continue;
                if (visited[xx][yy] || map[xx][yy] == 'X') continue;
                if (map[xx][yy] == 'P') count++;
                visited[xx][yy] = true;
                queue.add(new int[] { xx, yy });
            }
        }
        return count;
    }
}
