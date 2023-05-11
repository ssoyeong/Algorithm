import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 마법사 상어와 비바라기

public class BJ21610 {

    private static class Cloud {
        int x;
        int y;
        int amount;

        Cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Cloud(int x, int y, int amount) {
            this.x = x;
            this.y = y;
            this.amount = amount;
        }

        @Override
        public boolean equals(Object o) {
            if(this.x == ((Cloud)o).x && this.y == ((Cloud)o).y) return true;
            return false;
        }
    }
    static int n, m;
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] diagonalDx = {-1, -1, 1, 1};
    static int[] diagonalDy = {-1, 1, -1, 1};
    static int[][] board;
    static Queue<Cloud> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n+1][n+1];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n ;j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 비바라기 시전하여 초기 비구름이 생김
        queue.add(new Cloud(n, 1));
        queue.add(new Cloud(n, 2));
        queue.add(new Cloud(n-1, 1));
        queue.add(new Cloud(n-1, 2));

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            // 1번 명령
            moveAllCloud(direction, distance);
            // 2번 명령
            increaseAmountOfCloud();
            // 3번 명령 (구름이 모두 사라진다)
            // 4번 명령
            waterCopyBug();
            // 5번 명령
            createCloud();
        }

        int ans = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                ans += board[i][j];
            }
        }
        System.out.println(ans);
    }

    private static void moveAllCloud(int direction, int distance) {

        int size = queue.size();
        for(int i = 0; i < size; i++) {
            Cloud poll = queue.poll();

            int xx = poll.x + (dx[direction] * distance);
            xx = (xx + n * 50) % n;
            if(xx == 0) xx = n;
            int yy = poll.y + (dy[direction] * distance);
            yy = (yy + n * 50) % n;
            if(yy == 0) yy = n;

            queue.add(new Cloud(xx, yy));
        }
    }

    private static void increaseAmountOfCloud() {
        
        for(Cloud cloud : queue) {
            board[cloud.x][cloud.y]++;
        }
    }

    private static void waterCopyBug() {

        Queue<Cloud> temp = new LinkedList<>();

        for(Cloud cloud : queue) {

            int cnt = 0;
            for(int i = 0; i < 4; i++) {
                int xx = cloud.x + diagonalDx[i];
                int yy = cloud.y + diagonalDy[i];

                if(xx < 1 || xx > n || yy < 1 || yy > n) continue;
                if(board[xx][yy] > 0) cnt++;
            }

            temp.add(new Cloud(cloud.x, cloud.y, cnt));
        }

        for(Cloud cloud : temp) {
            board[cloud.x][cloud.y] += cloud.amount;
        }
    }

    private static void createCloud() {

        Queue<Cloud> temp = new LinkedList<>();

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(board[i][j] >= 2 && !queue.contains(new Cloud(i, j))) {
                    board[i][j] -= 2;
                    temp.add(new Cloud(i, j));
                }
            }
        }

        queue.clear();
        queue.addAll(temp);
    }
    
}
