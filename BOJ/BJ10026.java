import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.io.IOException;

// 적록색약

public class BJ10026 {

    private static class Point {

        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Point> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        visited = new boolean[n][n];
        
        for(int i = 0; i < n; i++) {
            String line = br.readLine();
            for(int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int resultFalse = solution(false);     // is not blind
        
        for(int i = 0; i < n; i++) {
            Arrays.fill(visited[i], false);
        }
        queue.clear();

        int resultTrue = solution(true);      // is blind

        System.out.println(resultFalse + " " + resultTrue);
    }

    private static int solution(boolean flag) {

        int cnt = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {

                if(visited[i][j]) continue;

                char[] target = new char[2];
                target[0] = map[i][j];
                if(flag) {
                    if(target[0] == 'R') target[1] = 'G';
                    else if(target[0] == 'G') target[1] = 'R';
                }

                bfs(target, i, j);
                cnt++;
            }
        }

        return cnt;
    }

    private static void bfs(char[] target, int r, int c) {

        queue.add(new Point(r, c));
        visited[r][c] = true;

        while(!queue.isEmpty()) {

            Point poll = queue.poll();

            for(int i = 0; i < 4; i++) {
                int xx = poll.x + dx[i];
                int yy = poll.y + dy[i];

                if(xx < 0 || xx >= n || yy < 0 || yy >= n) continue;
                if(visited[xx][yy]) continue;
                if(map[xx][yy] == target[0] || map[xx][yy] == target[1]) {
                    queue.add(new Point(xx, yy));
                    visited[xx][yy] = true;
                }
            }
        }
    } 
}
