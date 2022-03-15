package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// DFS and BFS

public class BJ1260 {
	
	static int n;
	static int m;
	static int v;
	static boolean[][] adj;
	static ArrayList<Integer> visited = new ArrayList<>();
	static Queue<Integer> queue = new LinkedList<>();
	static Stack<Integer> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		
		adj = new boolean[n+1][n+1];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a][b] = true;
			adj[b][a] = true;
		}
		
		dfs();
		printVisited();
		visited.clear();
		bfs();
		printVisited();
	}
	
	private static void dfs() {
		
		stack.add(v);
		while(!stack.isEmpty()) {
			int top = stack.pop();
			if(!visited.contains(top)) visited.add(top);
			for(int i = adj.length - 1; i >= 1; i--) {
				if(adj[top][i] && !visited.contains(i)) {
					stack.add(i);
				}
			}
		}
	}
	
	private static void bfs() {
		queue.add(v);
		while(!queue.isEmpty()) {
			int front = queue.poll();
			if(!visited.contains(front)) visited.add(front);
			for(int i = 1; i <= adj.length - 1; i++) {
				if(adj[front][i] && !visited.contains(i)) {
					queue.add(i);
				}
			}
		}
		
	}
	
	private static void printVisited() {
		for(int x : visited) {
			System.out.print(x + " ");
		}
		System.out.println();
	}
}
