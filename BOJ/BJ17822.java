import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

// 원판 돌리기

public class BJ17822 {

    private static class Point {
        int x; 
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int n, m, t;
    static int x, d, k;
    static int answer;
    static int cnt;         // 원판에 남아있는 수의 개수
    static boolean flag;    // 더이상 원판을 회전시키지 않아도 되는지 여부
    static int[][] board;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static Stack<Point> stack = new Stack<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        
        cnt = n * m;
        board = new int[n+1][m];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            
            if(flag) continue;
            rotateBoards();

            if(cnt > 0) {
                boolean isValid = searchAdj();
                if(!isValid) {      // 인접하면서 수가 같은 것이 없는 경우
                    updateBoards();
                }
            }
            else {
                flag = true;
            }
        }

        answer = calculateTotal();
        System.out.println(answer);
    }
    
    private static void rotateBoards() {

        for(int i = 1; i <= n; i++) {
            if(i % x == 0) {
                rotateBoard(i);
            }
        }  
    }

    private static void rotateBoard(int target) {

        // 배열 복사
        int[] temp = new int[m];
        for(int i = 0; i < m; i++) {
            temp[i] = board[target][i];
        }

        if(d == 0) {        // 시계 방향 회전
            for(int i = 0; i < m; i++) {
                board[target][i] = temp[(i - k + m) % m];
            }
        }
        else {              // 반시계 방향 회전
            for(int i = 0; i < m; i++) {
                board[target][i] = temp[(i + k) % m];
            }
        }
    }

    private static boolean searchAdj() {

        boolean isValid = false;        // 인접하면서 수가 같은 것의 존재 여부
        
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < m; j++) {

                if(board[i][j] == 0) continue;
                
                // board[i][j]에 수가 남아 있다면
                stack.clear();
                int target = board[i][j];
                stack.add(new Point(i, j));

                // 해당 수와 인접하면서 같은 것이 있는지 찾기
                do {
                    Point pop = stack.pop();

                    for(int d = 0; d < 4; d++) {
                        int xx = pop.x + dx[d];
                        int yy = (pop.y + dy[d] + m) % m;

                        if(xx < 1 || xx > n) continue;
                        if(board[xx][yy] == target) {
                            // 처음 선택한 수에 대해 인접한 수가 있을 때 처리
                            if(!isValid) {
                                board[pop.x][pop.y] = 0;
                                cnt--;
                            }
                            isValid = true;
                            board[xx][yy] = 0;
                            cnt--;
                            stack.add(new Point(xx, yy));
                        }
                    }
                } while(!stack.isEmpty());
            }
        }

        return isValid;
    }

    private static void updateBoards() {

        double total = calculateTotal();
        double avg = total / cnt;

        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < m; j++) {
                if(board[i][j] > avg) board[i][j] -= 1;
                else if(board[i][j] < avg && board[i][j] != 0) board[i][j] += 1;
            }
        }
    }

    private static int calculateTotal() {

        int total = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < m; j++) {
                if(board[i][j] != 0) total += board[i][j];
            }
        }

        return total;
    }

}
