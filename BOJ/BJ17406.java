import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 배열 돌리기 4

public class BJ17406 {

    private static class RotaInfo {
        int r;
        int c;
        int s;

        RotaInfo(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    static int n, m, k;
    static int answer = Integer.MAX_VALUE;
    static int[][] board;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[] visited;
    static RotaInfo[] rotaArr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n+1][m+1];
        visited = new boolean[k+1];
        rotaArr = new RotaInfo[k+1];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            rotaArr[i] = new RotaInfo(r, c, s);
        }

        backTracking(0);
        System.out.println(answer);
    }

    private static void backTracking(int level) {

        if(level == k) {
            int valueOfMatrix = calculate();
            answer = Math.min(answer, valueOfMatrix);
            return;
        }

        for(int i = 1; i <= k; i++) {

            if(visited[i]) continue;
            visited[i] = true;
            
            int[][] temp = new int[n+1][m+1];
            for(int r = 1; r <= n; r++) {
                for(int c = 1; c <= m; c++) {
                    temp[r][c] = board[r][c];
                }
            }

            rotate(i);
            backTracking(level+1);
            visited[i] = false;

            for(int r = 1; r <= n; r++) {
                for(int c = 1; c <= m; c++) {
                    board[r][c] = temp[r][c];
                }
            }
        }
    }

    private static void rotate(int rotaIdx) {

        int r = rotaArr[rotaIdx].r;
        int c = rotaArr[rotaIdx].c;
        int s = rotaArr[rotaIdx].s;

        int x = r - s;  // 좌상단 x좌표
        int y = c - s;  // 좌상단 y좌표

        for(int i = 0; i < s; i++) {
            cycle(x+i, y+i, 2*(s-i)+1);     // cycle별 좌상단 x좌표, 좌상단 y좌표, 정사각형 한 변의 길이 
        }

    }

    private static void cycle(int r, int c, int length) {

        int x = r;
        int y = c;
        int tempVal = board[x][y];

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < length-1; j++) {
                int xx = x + dx[i];
                int yy = y + dy[i];
                board[x][y] = board[xx][yy];
                x = xx;
                y = yy; 
            }
        }

        board[r][c+1] = tempVal;
    }

    private static int calculate() {

        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) {
            int sum = 0;
            for(int j = 1; j <= m; j++) {
                sum += board[i][j];
            }
            min = Math.min(min, sum);
        }
        
        return min;
    }
    
}
