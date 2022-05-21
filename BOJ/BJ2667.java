package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.io.IOException;

// 단지번호붙이기

public class BJ2667 {
	
	static class Point {
		
		int x;
		int y;
		
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static int n;
	static int total;		// 총 단지 수
	static int cnt;			// 단지별 집 수
	static int[] arr = new int[625];	// 각 단지의 집 수, cnt를 담을 배열
	static boolean[][] map;
	static boolean[][] visited;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new boolean[n][n];
		visited = new boolean[n][n];
		
		Arrays.fill(arr, Integer.MAX_VALUE);
		
		for(int i = 0; i < n; i++) {
			String str = br.readLine();
			for(int j = 0; j < n; j++) {
				if(str.charAt(j) == '1') map[i][j] = true;
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] && !visited[i][j]) {
					cnt = 0;
					bfs(i, j);
					arr[total++] = cnt;
					
				}
			}
		}
		
		print();
	}
	
	private static void bfs(int x, int y) {
		
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(x, y));
		visited[x][y] = true;
		
		while(!queue.isEmpty()) {
			
			Point poll = queue.poll();
			cnt++;
			
			for(int i = 0; i < 4; i++) {
				int xx = poll.x + dx[i];
				int yy = poll.y + dy[i];
				
				if(0 <= xx && xx < n && 0 <= yy && yy < n) {
					if(map[xx][yy] && !visited[xx][yy]) {
						queue.add(new Point(xx, yy));
						visited[xx][yy] = true;
					}
				}
			}
		}	
	}
	
	private static void print() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(total).append("\n");
		
		Arrays.sort(arr);
		for(int i = 0; i < total; i++) {
			sb.append(arr[i]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
