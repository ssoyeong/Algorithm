import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

// 빙산

public class BJ2573 {

    private static class Point {

        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            return (this.x == ((Point)o).x) && (this.y == ((Point)o).y);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static int n, m;
    static int answer;
    static int[][] map;
    static int[][] tempMap;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Point> queue = new LinkedList<>();
    static HashSet<Point> set = new HashSet<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        tempMap = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                int x = Integer.parseInt(st.nextToken());
                map[i][j] = x;
                if(x != 0) set.add(new Point(i, j));
            }
        }

        solution();
        System.out.println(answer);
    }

    private static void solution() {

        while(true) {

            queue.clear();
            for(int i = 0; i < n; i++) {
                Arrays.fill(visited[i], false);
            }
            
            Iterator iter = set.iterator();
            bfs((Point)iter.next());

            if(isSeperated()) return;

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    map[i][j] = tempMap[i][j];
                }
            }
            answer++;
        }
    }

    private static void bfs(Point start) {

        queue.add(start);
        visited[start.x][start.y] = true;

        while(!queue.isEmpty()) {

            Point poll = queue.poll();

            int cnt = 0;
            for(int i = 0; i < 4; i++) {
               
                int xx = poll.x + dx[i];
                int yy = poll.y + dy[i];

                if(xx < 0 || xx >= n || yy < 0 || yy >= m) continue;
                
                if(map[xx][yy] == 0) cnt++;  
                else {
                    if(!visited[xx][yy]) {
                        queue.add(new Point(xx, yy));
                        visited[xx][yy] = true;
                    }
                }              
            }

            if(cnt >= map[poll.x][poll.y]) {
                tempMap[poll.x][poll.y] = 0;
                set.remove(new Point(poll.x, poll.y));
            }
            else {
                tempMap[poll.x][poll.y] = map[poll.x][poll.y] - cnt;
            }
        }
    }

    private static boolean isSeperated() {

        if(set.isEmpty()) {
            answer = 0;
            return true;
        }

        Iterator iter = set.iterator();
        while(iter.hasNext()) {
            Point p = (Point) iter.next();
            if(!visited[p.x][p.y]) return true;  
        }

        return false;
    }
    
}
