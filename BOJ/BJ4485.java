import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

// 녹색 옷 입은 애가 젤다지?

public class BJ4485 {

    private static class Point implements Comparable<Point> {
        int x;
        int y;
        int total;

        Point(int x, int y, int total) {
            this.x = x;
            this.y = y;
            this.total = total;
        }

        @Override
        public int compareTo(Point o) {
            return this.total - o.total;
        }
    }
    static int n;
    static int ans;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board;
    static boolean[][] visited;
    static PriorityQueue<Point> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int problemNum = 1;
        while(true) {

            n = Integer.parseInt(br.readLine());
            if(n == 0) break;

            board = new int[n][n];
            visited = new boolean[n][n];
            ans = Integer.MAX_VALUE;
            queue.clear();

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dijkstra();
            sb.append("Problem " + problemNum + ": " + ans + "\n");
            problemNum++;
        }

        System.out.println(sb.toString());
    }

    private static void dijkstra() {

        queue.add(new Point(0, 0, board[0][0]));
        visited[0][0] = true;

        while(!queue.isEmpty()) {
            Point poll = queue.poll();

            if(poll.x == n-1 && poll.y == n-1) {
                ans = poll.total;
                return;
            }

            for(int i = 0; i < 4; i++) {
                int xx = poll.x + dx[i];
                int yy = poll.y + dy[i];

                if(xx < 0 || xx >= n || yy < 0 || yy >= n) continue;
                if(visited[xx][yy]) continue;
                queue.add(new Point(xx, yy, poll.total + board[xx][yy]));
                visited[xx][yy] = true;
            }
        }
    }
    
}
