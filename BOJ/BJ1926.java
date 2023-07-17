import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

// 그림

public class BJ1926 {

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int n, m;
    static int cntOfPictures;
    static int maxArea;
    static boolean[][] board;
    static boolean[][] visited;
    static Queue<Point> queue = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        board = new boolean[n][m];
        visited = new boolean[n][m];
        
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                if(st.nextToken().equals("1")) board[i][j] = true;
            }
        }

        solution();
        System.out.println(cntOfPictures);
        System.out.println(maxArea);
    }

    private static void solution() {

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(board[i][j] && !visited[i][j]) {

                    int cntArea = 1;
                    cntOfPictures++;

                    visited[i][j] = true;
                    queue.add(new Point(i, j));

                    // BFS탐색
                    while(!queue.isEmpty()) {
                        Point poll = queue.poll();

                        for(int d = 0; d < 4; d++) {
                            int xx = poll.x + dx[d];
                            int yy = poll.y + dy[d];

                            if(xx < 0 || xx >= n || yy < 0 || yy >= m) continue;
                            if(!board[xx][yy] || visited[xx][yy]) continue;

                            cntArea++;
                            visited[xx][yy] = true;
                            queue.add(new Point(xx, yy));
                        }
                    }

                    maxArea = Integer.max(maxArea, cntArea);
                }
            }
        }
    }
}