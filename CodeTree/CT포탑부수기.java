import java.io.*;
import java.util.*;

public class CT포탑부수기 {
    
    static class Point {
        int x;
        int y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Top implements Comparable<Top> {
        int score;
        int attacked;
        int rc;
        int r;
        int c;
        
        Top(int score, int attacked, int rc, int r, int c) {
            this.score = score;
            this.attacked = attacked;
            this.rc = rc;
            this.r = r;
            this.c = c;
        }
        
        @Override
        public int compareTo(Top o) {
            if(this.score == o.score) {
                if(this.attacked == o.attacked) {
                    if(this.rc == o.rc) {
                        return o.c - this.c;
                    }
                    return o.rc - this.rc;
                }
                return o.attacked - this.attacked;
            }
            return this.score - o.score;
        }
    }
    
    static int n, m;
    static int time;
    static Point start, end;
    static int[][] map;
    static int[][] attacked;
    static boolean[][] visited;
    static int[][] backX;
    static int[][] backY;
    static ArrayList<Top> tops = new ArrayList<>();
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[] dx2 = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy2 = {1, 1, 0, -1, -1, -1, 0, 1};
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        map = new int[n][m];
        attacked = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        while(k-- > 0) {
            
        	time++;
            visited = new boolean[n][m];
            backX = new int[n][m];
            backY = new int[n][m];
            if(!initTops()) break;
            setTarget();
            map[start.x][start.y] += (n + m);
            if(!attackRazer()) attackBomb();
            
            int cnt = 0;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(map[i][j] > 0) cnt++;
                }
            }
            if(cnt <= 1) break;
            upScore();
        }
        
        initTops();
        setTarget();
        System.out.println(map[end.x][end.y]);
    }
    
    private static boolean initTops() {
        
        tops.clear();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 0) continue;
                tops.add(new Top(map[i][j], attacked[i][j], (i + j), i, j));
            }
        }
        if(tops.size() <= 1) return false;
        return true;
    }
    
    private static void setTarget() {
        
        Collections.sort(tops);
        start = new Point(tops.get(0).r, tops.get(0).c);
        end = new Point(tops.get(tops.size() - 1).r, tops.get(tops.size() - 1).c);
        attacked[start.x][start.y] = time;
    }
    
    private static boolean attackRazer() {
        
        ArrayDeque<Point> queue = new ArrayDeque<>();
        visited[start.x][start.y] = true;
        queue.add(start);
        
        boolean flag = false;
        while(!queue.isEmpty()) {
            
            Point poll = queue.poll();
            if(poll.x == end.x && poll.y == end.y) {
                flag = true;
                break;
            }
            
            for(int d = 0; d < 4; d++) {
                
                int xx = (poll.x + dx[d] + n) % n;
                int yy = (poll.y + dy[d] + m) % m;
                
                if(map[xx][yy] == 0 || visited[xx][yy]) continue;
                visited[xx][yy] = true;
                backX[xx][yy] = poll.x;
                backY[xx][yy] = poll.y;
                queue.add(new Point(xx, yy));
            }
        }
        
        visited = new boolean[n][m];
        
        if(flag) {
            
            map[end.x][end.y] -= map[start.x][start.y];
            if(map[end.x][end.y] < 0) map[end.x][end.y] = 0;

            int half = map[start.x][start.y] / 2;
            int r = backX[end.x][end.y];
            int c = backY[end.x][end.y];
            
            while(true) {
                if(r == start.x && c == start.y) break;
                map[r][c] -= half;
                if(map[r][c] < 0) map[r][c] = 0;
                visited[r][c] = true;
                int newR = backX[r][c];
                int newC = backY[r][c];
                r = newR;
                c = newC;
            }
        }
        
        return flag;
    }
    
    private static void attackBomb() {
        
        map[end.x][end.y] -= map[start.x][start.y];
        if(map[end.x][end.y] < 0) map[end.x][end.y] = 0;
        
        for(int d = 0; d < 8; d++) {
            
            int xx = (end.x + dx2[d] + n) % n;
            int yy = (end.y + dy2[d] + m) % m;

            if(map[xx][yy] == 0 || (xx == start.x && yy == start.y)) continue;
            int half = map[start.x][start.y] / 2;
            map[xx][yy] -= half;
            if(map[xx][yy] < 0) map[xx][yy] = 0;
            visited[xx][yy] = true;
        }
    }
    
    private static void upScore() {
        
        visited[start.x][start.y] = true;
        visited[end.x][end.y] = true;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 0 || visited[i][j]) continue;
                map[i][j]++;
            }
        }
    }
    
}
