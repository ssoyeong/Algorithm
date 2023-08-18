import java.io.*;
import java.util.*;

// 알파벳

public class BJ1987_2 {
	
	static int r, c;
	static int ans;
	static char[][] board;
	static boolean[] visited = new boolean[26];
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		board = new char[r][c];
		
		for(int i = 0; i < r; i++) {
			String line = br.readLine();
			for(int j = 0; j < c; j++) {
				board[i][j] = line.charAt(j);
			}
		}
		
		visited[board[0][0] - 65] = true;
		dfs(0, 0, 1);
		System.out.println(ans);
	}
	
	private static void dfs(int x, int y, int cnt) {
		
		boolean flag = false;
		
		for(int i = 0; i < 4; i++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			
			if(xx < 0 || xx >= r || yy < 0 || yy >= c) continue;
			if(visited[board[xx][yy] - 65]) continue;
			flag = true;
			
			visited[board[xx][yy] - 65] = true;
			dfs(xx, yy, cnt + 1);
			visited[board[xx][yy] - 65] = false;
		}
		
		if(!flag) {
			ans = Integer.max(ans, cnt);
			return;
		}
	}
}
