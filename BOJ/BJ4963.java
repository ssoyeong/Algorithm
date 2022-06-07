package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

// 섬의 개수

public class BJ4963 {
	
	static class Point {
		
		int x;
		int y;
		
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static int w;
	static int h;
	static boolean[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
	static int cnt;
	static Stack<Point> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if(w == 0 && h == 0) break;
			
			map = new boolean[h+1][w+1];
			visited = new boolean[h+1][w+1];
			cnt = 0;
			for(int i = 1; i <= w; i++) {
				for(int j = 1; j <= h; j++) {
					map[j][i] = false;
					visited[j][i] = false;
				}
			}
			
			for(int i = 1; i <= h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1; j <= w; j++) {
					int x = Integer.parseInt(st.nextToken());
					if(x == 1) map[i][j] = true;
				}
			}
			
			for(int i = 1; i <= w; i++) {
				for(int j = 1; j <= h; j++) {
					if(!visited[j][i]) {
						if(map[j][i]) dfs(i, j);
						else visited[j][i] = true;
					}
				}
			}
	
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void dfs(int x, int y) {
		
		stack.add(new Point(x, y));
		visited[y][x] = true;
		
		boolean flag = false;		// 섬이 여러 칸으로 이루어진 경우 판단
		int isOne = 1;				// 섬이 한 칸으로 이루어진 경우 판단
		
		while(!stack.isEmpty()) {
			
			Point poll = stack.pop();
			for(int i = 0; i < dx.length; i++) {
				
				int xx = poll.x + dx[i];
				int yy = poll.y + dy[i];
				if(1 > xx || xx > w || 1 > yy || yy > h) continue;
				
				if(map[yy][xx] && !visited[yy][xx]) {
					stack.add(new Point(xx, yy));
					visited[yy][xx] = true;
					flag = true;
					isOne++;
				}
			}
		}
		
		if(flag || isOne == 1) cnt++;
	}
  
}
