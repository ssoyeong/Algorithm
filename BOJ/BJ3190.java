package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 뱀

public class BJ3190 {
	
	static class Point {
		
		int x;
		int y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int n, k, l;
	static int sec = 0;
	static int cvtIdx = 0;
	static int direction = 0;	// 0: E, 1: S, 2: W, 3: N
	static Point head;
	static Point tail;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[] cvtTime;
	static int[] cvtInfo;	// right: +1, left: +3
	static int[][] map;		// 0: empty, 1: apple, 2: snake
	static Queue<Point> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		map = new int[n+1][n+1];
		
		StringTokenizer st;
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = 1;
		}
		
		l = Integer.parseInt(br.readLine());
		cvtTime = new int[l];
		cvtInfo = new int[l];
		
		for(int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			cvtTime[i] = Integer.parseInt(st.nextToken());
			
			if(st.nextToken().equals("D")) cvtInfo[i] = 1;
			else cvtInfo[i] = 3;
		}
		
		head = new Point(1, 1);
		tail = new Point(1, 1);
		map[1][1] = 2;
		queue.add(head);
		
		while(true) {
			
			sec++;
			int xx = head.x + dx[direction];
			int yy = head.y + dy[direction];
			
			if(xx < 1 || xx > n || yy < 1 || yy > n) break;
			if(map[xx][yy] == 2) break;
			
			head = new Point(xx, yy);
			queue.add(head);
			
			if(map[xx][yy] == 0) {
				map[tail.x][tail.y] = 0;
				queue.poll();
				Point peek = queue.peek();
				tail = new Point(peek.x, peek.y);
			}
			
			map[xx][yy] = 2;
			
			if(cvtIdx < l && sec == cvtTime[cvtIdx]) {
				direction = (direction + cvtInfo[cvtIdx]) % 4;
				cvtIdx++;
			}
		}
		
		System.out.println(sec);
		
	}
}
