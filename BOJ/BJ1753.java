package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// 최단 경로

public class BJ1753 {
	
	static int numV;
	static int numE;
	static ArrayList<Node>[] adj;
	static PriorityQueue<Node> queue = new PriorityQueue<Node>();
	static Integer[] vertex;
	static boolean[] visited;
	static int start;
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
		start = Integer.parseInt(br.readLine());
		adj = new ArrayList[numV+1];
		vertex = new Integer[numV+1];
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
		}
		
		dijkstra();
		
		for(int i = 1; i <= numV; i++) {
			if(vertex[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			}
			else {
				System.out.println(vertex[i]);
			}
		}
		
	}
	
	private static void dijkstra() {
		
		Arrays.fill(vertex, Integer.MAX_VALUE);
		vertex[start] = 0;
		
		queue.add(new Node(start, 0));
		
		while(!queue.isEmpty()) {
			
			Node p = queue.poll();
			
			if(visited[p.to]) continue;
			
			visited[p.to] = true;
			
			for(Node n : adj[p.to]) {
				if(visited[n.to]) continue;
				if(vertex[n.to] > vertex[p.to] + n.value) {
					vertex[n.to] = vertex[p.to] + n.value;
				}
				queue.add(new Node(n.to, vertex[n.to]));
			}
			
		}
	}

}
