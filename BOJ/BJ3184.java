import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

// 양

public class BJ3184 {
	
	private static class Point {
		int x;
		int y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int r, c;
	static char[][] board;
	static boolean[][] visited;
	static int cntOfSheep, cntOfWolf;
	static int answerSheep, answerWolf;
	static Queue<Point> queue = new LinkedList<>();
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		board = new char[r][c];
		visited = new boolean[r][c];
		
		for(int i = 0; i < r; i++) {
			String line = br.readLine();
			for(int j = 0; j < c; j++) {
				board[i][j] = line.charAt(j);
			}
		}
		
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(visited[i][j] || board[i][j] == '#') continue;
				cntOfSheep = 0;
				cntOfWolf = 0;
				bfs(i, j);
				if(cntOfSheep > cntOfWolf) {
					answerSheep += cntOfSheep;
				}
				else {
					answerWolf += cntOfWolf;
				}
			}
		}
		
		System.out.println(answerSheep + " " + answerWolf);
	}
	
	private static void bfs(int startX, int startY) {
		
		visited[startX][startY] = true;
		queue.add(new Point(startX, startY));
		
		if(board[startX][startY] == 'o') cntOfSheep++;
		else if(board[startX][startY] == 'v') cntOfWolf++;
		
		while(!queue.isEmpty()) {
			
			Point poll = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				
				int xx = poll.x + dx[i];
				int yy = poll.y + dy[i];
				
				if(xx < 0 || xx >= r || yy < 0 || yy >= c) continue;
				if(visited[xx][yy] || board[xx][yy] == '#') continue;
				
				visited[xx][yy] = true;
				queue.add(new Point(xx, yy));
				
				if(board[xx][yy] == 'o') cntOfSheep++;
				else if(board[xx][yy] == 'v') cntOfWolf++;
			}
		}
	}

}
