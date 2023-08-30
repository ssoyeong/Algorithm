import java.io.*;
import java.util.*;

// ¸»ÀÌ µÇ°íÇÂ ¿ø¼þÀÌ

public class BJ1600 {
	
	static final int INF = 99999;
	static int maxK, w, h;
	static int ans = INF;
	static boolean[][] board;
	static int[][][] dp;
	static int[][] dx = {{-2, -1, 1, 2, 2, 1, -1, -2}, {-1, 0, 1, 0}};
	static int[][] dy = {{1, 2, 2, 1, -1, -2, -2, -1}, {0, 1, 0, -1}};
	static ArrayDeque<int[]> queue = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		maxK = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		board = new boolean[w][h];
		dp = new int[maxK + 1][w][h];
		
		for(int i = 0; i < w; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < h; j++) {
				if(st.nextToken().equals("1")) board[i][j] = true;
				for(int k = 0; k <= maxK; k++) {
					dp[k][i][j] = INF;
				}
			}
		}
		
		bfs(0, 0, 0, 0);
		if(ans == INF) ans = -1;
		System.out.println(ans);
	}
	
	private static void bfs(int x, int y, int cnt, int k) {
		
		dp[k][x][y] = cnt;
		queue.add(new int[] {x, y, cnt, k});
		
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			
			if(poll[0] == w - 1 && poll[1] == h - 1) {
				ans = Integer.min(ans, poll[2]);
				continue;
			}
			
			if(poll[3] < maxK) {
				for(int i = 0; i < 8; i++) {
					int xx = poll[0] + dx[0][i];
					int yy = poll[1] + dy[0][i];
					if(xx < 0 || xx >= w || yy < 0 || yy >= h || board[xx][yy]) continue;
					if(dp[poll[3] + 1][xx][yy] == INF || (dp[poll[3] + 1][xx][yy] > poll[2] + 1)) {
						dp[poll[3] + 1][xx][yy] = poll[2] + 1; 
						queue.add(new int[] {xx, yy, poll[2] + 1, poll[3] + 1});
					}
				}
			}
			
			for(int i = 0; i < 4; i++) {
				int xx = poll[0] + dx[1][i];
				int yy = poll[1] + dy[1][i];
				
				if(xx < 0 || xx >= w || yy < 0 || yy >= h || board[xx][yy]) continue;
				if(dp[poll[3]][xx][yy] == INF || (dp[poll[3]][xx][yy] > poll[2] + 1)) {
					dp[poll[3]][xx][yy] = poll[2] + 1; 
					queue.add(new int[] {xx, yy, poll[2] + 1, poll[3]});
				}
			}
		}

	}

}
