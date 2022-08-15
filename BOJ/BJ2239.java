import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 스도쿠

public class BJ2239 {

    static int[][] board = new int[9][9];
    static boolean flag;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 9; i++) {
            String input = br.readLine();
            for(int j = 0; j < 9; j++) {
                board[i][j] = input.charAt(j) - '0';
            }
        }
        
        backTracking(0, 0);
        print();
    }

    private static void backTracking(int r, int c) {

        if(r == 9) {
            flag = true;
            return;
        }

        if(board[r][c] != 0) {
            if(c == 8) backTracking(r+1, 0);
            else backTracking(r, c+1);
        }
        else {

            for(int n = 1; n <= 9; n++) {

                if(isPromising(r, c, n)) {
                    board[r][c] = n;
                    if(c == 8) backTracking(r+1, 0);
                    else backTracking(r, c+1);

                    if(flag) return;
                    board[r][c] = 0;
                }
            }
        }
    }

    private static boolean isPromising(int r, int c, int n) {

        for(int i = 0; i < 9; i++) {
            if(board[i][c] == n) return false;
            if(board[r][i] == n) return false;
        }

        for(int i = r/3*3; i < r/3*3+3; i++) {
            for(int j = c/3*3; j < c/3*3+3; j++) {
                if(board[i][j] == n) return false;
            }
        }

        return true;
    }

    private static void print() {

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        } 
    }
    
}
