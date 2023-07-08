import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.IOException;

// 마법사 상어와 블리자드

public class BJ21611 {

    static int n, m;
    static int d, s;
    static int sharkPos;
    static int[] cntOfExploded = new int[4];  // 번호별 폭발한 구슬의 개수
    static int[][] board;
    static int[] dx = {0, -1, 1, 0, 0};       // 상 하 좌 우
    static int[] dy = {0, 0, 0, -1, 1};
    static ArrayList<Integer> marbles = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        sharkPos = (n + 1) / 2;
        board = new int[n + 1][n + 1];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < m; i++) {

            marbles.clear();

            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());

            solution();
        }

        int ans = 0;
        for(int i = 1; i <= 3; i++) {
            ans += i * cntOfExploded[i];
        }
        System.out.println(ans);
    }

    private static void solution() {

        // 얼음 파편을 던져 구슬을 파괴
        destroyMarbles();

        // 구슬 폭발
        while(true) {
            boolean flag = explodeMarbles();        // 더 이상 폭발할 구슬이 있는지의 여부
            if(!flag) break;
        }
        if(marbles.size() == 1) return;             // 상어만 들어있는 경우

        // 구슬이 변화
        changeMarbles();
    }

    private static void destroyMarbles() {

        // d 방향으로 거리 s 이하의 구슬 파괴
        for(int i = 1; i <= s; i++) {
            int xx = sharkPos + dx[d] * i;
            int yy = sharkPos + dy[d] * i;

            board[xx][yy] = 0;
        }

        // 빈칸을 제외하고 보드의 구슬을 리스트에 담기
        moveMarblesToList();
    }

    private static void moveMarblesToList() {

        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};
        
        int x = sharkPos;
        int y = sharkPos;
        marbles.add(board[x][y]);

        int time = 0;
        int cnt = 1;
        int idx = 0;
        while(true) {
            for(int i = 0; i < 2; i++) {
                for(int j = 0; j < cnt; j++) {
                    x = x + dx[idx % 4];
                    y = y + dy[idx % 4];
    
                    if(board[x][y] != 0) marbles.add(board[x][y]);
                    time++;
                    if(time == n * n) return;
                }
                idx++;
            }
            cnt++;
        }
    }

    private static boolean explodeMarbles() {

        boolean flag = false;       // 폭발한 구슬이 있는지의 여부
        ArrayList<Integer> tempMarbles = new ArrayList<>();
        if(marbles.size() == 1) tempMarbles.add(0);

        int pre = marbles.get(0);
        int preIdx = 0;
        int cnt = 1;

        for(int i = 1; i < marbles.size(); i++) {
            
            // 현재 구슬과 비교하여 연속하는 경우
            if(marbles.get(i) == pre) {
                cnt++;

                if(i == marbles.size() - 1) {
                    if(cnt < 4) {                           // 마지막 구슬에 대하여 4개 미만 연속한다면 tempMarbles에 넣어주기
                        for(int j = 0; j < cnt; j++) {
                        tempMarbles.add(pre);
                        }
                    }
                    else {                                  // 마지막 구슬에 대하여 4개 이상 연속한다면 폭발하기
                        flag = true;
                        cntOfExploded[pre] += cnt;
                    }
                }
            }
            // 현재 구슬과 비교하여 연속하지 않는 경우
            else {
                if(cnt < 4) {                               // 4개 미만 연속한다면 tempMarbles에 넣어주기
                    for(int j = preIdx; j < i; j++) {
                        tempMarbles.add(marbles.get(j));
                    }
                }
                else {                                      // 4개 이상 연속한다면 폭발하기
                    flag = true;
                    cntOfExploded[pre] += cnt;
                }

                // 이전 구슬에 대한 정보 업데이트
                pre = marbles.get(i);
                preIdx = i;
                cnt = 1;

                if(i == marbles.size() - 1) {               // 마지막 구슬에 대하여 tempMarbles에 넣어주기
                    tempMarbles.add(marbles.get(i));
                }
            }
        }

        marbles = tempMarbles;

        return flag;
    }
    
    private static void changeMarbles() {

        ArrayList<Integer> tempMarbles = new ArrayList<>();
        tempMarbles.add(marbles.get(0));

        if(marbles.size() > 1) {
            int pre = marbles.get(1);
            int cnt = 1;

            for(int i = 2; i < marbles.size(); i++) {

                if(marbles.get(i) == pre) {     // 연속한다면 cnt값 증가
                    cnt++;
                }
                else {                          // 연속하지 않는다면 지금까지 카운트한 구슬 정보 넣어주기
                    tempMarbles.add(cnt);
                    tempMarbles.add(pre);
                    
                    // 이전 구슬에 대한 정보 업데이트
                    pre = marbles.get(i);
                    cnt = 1;
                }
            }

            // 마지막 구슬에 대한 정보 넣어주기
            tempMarbles.add(cnt);
            tempMarbles.add(pre);
        }

        marbles = tempMarbles;

        // 리스트의 구슬을 보드로 옮기기
        moveMarblesToBoard();
    }

    private static void moveMarblesToBoard() {

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                board[i][j] = 0;
            }
        }

        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};
        
        int x = sharkPos;
        int y = sharkPos;
        int pos = 0;
        board[x][y] = marbles.get(pos++);

        int cnt = 1;
        int idx = 0;
        while(true) {
            for(int i = 0; i < 2; i++) {
                for(int j = 0; j < cnt; j++) {
                    x = x + dx[idx % 4];
                    y = y + dy[idx % 4];
                    board[x][y] = marbles.get(pos++);
                    if(pos >= n * n || pos == marbles.size()) return;       // 구슬이 보드 칸의 수보다 많거나 marbles의 모든 구슬을 넣은 경우
                }
                idx++;
            }
            cnt++;
        }
    }
    
}
