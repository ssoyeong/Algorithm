import java.io.*;
import java.util.*;

// 상범 빌딩

public class BJ6593 {
    
    static class Point {
        int x;
        int y;
        int z;
        int time;

        Point(int x, int y, int z, int time) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.time = time;
        } 
    }
    static int L, R, C;
    static char[][][] map;
    static boolean[][][] visited;
    static int[] dx = {0, 0, 1, -1, 0, 0};
    static int[] dy = {1, -1, 0, 0, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static ArrayDeque<Point> queue = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(true) {

            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if(L == 0 && R == 0 && C == 0) break;

            map = new char[R][C][L];
            visited = new boolean[R][C][L];
            queue.clear();

            int l = 0;
            while(l < L) {
                for(int i = 0; i < R; i++) {
                    String line = br.readLine();
                    for(int j = 0; j < C; j++) {
                        map[i][j][l] = line.charAt(j);
                        if(map[i][j][l] == 'S') {
                            queue.add(new Point(i, j, l, 0));
                            visited[i][j][l] = true;
                        }
                    }
                }

                br.readLine();
                l++;
            }

            int result = bfs();
            if(result == -1) sb.append("Trapped!\n");
            else sb.append("Escaped in " + result + " minute(s).\n");
        }

        System.out.println(sb.toString());
    }

    private static int bfs() {

        while(!queue.isEmpty()) {

            Point poll = queue.poll();
            for(int d = 0; d < 6; d++) {
                
                int xx = poll.x + dx[d];
                int yy = poll.y + dy[d];
                int zz = poll.z + dz[d];

                if(xx < 0 || xx >= R || yy < 0 || yy >= C || zz < 0 || zz >= L || visited[xx][yy][zz]) continue;
                if(map[xx][yy][zz] == 'E') return poll.time + 1;
                if(map[xx][yy][zz] == '.') {
                    queue.add(new Point(xx, yy, zz, poll.time + 1));
                    visited[xx][yy][zz] = true;
                }
            }
        }

        return -1;
    }
}
