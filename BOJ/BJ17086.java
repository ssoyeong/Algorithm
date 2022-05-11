package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 아기 상어 2

public class BJ17086 {
	
	static int n;
	static int m;
	static int answer;
	static int[] x = {-1, 0, -1, -1, 1, 1, 0, 1};
	static int[] y = {-1, -1, 1, 0, 0, -1, 1, 1};
	static boolean[][] map;
	static int[][] dist;
	static Queue<Shark> queue = new LinkedList<>();
	
	static class Shark {
		
		int x;
		int y;
		
		Shark(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new boolean[n][m];
		dist = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				if(st.nextToken().equals("1")) {
					map[i][j] = true;
					queue.add(new Shark(j, i));
				}
			}
		}
		
		bfs();
		System.out.println(answer);
	}
	
	private static void bfs() {
		
		while(!queue.isEmpty()) {
			
			Shark poll = queue.poll();
			int px = poll.x;
			int py = poll.y;
			for(int i = 0; i < x.length; i++) {
				int xx = px + x[i];
				int yy = py + y[i];
				if(xx < 0 || yy < 0 || xx >= m || yy >= n) continue;
				if(map[yy][xx] || dist[yy][xx] != 0) continue;
				dist[yy][xx] = dist[py][px] + 1;
				answer = Math.max(answer, dist[yy][xx]);
				queue.add(new Shark(xx, yy));
			}
			
		}
	}
}
