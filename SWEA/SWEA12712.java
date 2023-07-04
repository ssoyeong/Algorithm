import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SWEA12712 {

    static int t;
    static int n, m;
    static int[][] board;
    static int max;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int tc = 1; tc <= t; tc++) {

            max = 0;

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            board = new int[n][n];

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            solution();
            sb.append("#" + tc + " " + max + "\n");
        }

        System.out.println(sb.toString());
    }
    
    private static void solution() {

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                sprayPlus(i, j);
                sprayMultiplication(i, j);
            }
        }
    }

    private static void sprayPlus(int r, int c) {

        int total = 0;

        // 가로 방향
        for(int i = c - m + 1; i <= c + m - 1; i++) {
            if(0 <= i && i < n) {
                total += board[r][i];
            }
        }

        // 세로 방향
        for(int i = r - m + 1; i <= r + m - 1; i++) {
            if(0 <= i && i < n) {
                total += board[i][c];
            }
        }

        total -= board[r][c];
        max = Integer.max(max, total);
    }

    private static void sprayMultiplication(int r, int c) {

        int total = 0;

        for(int i = 1; i < m; i++) {

            if(isValid(r - i, c - i)) {
                total += board[r - i][c - i];
            }

            if(isValid(r - i, c + i)) {
                total += board[r - i][c + i];
            }

            if(isValid(r + i, c - i)) {
                total += board[r + i][c - i];
            }

            if(isValid(r + i, c + i)) {
                total += board[r + i][c + i];
            }
        }

        total += board[r][c];
        max = Integer.max(max, total);
    }

    private static boolean isValid(int rr, int cc) {
        if((0 <= rr && rr < n) && (0 <= cc && cc < n)) return true;
        return false;
    }

}
