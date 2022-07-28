import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;
import java.util.StringTokenizer;

// 치즈

public class BJ2638 {

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
    static boolean isMelted;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static Queue<Point> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                if(st.nextToken().equals("1")) board[i][j] = 1;
            }
        }
        
        while(true) {
            
            for(int i = 0; i < n; i++) {
                Arrays.fill(visited[i], false);
            }
            searchAir();

            for(int i = 0; i < n; i++) {
                Arrays.fill(visited[i], false);
            }
            queue.clear();
            isMelted = false;
            
            queue.add(new Point(0, 0));
            visited[0][0] = true;
            solution();
            
            if(!isMelted) break;
            answer++;
        }
        
        System.out.println(answer);
    }

    private static void searchAir() {

        queue.add(new Point(0, 0));
        visited[0][0] = true;

        while(!queue.isEmpty()) {
            
            Point poll = queue.poll();

            for(int i = 0; i < 4; i++) {

                int xx = poll.x + dx[i];
                int yy = poll.y + dy[i];

                if(xx < 0 || xx >= n || yy < 0 || yy >= m) continue;
                if(visited[xx][yy]) continue;

                if(board[xx][yy] == 1) {
                    board[poll.x][poll.y] = 9;
                }
                else {
                    queue.add(new Point(xx, yy));
                    visited[xx][yy] = true;
                }
            }
        }
    }

    private static void solution() {

        while(!queue.isEmpty()) {
            Point poll = queue.poll();
         
            for(int i = 0; i < 4; i++) {

                int xx = poll.x + dx[i];
                int yy = poll.y + dy[i];

                if(xx < 0 || xx >= n || yy < 0 || yy >= m) continue;
                if(visited[xx][yy]) continue;

                if(board[xx][yy] == 1) {
                    if(isPromising(xx, yy)) board[xx][yy] = 2;
                }
                else {
                    queue.add(new Point(xx, yy));
                }
            
                visited[xx][yy] = true;
            }
        }

        melting();
    }
    
    private static boolean isPromising(int x, int y) {

        if(x == 0 || x == n-1 || y == 0 || y == m-1) return false;

        int cnt = 0;
        for(int i = 0; i < 4; i++) {
            if(board[x + dx[i]][y + dy[i]] == 9) cnt++;
        }

        if(cnt >= 2) return true;
        return false;
    }

    private static void melting() {

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {

                if(board[i][j] == 2) {
                    isMelted = true;
                    board[i][j] = 0;
                }
            }
        }
    }

}
