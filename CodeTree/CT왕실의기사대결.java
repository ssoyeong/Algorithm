import java.io.*;
import java.util.*;

public class CT왕실의기사대결 {
    
    static class Knight {
        int r;
        int c;
        int h;
        int w;
        int k;
        boolean exist;

        public Knight(boolean exist) {
            this.exist = exist;
        }

        public Knight(int r, int c, int h, int w, int k) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
            this.k = k;
        }
    }

    static int l, n, q;
    static boolean flag;
    static Knight[] knights;
    static int[][] board;
    static int[][] pos;
    static boolean[] visited;
    static int[] initK;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static ArrayDeque<Integer> stack = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        knights = new Knight[n + 1];
        board = new int[l][l];
        pos = new int[l][l];
        visited = new boolean[n + 1];
        initK = new int[n + 1];

        for(int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < l; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i <= n; i++) {
            knights[i] = new Knight(true);
            st = new StringTokenizer(br.readLine());
            knights[i].r = Integer.parseInt(st.nextToken()) - 1;
            knights[i].c = Integer.parseInt(st.nextToken()) - 1;
            knights[i].h = Integer.parseInt(st.nextToken());
            knights[i].w = Integer.parseInt(st.nextToken());
            knights[i].k = Integer.parseInt(st.nextToken());
            initK[i] = knights[i].k;

            int r = knights[i].r;
            int c = knights[i].c;
            int h = knights[i].h;
            int w = knights[i].w;
            for(int x = r; x < r + h; x++) {
                for(int y = c; y < c + w; y++) {
                    pos[x][y] = i;
                }
            }
        }

        while(q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            command(i, d);
        }

        int ans = 0;
        for(int i = 1; i <= n; i++) {
            if(knights[i].exist) {
                ans += initK[i] - knights[i].k;
            }
        }
        System.out.println(ans);
    }

    private static void command(int idx, int dir) {

        if(!knights[idx].exist) return;
        flag = false;
        Arrays.fill(visited, false);
        stack.clear();
        
        // find knights to be pushed
        visited[idx] = true;
        stack.add(idx);
        dfs(idx, dir);
        if(flag) return;

        // move pushed knights
        while(!stack.isEmpty()) {
            int num = stack.poll();
            knights[num].r = knights[num].r + dx[dir];
            knights[num].c = knights[num].c + dy[dir];
        }

        for(int i = 1; i <= n; i++) {
            boolean out = false;
            if(i == idx || !knights[i].exist || !visited[i]) continue;
            int r = knights[i].r;
            int c = knights[i].c;
            int h = knights[i].h;
            int w = knights[i].w;
            for(int x = r; x < r + h; x++) {
                for(int y = c; y < c + w; y++) {
                    if(board[x][y] == 1) {
                        knights[i].k--;
                        if(knights[i].k == 0) {
                            knights[i].exist = false;
                            out = true;
                            break;
                        }
                    }
                }
                if(out) break;
            }
        }

        // reset pos[][]
        for(int i = 0; i < l; i++) {
            Arrays.fill(pos[i], 0);
        }
        for(int i = 1; i <= n; i++) {
            if(!knights[i].exist) continue;
            int r = knights[i].r;
            int c = knights[i].c;
            int h = knights[i].h;
            int w = knights[i].w;
            for(int x = r; x < r + h; x++) {
                for(int y = c; y < c + w; y++) {
                    pos[x][y] = i;
                }
            }
        }
    }

    private static void dfs(int idx, int dir) {

        if(flag) return;

        // search the edge side
        int x = knights[idx].r;
        int y = knights[idx].c;
        if(dir == 0) {
            x = x - 1;
            while(y < knights[idx].c + knights[idx].w) {
                if(x < 0 || board[x][y] == 2) {
                    flag = true;
                    return;
                }
                else if(pos[x][y] != 0 && !visited[pos[x][y]]) {
                    visited[pos[x][y]] = true;
                    stack.add(pos[x][y]);
                    dfs(pos[x][y], dir);
                }
                y++;
            }
        }
        else if(dir == 1) {
            y = y + knights[idx].w;
            while(x < knights[idx].r + knights[idx].h) {
                if(y >= l || board[x][y] == 2) {
                    flag = true;
                    return;
                }
                else if(pos[x][y] != 0 && !visited[pos[x][y]]) {
                    visited[pos[x][y]] = true;
                    stack.add(pos[x][y]);
                    dfs(pos[x][y], dir);
                }
                x++;
            }
        }
        else if(dir == 2) {
            x = knights[idx].r + knights[idx].h;
            while(y < knights[idx].c + knights[idx].w) {
                if(x >= l || board[x][y] == 2) {
                    flag = true;
                    return;
                }
                else if(pos[x][y] != 0 && !visited[pos[x][y]]) {
                    visited[pos[x][y]] = true;
                    stack.add(pos[x][y]);
                    dfs(pos[x][y], dir);
                }
                y++;
            }
        }
        else {
            y = y - 1;
            while(x < knights[idx].r + knights[idx].h) {
                if(y < 0 || board[x][y] == 2) {
                    flag = true;
                    return;
                }
                else if(pos[x][y] != 0 && !visited[pos[x][y]]) {
                    visited[pos[x][y]] = true;
                    stack.add(pos[x][y]);
                    dfs(pos[x][y], dir);
                }
                x++;
            }
        } 
    }
}
