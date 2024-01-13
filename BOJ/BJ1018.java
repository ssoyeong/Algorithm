import java.io.*;
import java.util.*;

// 체스판 다시 칠하기

public class BJ1018 {

    static final int SIZE = 8;
    static int n, m;
    static int min = Integer.MAX_VALUE;
    static boolean[][][] chess = new boolean[2][SIZE][SIZE];
    static boolean[][] board;

    public static void main(String[] args) throws Exception {

        // init white & board
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if((i + j) % 2 == 1) chess[0][i][j] = true;
                else chess[1][i][j] = true;
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new boolean[n][m];

        for(int i = 0; i < n; i++) {
            String line = br.readLine();
            for(int j = 0; j < m; j++) {
                if(line.charAt(j) == 'B') board[i][j] = true;
            }
        }

        for(int i = 0; i < n - SIZE + 1; i++) {
            for(int j = 0; j < m - SIZE + 1; j++) {
                check(i, j);
            }
        }

        System.out.println(min);
    }

    private static void check(int r, int c) {

        int cnt = 0;
        boolean flag = false;

        // start white
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if(board[i + r][j + c] != chess[0][i][j]) cnt++;
                if(cnt >= min) {
                    flag = true;
                    break;
                }
            }
            if(flag) break;
        }

        if(!flag) min = cnt;
        cnt = 0;
        flag = false;

        // start black
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if(board[i + r][j + c] != chess[1][i][j]) cnt++;
                if(cnt >= min) {
                    flag = true;
                    break;
                }
            }
            if(flag) break;
        }

        if(!flag) min = cnt;
    }
}
