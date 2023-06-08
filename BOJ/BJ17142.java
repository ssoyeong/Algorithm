import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 연구소 3

public class BJ17142 {

    static private class Point {
        int x;
        int y;
        int time;

        Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    static int n, m;
    static int min = Integer.MAX_VALUE;
    static int cntVirus, cntEmpty;
    static ArrayList<Point> virus = new ArrayList<>();
    static boolean[] visitedVirus;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board;
    static boolean[][] visited;
    static Queue<Point> queue = new LinkedList<>();
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        visited = new boolean[n][n];
        
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 2) {
                    virus.add(new Point(i, j, 0));
                    cntVirus++;
                }
                else if(board[i][j] == 0) {
                    cntEmpty++;
                }
            }
        }

        int ans;
        if(cntEmpty == 0) ans = 0;
        else {
            visitedVirus = new boolean[cntVirus];
            backTracking(0, 0);
            if(min == Integer.MAX_VALUE) ans = -1;
            else ans = min;
        }

        System.out.println(ans);
    }



    private static void backTracking(int depth, int start) {

        if(depth == m) {
            spreadVirus();
            return;
        }

        for(int i = start; i < cntVirus; i++) {

            if(visitedVirus[i]) continue;
            visitedVirus[i] = true;
            backTracking(depth + 1, i + 1);
            visitedVirus[i] = false;
        }
    }

    private static void spreadVirus() {

        queue.clear();
        int emptySpace = cntEmpty;
        for(int i = 0; i < n; i++) {
            Arrays.fill(visited[i], false);
        }
        
        for(int i = 0; i < cntVirus; i++) {
            if(visitedVirus[i]) {
                Point target = virus.get(i);
                queue.add(target);
                visited[target.x][target.y] = true;
            }
        }

        while(!queue.isEmpty()) {

            Point poll = queue.poll();

            for(int i = 0; i < 4; i++) {
                int xx = poll.x + dx[i];
                int yy = poll.y + dy[i];

                if(xx < 0 || xx >= n || yy < 0 || yy >= n) continue;
                if(visited[xx][yy]) continue;

                if(board[xx][yy] != 1) {
                    queue.add(new Point(xx, yy, poll.time + 1));
                    visited[xx][yy] = true;
                    if(board[xx][yy] == 0)  emptySpace--;
                }

                if(emptySpace == 0) {
                    min = Integer.min(min, poll.time + 1);
                    return;
                }
            }
        }
    }
    
}
