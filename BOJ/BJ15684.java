import java.io.*;
import java.util.*;

// 사다리 조작

public class BJ15684 {
	
	static int n, m, h;
	static int ans;
	static boolean flag;
	static boolean[][] board;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		board = new boolean[h][n];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			board[a - 1][b - 1] = true;
		}
		
		solution();
		System.out.println(ans);
	}
	
	private static void solution() {
		
		while(true) {
			
			backTracking(0, 0, 0);
			if(flag) break;
			
			ans++;
			if(ans == 4) {
				ans = -1;
				break;
			}
		}
	}
	
	private static void backTracking(int row, int col, int cnt) {
		
		if(flag) return;
	
		if(cnt == ans) {
			if(isValid()) flag = true;
			return;
		}
		
		if(row == h || col == n) return;
		if(cnt == n) backTracking(row + 1, 0, cnt);
		
		for(int i = 0; i < n; i++) {
			if(isPromising(row, i)) {
				board[row][i] = true;
				backTracking(row, i + 1, cnt + 1);
				board[row][i] = false;
			}
		}
		
		backTracking(row + 1, 0, cnt);
	}
	
	private static boolean isValid() {
		
		for(int idx = 0; idx < n; idx++) {
			
			int col = idx;
			for(int row = 0; row < h; row++) {
				if(col - 1 >= 0 && board[row][col - 1] && board[row][col]) return false;
				if(col - 1 >= 0 && board[row][col - 1]) col--;
				else if(col < n - 1 && board[row][col]) col++;
			}
			
			if(idx != col) return false;
		}
		return true;
	}
	
	private static boolean isPromising(int r, int c) {
		
		if(board[r][c]) return false;
		if(c - 1 >= 0 && board[r][c - 1]) return false;
		if(c + 1 < n && board[r][c + 1]) return false;
		return true;
	}
}
