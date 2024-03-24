import java.io.*;
import java.util.*;

// 연구소 2

public class BJ17141 {
    
    static class Pos {
        int x;
        int y;
        int sec;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Pos(int x, int y, int sec) {
            this.x = x;
            this.y = y;
            this.sec = sec;
        }
    }
    static int n, m;
    static int numOfNotWall;
    static int numOfVirus;
    static int min = Integer.MAX_VALUE;
    static boolean[] selected;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<Pos> twos = new ArrayList<>();
    static ArrayDeque<Pos> queue = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        visited = new boolean[n][n];
        
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) twos.add(new Pos(i, j));
                if(map[i][j] != 1) numOfNotWall++;
            }
        }
        selected = new boolean[twos.size()];

        setPositionOfVirus(0, 0);
        if(min == Integer.MAX_VALUE) min = -1;
        System.out.println(min);
    }

    private static void setPositionOfVirus(int depth, int idx) {

        if(depth == m) {
            spreadVirus();
            return;
        }

        for(int i = idx; i < twos.size(); i++) {
            selected[i] = true;
            setPositionOfVirus(depth + 1, i + 1);
            selected[i] = false;
        }
    }

    private static void spreadVirus() {

        // init
        int endTime = 0;
        numOfVirus = 0;
        visited = new boolean[n][n];
        queue.clear();

        // start spreading virus
        for(int i = 0; i < selected.length; i++) {
            if(selected[i]) {
                int x = twos.get(i).x;
                int y = twos.get(i).y;
                queue.add(new Pos(x, y, 0));
                visited[x][y] = true;
                numOfVirus++;
            }
        }
        
        while(!queue.isEmpty()) {
            Pos poll = queue.poll();
            for(int d = 0; d < 4; d++) {
                int xx = poll.x + dx[d];
                int yy = poll.y + dy[d];

                if(xx < 0 || xx >= n || yy < 0 || yy >= n) continue;
                if(map[xx][yy] == 1 || visited[xx][yy]) continue;

                queue.add(new Pos(xx, yy, poll.sec + 1));
                visited[xx][yy] = true;
                numOfVirus++;
                endTime = Integer.max(endTime, poll.sec + 1);
                if(endTime >= min) return;
            }
        }

        // update minimum time
        if(numOfVirus == numOfNotWall) min = endTime;
    }
}
