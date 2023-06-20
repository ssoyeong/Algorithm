import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 주사위 굴리기 2

public class BJ23288 {

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int n, m;
    static int k;
    static int dir = 0;                                 // 0(동), 1(남), 2(서), 3(북)
    static int total;
    static Point currentPos = new Point(1, 1);      // 현재 주사위의 위치
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[] dice = {1, 3, 5};                      // 위쪽, 오른쪽, 앞쪽 주사위의 수
    static int[][] board;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n+1][m+1];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < k; i++) {

            // 1. 이동 방향으로 한 칸 굴러감
            moveDice();

            // 2. 도착한 칸에 대한 점수 획득
            getScore();

            // 3. 이동 방향을 결정
            setDirection();
        }

        System.out.println(total);
        
    }

    private static void moveDice() {

        int xx = currentPos.x + dx[dir];
        int yy = currentPos.y + dy[dir];

        // 범위를 벗어난다면, 이동 방향을 반대로 함
        if(xx < 1 || xx > n || yy < 1 || yy > m) {
            dir = dir % 2 == 0 ? 2 - dir : 4 - dir;
            xx = currentPos.x + dx[dir];
            yy = currentPos.y + dy[dir];
        }

        // 해당 방향으로 한 칸 이동
        currentPos = new Point(xx, yy);
        int zero = dice[0];
        int one = dice[1];
        int two = dice[2];
        if(dir % 2 == 0) {
            if(dir == 0) {
                dice[0] = 7 - one;
                dice[1] = zero;
            }
            else {
                dice[0] = one;
                dice[1] = 7 - zero;
            }
        }
        else {
            if(dir == 1) {
                dice[0] = 7 - two;
                dice[2] = zero;
            }
            else {
                dice[0] = two;
                dice[2] = 7 - zero;
            }
        }
    }

    private static void getScore() {

        int B = board[currentPos.x][currentPos.y];
        int C = 0;

        boolean[][] visited = new boolean[n+1][m+1];
        Queue<Point> queue = new LinkedList<>();
        visited[currentPos.x][currentPos.y] = true;
        C++;
        queue.add(new Point(currentPos.x, currentPos.y));

        while(!queue.isEmpty()) {
            Point poll = queue.poll();

            for(int i = 0; i < 4; i++) {
                int xx = poll.x + dx[i];
                int yy = poll.y + dy[i];

                if(xx < 1 || xx > n || yy < 1 || yy > m) continue;
                if(visited[xx][yy] || board[xx][yy] != B) continue;
                
                visited[xx][yy] = true;
                C++;
                queue.add(new Point(xx, yy));
            }
        }

        total += B * C;
    }

    private static void setDirection() {

        int A = 7 - dice[0];
        int B = board[currentPos.x][currentPos.y];

        if(A > B) {
            dir = (dir + 1) % 4;
        }
        else if(A < B) {
            dir = (dir + 3) % 4;
        }
    }
    
}
