import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 종이의 개수

public class BJ1780 {

    static int n;
    static int numOfMinusOne, numOfZero, numOfPlusOne;
    static int[][] board;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(0, 0, n);

        StringBuilder sb = new StringBuilder();
        sb.append(numOfMinusOne + "\n");
        sb.append(numOfZero + "\n");
        sb.append(numOfPlusOne + "\n");
        System.out.println(sb.toString());
    }

    private static void divide(int r, int c, int length) {

        if(isSame(r, c, length)) {
            switch(board[r][c]) {
                case -1: numOfMinusOne++;
                         break;
                case 0: numOfZero++;
                         break;
                case 1: numOfPlusOne++;  
            }

            return;
        }

        int newLength = length / 3;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                divide(r + newLength * i, c + newLength * j, newLength);
            }
        }
    }

    private static boolean isSame(int r, int c, int length) {

        int target = board[r][c];
        for(int i = r; i < r + length; i++) {
            for(int j = c; j < c + length; j++) {
                if(board[i][j] != target) return false;
            }
        }

        return true;
    }
    
}
