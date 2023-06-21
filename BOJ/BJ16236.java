import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// 아기 상어

public class BJ16236 {

    private static class Fish implements Comparable<Fish> {
        int x;
        int y;
        int cnt;

        Fish(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        Fish(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Fish o) {
            if(this.cnt == o.cnt) {
                if(this.x == o.x) {
                    return this.y - o.y;
                }
                return this.x - o.x;
            }
            return this.cnt - o.cnt;
        }  
    }
    static int n;
    static int time;
    static int sizeOfBaby = 2;
    static int numOfEaten = 0;
    static Fish baby;                   // 아기 상어의 위치
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board;
    static boolean[][] visited;
    static Queue<Fish> queue = new LinkedList<>();                  // bfs 탐색을 위한 queue
    static PriorityQueue<Fish> selected = new PriorityQueue<>();    // 먹을 수 있는 물고기를 담는 queue
 
     public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        visited = new boolean[n][n];

        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                int x = Integer.parseInt(st.nextToken());
                board[i][j] = x;
                if(x == 9) {
                    baby = new Fish(i, j);
                }
            }
        }

        solution();
        System.out.println(time);
     }

     private static void solution() {

        while(true) {

            // 먹을 물고기를 선정
            Fish target = bfs();

            // 종료 조건
            if(target.x == -1) break;

            // 이동과 동시에 물고기를 먹음
            time += target.cnt;
            board[target.x][target.y] = 9;
            board[baby.x][baby.y] = 0;
            baby = new Fish(target.x, target.y);
            
            numOfEaten++;
            if(numOfEaten == sizeOfBaby) {
                sizeOfBaby++;
                numOfEaten = 0;
            }
        }
     }

     private static Fish bfs() {

        for(int i = 0; i < n; i++) {
            Arrays.fill(visited[i], false);
        }
        queue.clear();
        selected.clear();

        visited[baby.x][baby.y] = true;
        queue.add(new Fish(baby.x, baby.y, 0));

        while(!queue.isEmpty()) {

            Fish poll = queue.poll();
            for(int i = 0; i < 4; i++) {
                int xx = poll.x + dx[i];
                int yy = poll.y + dy[i];

                if(xx < 0 || xx >= n || yy < 0 || yy >= n) continue;
                if(visited[xx][yy] || board[xx][yy] > sizeOfBaby) continue;

                visited[xx][yy] = true;
                queue.add(new Fish(xx, yy, poll.cnt + 1));

                if(board[xx][yy] < sizeOfBaby && board[xx][yy] != 0) {
                    selected.add(new Fish(xx, yy, poll.cnt + 1));
                }
            }
        }

        if(selected.isEmpty()) {
            return new Fish(-1, -1);        // 더 이상 먹을 수 있는 물고기가 없는 경우
        }
        else {
            return selected.poll();
        }
    }
}

