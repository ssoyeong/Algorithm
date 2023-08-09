import java.io.*;
import java.util.*;

// 배열 돌리기 3

public class BJ16935 {
    
    static int n, m;
    static int r;
    static int[][] board;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < r; i++) {
            int cmd = Integer.parseInt(st.nextToken());
            calculate(cmd);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void calculate(int cmd) {

        switch(cmd) {
            case 1: reverseUpDown();
                break;
            case 2: reverseLeftRight();
                break;
            case 3: rotateRight();
                break;
            case 4: rotateLeft();
                break;
            case 5: moveClockWise();
                break;
            case 6: moveCounterClockWise();
                break;
        }
    }

    private static void reverseUpDown() {

        int rowSize = board.length;
        int colSize = board[0].length;

        for(int i = 0; i < rowSize/2; i++) {
            for(int j = 0; j < colSize; j++) {
                int tmp = board[i][j];
                board[i][j] = board[rowSize - 1 - i][j];
                board[rowSize - 1 - i][j] = tmp;
            }
        }
    }

    private static void reverseLeftRight() {

        int rowSize = board.length;
        int colSize = board[0].length;

        for(int i = 0; i < rowSize; i++) {
            for(int j = 0; j < colSize/2; j++) {
                int tmp = board[i][j];
                board[i][j] = board[i][colSize - 1 - j];
                board[i][colSize - 1 - j] = tmp;
            }
        }
    }

    private static void rotateRight() {

        int rowSize = board.length;
        int colSize = board[0].length;
        int[][] temp = new int[colSize][rowSize];

        // temp[][]에 연산 결과 담기
        for(int i = 0; i < colSize; i++) {
            for(int j = 0; j < rowSize; j++) {
                temp[i][j] = board[rowSize - 1 - j][i];
            }
        }

        // board[][]에 temp[][] 복사하기
        board = new int[board[0].length][rowSize];
        for(int i = 0; i < colSize; i++) {
            for(int j = 0; j < rowSize; j++) {
                board[i][j] = temp[i][j];
            }
        }
    }

    private static void rotateLeft() {
        int rowSize = board.length;
        int colSize = board[0].length;
        int[][] temp = new int[colSize][rowSize];

        // temp[][]에 연산 결과 담기
        for(int i = 0; i < colSize; i++) {
            for(int j = 0; j < rowSize; j++) {
                temp[i][j] = board[j][colSize - 1 - i];
            }
        }

        // board[][]에 temp[][] 복사하기
        board = new int[board[0].length][board.length];
        for(int i = 0; i < colSize; i++) {
            for(int j = 0; j < rowSize; j++) {
                board[i][j] = temp[i][j];
            }
        }
    }

    private static void moveClockWise() {
        int rowSize = board.length;
        int colSize = board[0].length;
        int[] dx = {0, -1, 0};
        int[] dy = {1, 0, -1};
        int[][] temp = new int[rowSize/2][colSize/2];
        
        // 4번 그룹 temp[][]에 담아 두기
        for(int i = 0; i < rowSize/2; i++) {
            for(int j = 0; j < colSize/2; j++) {
                temp[i][j] = board[i + rowSize/2][j];
            }
        }

        // 3, 2, 1번 그룹 순으로 옮기기
        for(int d = 0; d < 3; d++) {
            int startX = 0;
            int startY = 0;
            if(d == 0 || d == 1) {
                startX = rowSize/2;
            }
            if(d == 1 || d == 2) {
                startY = colSize/2;
            }
            
            for(int i = startX; i < startX + rowSize/2; i++) {
                for(int j = startY; j < startY + colSize/2; j++) {
                    board[i][j] = board[i + dx[d] * rowSize/2][j + dy[d] * colSize/2];
                }
            }
        }

        // temp[][]에 있던 4번 그룹 옮기기
        for(int i = 0; i < rowSize/2; i++) {
            for(int j = 0; j < colSize/2; j++) {
                board[i][j] = temp[i][j];
            }
        }
    }

    private static void moveCounterClockWise() {
        int rowSize = board.length;
        int colSize = board[0].length;
        int[] dx = {1, 0, -1};
        int[] dy = {0, -1, 0};
        int[][] temp = new int[rowSize/2][colSize/2];
        
        // 2번 그룹 temp[][]에 담아 두기
        for(int i = 0; i < rowSize/2; i++) {
            for(int j = 0; j < colSize/2; j++) {
                temp[i][j] = board[i][j + colSize/2];
            }
        }

        // 3, 4, 1번 그룹 순으로 옮기기
        for(int d = 0; d < 3; d++) {
            int startX = 0;
            int startY = 0;
            if(d == 1 || d == 2) {
                startX = rowSize/2;
            }
            if(d == 0 || d == 1) {
                startY = colSize/2;
            }
            
            for(int i = startX; i < startX + rowSize/2; i++) {
                for(int j = startY; j < startY + colSize/2; j++) {
                    board[i][j] = board[i + dx[d] * rowSize/2][j + dy[d] * colSize/2];
                }
            }
        }

        // temp[][]에 있던 2번 그룹 옮기기
        for(int i = 0; i < rowSize/2; i++) {
            for(int j = 0; j < colSize/2; j++) {
                board[i][j] = temp[i][j];
            }
        }
    }
}
