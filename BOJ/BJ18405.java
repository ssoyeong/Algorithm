import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 경쟁적 전염

public class BJ18405 {

    private static class Virus implements Comparable<Virus> {
        int time;
        int number;
        int x;
        int y;

        Virus(int time, int number, int x, int y) {
            this.time = time;
            this.number = number;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Virus o) {
            if(this.time != o.time) return this.time - o.time;
            return this.number - o.number;
        }
    }

    static int n, k;
    static int s, x, y;
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static PriorityQueue<Virus> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n+1][n+1];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] != 0) {
                    queue.add(new Virus(0, board[i][j], i, j));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        solution();
        System.out.println(board[x][y]);
    }

    private static void solution() {

        for(int i = 0; i < s; i++) {
            int size = queue.size();
            for(int j = 0; j < size; j++) {
                Virus poll = queue.poll();
                
                for(int k = 0; k < 4; k++) {
                    int xx = poll.x + dx[k];
                    int yy = poll.y + dy[k];
                    if(xx < 1 || xx > n || yy < 1 || yy > n) continue;
                    if(board[xx][yy] != 0) continue;

                    board[xx][yy] = poll.number;
                    queue.add(new Virus(poll.time + 1, board[xx][yy], xx, yy));
                }
            }
        }

    }
    
}
