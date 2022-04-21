package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 인구 이동

public class BJ16234 {
	
	static class Node {
		
		int idx;
		int size;
		int sum;
		
		Node(int idx, int size, int sum){
			this.idx = idx;
			this.size = size;
			this.sum = sum;
		}
	}
	
	static int N, L, R;
	static int[][] board;
	static Node[] parent;
	static int day;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		board = new int[N][N];
		parent = new Node[N*N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			
			init();
			boolean moving = false;
			
			for(int a = 0; a < N; a++) {
				for(int b = 0; b < N; b++) {
					
					int uc = caseOfUnion(a, b);		// 어느 방향과 국경선을 공유할지 구분
					
					if(uc == 3) continue;
					if(uc != 1) {
						int x = board[a][b];
						int y = board[a+1][b];
						if(L <= Math.abs(x - y) && Math.abs(x - y) <= R) {
							union(a * N + b, (a+1) * N + b);
							moving = true;
						}
					}
					if(uc != 2) {
						int x = board[a][b];
						int y = board[a][b+1];
						if(L <= Math.abs(x - y) && Math.abs(x - y) <= R) {
							union(a * N + b, a * N + (b+1));
							moving = true;
						}
					}
				}
			}
			
			if(!moving) break;
			else {
				update();
				day++;
			}
		}
		
		System.out.println(day);
	}
		
	private static void init() {
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				Node node = new Node(i * N + j, 1, board[i][j]);
				parent[i * N + j] = node;
			}
		}
	}
		
	private static int caseOfUnion(int a, int b) {
		
		if(a == N-1 && b == N-1) return 3; 		// 마지막 행 & 열, 공유 X
		else if(a == N-1) return 1;				// 마지막 행, 오른쪽 칸만 공유
		else if(b == N-1) return 2;				// 마지막 열, 아래 칸만 공유
		else return 0;							// 나머지, 오른쪽 & 아래 모두 공유
	}
		
	private static void union(int idxA, int idxB) {
		
		int pa = findParent(idxA);
		int pb = findParent(idxB);
		
		if(pa == pb) return;
		parent[pb].idx = pa;
		parent[pa].size += parent[pb].size;
		parent[pa].sum += parent[pb].sum;
	}
	
	private static int findParent(int x) {
		
		if(parent[x].idx == x) return x;
		return parent[x].idx = findParent(parent[x].idx);
	}
	
	private static void update() {
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				
				int px = findParent(i * N + j);
				board[i][j] = parent[px].sum / parent[px].size;
			}
		}
	}
	
}
