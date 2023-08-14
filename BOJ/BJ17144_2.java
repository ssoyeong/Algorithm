import java.io.*;
import java.util.*;

// 미세먼지 안녕!

public class BJ17144_2 {
	
	static int r, c, t;
	static int airCleanerIdx;			// 공기청정기의 두번째 행 인덱스
	static int[][] board;
	static int[] dx = {-1, 0, 1, 0};	// 위쪽 공기청정기 방향
	static int[] dy = {0, 1, 0, -1};
	static int[] dx2 = {1, 0, -1, 0};	// 아래쪽 공기청정기 방향
	static int[] dy2 = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		board = new int[r][c];
		
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < c; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == -1) airCleanerIdx = i;
			}
		}
		
		// 풀이
		for(int i = 0; i < t; i++) {
			spreadDust();
			runAirCleaner();
		}
		
		// 출력
		int ans = 0;
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				ans += board[i][j];
			}
		}
		System.out.println(ans + 2);
		
	}
	
	private static void spreadDust() {
		
		int[][] tempBoard = new int[r][c];
		
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(board[i][j] > 0) {
					
					int amount = board[i][j] / 5;		// 확산되는 양
					int cnt = 0;						// 확산할 수 있는 방향의 개수
					
					for(int d = 0; d < 4; d++) {
						int xx = i + dx[d];
						int yy = j + dy[d];
						
						if(xx < 0 || xx >= r || yy < 0 || yy >= c) continue;
						if(board[xx][yy] != -1) {						// 공기청정기가 아니라면, 확산 가능
							tempBoard[xx][yy] += amount;
							cnt++;										
						}
					}
					
					tempBoard[i][j] += board[i][j] - amount * cnt;		// 확산되고 남은 미세먼지의 양 더해주기
				}
			}
		}
		
		// board에 tempBoard 복사하기
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				board[i][j] = tempBoard[i][j];
			}
		}
		
		// 기존 공기청정기 설치
		board[airCleanerIdx][0] = -1;
		board[airCleanerIdx - 1][0] = -1;
	}
	
	private static void runAirCleaner() {
		
		cycle(airCleanerIdx - 1, true);		// 위쪽 공기청정기
		cycle(airCleanerIdx, false);		// 아래쪽 공기청정기
	}
	
	private static void cycle(int startRowIndex, boolean flag) {
		
		int x = startRowIndex;
		int y = 0;

		int d = 0;
		x = flag ? x + dx[d] : x + dx2[d];
		y = flag ? y + dy[d] : y + dy2[d];
			
		while(true) {
				
			int xx = flag ? x + dx[d] : x + dx2[d];
			int yy = flag ? y + dy[d] : y + dy2[d];
			
			if(flag) {
				if(xx < 0 || xx > startRowIndex || yy < 0 || yy >= c) {
					d = (d + 1) % 4;
					continue;
				}
			}
			else {
				if(xx < startRowIndex || xx >= r || yy < 0 || yy >= c) {
					d = (d + 1) % 4;
					continue;
				}
			}

			if(board[xx][yy] == -1) {
				board[x][y] = 0;
				break;
			}
				
			board[x][y] = board[xx][yy];
			x = xx;
			y = yy;
		}
	}

}
