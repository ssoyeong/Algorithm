import java.util.*;

class Solution {
    
    static int n;
    static int m;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static ArrayDeque<int[]> queue = new ArrayDeque<>();
    
    public int solution(String[] board) {
        
        n = board.length;
        m = board[0].length();
        map = new char[n][m];
        visited = new boolean[n][m];
        
        int sx = -1;
        int sy = -1;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                map[i][j] = board[i].charAt(j);
                if(map[i][j] == 'R') {
                    sx = i;
                    sy = j;
                }
            }
        }
        
        visited[sx][sy] = true;
        queue.add(new int[] {sx, sy, 0});
        
        while(!queue.isEmpty()) {
            int[] xyc = queue.poll();
            for(int d = 0; d < 4; d++) {
                int[] newPos = getNewPos(xyc[0], xyc[1], d);
                int nx = newPos[0];
                int ny = newPos[1];
                
                if(nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == 'D') {
                    nx -= dx[d];
                    ny -= dy[d];
                }
                if(map[nx][ny] == 'G') return xyc[2] + 1;
                
                if(visited[nx][ny]) continue;
                visited[nx][ny] = true;
                queue.add(new int[] {nx, ny, xyc[2] + 1});
            }
        }
        
        return -1;
    }
    
    private static int[] getNewPos(int x, int y, int d) {
        while(true) {
            x += dx[d];
            y += dy[d];
            if(x < 0 || x >= n || y < 0 || y >= m || map[x][y] == 'D') {
                return new int[] {x, y};
            }
        }
    }
}
