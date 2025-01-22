import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 숫자 정사각형

public class BJ1051 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][m];
        for(int i = 0; i < n; i++) {
            String line = br.readLine();
            for(int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(solution(n, m, board));
    }

    private static int solution(int n, int m, int[][] board) {

        int length = Integer.min(n, m);
        while(length > 0) {
            for(int i = 0; i < n; i++) {
                if((i + length - 1) >= n) break;
                for(int j = 0; j < m; j++) {
                    if((j + length - 1) >= m) break;

                    int a = board[i][j];
                    int b = board[i + length - 1][j];
                    int c = board[i][j + length - 1];
                    int d = board[i + length - 1][j + length - 1];
                    if(a == b && a == c && a == d) return length * length;
                }
            }
            length--;
        }

        return 1;
    }
}
