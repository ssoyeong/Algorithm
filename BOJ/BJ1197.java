package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

// 최소 스패닝 트리

public class BJ1197 {

	static int numV;
	static int numE;
	
	static ArrayList<Node>[] adj;
	static PriorityQueue<Node> queue = new PriorityQueue<Node>();
	static boolean[] visited;
	static long total;
	static class Node implements Comparable<Node> {
		
		int to;
		int value;
		
		Node(int to, int value){
			
			this.to = to;
			this.value = value;
		}
		
		@Override
		public int compareTo(Node o) {
			
			return this.value - o.value;
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		numV = Integer.parseInt(st.nextToken());
		numE = Integer.parseInt(st.nextToken());
		adj = new ArrayList[numV+1];
		visited = new boolean[numV+1];
		
		for(int i = 1; i <= numV; i++) {
			adj[i] = new ArrayList<Node>();
		}
		
		for(int i = 0; i < numE; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			
			adj[x].add(new Node(y, val));
			adj[y].add(new Node(x, val));
		}
	
		prim(1);	// 임의로 1번 vertex에서 탐색 시작
		System.out.println(total);
		
	}

	private static void prim(int start) {
		
		queue.add(new Node(start, 0));
		
		while(!queue.isEmpty()) {
			Node p = queue.poll();
			
			if(visited[p.to]) continue;
			
			visited[p.to] = true;
			total += p.value;
			
			for(Node n : adj[p.to]) {
				if(visited[n.to]) continue;
				queue.add(n);
			}
		}
	}
	
}
