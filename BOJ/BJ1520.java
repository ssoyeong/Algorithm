package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

// 내리막 길

public class BJ1520 {
	
	static int m, n;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] map;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[m+1][n+1];
		dp = new int[m+1][n+1];
		
		for(int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if(map[1][1] <= map[m][n]) {
			System.out.println(0);
			System.exit(0);
		}
		
		for(int i = 1; i <= m; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		System.out.println(dfs(1, 1));
	}
	
	private static int dfs(int x, int y) {
		
		if(x == m && y == n) return 1;
		if(dp[x][y] != -1) return dp[x][y];
		
		dp[x][y] = 0;
		for(int i = 0; i < 4; i++) {
			
			int xx = x + dx[i];
			int yy = y + dy[i];
			
			if(xx <= 0 || xx > m || yy <= 0 || yy > n) continue;
			if(map[x][y] > map[xx][yy]) {
				dp[x][y] += dfs(xx, yy);
			}
		}
		
		return dp[x][y];
	}
}
