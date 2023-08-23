import java.io.*;
import java.util.*;

// 연구소

public class BJ14502_2 {
	
	static int n, m;
	static int ans;
	static int[][] board;
	static boolean[][] visited;
	static ArrayDeque<int[]> queue = new ArrayDeque<>();
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		backTracking(0, 0, 0);
		System.out.println(ans);
		
	}
	
	private static void backTracking(int depth, int r, int c) {
		
		if(depth == 3) {
			ans = Integer.max(ans, calcualteSafeZone());
			return;
		}
		
		for(int i = r; i < n; i++) {
			for(int j = c; j < m; j++) {
				if(board[i][j] == 0) {
					board[i][j] = 1;
					backTracking(depth + 1, i, j + 1);
					board[i][j] = 0;
				}
			}
			
			c = 0;
		}
	}
	
	private static int calcualteSafeZone() {
		
		int[][] tempBoard = new int[n][m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				tempBoard[i][j] = board[i][j];
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(tempBoard[i][j] == 2) {
					bfs(i, j, tempBoard);
				}
			}
		}
		
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(tempBoard[i][j] == 0) cnt++;
			}
		}
		return cnt;
	}
	
	private static void bfs(int x, int y, int[][] board) {
		
		queue.add(new int[] {x, y});
		
		while(!queue.isEmpty()) {
			
			int[] poll = queue.poll();
			for(int i = 0; i < 4; i++) {
				int xx = poll[0] + dx[i];
				int yy = poll[1] + dy[i];
				
				if(xx < 0 || xx >= n || yy < 0 || yy >= m) continue;
				if(board[xx][yy] == 0) {
					board[xx][yy] = 2;
					queue.add(new int[] {xx, yy});
				}
			}
		}
	}

}
