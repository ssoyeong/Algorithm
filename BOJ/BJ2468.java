import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 안전 영역

public class BJ2468 {

    static private class Point {

        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int n;
    static int maxHeight;
    static int ans;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] input;
    static boolean[][] board;
    static boolean[][] visited;
    static Queue<Point> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        input = new int[n][n];
        board = new boolean[n][n];
        visited = new boolean[n][n];
        
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, input[i][j]);
            }
        }

        
        for(int h = 0; h <= maxHeight; h++) {

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(input[i][j] > h) board[i][j] = true;
                    else board[i][j] = false;
                }
            }

            for(int i = 0; i < n; i++) {
                Arrays.fill(visited[i], false);
            }

            int result = solution();
            ans = Math.max(ans, result);
        }
        
        System.out.println(ans);
    }

    private static int solution() {

        int cnt = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {

                if(board[i][j] && !visited[i][j]) {
                    bfs(i, j);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private static void bfs(int r, int c) {

        queue.add(new Point(r, c));
        visited[r][c] = true;

        while(!queue.isEmpty()) {

            Point poll = queue.poll();

            for(int i = 0; i < 4; i++) {
                
                int xx = poll.x + dx[i];
                int yy = poll.y + dy[i];

                if(xx < 0 || xx >= n || yy < 0 || yy >= n) continue;
                if(board[xx][yy] && !visited[xx][yy]) {
                    queue.add(new Point(xx, yy));
                    visited[xx][yy] = true;
                }
            }
        }
    }
    
}
