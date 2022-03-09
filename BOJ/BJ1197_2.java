package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

// 최소 스패닝 트리

public class BJ1197_2 {
	
	static int numV;
	static int numE;
	static PriorityQueue<Node> queue = new PriorityQueue<Node>();
	static int[] parent;
	static long total;
	static class Node implements Comparable<Node> {
		
		int from;
		int to;
		int value;
		
		Node(int from, int to, int value){
			
			this.from = from;
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
		parent = new int[numV+1];
		
		for(int i = 1; i <= numV; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < numE; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			
			queue.add(new Node(x, y, val));
		}
	
		kruskal();
		System.out.println(total);
		
	}

	private static void kruskal() {
		
		while(!queue.isEmpty()) {
			
			Node p = queue.poll();
			
			int parentFrom = findParent(p.from);
			int parentTo = findParent(p.to);
			
			if(parentFrom != parentTo) {
				union(parentFrom, parentTo);
				total += p.value;
			}
		}
	}
	
	private static int findParent(int x) {
		
		if(parent[x] == x) return x;
		parent[x] = findParent(parent[x]);		// 루트 노드를 찾아서 리턴한다.
		return parent[x];						// 시간 초과 방지, 재귀 효율화
	}
	
	private static void union(int x, int y) {
		parent[x] = y;
	}
}
