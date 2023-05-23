import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 감시

public class BJ15683 {

    private static class CCTV {
        int x;
        int y;
        int type;

        CCTV(int x, int y) {
            this.x = x;
            this.y = y;
        }

        CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
    static int n, m;
    static int numOfBlindSpot;
    static int[][] board;
    static Queue<CCTV> queueForFiveCCTV = new LinkedList<>();
    static ArrayList<CCTV> arrForOtherCCTV = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        numOfBlindSpot = n * m;
        board = new int[n][m];
        
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 5) {
                    queueForFiveCCTV.add(new CCTV(i, j));
                }
                else if(1 <= board[i][j] && board[i][j] <= 4) {
                    arrForOtherCCTV.add(new CCTV(i, j, board[i][j]));
                }
            }
        }

        solution();
        System.out.println(numOfBlindSpot);
    }

    private static void solution() {

        // 5번 CCTV 처리
        while(!queueForFiveCCTV.isEmpty()) {
            CCTV poll = queueForFiveCCTV.poll();
            upside(poll.x, poll.y, board);
            downside(poll.x, poll.y, board);
            leftside(poll.x, poll.y, board);
            rightside(poll.x, poll.y, board);
        }

        // 1~4번 CCTV 처리
        int[][] temp = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                temp[i][j] =board[i][j];
            }
        }
        backTracking(0, temp);
    }

    private static void backTracking(int idx, int[][] temp) {

        if(idx == arrForOtherCCTV.size()) {
            int cnt = countNumOfBlindSpot(temp);
            numOfBlindSpot = Integer.min(numOfBlindSpot, cnt);
          
            return;
        }

        int[][] result = new int[n][m];

        CCTV target = arrForOtherCCTV.get(idx);
        if(target.type == 1) {

            copyAToB(temp, result);
            result = upside(target.x, target.y, result);
            backTracking(idx + 1, result);

            copyAToB(temp, result);
            result = downside(target.x, target.y, result);
            backTracking(idx + 1, result);

            copyAToB(temp, result);
            result = leftside(target.x, target.y, result);
            backTracking(idx + 1, result);

            copyAToB(temp, result);
            result = rightside(target.x, target.y, result);
            backTracking(idx + 1, result);
        }
        else if(target.type == 2) {

            copyAToB(temp, result);
            result = leftside(target.x, target.y, result);
            result = rightside(target.x, target.y, result);
            backTracking(idx + 1, result);

            copyAToB(temp, result);
            result = upside(target.x, target.y, result);
            result = downside(target.x, target.y, result);
            backTracking(idx + 1, result);
        }
        else if(target.type == 3) {

            copyAToB(temp, result);
            result = upside(target.x, target.y, result);
            result = rightside(target.x, target.y, result);
            backTracking(idx + 1, result);

            copyAToB(temp, result);
            result = rightside(target.x, target.y, result);
            result = downside(target.x, target.y, result);
            backTracking(idx + 1, result);

            copyAToB(temp, result);
            result = downside(target.x, target.y, result);
            result = leftside(target.x, target.y, result);
            backTracking(idx + 1, result);

            copyAToB(temp, result);
            result = leftside(target.x, target.y, result);
            result = upside(target.x, target.y, result);
            backTracking(idx + 1, result);
        }
        else {

            copyAToB(temp, result);
            result = upside(target.x, target.y, result);
            result = leftside(target.x, target.y, result);
            result = rightside(target.x, target.y, result);
            backTracking(idx + 1, result);

            copyAToB(temp, result);
            result = upside(target.x, target.y, result);
            result = downside(target.x, target.y, result);
            result = rightside(target.x, target.y, result);
            backTracking(idx + 1, result);

            copyAToB(temp, result);
            result = leftside(target.x, target.y, result);
            result = downside(target.x, target.y, result);
            result = rightside(target.x, target.y, result);
            backTracking(idx + 1, result);

            copyAToB(temp, result);
            result = leftside(target.x, target.y, result);
            result = downside(target.x, target.y, result);
            result = upside(target.x, target.y, result);
            backTracking(idx + 1, result);
        }
    }

    private static int[][] upside(int x, int y, int[][] temp) {

        int xx = x;
        while(true) {
            xx--;
            if(xx < 0) break;
            if(temp[xx][y] == 6) break;
            if(temp[xx][y] == 0) temp[xx][y] = 9;
        }

        return temp;
    }

    private static int[][] downside(int x, int y, int[][] temp) {

        int xx = x;
        while(true) {
            xx++;
            if(xx >= n) break;
            if(temp[xx][y] == 6) break;
            if(temp[xx][y] == 0) temp[xx][y] = 9;
        }

        return temp;
    }

    private static int[][] leftside(int x, int y, int[][] temp) {

        int yy = y;
        while(true) {
            yy--;
            if(yy < 0) break;
            if(temp[x][yy] == 6) break;
            if(temp[x][yy] == 0) temp[x][yy] = 9;
        }

        return temp;
    }

    private static int[][] rightside(int x, int y, int[][] temp) {

        int yy = y;
        while(true) {
            yy++;
            if(yy >= m) break;
            if(temp[x][yy] == 6) break;
            if(temp[x][yy] == 0) temp[x][yy] = 9;
        }

        return temp;
    }

   
    private static void copyAToB(int[][] A, int[][] B) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                B[i][j] = A[i][j];
            }
        }
    }
    
    private static int countNumOfBlindSpot(int[][] temp) {

        int cnt = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(temp[i][j] == 0) cnt++;
            }
        }

        return cnt;
    }
    
}
