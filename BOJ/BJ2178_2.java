import java.io.*;
import java.util.*;

// 미로 탐색

public class BJ2178_2 {
	
	static int n, m;
	static int ans;
	static boolean[][] board;
	static boolean[][] visited;
	static ArrayDeque<int[]> queue = new ArrayDeque<>();
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new boolean[n][m];
		visited = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			for(int j = 0; j < m; j++) {
				if(line.charAt(j) == '1') board[i][j] = true;
			}
		}
		
		bfs(0, 0, 1);
		System.out.println(ans);
	}

	private static void bfs(int x, int y, int cnt) {
		
		visited[x][y] = true;
		queue.add(new int[] {x, y, cnt});
		
		while(!queue.isEmpty()) {
			
			int[] poll = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int xx = poll[0] + dx[i];
				int yy = poll[1] + dy[i];
				
				if(xx == n - 1 && yy == m - 1) {
					ans = poll[2] + 1;
					return;
				}
				if(xx < 0 || xx >= n || yy < 0 || yy >= m) continue;
				if(board[xx][yy] && !visited[xx][yy]) {
					visited[xx][yy] = true;
					queue.add(new int[] {xx, yy, poll[2] + 1});
				}
			}
		}
	}
}
