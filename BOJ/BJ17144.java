import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.StringTokenizer;

// 미세먼지 안녕!

public class BJ17144 {

    private static class Point {

        int x;
        int y;
        int amount;

        Point(int x, int y, int amount) {
            this.x = x;
            this.y = y;
            this.amount = amount;
        }
    }

    static int r, c;
    static int t;
    static int[][] board;
    static int[][] temp;
    static int[] cleanerRows = new int[2];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static HashSet<Point> dustSet = new HashSet<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        board = new int[r+1][c+1];
        temp = new int[r+1][c+1];

        for(int i = 1; i <= r; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= c; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] > 0) {
                    dustSet.add(new Point(i, j, board[i][j]));
                }
                if(board[i][j] == -1 && cleanerRows[0] == 0) {
                    cleanerRows[0] = i;
                    cleanerRows[1] = i+1;
                }
            }
        }

        int ans = solution();
        System.out.println(ans);
    }

    private static int solution() {

        for(int time = 0; time < t; time++) {
            dustSet.clear();

            for(int i = 1; i <= r; i++) {
                for(int j = 1; j <= c; j++) {
                    temp[i][j] = 0;
                    if(board[i][j] > 0) dustSet.add(new Point(i, j, board[i][j]));
                }
            }

            spreadDust();
            runCleaner();
        }

        // 남아있는 미세먼지 합계
        int total = 0;
        for(int i = 1; i <= r; i++) {
            for(int j = 1; j <= c; j++) {
                if(board[i][j] != -1) {
                    total += board[i][j];
                }
            }
        }

        return total;
    }

    private static void spreadDust() {

        for(Point dust : dustSet) {

            int amount = Math.floorDiv(dust.amount, 5);
            if(amount <= 0) continue;

            int cnt = 0;
            for(int i = 0; i < 4; i++) {

                int xx = dust.x + dx[i];
                int yy = dust.y + dy[i];

                if(xx <= 0 || xx > r || yy <= 0 || yy > c) continue;
                if(board[xx][yy] == -1) continue;

                temp[xx][yy] += amount;
                cnt++;
            }

            board[dust.x][dust.y] -= amount * cnt;
        }

        for(int i = 1; i <= r; i++) {
            for(int j = 1; j <= c; j++) {
                board[i][j] += temp[i][j];
            }
        }
    }

    private static void runCleaner() {

        /* 위쪽 공기청정기 */
        // 아래로 이동
        for(int i = cleanerRows[0]-2; i >= 1; i--) {
            board[i+1][1] = board[i][1];
        }
        // 왼쪽으로 이동
        for(int i = 2; i <= c; i++) {
            board[1][i-1] = board[1][i];
        }
        // 위로 이동
        for(int i = 2; i <= cleanerRows[0]; i++) {
            board[i-1][c] = board[i][c];
        }
        // 오른쪽으로 이동
        for(int i = c-1; i >= 2; i--) {
            board[cleanerRows[0]][i+1] = board[cleanerRows[0]][i];
        }

        board[cleanerRows[0]][2] = 0;

        /* 아래쪽 공기청정기 */
        // 위로 이동
        for(int i = cleanerRows[1] + 2; i <= r; i++) {
            board[i-1][1] = board[i][1];
        }
        // 왼쪽으로 이동
        for(int i = 2; i <= c; i++) {
            board[r][i-1] = board[r][i];
        }
        // 아래로 이동
        for(int i = r-1; i >= cleanerRows[1]; i--) {
            board[i+1][c] = board[i][c];
        }
        // 오른쪽으로 이동
        for(int i = c-1; i >= 2; i--) {
            board[cleanerRows[1]][i+1] = board[cleanerRows[1]][i];
        }

        board[cleanerRows[1]][2] = 0;
    }
       
}
