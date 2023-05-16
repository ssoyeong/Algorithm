import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 주사위 굴리기

public class BJ14499 {

    static int n, m;
    static int x, y;
    static int k;
    static int down = 0, front = 1, right = 2;
    static int[] dice = new int[6];     // 아래 0, 앞 1, 오른쪽 2, 반대 합 5
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        map = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++) {
            int cmd = Integer.parseInt(st.nextToken());

            boolean isValid = solution(cmd);
            if(isValid) {
                sb.append(dice[5-down] + "\n");
            }
        }

        System.out.println(sb.toString());
    }

    private static boolean solution(int cmd) {

        if(!moveDice(cmd)) return false;
        copyNumber();
        return true;
    }

    private static boolean moveDice(int cmd) {

        int xx = x + dx[cmd];
        int yy = y + dy[cmd];

        if(xx < 0 || xx >= n || yy < 0 || yy >= m) return false;
        
        x = xx;
        y = yy;
        
        int tempDown = down;
        if(cmd == 1) {
            down = right;
            right = 5 - tempDown;
        }
        else if(cmd == 2) {
            down = 5 - right;
            right = tempDown;
        }
        else if(cmd == 3) {
            down = 5 - front;
            front = tempDown;
        }
        else {
            down = front;
            front = 5 - tempDown;
        }

        return true;
    }

    private static void copyNumber() {

        if(map[x][y] == 0) {
            map[x][y] = dice[down];
        }
        else {
            dice[down] = map[x][y];
            map[x][y] = 0;
        }
    }
    
}
