import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 마법사 상어와 토네이도

public class BJ20057 {

    static int n;
    static int ans;
    static int[][] board;
    static int[][] posX = new int[1][2];
    static int[][] posY = new int[1][2];
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
     static int[][][] spreadPos = {{{-1, 1}, {-1, 0}, {-1, -1}, {-2, 0},    // y보다 위에 있는 칸들
                                {1, 1}, {1, 0}, {1, -1}, {2, 0},            // y보다 아래에 있는 칸들
                                {0, -2}, {0, -1}},                          // y와 같은 행의 칸들
                                {{-1, -1}, {0, -1}, {1, -1}, {0, -2},
                                {-1, 1}, {0, 1}, {1, 1}, {0, 2},
                                {2, 0}, {1, 0}}};
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        posX[0][0] = n / 2;
        posX[0][1] = n / 2;

        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();
        System.out.println(ans);
    }

    private static void solution() {

        int cntOfMove = 0;      // 한 칸씩 이동하는 횟수

        while(true) {
            for(int i = 0; i < 4; i++) {
                if(i % 2 == 0) cntOfMove++;

                for(int j = 0; j < cntOfMove; j++) {

                    // y의 좌표 구하기
                    posY[0][0] = posX[0][0] + dx[i];
                    posY[0][1] = posX[0][1] + dy[i];

                    // 모래가 일정한 비율로 흩날림
                    spreadSand(i);

                    // X의 위치가 Y가 있던 위치로 이동
                    posX[0][0] = posY[0][0];
                    posX[0][1] = posY[0][1];

                    // 종료 조건
                    if(posX[0][0] == 0 && posX[0][1] == 0) return;
                }
            }
        }
    }

    private static void spreadSand(int dir) {
        double movedSand = 0;
        int totalOfMovedSand = 0;

        for(int i = 0; i < 10; i++) {

            int xx = posY[0][0];
            int yy =  posY[0][1];
            if(dir == 0 || dir == 1) {
                xx += spreadPos[dir % 2][i][0];
                yy += spreadPos[dir % 2][i][1];
            }
            else if(dir == 2) {
                xx += spreadPos[dir % 2][i][0];
                yy -= spreadPos[dir % 2][i][1];
            }
            else {
                xx -= spreadPos[dir % 2][i][0];
                yy += spreadPos[dir % 2][i][1];
            }
            
            // 비율에 따른 이동할 모래의 양
            if(i < 8) {
                if(i % 4 == 0) movedSand = Math.floor(board[posY[0][0]][posY[0][1]] * 0.01);
                else if(i % 4 == 1) movedSand = Math.floor(board[posY[0][0]][posY[0][1]] * 0.07);
                else if(i % 4 == 2) movedSand = Math.floor(board[posY[0][0]][posY[0][1]] * 0.1);
                else movedSand = Math.floor(board[posY[0][0]][posY[0][1]] * 0.02);            
            }
            else if(i == 8) {
                movedSand = Math.floor(board[posY[0][0]][posY[0][1]] * 0.05);
            }
            else {      // 알파로 이동하는 경우

                if(xx < 0 || xx >= n || yy < 0 || yy >= n) {
                    ans += board[posY[0][0]][posY[0][1]] - totalOfMovedSand;
                }
                else {
                    board[xx][yy] += board[posY[0][0]][posY[0][1]] - totalOfMovedSand;
                }
                board[posY[0][0]][posY[0][1]] = 0;
                break;
            }

            if(xx < 0 || xx >= n || yy < 0 || yy >= n) {
                ans += movedSand;
            }
            else {
                board[xx][yy] += movedSand;
            }
            totalOfMovedSand += movedSand;
        }
    }

}
