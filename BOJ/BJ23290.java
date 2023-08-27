import java.io.*;
import java.util.*;

// 마법사 상어와 복제

public class BJ23290 {

    static class Fish {
        int r, c, d;
        Fish next;

        Fish(int r, int c) {
            this.r = r;
            this.c = c;
        }

        Fish(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        Fish(int r, int c, int d, Fish next) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.next = next;
        }
    }
    static final int SIZE = 4;
    static Fish shark;
    static Fish[][] board = new Fish[SIZE][SIZE];
    static int[][] boardSmell = new int[SIZE][SIZE];
    static int[][] visited = new int[SIZE][SIZE];
    static PriorityQueue<int[]> movingSharkDir = new PriorityQueue<>((o1, o2) -> o1[0] == o2[0] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o2[0], o1[0]));
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dxShark = {0, -1, 0, 1, 0};
    static int[] dyShark = {0, 0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            board[r][c] = new Fish(r, c, d, board[r][c]);
        }

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        shark = new Fish(r, c);

        for(int time = 0; time < s; time++) {
            Fish[][] copied = new Fish[SIZE][SIZE];
            for(int i = 0; i < SIZE; i++) {
                for(int j = 0; j < SIZE; j++) {
                    copied[i][j] = board[i][j];
                }
            }

            moveFishes();
            moveShark();
            decreaseFishSmell();

            // 복제 마법 완료
            for(int i = 0; i < SIZE; i++) {
                for(int j = 0; j < SIZE; j++) {
                    for(Fish target = copied[i][j]; target != null; target = target.next) {
                        board[i][j] = new Fish(target.r, target.c, target.d, board[i][j]);
                    }
                }
            }
        }

        System.out.println(getNumOfFish());
    }
    
    private static void moveFishes() {

        ArrayDeque<Fish> temp = new ArrayDeque<>();
        for(int i = 0 ; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                for(Fish target = board[i][j]; target != null; target = target.next) {
                    
                    boolean isMoved = false;
                    for(int k = 0; k < 8; k++) {
                        int dir = (target.d + 8 - k) % 8;
                        int xx = target.r + dx[dir];
                        int yy = target.c + dy[dir];

                        if(xx < 0 || xx >= SIZE || yy < 0 || yy >= SIZE) continue;
                        if((shark.r == xx && shark.c == yy) || boardSmell[xx][yy] > 0) continue;

                        temp.add(new Fish(xx, yy, dir));
                        isMoved = true;
                        break;
                    }
                    if(!isMoved) temp.add(target);
                }
                board[i][j] = null;
            }
        }

        while(!temp.isEmpty()) {
            Fish poll = temp.poll();
            board[poll.r][poll.c] = new Fish(poll.r, poll.c, poll.d, board[poll.r][poll.c]);
        }
    }

    private static void moveShark() {

        movingSharkDir.clear();
        for(int i = 0; i < SIZE; i++) {
            Arrays.fill(visited[i], 0);
        }

        dfs(0, shark.r, shark.c, 0, "");        // 상어가 이동할 수 있는 방법을 movingSharkDir에 담아두기
        String dir = String.valueOf(movingSharkDir.poll()[1]);
        int x = shark.r;
        int y = shark.c;

        for(int i = 0; i < 3; i++) {
            x += dxShark[dir.charAt(i) - '0'];
            y += dyShark[dir.charAt(i) - '0'];
            if(board[x][y] != null) {
                boardSmell[x][y] = 3;
                board[x][y] = null;
            }
        }

        shark = new Fish(x, y);
    }

    private static void dfs(int depth, int x, int y, int total, String dir) {

        if(depth == 3) {
            movingSharkDir.add(new int[] {total, Integer.parseInt(dir)});
            return;
        }

        for(int i = 1; i <= 4; i++) {
            int xx = x + dxShark[i];
            int yy = y + dyShark[i];

            if(xx < 0 || xx >= SIZE || yy < 0 || yy >= SIZE) continue;

            int count = 0;
            if(visited[xx][yy] == 0) {
                for(Fish fish = board[xx][yy]; fish != null; fish = fish.next) {
                    count++;
                }
            }
            visited[xx][yy]++;
            dfs(depth + 1, xx, yy, total + count, dir.concat(String.valueOf(i)));
            visited[xx][yy]--;
        }
    }

    private static void decreaseFishSmell() {

        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if(boardSmell[i][j] > 0) boardSmell[i][j]--;
            }
        }
    }

    private static int getNumOfFish() {
        
        int count = 0;
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                for(Fish fish = board[i][j]; fish != null; fish = fish.next) {
                    count++;
                }
            }
        }

        return count;
    }

}
