import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 벽 부수고 이동하기 2

public class BJ14442 {

    private static class Node {
        int x;
        int y;
        int distance;
        int destroyed;

         Node(int x, int y, int distance, int destroyed) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.destroyed = destroyed;
         }
    }
    static int n, m;
    static int k;
    static int ans = Integer.MAX_VALUE;
    static boolean[][] board;
    static boolean[][][] visited;           // visited[k][n][m] 벽은 부순 횟수 k에 따라 방문 여부 체크
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        board = new boolean[n + 1][m + 1];
        visited = new boolean[k+1][n + 1][m + 1];

        for(int i = 1; i <= n; i++) {
            String line = br.readLine();
            for(int j = 0; j < m; j++) {
                if(line.charAt(j) == '1') {
                    board[i][j + 1] = true;
                }
            }
        }

        solution();

        if(ans == Integer.MAX_VALUE) ans = -1;
        System.out.println(ans);
    }

    private static void solution() {

        visited[0][1][1] = true;
        queue.add(new Node(1, 1, 1, 0));
    
        while(!queue.isEmpty()) {

            Node poll = queue.poll();

            if(poll.x == n && poll.y == m) {
                ans = Integer.min(ans, poll.distance);
                return;
            }
            
            for(int i = 0; i < 4; i++) {
                int xx = poll.x + dx[i];
                int yy = poll.y + dy[i];

                if(xx < 1 || xx > n || yy < 1 || yy > m) continue;
                if(visited[poll.destroyed][xx][yy]) continue;

                if(!board[xx][yy]) {        // 벽이 없는 경우
                    visited[poll.destroyed][xx][yy] = true;
                    queue.add(new Node(xx, yy, poll.distance + 1, poll.destroyed));
                }
                else {                      // 벽이 있는 경우
                    if(poll.destroyed < k) {
                        visited[poll.destroyed][xx][yy] = true;
                        queue.add(new Node(xx, yy, poll.distance + 1, poll.destroyed + 1));
                    }
                }
            }
        }
    }
    
}
