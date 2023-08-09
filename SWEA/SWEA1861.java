import java.io.*;
import java.util.*;

// 정사각형 방

public class SWEA1861 {

	static int tc;
	static int n;
	static int[][] board;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[] answer = new int[2];		// 출발 방 번호, 방의 개수
	static ArrayDeque<int[]> queue = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tc = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine());
			board = new int[n][n];
			
			answer[0] = Integer.MAX_VALUE;
			answer[1] = 0;
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			solution();
			sb.append("#" + t + " " + answer[0] + " " + answer[1] + " " + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void solution() {
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				int cnt = bfs(i, j);
				
				// answer 업데이트하기
				if(answer[1] <= cnt) {
					if(answer[1] == cnt) {
						if(answer[0] > board[i][j]) answer[0] = board[i][j];
					}
					else {
						answer[1] = cnt;
						answer[0] = board[i][j];
					}
				}
			}
		}
	}
	
	private static int bfs(int x, int y) {
		
		int cnt = 1;		// 연속한 방의 수
		queue.add(new int[] {x, y});
		
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int xx = poll[0] + dx[i];
				int yy = poll[1] + dy[i];
				
				if(xx < 0 || xx >= n || yy < 0 || yy >= n) continue;
				if(board[xx][yy] == board[poll[0]][poll[1]] + 1) {
					queue.add(new int[] {xx, yy});
					cnt++;
				}
			}
		}
		
		return cnt;
	}
}
