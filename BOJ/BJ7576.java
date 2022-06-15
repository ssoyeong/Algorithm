package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 토마토

public class BJ7576 {
	
	private static class Point {
		
		int x;
		int y;
		int day;
		
		Point(int x, int y, int day) {
			this.x = x;
			this.y = y;
			this.day = day;
		}
	}
	
	static int m, n;
	static int answer;
	static int[][] box;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static Queue<Point> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		box = new int[n+1][m+1];
		
		boolean flag = false;
		for(int i = 1; i <= n; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j <= m ; j++) {
				
				int x = Integer.parseInt(st.nextToken());
				if(x == 1) queue.add(new Point(i, j, 0));
				else if(x == 0) flag = true;
				box[i][j] = x;
			}
		}
		
		if(!flag) answer = 0;
		else {
			
			solution();
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= m; j++) {	
					if(box[i][j] == 0) {
						answer = -1;
						break;
					}
				}
			}
		}
		
		System.out.println(answer);
	}
	
	private static void solution() {
		
		while(!queue.isEmpty()) {
			
			Point poll = queue.poll();
		
			for(int i = 0; i < 4; i++) {
				
				int xx = poll.x + dx[i];
				int yy = poll.y + dy[i];
				
				if(xx <= 0 || xx > n || yy <= 0 || yy > m) continue;
				if(box[xx][yy] == 0) {
					box[xx][yy] = 1;
					queue.add(new Point(xx, yy, poll.day + 1));
				}
			}
			
			if(queue.isEmpty()) answer = poll.day;
		}
	}


}
