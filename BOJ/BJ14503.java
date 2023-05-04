import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 로봇 청소기

public class BJ14503 {

    static int n, m;
    static int robotX, robotY;
    static int direction;       // 0: 북쪽, 1: 동쪽, 2: 남쪽, 3: 서쪽
    static int cnt;
    static int[][] board;       // 0: 청소되지 않은 빈 칸, 1: 벽, 2: 청소된 빈 칸
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        st = new StringTokenizer(br.readLine());
        robotX = Integer.parseInt(st.nextToken());
        robotY = Integer.parseInt(st.nextToken());
        direction = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                if(st.nextToken().equals("1")) board[i][j] = 1;
            }
        }

        solution();
        System.out.println(cnt);
    }
    
    private static void solution() {

        while(true) {
            if(board[robotX][robotY] == 0) {        // 1번 동작
                board[robotX][robotY] = 2;
                cnt++;
            }

            boolean flag = false;
            for(int i = 0; i < 4; i++) {
                int xx = robotX + dx[i];
                int yy = robotY + dy[i];

                if(board[xx][yy] == 0) {
                    flag = true;
                    break;
                }
            }

            if(!flag) {                             // 2번 동작 
                int backX = robotX + dx[direction];
                int backY = robotY + dy[direction];

                if(board[backX][backY] == 1) return;
                else {
                    robotX = backX;
                    robotY = backY;
                }
            }
            else {                                  // 3번 동작
                direction = (direction + 3) % 4;
                int frontX = robotX + dx[(direction + 2) % 4];
                int frontY = robotY + dy[(direction + 2) % 4];

                if(board[frontX][frontY] == 0) {
                    robotX = frontX;
                    robotY = frontY;
                }
            }
        }
    }

}
