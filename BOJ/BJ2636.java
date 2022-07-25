import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 치즈

public class BJ2636 {
    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int r, c;
    static int time;
    static int numLast;
    static int numMelt;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Point> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new int[r][c];
        visited = new boolean[r][c];

        for(int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < c; j++) {
                if(st.nextToken().equals("1")) {
                    board[i][j] = 1;
                    numLast++;
                }
            }
        }

        if(numLast == 0) {
            System.out.println(time);
            System.out.println(numLast);
            return;
        }

        while(true) {

            numMelt = 0;
            for(int i = 0; i < r; i++) {
                Arrays.fill(visited[i], false);
            }

            queue.add(new Point(0, 0));
            visited[0][0] = true;
            bfs();
            time++;

            boolean flag = melting();
            if(flag) break;
        }

        System.out.println(time);
        System.out.println(numLast);
    }
    
    static private void bfs() {

        while(!queue.isEmpty()) {
            Point poll = queue.poll();

            for(int i = 0; i < 4; i++) {
                int xx = poll.x + dx[i];
                int yy = poll.y + dy[i];

                if(xx < 0 || xx >= r || yy < 0 || yy >= c) continue;
                if(visited[xx][yy]) continue;

                if(board[xx][yy] == 0) {
                    queue.add(new Point(xx, yy));
                    visited[xx][yy] = true;
                }
                else if(board[xx][yy] == 1) {
                    board[xx][yy] = 2;
                    visited[xx][yy] = true;
                }
            }
        }
    }

    static private boolean melting() {  // 종료 여부를 반환

        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(board[i][j] == 2) {
                    board[i][j] = 0;
                    numMelt++;
                }
            }
        }

        if(numLast == numMelt) return true;
        numLast -= numMelt;

        return false;
    }
}
