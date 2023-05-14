import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 쿼드트리

public class BJ1992 {

    static int n;
    static boolean[][] board;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new boolean[n][n];

        for(int i = 0; i < n; i++) {
            String line = br.readLine();
            for(int j = 0; j < n; j++) {
                if(line.charAt(j) == '1') board[i][j] = true;
            }
        }

        divide(0, 0, n);
        sb.append("\n");
        System.out.println(sb.toString());
    }

    private static void divide(int r, int c, int size) {

        if(isSame(r, c, size)) {
            if(board[r][c]) sb.append("1");
            else sb.append("0");
        }
        else {
            sb.append("(");
            divide(r, c, size/2);
            divide(r, c + size/2, size/2);
            divide(r + size/2, c, size/2);
            divide(r + size/2, c + size/2, size/2);
            sb.append(")");
        }

    }

    private static boolean isSame(int r, int c, int size) {

        boolean flag = board[r][c];
        for(int i = r; i < r + size; i++) {
            for(int j = c; j < c + size; j++) {
                if(board[i][j] != flag) {
                    return false;
                }
            }
        }

        return true;
    }
    
}
