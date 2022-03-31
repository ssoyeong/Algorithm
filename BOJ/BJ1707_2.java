package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

// 이분 그래프

public class BJ1707_2 {
	
	static int numOfCase;
	static int numV;
	static int numE;
	static Stack<Integer> stack;
	static ArrayList<Integer>[] adj;
	static int[] visited;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		numOfCase = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < numOfCase; i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			numV = Integer.parseInt(st.nextToken());
			numE = Integer.parseInt(st.nextToken());
			adj = new ArrayList[numV+1];
			visited = new int[numV+1];
			
			for(int j = 0; j < adj.length; j++) {
				adj[j] = new ArrayList<Integer>();
			}

			for(int j = 0; j < numE; j++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				adj[u].add(v);
				adj[v].add(u);
			
			}
			
			dfs();
		}
		
	}
	
	private static void dfs() {
		
		stack = new Stack<>();
		
		for(int i = 1; i <= numV; i++) {
			if(visited[i] == 0) {		// 0: 방문 전 / 1: Group A / 2: Group B
				stack.add(i);
				visited[i] = 1;
			}
			
			while(!stack.isEmpty()) {
				
				int top = stack.pop();
				for(int j = adj[top].size() - 1; j >= 0; j--) {
					
					if(visited[adj[top].get(j)] == 0) {
						stack.add(adj[top].get(j));
					}
					if(visited[adj[top].get(j)] == visited[top]) {
						System.out.println("NO");
						return;
					}
					
					if(visited[adj[top].get(j)] == 0 && visited[top] == 1) {
						visited[adj[top].get(j)] = 2;
					}
					else if(visited[adj[top].get(j)] == 0 && visited[top] == 2) {
						visited[adj[top].get(j)] = 1;
					}
				}
			}
		}
		
		System.out.println("YES");
	}
	
}
