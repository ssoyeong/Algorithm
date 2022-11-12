import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

// 보급로

class SWEA1249 {
    
    static int t;
    static int n;
    static int[][] arr;
    static boolean[][] visited;
    static int[][] dp;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Point> queue = new LinkedList<>();
    static class Point {
    	int x;
        int y;
        
         Point(int x, int y) {
        	this.x = x;
            this.y = y;
         }
    }
    
    public static void main(String[] args) throws Exception {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        for(int tc = 1; tc <= t; tc++) {
        	
            n = Integer.parseInt(br.readLine());
            arr = new int[n][n];
            visited = new boolean[n][n];
            dp = new int[n][n];
            for(int i = 0; i < n; i++) {
            	Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            
            for(int i = 0; i < n; i++) {
            	String line = br.readLine();
                for(int j = 0; j < n; j++) {
                	arr[i][j] = line.charAt(j) - '0';
                }
            }
            
            bfs();
            
            sb.append(String.format("#%d %d\n", tc, dp[n-1][n-1]));
        }
        
        System.out.println(sb.toString());
    }
    
    private static void bfs() {

        queue.add(new Point(0, 0));
        visited[0][0] = true;
        dp[0][0] = arr[0][0];
    	
		while(!queue.isEmpty()) {
        
        	Point poll = queue.poll();
            
            for(int i = 0; i < 4; i++) {
        	
            	int xx = poll.x + dx[i];
            	int yy = poll.y + dy[i];
            
            	if(xx < 0 || xx >= n || yy < 0 || yy >= n) continue;
                
                if(!visited[xx][yy] || dp[xx][yy] > dp[poll.x][poll.y] + arr[xx][yy]) {
                    dp[xx][yy] = dp[poll.x][poll.y] + arr[xx][yy];
                    queue.add(new Point(xx, yy));
                    visited[xx][yy] = true;

                }
        	}
        }
        
    }
}