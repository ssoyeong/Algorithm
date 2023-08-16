import java.io.*;
import java.util.*;

// 빵집

public class BJ3109 {
	
	static int r, c;
	static int ans;
	static boolean flag;			// dfs를 종료하는 플래그
	static char[][] board;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1};
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		board = new char[r + 1][c + 1];
		visited = new boolean[r + 1][c + 1];
		
		for(int i = 1; i <= r; i++) {
			String line = br.readLine();
			for(int j = 1; j <= c; j++) {
				board[i][j] = line.charAt(j - 1);
			}
		}
		
		for(int i = 1; i < r; i++) {
			flag = false;
			visited[i][1] = true;
			dfs(i, 1);
		}
		
		System.out.println(ans);
	}
	
	private static void dfs(int row, int col) {
		
		if(col == c) {
			ans++;
			flag = true;
			return;
		}
		
		for(int i = 0; i < 3; i++) {
			int xx = row + dx[i];
			int yy = col + 1;
			
			if(xx < 1 || xx > r || visited[xx][yy]) continue;
			
			if(board[xx][yy] == '.') {
				visited[xx][yy] = true;
				dfs(xx, yy);
			}
			
			if(flag) break;				// 유효한 경로로 탐색을 끝까지 완료했다면, 다른 방향으로 탐색할 필요가 없기에 for문 탈출
		}
		return;
	}

}
