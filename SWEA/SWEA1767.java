import java.io.*;
import java.util.*;

// [SW Test 샘플문제] 프로세서 연결하기

public class SWEA1767 {

    static int tc;
    static int n;
    static int ans;
    static int numOfCores;                              // 코어의 개수
    static boolean[] selected = new boolean[12];        // 선택한 코어인지의 여부
    static int[][] board;
    static int[][] cores = new int[12][2];              // 코어의 좌표값
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int t = 1; t <= tc; t++) {

            // 변수 초기화
            ans = Integer.MAX_VALUE;
            numOfCores = 0;
            
            // 입력 받기
            n = Integer.parseInt(br.readLine());
            board = new int[n][n];
            
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if(board[i][j] == 1 && (i != 0 && i != n - 1 && j != 0 && j != n - 1)) {        // 가장자리가 아닌 코어인 경우 cores에 넣어줌 (가장자리인 경우 무조건 선택해야 하므로 탐색 대상에서 제외)
                        cores[numOfCores][0] = i;
                        cores[numOfCores][1] = j;
                        numOfCores++; 
                    }
                }
            }
            
            // r개의 코어들 조합하기
            for(int r = numOfCores; r > 0; r--) {

                Arrays.fill(selected, false);
                comb(r, 0, 0);
                if(ans != Integer.MAX_VALUE) break;
            }

            sb.append("#" + t + " " + ans + "\n");
        }

        System.out.println(sb.toString());
    }

    private static void comb(int r, int depth, int idx) {

        // r개의 코어들을 모두 선택했으므로 연결할 수 있는 전선 탐색하기
        if(depth == r) {
            backTracking(0, 0);
            return;
        }

        for(int i = idx; i < numOfCores; i++) {
            selected[i] = true;
            comb(r, depth + 1, i + 1);
            selected[i] = false;
        }
    }

    private static void backTracking(int depth, int totalDist) {        // depth번째 코어에 대해 조사

        if(depth == numOfCores) {       // 모든 코어들을 조사했다면 ans 업데이트하기
            ans = Integer.min(ans, totalDist);
            return;
        }
        if(!selected[depth]) {          // 선택된 코어가 아니라면, 다음 코어로 넘어가기
            backTracking(depth + 1, totalDist);
            return;
        }

        // depth번째 코어에 대하여 연결할 수 있는 전선 탐색하기
        for(int d = 0; d < 4; d++) {

            boolean isValid = false;        // 유효한 전선인지의 여부
            int x = cores[depth][0];
            int y = cores[depth][1];
            int dist = 0;

            while(true) {
                x += dx[d];
                y += dy[d];
                
                if(x < 0 || x >= n || y < 0 || y >= n) {    // 전원을 만났다면 유효한 전선
                    isValid = true;
                    break;
                }
                if(board[x][y] != 0) break;                 // 코어나 전선을 만났다면 탐색 종료
                board[x][y] = 9;                            // 전선을 놓은 위치는 9로 표시
                dist++;
            }

            // 유효한 전선을 찾았다면, 다음 코어에 대해 탐색 이어가기
            if(isValid) {
                backTracking(depth + 1, totalDist + dist);
            }

            // board 되돌리기
            while(true) {
                x -= dx[d];
                y -= dy[d];
                if(x == cores[depth][0] && y == cores[depth][1]) break;
                board[x][y] = 0;
            }
        }
    }

}