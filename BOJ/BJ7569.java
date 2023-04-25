import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 토마토

public class BJ7569 {

    private static class Box {

        int x;
        int y;
        int z;
        int cnt;

        Box(int x, int y, int z, int cnt) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.cnt = cnt;
        }
    }

    static int m, n, h;
    static int numOfZero;
    static int ans;
    static int[][][] board;
    static Queue<Box> queue = new LinkedList<>();
    static int[] dx = {0, 0, 0, 0, 1, -1};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {1, -1, 0, 0, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        board = new int[n][m][h];

        for(int k = 0; k < h; k++) {
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < m; j++) {
                    int input = Integer.parseInt(st.nextToken());
                    board[i][j][k] = input;
                    if(input == 1) {
                        queue.add(new Box(i, j, k, 0));
                    }
                    else if(input == 0) {
                        numOfZero++;
                    }
                }
            }
        }

        if(numOfZero > 0) {
            bfs();
            if(numOfZero > 0) ans = -1;
        }

        System.out.println(ans);
    }

    private static void bfs() {

        while(!queue.isEmpty()) {

            if(numOfZero == 0) return;
            Box poll = queue.poll();

            for(int i = 0; i < 6; i++) {
                int xx = poll.x + dx[i];
                int yy = poll.y + dy[i];
                int zz = poll.z + dz[i];

                if(xx < 0 || xx >= n || yy < 0 || yy >= m || zz < 0 || zz >= h) continue;
                if(board[xx][yy][zz] == 0) {

                    board[xx][yy][zz] = 1;
                    numOfZero--;
                    queue.add(new Box(xx, yy, zz, poll.cnt + 1));
                    ans = Integer.max(ans, poll.cnt + 1);
                }
            }
        }
    }

}
