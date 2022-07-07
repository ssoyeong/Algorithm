package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 이차원 배열과 연산

public class BJ17140 {
	
	static class Node implements Comparable<Node> {
		
		int num;
		int cnt;
		
		Node(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Node o) {
			if(this.cnt == o.cnt) return this.num - o.num;
			else return this.cnt - o.cnt;
		}
		
	}
	
	static int r, c, k;
	static int[][] board = new int[101][101];
	static HashMap<Integer, Integer> map = new HashMap<>();
	static PriorityQueue<Node> queue = new PriorityQueue<>();
	static int answer;
	static int rowSize = 3, colSize = 3;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// read input
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= rowSize; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= colSize; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// solution
		while(true) {
			if(board[r][c] == k) break;
			
			answer++;
			if(answer > 100) {
				answer = -1;
				break;
			}
			
			solution();
		}
		
		System.out.println(answer);
	}
	
	private static void solution() {
		
		if(rowSize >= colSize) calculate(true);	// R 연산
		else calculate(false);	// C 연산
		
	}
	
	private static void calculate(boolean flag) {
		
		int rowN = 0;
		int colN = 0;
		int maxSize = 0;
		
		if(flag) {
			rowN = rowSize;
			colN = colSize;
		}
		else {
			rowN = colSize;
			colN = rowSize;
		}
		
		for(int i = 1; i <= rowN; i++) {
			
			map.clear();
			queue.clear();
			
			// insert (number, count) into map
			for(int j = 1; j <= colN; j++) {
				
				int x = (flag) ? board[i][j] : board[j][i];
				
				if(x == 0) continue;
				if(map.containsKey(x)) {
					int count = map.get(x);
					map.put(x, count + 1);
				}
				else map.put(x, 1);
			}
			
			// set the size of the next board
			maxSize = Math.max(maxSize, map.size());
			if(flag) colSize = maxSize * 2;
			else rowSize = maxSize * 2;
			
			// insert map values into queue
			for(Integer key : map.keySet()) {
				Node node = new Node(key, map.get(key));
				queue.add(node);
			}
			
			int queueSize = queue.size();
			
			// restructure the board
			int idx = 1;
			while(!queue.isEmpty()) {
				Node poll = queue.poll();
				
				if(flag) {
					board[i][idx++] = poll.num;
					board[i][idx++] = poll.cnt;
				}
				else {
					board[idx++][i] = poll.num;
					board[idx++][i] = poll.cnt;
				}
				
				if(idx > 100) break;
			}
			
			// set zeros
			if(queueSize < colN && idx < 100) {
				for(int k = queue.size(); k < colN; k++) {
					if(flag) {
						board[i][idx++] = 0;
						board[i][idx++] = 0;
					}
					else {
						board[idx++][i] = 0;
						board[idx++][i] = 0;
					}
					
					if(idx > 100) break;
				}
			}
		}
	}

}
