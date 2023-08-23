import java.io.*;
import java.util.*;

// 다리 만들기 2

public class BJ17472_2 {
	
	static class Bridge implements Comparable<Bridge> {
		int idA;
		int idB;
		int length;
		
		Bridge(int idA, int idB, int length) {
			this.idA = idA;
			this.idB = idB;
			this.length = length;
		}
		
		@Override
		public boolean equals(Object o) {
			return (this.length == ((Bridge)o).length) && ((this.idA == ((Bridge)o).idA) && (this.idB == ((Bridge)o).idB) || (this.idA == ((Bridge)o).idB) && (this.idB == ((Bridge)o).idA));
		}
		
		@Override
		public int compareTo(Bridge o) {
			return this.length - o.length;
		}
	}
	static int n, m;
	static int numOfIslands;
	static int[][] board;
	static int[] parent;
	static ArrayDeque<int[]> queue = new ArrayDeque<>();
	static PriorityQueue<Bridge> bridges = new PriorityQueue<>();
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				if(st.nextToken().equals("1")) board[i][j] = 9;
			}
		}

		makeIslands();
		makeBridges();
		System.out.println(selectBridges());
	}
	
	private static void makeIslands() {
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(board[i][j] == 9) {
					bfs(i, j, ++numOfIslands);
				}
			}
		}
	}
	
	private static void bfs(int x, int y, int id) {
		
		board[x][y] = id;
		queue.add(new int[] {x, y});
		
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int xx = poll[0] + dx[i];
				int yy = poll[1] + dy[i];
				
				if(xx < 0 || xx >= n || yy < 0 || yy >= m) continue;
				if(board[xx][yy] == 9) {
					board[xx][yy] = id;
					queue.add(new int[] {xx, yy});
				}
			}
		}
	}
	
	private static void makeBridges() {
		
		parent = new int[numOfIslands + 1];
		for(int i = 1; i <= numOfIslands; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(board[i][j] != 0) {
					for(int d = 0; d < 4; d++) {
						dfs(i, j, d);
					}
				}
			}
		}
	}
	
	private static void dfs(int r, int c, int d) {

		int x = r + dx[d];
		int y = c + dy[d];
		int length = 0;
		int connectedBridgeId = 0;
		
		while(true) {
			if(x < 0 || x >= n || y < 0 || y >= m || board[x][y] == board[r][c]) break;
			if(board[x][y] != 0) {
				connectedBridgeId = board[x][y];
				break;
			}
			length++;
			x += dx[d];
			y += dy[d];
		}
		
		if(length > 1 && connectedBridgeId != 0) {
			Bridge newBridge = new Bridge(board[r][c], connectedBridgeId, length);
			if(!bridges.contains(newBridge)) {
				bridges.add(newBridge);
			}
		}
	}
	
	private static int selectBridges() {
		
		int selected = 0;
		int total = 0;
		while(!bridges.isEmpty()) {
			Bridge poll = bridges.poll();
			
			if(union(poll.idA, poll.idB)) {
				total += poll.length;
				selected++;
				if(selected == numOfIslands - 1) return total;
			}
		}
		
		return -1;
	}
	
	private static boolean union(int a, int b) {
		
		int pa = findParent(a);
		int pb = findParent(b);
		
		if(pa == pb) return false;
		parent[pb] = pa;
		return true;
	}
	
	private static int findParent(int x) {
		
		if(parent[x] == x) return x;
		return parent[x] = findParent(parent[x]);
	}

}
