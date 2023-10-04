import java.io.*;
import java.util.*;

// [모의 SW 역량테스트] 탈주범 검거

public class SWEA1953 {
	
	static class Point {
		int x;
		int y;
		int type;
		int time;
		
		Point(int x, int y, int type, int time) {
			this.x = x;
			this.y = y;
			this.type = type;
			this.time = time;
		}
	}
	static int n, m, l;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] dir = {{}, {0, 1, 2, 3}, {0, 2}, {1, 3}, {0, 1}, {1, 2}, {2, 3}, {0, 3}};
	static int[][] types = {{1, 2, 5, 6}, {1, 3, 6, 7}, {1, 2, 4, 7}, {1, 3, 4, 5}};
	static ArrayDeque<Point> queue = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			
			map = new int[n][m];
			visited = new boolean[n][m];
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < m; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			visited[r][c] = true;
			queue.add(new Point(r, c, map[r][c], 1));
			sb.append("#" + t + " " + solution() + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static int solution() {
		
		int cnt = 1;
		
		while(!queue.isEmpty()) {
			
			Point poll = queue.poll();			
			if(poll.time >= l) continue;
			
			for(int i = 0; i < dir[poll.type].length; i++) {
				
				int d = dir[poll.type][i];
				int xx = poll.x + dx[d];
				int yy = poll.y + dy[d];
				
				if(xx < 0 || xx >= n || yy < 0 || yy >= m || visited[xx][yy]) continue;
				
				for(int j = 0; j < 4; j++) {
					if(types[d][j] == map[xx][yy]) {
						visited[xx][yy] = true;
						queue.add(new Point(xx, yy, map[xx][yy], poll.time + 1));
						cnt++;
					}
				}
			}
		}
		
		return cnt;
	}
}
