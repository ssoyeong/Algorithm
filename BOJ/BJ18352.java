import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

// 특정 거리의 도시 찾기

public class BJ18352 {
	
	private static class Node implements Comparable<Node> {
		int to;
		int cost;
		
		Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	static int n, m, k, x;
	static int[] cost;
	static boolean[] visited;
	static ArrayList<Integer>[] adj;
	static PriorityQueue<Node> queue = new PriorityQueue<>();
	static final int INF = 999999999;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		
		cost = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			cost[i] = INF;
		}
		visited = new boolean[n + 1];
		adj = new ArrayList[n + 1];
		for(int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int  v = Integer.parseInt(st.nextToken());
			adj[u].add(v);
		}
		
		dijkstra();
		
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		
		for(int i = 1; i <= n; i++) {
			if(i == x) continue;
			
			if(cost[i] == k) {
				sb.append(i + "\n");
				flag = true;
			}
		}
		
		if(!flag) sb.append("-1\n");
		
		System.out.println(sb.toString());
	}
	
	private static void dijkstra() {
		
		cost[x] = 0;
		visited[x] = true;
		queue.add(new Node(x, 0));
		
		while(!queue.isEmpty()) {
			
			Node poll = queue.poll();
			
			for(int i = 0; i < adj[poll.to].size(); i++) {
				
				int target = adj[poll.to].get(i);
				if(visited[target]) continue;
				
				cost[target] = poll.cost + 1;
				visited[target] = true;
				queue.add(new Node(target, cost[target]));
			}
		}
	}
	
}
