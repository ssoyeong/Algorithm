package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// DFS with recursion and BFS

public class BJ1260_2 {
	
	static int n;
	static int m;
	static int v;
	static boolean[][] adj;
	static boolean[] visited;
	static Queue<Integer> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		
		adj = new boolean[n+1][n+1];
		visited = new boolean[n+1];
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a][b] = true;
			adj[b][a] = true;
		}
		
		dfs(v);
		visited = new boolean[n+1];
		System.out.println();
		bfs();
	}
	
	private static void dfs(int x) {
		
		visited[x] = true;
		System.out.print(x + " ");
		
		for(int i = 1; i <= adj.length -1; i++) {
			if(adj[x][i] && !visited[i]) {
				dfs(i);
			}
		}
	}
	
	private static void bfs() {
		queue.add(v);
		visited[v] = true;
		System.out.print(v + " ");
		while(!queue.isEmpty()) {
			int front = queue.poll();
			for(int i = 1; i <= adj.length - 1; i++) {
				if(adj[front][i] && !visited[i]) {
					queue.add(i);
					visited[i] = true;
					System.out.print(i + " ");
				}
			}
		}
		
		System.out.println();
	}
	
}
