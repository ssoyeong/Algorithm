import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 마법사 상어와 파이어볼

public class BJ20056 {

    private static class Ball {
        int r;
        int c;
        int m;
        int s;
        int d;
        int cnt;
        int flag;           // 합쳐진 파이어볼 방향, 홀짝 섞이면 -1

        Ball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        Ball(int m, int s, int cnt, int flag) {
            this.m = m;
            this.s = s;
            this.cnt = cnt;
            this.flag = flag;
        }
    }
    static int n, m, k;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static Ball[][] board;
    static Queue<Ball> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new Ball[n+1][n+1];

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            queue.add(new Ball(r, c, m, s, d));
        }
        
        for(int cmd = 0; cmd < k; cmd++) {
            moveBalls();
            combineDivideBalls();
        }

        int ans = 0;
        while(!queue.isEmpty()) {
            Ball poll = queue.poll();
            ans += poll.m;
        }
        System.out.println(ans);
    }

    private static void moveBalls() {

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                board[i][j] = new Ball(0, 0, 0, 0);
            }
        }

        while(!queue.isEmpty()) {

            Ball poll = queue.poll();

            int xx = (poll.r + (dx[poll.d] * poll.s % n)) % n;
            int yy = (poll.c + (dy[poll.d] * poll.s % n)) % n;

            if(xx <= 0) xx += n;
            if(yy <= 0) yy += n;

            Ball target = board[xx][yy];

            // 속력의 합
            target.m += poll.m;

            // 질량의 합
            target.s += poll.s;

            // 방향의 홀짝 여부
            if(target.flag != -1) {         // 이미 방향이 유지되지 않은 경우가 아니라면
                if(target.cnt == 0) {       // 처음 들어오는 파이어볼인 경우
                    target.flag = poll.d;
                }
                else {                      // 그외 방향이 유지되지 않는다면 -1로 설정
                    target.flag = (target.flag % 2) == (poll.d % 2) ? target.flag : -1;
                }
            }
                
            // 합쳐진 개수
            target.cnt++;
        }
    }

    private static void combineDivideBalls() {

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                Ball target = board[i][j];

                if(target.cnt == 0) continue;
                if(target.cnt == 1) {
                    queue.add(new Ball(i, j, target.m, target.s, target.flag));
                    continue;
                }
                if(target.m < 5) continue;
                
                int dir = 0;
                if(target.flag == -1) {      // 합쳐진 파이어볼의 방향이 다른 경우
                    dir = 1;
                }

                for(int k = 0; k < 4; k++) {
                    queue.add(new Ball(i, j, target.m / 5, target.s / target.cnt, k * 2 + dir));
                }
            }
        }
    }
    
}
