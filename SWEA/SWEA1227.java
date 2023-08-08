package a0808;

import java.io.*;
import java.util.*;

public class SWEA1227 {
	
	static final int SIZE = 100;
	static int[] start = new int[2];
	static int[] end = new int[2];
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] board = new boolean[SIZE][SIZE];
	static boolean[][] visited = new boolean[SIZE][SIZE];
	static ArrayDeque<int[]> queue = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= 10; tc++) {
			
			// visited[][] 초기화
			for(int i = 0; i < SIZE; i++) {
				Arrays.fill(visited[i], false);
			}
			// queue 초기화
			queue.clear();
			
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
			
			// bfs 탐색하기
			int answer = bfs(start[0], start[1]);
			sb.append("#" + tc + " " + answer + "\n");
		}
		
		System.out.println(sb.toString());
	}

	private static int bfs(int x, int y) {
		
		visited[x][y] = true;
		queue.add(new int[] {x, y});
		
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int xx = poll[0] + dx[i];
				int yy = poll[1] + dy[i];
				
				if(xx < 0 || xx >= SIZE || yy < 0 || yy >= SIZE) continue;
				if(board[xx][yy] || visited[xx][yy]) continue;
				
				// 도착점을 만나면 바로 탐색 종료하기
				if(xx == end[0] && yy == end[1]) return 1;
				
				visited[xx][yy] = true;
				queue.add(new int[] {xx, yy});
			}
		}

		return 0;
	}

}
