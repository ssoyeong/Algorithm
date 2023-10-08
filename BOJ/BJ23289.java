import java.io.*;
import java.util.*;

// 온풍기 안녕!

public class BJ23289 {
	
	static int r, c;
	static int[] dx = {0, 0, 0, -1, 1};
	static int[] dy = {0, 1, -1, 0, 0};
	static int[][] ddx = {{}, {-1, 0, 1}, {-1, 0, 1}, {-1, -1, -1}, {1, 1, 1}};
	static int[][] ddy = {{}, {1, 1, 1}, {-1, -1, -1}, {-1, 0, 1}, {-1, 0, 1}};
	
	static ArrayList<int[]> machines = new ArrayList<>();
	static ArrayList<int[]> targets = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] map = new int[r + 1][c + 1];
		boolean[][][] wall = new boolean[2][r + 1][c + 1];
		
		for(int i = 1; i <= r; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= c; j++) {
				map[i][j] = 0;
				int x = Integer.parseInt(st.nextToken());
				if(1 <= x && x <= 4) machines.add(new int[] {i, j, x});
				else if(x == 5) targets.add(new int[] {i, j});
			}
		}
		
		int w = Integer.parseInt(br.readLine());
		for(int i = 0; i < w; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			wall[t][x][y] = true;
		}
		
		int cnt = 0;
		while(true) {
			runWind(map, wall);
			adjustTemp(map, wall);
			if(++cnt > 100) break;
			if(isOverK(map, k)) break;
		}
		
		System.out.println(cnt);
	}
	
	private static void runWind(int[][] map, boolean[][][] wall) {
		
		int[][] tmp = new int[r + 1][c + 1];
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		
		for(int[] machine : machines) {
			
			boolean[][] visited = new boolean[r + 1][c + 1];
			int d = machine[2];
			
			int startX = machine[0] + dx[d];
			int startY = machine[1] + dy[d];
			visited[startX][startY] = true;
			tmp[startX][startY] += 5;
			queue.add(new int[] {startX, startY, 5});
			
			while(!queue.isEmpty()) {

				int[] xyt = queue.poll();
				int x = xyt[0];
				int y = xyt[1];
				int t = xyt[2];
				
				for(int i = 0; i < 3; i++) {
					int xx = x + ddx[d][i];
					int yy = y + ddy[d][i];
					
					if(xx < 1 || xx > r || yy < 1 || yy > c || visited[xx][yy]) continue;
					if(i == 1) {
						if(d == 1 && wall[1][x][y]) continue;
						else if(d == 2 && wall[1][x][y - 1]) continue;
						else if(d == 3 && wall[0][x][y]) continue;
						else if(d == 4 && wall[0][x + 1][y]) continue;
					}
					else if(i == 0) {
						if(d == 1 && (wall[0][x][y] || wall[1][x - 1][y])) continue;
						else if(d == 2 && (wall[0][x][y] || wall[1][x - 1][y - 1])) continue;
						else if(d == 3 && (wall[0][x][y - 1] || wall[1][x][y - 1])) continue;
						else if(d == 4 && (wall[0][x + 1][y - 1] || wall[1][x][y - 1])) continue;
					}
					else {
						if(d == 1 && (wall[0][x + 1][y] || wall[1][x + 1][y])) continue;
						else if(d == 2 && (wall[0][x + 1][y] || wall[1][x + 1][y - 1])) continue;
						else if(d == 3 && (wall[0][x][y + 1] || wall[1][x][y])) continue;
						else if(d == 4 && (wall[0][x + 1][y + 1] || wall[1][x][y])) continue;
					}
					
 					visited[xx][yy] = true;
					tmp[xx][yy] += t - 1;
					if(t > 1) queue.add(new int[] {xx, yy, t - 1});
				}
			}
		}
		
		for(int i = 1; i <= r; i++) {
			for(int j = 1; j <= c; j++) {
				map[i][j] += tmp[i][j];
			}
		}
	}
	
	private static void adjustTemp(int[][] map, boolean[][][] wall) {
		
		int[][] tmp = new int[r + 1][c + 1];
		
		// 인접한 행
		for(int j = 1; j <= c; j++) {
			for(int i = 1; i < r; i++) {
				if(wall[0][i + 1][j]) continue;
				int diff = Math.abs(map[i][j] - map[i + 1][j]) / 4;
				if(map[i][j] > map[i + 1][j]) {
					tmp[i][j] -= diff;
					tmp[i + 1][j] += diff;
				}
				else {
					tmp[i][j] += diff;
					tmp[i + 1][j] -= diff;
				}
			}
		}
		
		// 인접한 열
		for(int i = 1; i <= r; i++) {
			for(int j = 1; j < c; j++) {
				if(wall[1][i][j]) continue;
				int diff = Math.abs(map[i][j] - map[i][j + 1]) / 4;
				if(map[i][j] > map[i][j + 1]) {
					tmp[i][j] -= diff;
					tmp[i][j + 1] += diff;
				}
				else {
					tmp[i][j] += diff;
					tmp[i][j + 1] -= diff;
				}
			}
		}
		
		for(int i = 1; i <= r; i++) {
			for(int j = 1; j <= c; j++) {
				map[i][j] += tmp[i][j];
				if((i == 1 || i == r || j == 1 || j == c) && map[i][j] != 0) map[i][j]--;	// 가장자리 1 감소
			}
		}
	}
	
	private static boolean isOverK(int[][] map, int k) {
		for(int[] target : targets) {
			if(map[target[0]][target[1]] < k) return false;
		}
		return true;
	}

}
