import java.io.*;
import java.util.*;

public class Main {
	
	static final int SIZE = 19;
	static int[][] board = new int[20][20];
	static boolean[][] visited = new boolean[20][20];
	static int[] dx = {-1, 0, 1, 1};
	static int[] dy = {1, 1, 1, 0};

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i = 1; i <= SIZE; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= SIZE; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solution();
	}
	
	private static void solution() {
		
		StringBuilder sb = new StringBuilder();
		
		for(int j = 1; j <= SIZE; j++) {
			for(int i = 1; i <= SIZE; i++) {
				
				if(!visited[i][j] && (board[i][j] == 1 || board[i][j] == 2)) {		// (i, j)칸에서 탐색을 시작할 수 있다면
					
					for(int d = 0; d < 4; d++) {
						int xx = i + dx[d];
						int yy = j + dy[d];
						if(xx < 1 || xx > SIZE || yy < 1 || yy > SIZE) continue;
						if(visited[xx][yy]) continue;
						
						visited[i][j] = true;
						boolean isValid = startSearch(i, j, d);		// (i, j)칸을 기준으로 d방향으로 탐색을 시작해서, 5개 연속인지의 여부 구하기
						
						if(isValid) {
							sb.append(board[i][j] + "\n" + i + " " + j + "\n");
							System.out.println(sb.toString());
							System.exit(0);
						}
					}
				}
			}
		}
		
		// 승부가 결정되지 않았다면
		System.out.println(0);
	}
	
	private static boolean startSearch(int x, int y, int d) {
		
		for(int time = 1; time < 5; time++) {
			int xx = x + dx[d] * time;
			int yy = y + dy[d] * time;
			
			if(xx < 1 || xx > SIZE || yy < 1 || yy > SIZE) return false;		// 범위를 벗어난다면, 5개가 연속할 수 없으므로 무효
			if(board[xx][yy] != board[x][y]) return false;						// 같은 색이 아니라면 무효
		}
		
		// 6개 이상 연속한다면 무효이므로 선택한 5개의 칸을 기준으로 앞, 뒤를 확인함
		// 앞
		int xx = x + dx[d] * -1;
		int yy = y + dy[d] * -1;
		if(0 < xx && xx <= SIZE && 0 < yy && yy <= SIZE) {
			if(board[xx][yy] == board[x][y]) return false;
		}
		
		// 뒤
		xx = x + dx[d] * 5;
		yy = y + dy[d] * 5;
		if(0 < xx && xx <= SIZE && 0 < yy && yy <= SIZE) {
			if(board[xx][yy] == board[x][y]) return false;
		}
		
		return true;
	}
}

