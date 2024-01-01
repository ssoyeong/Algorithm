import java.util.*;

class Solution {
    
    static int n, m;
    static char[][] map;
    static boolean[][] visited;
    static ArrayDeque<int[]> queue = new ArrayDeque<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public int solution(String[] maps) {
        
        n = maps.length;
        m = maps[0].length();
        map = new char[n][m];
        visited = new boolean[n][m];
        int sx = -1;
        int sy = -1;
        int lx = -1;
        int ly = -1;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                map[i][j] = maps[i].charAt(j);
                if(map[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }
                else if(map[i][j] == 'L') {
                    lx = i;
                    ly = j;
                }
            }
        }
        
        int ans = 0;
        // 레버 찾기
        int dist = bfs(sx, sy, 'L');
        if(dist == -1) return dist;
        ans += dist;
        // 출구 찾기
        dist = bfs(lx, ly, 'E');
        if(dist == -1) return dist;
        ans += dist;
        return ans;
    }
    
    private static int bfs(int x, int y, char target) {
        
        for(int i = 0; i < n; i++) {
            Arrays.fill(visited[i], false);
        }
        queue.clear();
        
        visited[x][y] = true;
        queue.add(new int[] {x, y, 0});
        
        while(!queue.isEmpty()) {
            int[] xyd = queue.poll();
            
            for(int d = 0; d < 4; d++) {
                int xx = xyd[0] + dx[d];
                int yy = xyd[1] + dy[d];
                
                if(xx < 0 || xx >= n || yy < 0 || yy >= m || visited[xx][yy] || map[xx][yy] == 'X') continue;
                if(map[xx][yy] == target) return xyd[2] + 1;
                visited[xx][yy] = true;
                queue.add(new int[] {xx, yy, xyd[2] + 1});
            }
        }
        
        return -1;
    }
}
