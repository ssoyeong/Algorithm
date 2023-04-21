import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 나이트의 이동

public class BJ7562 {

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

    static int l;
    static int startX, startY;
    static int targetX, targetY;
    static int ans;
    static int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dy = {-2, -1, 1, 2, -2, -1, 1, 2};
    static int[][] board;
    static boolean[][] visited;
    static Queue<Point> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int tc = 0; tc < testCase; tc++) {

            ans = Integer.MAX_VALUE;
            queue.clear();

            l = Integer.parseInt(br.readLine());
            board = new int[l][l];
            visited = new boolean[l][l];

            st = new StringTokenizer(br.readLine());
            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            targetX = Integer.parseInt(st.nextToken());
            targetY = Integer.parseInt(st.nextToken());

            queue.add(new Point(startX, startY, 0));
            visited[startX][startY] = true;
            bfs();

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void bfs() {

        while(!queue.isEmpty()) {

            Point poll = queue.poll();

            if(poll.x == targetX && poll.y == targetY) {
                ans = Math.min(ans, poll.cnt);
                return;
            }

            for(int i = 0; i < 8; i++) {
                int xx = poll.x + dx[i];
                int yy = poll.y + dy[i];
                
                if(xx < 0 || xx >= l || yy < 0 || yy >= l) continue;
                if(visited[xx][yy]) continue;
                
                queue.add(new Point(xx, yy, poll.cnt + 1));
                visited[xx][yy] = true;
            }
        }
    }
    
}
