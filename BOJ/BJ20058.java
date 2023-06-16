import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;

// 마법사 상어와 파이어스톰

public class BJ20058 {

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int n, q;
    static int l;
    static int size;
    static int cntOfTheBiggest, maxOfTheBiggest;
    static int[][] board;
    static boolean[][] isIce;
    static boolean[][] visited;
    static Queue<Point> queue = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        size = (int)Math.pow(2, n);
        board = new int[size][size];
        isIce = new boolean[size][size];
        visited = new boolean[size][size];

        for(int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < size; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < q; i++) {
            l = Integer.parseInt(st.nextToken());
            firestorm();
        }

        int total = sumOfRemainIce();
        searchTheBiggest();

        StringBuilder sb = new StringBuilder();
        sb.append(total + "\n" + maxOfTheBiggest + "\n");
        System.out.println(sb.toString());
    }

    private static void firestorm() {

        int length = (int)Math.pow(2, l);       // 나누어진 부분 격자의 한 변의 길이
        
        /**** Step 1. 2^L 크기의 부분 격자로 나눈 후, 모든 부분 격자를 회전시킴 ****/
        for(int i = 0; i < size; i += length) {
            for(int j = 0; j < size; j += length) {
                rotate(i, j, length);
            }
        }

        /**** Step 2. 모든 칸에 대해 인접한 칸 중 얼음이 있는 칸의 캐수가 3개 미만이면, 얼음의 양이 1 줄어듦 ****/
        
        // isIce[][]에 얼음 여부 저장
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(board[i][j] > 0) isIce[i][j] = true;
                else isIce[i][j] = false;
            }
        }

        // 얼음이 있는 칸이 3개 미만이라면, 얼음의 양 1 감소
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {

                if(board[x][y] == 0) continue;

                int cnt = 0;
                for(int dir = 0; dir < 4; dir++) {
                    int xx = x + dx[dir];
                    int yy = y + dy[dir];

                    if(xx < 0 || xx >= size || yy < 0 || yy >= size) continue;
                    if(isIce[xx][yy]) cnt++;
                }

                if(cnt < 3) board[x][y]--;
            }
        }
    }

    private static void rotate(int startX, int startY, int length) {

        // 부분 격자에 해당하는 만큼 temp[][]에 board[][] 값 복사
        int[][] temp = new int[length][length];
        for(int i = startX; i < startX + length; i++) {
            for(int j = startY; j < startY + length; j++) {
                temp[i - startX][j - startY] = board[i][j];
            }
        }

        // temp[][]의 값을 열 단위로 시계방향 90도 회전시켜 board[][]에 옮김
        for(int c = 0; c < length; c++) {
            for(int r = 0; r < length; r++) {
                board[c + startX][length - r + startY - 1] = temp[r][c];
            }
        }
    }

    private static int sumOfRemainIce() {

        int total = 0;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                total += board[i][j];
            }
        }

        return total;
    }

    private static void searchTheBiggest() {

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(board[i][j] == 0 || visited[i][j]) continue;
                visited[i][j] = true;
                queue.add(new Point(i, j));
                cntOfTheBiggest = 1;
                bfs();
                maxOfTheBiggest = Integer.max(maxOfTheBiggest, cntOfTheBiggest);
            }
        }
    }

    private static void bfs() {

        while(!queue.isEmpty()) {

            Point poll = queue.poll();

            for(int i = 0; i < 4; i++) {
                int xx = poll.x + dx[i];
                int yy = poll.y + dy[i];

                if(xx < 0 || xx >= size || yy < 0 || yy >= size) continue;
                if(visited[xx][yy] || board[xx][yy] == 0) continue;

                visited[xx][yy] = true;
                queue.add(new Point(xx, yy));
                cntOfTheBiggest++;
            }
        }
    }
    
}
