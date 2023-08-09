package a0808;

import java.io.*;
import java.util.*;

public class SWEA1227_2 {
	
	static final int SIZE = 100;
	static boolean flag;				// 하나의 테스트 케이스에 대해 도달 가능 여부
	static int[] start = new int[2];
	static int[] end = new int[2];
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] board = new boolean[SIZE][SIZE];
	static boolean[][] visited = new boolean[SIZE][SIZE];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= 10; tc++) {
			
			// flag 초기화
			flag = false;
			
			// visited[][] 초기화
			for(int i = 0; i < SIZE; i++) {
				Arrays.fill(visited[i], false);
			}
			
			// 입력 받기
			br.readLine();
			for(int i = 0; i < SIZE; i++) {
				String line = br.readLine();
				for(int j = 0; j < SIZE; j++) {
					char ch = line.charAt(j);
					
					if(ch == '0') {					// 길인 경우
						board[i][j] = false;
					}
					else if(ch == '1') {			// 벽인 경우
						board[i][j] = true;
					}
					else if(ch == '2') {			// 출발점인 경우
						start = new int[] {i, j};
					}
					else {							// 도착점인 경우
						end = new int[] {i, j};
					}
				}
			}
			
			// dfs 탐색하기
			dfs(start[0], start[1]);
			int ans = flag ? 1: 0;
			sb.append("#" + tc + " " + ans + "\n");
		}
		
		System.out.println(sb.toString());
	}

	private static void dfs(int x, int y) {
		
		if(flag) return;
		
		visited[x][y] = true;
		
		for(int i = 0; i < 4; i++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			
			if(xx < 0 || xx >= SIZE || yy < 0 || yy >= SIZE) continue;
			if(board[xx][yy] || visited[xx][yy]) continue;
			
			// 도착점을 만나면 바로 탐색 종료하기
			if(xx == end[0] && yy == end[1]) {
				flag = true;
			}
			
			dfs(xx, yy);
		}

	}

}
