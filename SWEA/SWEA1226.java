import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// [S/W 문제해결 기본] 7일차 - 미로1

public class SWEA1226 {

    private static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int isValid;
    static Point start, end;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][] visited;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for(int tc = 1; tc <= 10; tc++) {
            br.readLine();
            isValid = 0;
            map = new int[16][16];
            visited = new boolean[16][16];

            for(int i = 0; i < 16; i++) {
                String line = br.readLine();
                for(int j = 0; j < 16; j++) {
                    map[i][j] = line.charAt(j) - '0';
                    if(map[i][j] == 2) {
                        start = new Point(i, j);
                    }
                    else if(map[i][j] == 3) {
                        end = new Point(i, j);
                    }
                }
            }

            visited[start.x][start.y] = true;
            dfs(start);
            sb.append("#" + tc + " " + isValid + "\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(Point p) {

        if(p.x == end.x && p.y == end.y) {
            isValid = 1;
            return;
        }

        for(int i = 0; i < 4; i++) {
            int xx = p.x + dx[i];
            int yy = p.y + dy[i];

            if(xx < 0 || xx >= 16 || yy < 0 || yy >= 16) continue;
            if(map[xx][yy] == 1 || visited[xx][yy]) continue;

            visited[xx][yy] = true;
            dfs(new Point(xx, yy));
            visited[xx][yy] = false;
        }
    }
}
