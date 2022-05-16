package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 유기농 배추

public class BJ1012 {
	
	static class Point {
		
		int r;
		int c;
		
		Point(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static int t;
	static int m;
	static int n;
	static int k;
	static boolean[][] field;
	static boolean[][] visited;
	static int cnt;
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		t = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int numT = 0; numT < t; numT++) {
			
			cnt = 0;
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			field = new boolean[m][n];
			visited = new boolean[m][n];
			
			for(int numK = 0; numK < k; numK++) {
				st = new StringTokenizer(br.readLine());
				field[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
			}
			
			for(int i = 0; i < m; i++) {
				for(int j = 0; j < n; j++) {
					
					if(field[i][j] && !visited[i][j]) {
						bfs(i, j);
					}
				}
			}
			
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void bfs(int r, int c) {
		
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(r, c));
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			
			Point poll = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int xx = poll.r + dx[i];
				int yy = poll.c + dy[i];
				if(xx < 0 || xx >= m || yy < 0 || yy >= n) continue;
				if(field[xx][yy] && !visited[xx][yy]) {
					queue.add(new Point(xx, yy));
					visited[xx][yy] = true;
				}
			}
		}
		
		cnt++;
	}
	
}
