import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.Arrays;
import java.util.LinkedList;

// 쉬운 최단거리

public class BJ14940 {

    private static class Point {
        int x;
        int y;
        int cnt;

        Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    static int n, m;
    static int[][] board;
    static int[][] ans;
    static Queue<Point> queue = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        ans = new int[n][m];
        for(int i = 0; i < n; i++) {
            Arrays.fill(ans[i], -1);
        }

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                int x = Integer.parseInt(st.nextToken());

                board[i][j] = x;

                if(board[i][j] == 2) {
                    ans[i][j] = 0;
                    queue.add(new Point(i, j, 0));
                }
                else if(board[i][j] == 0) {
                    ans[i][j] = 0;
                }
            }
        }

        solution();
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                sb.append(ans[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void solution() {

        while(!queue.isEmpty()) {

            Point poll = queue.poll();

            for(int i = 0; i < 4; i++) {

                int xx = poll.x + dx[i];
                int yy = poll.y + dy[i];

                if(xx < 0 || xx >= n || yy < 0 || yy >= m) continue;
                if(ans[xx][yy] >= 0) continue;

                ans[xx][yy] = poll.cnt + 1;
                queue.add(new Point(xx, yy, poll.cnt + 1));
            }
        }
    }
    
}
