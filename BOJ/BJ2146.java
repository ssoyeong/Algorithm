import java.io.*;
import java.util.*;

// 다리 만들기

public class BJ2146 {
    
    static int n;
    static int ans = Integer.MAX_VALUE;
    static int[][] board;
    static ArrayDeque<int[]> queue = new ArrayDeque<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                if(st.nextToken().equals("1")) board[i][j] = -1;
            }
        }

        searchIslands();
        searchBridges();
        System.out.println(ans);
    }

    private static void searchIslands() {

        int id = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == -1) bfsIsland(i, j, ++id);
            }
        }
    }
 
    private static void bfsIsland(int x, int y, int id) {

        board[x][y] = id;
        queue.add(new int[] {x, y});

        while(!queue.isEmpty()) {
            int[] poll = queue.poll();
            for(int i = 0; i < 4; i++) {
                int xx = poll[0] + dx[i];
                int yy = poll[1] + dy[i];

                if(xx < 0 || xx >= n || yy < 0 || yy >= n) continue;
                if(board[xx][yy] == -1) {
                    board[xx][yy] = id;
                    queue.add(new int[] {xx, yy});
                }
            }
        }
    }

    private static void searchBridges() {
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] != 0) {
                    bfsBridge(i, j, 0, board[i][j]);
                }
            }
        }
    }

    private static void bfsBridge(int x, int y, int dist, int myId) {

        boolean[][] visited = new boolean[n][n];
        queue.clear();
        visited[x][y] = true;
        queue.add(new int[] {x, y, dist});

        while(!queue.isEmpty()) {

            int[] poll = queue.poll();
            for(int i = 0; i < 4; i++) {
                int xx = poll[0] + dx[i];
                int yy = poll[1] + dy[i];

                if(xx < 0 || xx >= n || yy < 0 || yy >= n || visited[xx][yy] || board[xx][yy] == myId) continue;
                if(board[xx][yy] > 0) {
                    ans = Integer.min(ans, poll[2]);
                    return;
                }
                if(poll[2] + 1 < ans) {
                    visited[xx][yy] = true;
                    queue.add(new int[] {xx, yy, poll[2] + 1});
                }
            }
        }
    }
}
