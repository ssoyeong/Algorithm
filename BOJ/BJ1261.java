import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 알고스팟

public class BJ1261 {

    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int cnt;

        Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return this.cnt - o.cnt;
        }
    }

    static int n, m;
    static int ans;
    static boolean[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static PriorityQueue<Node> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        board = new boolean[n+1][m+1];
        visited = new boolean[n+1][m+1];
        
        for(int i = 1; i <= n; i++) {
            String line = br.readLine();
            for(int j = 1; j <= m; j++) {
                if(line.charAt(j-1) == '1') board[i][j] = true;
            }
        }

        solution();
        System.out.println(ans);

    }

    private static void solution() {

        queue.add(new Node(1, 1, 0));
        visited[1][1] = true;

        while(!queue.isEmpty()) {
            Node poll = queue.poll();

            if(poll.x == n && poll.y == m) {
                ans = poll.cnt;
                return;
            }

            for(int i = 0; i < 4; i++) {
                int xx = poll.x + dx[i];
                int yy = poll.y + dy[i];
               
                if(xx < 1 || xx > n || yy < 1 || yy > m || visited[xx][yy] == true) continue;

                if(board[xx][yy] == true) {
                    queue.add(new Node(xx, yy, poll.cnt + 1));
                }
                else {
                    queue.add(new Node(xx, yy, poll.cnt));
                }
                visited[xx][yy] = true;
            }
        }
    }
    
}
