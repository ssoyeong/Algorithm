import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/// 탈출

public class BJ3055 {

    private static class Point {
        int x;
        int y;
        int time;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    static int r, c;
    static int ans = Integer.MAX_VALUE;
    static Character[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Point> queue = new LinkedList<>();
    static HashSet<Point> water = new HashSet<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new Character[r][c];
        visited = new boolean[r][c];
        
        for(int i = 0; i < r; i++) {
            String line = br.readLine();
            for(int j = 0; j < c; j++) {
                board[i][j] = line.charAt(j);
                if(board[i][j] == '*') {
                    water.add(new Point(i, j));
                }
                else if(board[i][j] == 'S') {
                    queue.add(new Point(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        solution();

        if(ans == Integer.MAX_VALUE) System.out.println("KAKTUS");
        else System.out.println(ans);
        
    }

    private static void solution() {

        while(!queue.isEmpty()) {

            // 인접한 칸으로 물을 퍼뜨림
            spreadWater();

            // 고슴도치가 이동할 수 있는 칸 탐색
            moveHedgehog();
            
        }
    }

    private static void spreadWater() {

        HashSet<Point> temp = new HashSet<>();
        temp.addAll(water);

        for(Point w : water) {
            for(int j = 0; j < 4; j++) {
                int xx = w.x + dx[j];
                int yy = w.y + dy[j];

                if(xx < 0 || xx >= r || yy < 0 || yy >= c) continue;
                if(board[xx][yy] == '.') {
                    board[xx][yy] = '*';
                    temp.add(new Point(xx, yy));
                }
            }
        }

        water.addAll(temp);
    }

    private static void moveHedgehog() {

        int size = queue.size();
        for(int i = 0; i < size; i++) {
            Point poll = queue.poll();
            for(int j = 0; j < 4; j++) {
                int xx = poll.x + dx[j];
                int yy = poll.y + dy[j];

                if(xx < 0 || xx >= r || yy < 0 || yy >= c) continue;
                if(visited[xx][yy]) continue;

                if(board[xx][yy] == 'D') {
                    ans = Integer.min(ans, poll.time + 1);
                    return;
                }
                if(board[xx][yy] == '.') {
                    board[poll.x][poll.y] = 'S';
                    queue.add(new Point(xx, yy, poll.time + 1));
                    visited[xx][yy] = true;
                }
            }
        }
    }
    
}
