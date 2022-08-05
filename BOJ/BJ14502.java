import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 연구소

public class BJ14502 {

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int n, m;
    static int answer;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board;
    static int[][] temp;
    static Queue<Point> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        temp = new int[n][m];
        
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                int x = Integer.parseInt(st.nextToken());
                board[i][j] = x;
            }
        }

        solution(0);
        System.out.println(answer);
    }

    private static void solution(int wall) {

        if(wall == 3) {
            spreadVirus();
            return;
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(board[i][j] == 0) {
                    board[i][j] = 1;
                    solution(wall + 1);
                    board[i][j] = 0;
                }
            }
        }
    }

    private static void spreadVirus() {

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                temp[i][j] = board[i][j];
                if(temp[i][j] == 2) {
                    queue.add(new Point(i, j));
                }
            }
        }

        while(!queue.isEmpty()) {
            Point poll = queue.poll();

            for(int i = 0; i < 4; i++) {
                int xx = poll.x + dx[i];
                int yy = poll.y + dy[i];

                if(xx < 0 || xx >= n || yy < 0 || yy >= m) continue;
                if(temp[xx][yy] != 0) continue;
                queue.add(new Point(xx, yy));
                temp[xx][yy] = 2;
            }
        }

        int numOfSafe = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(temp[i][j] == 0) numOfSafe++;
            }
        }

        answer = Math.max(answer, numOfSafe);
    }
    
}
