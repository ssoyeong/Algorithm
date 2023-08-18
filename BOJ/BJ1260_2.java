import java.io.*;
import java.util.*;


// DFS와 BFS

public class BJ1260_2 {
	
	static int n, m, v;
	static boolean[] visited;
	static ArrayList<Integer>[] adj;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		visited = new boolean[n + 1];
		adj = new ArrayList[n + 1];
		for(int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}
		
		for(int i = 1; i <= n; i++) {
			Collections.sort(adj[i]);
		}
		
		visited[v] = true;
		sb.append(v + " ");
		dfs(v);
		
		sb.append("\n");
		Arrays.fill(visited, false);
		
		bfs(v);
		System.out.println(sb.toString());
	}
	
	private static void dfs(int target) {
		
		for(int x : adj[target]) {
			if(visited[x]) continue;
			visited[x] = true;
			sb.append(x + " ");
			dfs(x);
		}
	}
	
	private static void bfs(int target) {
		
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		visited[target] = true;
		sb.append(target + " ");
		queue.add(target);
		
		while(!queue.isEmpty()) {
			target = queue.poll();

			for(int x : adj[target]) {
				if(visited[x]) continue;
				visited[x] = true;
				sb.append(x + " ");
				queue.add(x);
			}
		}
	}

}
