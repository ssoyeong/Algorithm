import java.io.*;
import java.util.*;

// 감시

public class BJ15683_2 {

	private static class CCTV {
        int x;
        int y;
        int type;

        CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
	static int n, m;
	static int numOfCCTV;
	static int ans = Integer.MAX_VALUE;
	static ArrayList<CCTV> cctv = new ArrayList<>();
	static int[][] cctvDirection = {{}, {0}, {0, 2}, {0, 1}, {0, 1, 2}, {0, 1, 2, 3}};
	static int[] loop = {0, 4, 2, 4, 4, 1};
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[][] board = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(1 <= board[i][j] && board[i][j] <= 5) {
					cctv.add(new CCTV(i, j, board[i][j]));
					numOfCCTV++;
				}
			}
		}
		
		dfs(0, board);
		System.out.println(ans);
	}
	
	private static void dfs(int depth, int[][] board) {
		
		if(depth == numOfCCTV) {
			int numOfBlind = calculateBlind(board);
			ans = Integer.min(ans, numOfBlind);
			return;
		}
		
		CCTV target = cctv.get(depth);
		for(int i = 0; i < loop[target.type]; i++) {
			
			int[][] tempBoard = new int[n][m];
			for(int r = 0; r < n; r++) {
				for(int c = 0; c < m; c++) {
					tempBoard[r][c] = board[r][c];
				}
			}
			
			for(int dir : cctvDirection[target.type]) {
				int d = dir + i > 3 ? dir + i - 4 : dir + i;
				int xx = target.x + dx[d];
				int yy = target.y + dy[d];
				while(0 <= xx && xx < n && 0 <= yy && yy < m && tempBoard[xx][yy] != 6) {
					if(tempBoard[xx][yy] == 0) tempBoard[xx][yy] = 9;
					xx += dx[d];
					yy += dy[d];
				}
			}
			
			dfs(depth + 1, tempBoard);
		}
	}
	
	private static int calculateBlind(int[][] board) {
		
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(board[i][j] == 0) cnt++;
			}
		}
		return cnt;
	}
}
