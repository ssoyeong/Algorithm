import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 어른 상어

public class BJ19237 {

    private static class Point {
        int shark;
        int smell;

        Point(int shark, int smell) {
            this.shark = shark;
            this.smell = smell;
        }
    }
    private static class Shark {
        int x;
        int y;
        int dir;

        Shark(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Shark(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    private static class Next implements Comparable<Next> {
        int dir;
        int priority;

        Next(int dir, int priority) {
            this.dir = dir;
            this.priority = priority;
        }

        @Override
        public int compareTo(Next o) {
            return this.priority - o.priority;
        }
    }
    static int n, m, k;
    static int time;
    static int cntOfSharks;
    static Point[][] board;
    static Point[][] temp;
    static Shark[] sharks;
    static int[][][] priority;
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // n, m, k 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        cntOfSharks = m;
        board = new Point[n][n];
        temp = new Point[n][n];
        sharks = new Shark[m + 1];
        priority = new int[m + 1][5][4];

        // 격자 입력받기
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                int input = Integer.parseInt(st.nextToken());
                if(input != 0) {
                    sharks[input] = new Shark(i, j);
                    board[i][j] = new Point(input, k);
                }
            }
        }

        // 방향 입력받기
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= m; i++) {
            int dir = Integer.parseInt(st.nextToken());
            sharks[i] = new Shark(sharks[i].x, sharks[i].y, dir);
        }

        // 우선순위 입력받기
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= 4; j++) {
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < 4; k++) {
                    priority[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        solution();
        System.out.println(time);
    }

    private static void solution() {

        while(true) {
            time++;

            if(time > 1000) {
                time = -1;
                break;
            }

            // 배열 복사
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    temp[i][j] = board[i][j];

                    // 냄새를 1씩 감소
                    if(temp[i][j] != null) {
                        if(temp[i][j].smell == 1) {
                            temp[i][j] = null;
                        }
                        else {
                            temp[i][j] = new Point(temp[i][j].shark, temp[i][j].smell - 1);
                        }
                    }
                }
            }

            // 상어 이동
            for(int i = 1; i <= m; i++) {
                if(sharks[i] != null) {

                    int nextDir = searchNextForNumber(i);     // i번 상어에 대해 다음에 이동할 방향을 찾음
                    int xx = sharks[i].x + dx[nextDir];
                    int yy = sharks[i].y + dy[nextDir];

                    if(temp[xx][yy] != null && temp[xx][yy].shark < i) {      // 같은 칸에 여러 상어가 들어갈 경우
                        sharks[i] = null;
                        cntOfSharks--;
                        continue;
                    }
                    sharks[i] = new Shark(xx, yy, nextDir);
                    temp[xx][yy] = new Point(i, k);
                }
            }

            // 배열 복사
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    board[i][j] = temp[i][j];
                }
            }

            if(cntOfSharks == 1) break;
        }
    }

    private static int searchNextForNumber(int num) {

        Shark shark = sharks[num];
        int[] priorityByTarget = new int[5];        // 타겟 상어의 방향에 따른 우선순위 
        for(int i = 0; i < 4; i++) {
            int dir = priority[num][shark.dir][i];
            priorityByTarget[dir] = i;              // priorityByTarget[a] = b : 방향 a에 대한 우선순위 b
        }

        PriorityQueue<Next> nextForEmpty = new PriorityQueue<>();       // 아무 냄새가 없는 칸
        PriorityQueue<Next> nextForMySmell = new PriorityQueue<>();       // 자신의 냄새가 있는 칸

        for(int i = 1; i <= 4; i++) {
            int xx = shark.x + dx[i];
            int yy = shark.y + dy[i];

            if(xx < 0 || xx >= n || yy < 0 || yy >= n) continue;

            if(board[xx][yy] == null) {
                nextForEmpty.add(new Next(i, priorityByTarget[i]));
            }
            else if(board[xx][yy].shark == num) {
                nextForMySmell.add(new Next(i, priorityByTarget[i]));
            }
        }

        if(!nextForEmpty.isEmpty()) {
            return nextForEmpty.poll().dir;
        }
        else {
            return nextForMySmell.poll().dir;
        }
    }
    
}
