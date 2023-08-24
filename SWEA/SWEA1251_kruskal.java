import java.io.*;
import java.util.*;

// [S/W 문제해결 응용] 4일차 - 하나로

public class SWEA1251_kruskal {
	
	static class Edge implements Comparable<Edge> {
		int u;
		int v;
		double weight;
		
		Edge(int u, int v, double weight) {
			this.u = u;
			this.v = v;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return (int)(this.weight - o.weight);
		}
	}
	static int tc;
	static int n;
	static double e;
	static int[][] islands;
	static int[] parent;
	static PriorityQueue<Edge> queue = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		tc = Integer.parseInt(br.readLine());
		for(int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine());
			islands = new int[n][2];
			parent = new int[n];
			for(int i = 0; i < n; i++) {
				parent[i] = i;
			}
			
			StringTokenizer stX = new StringTokenizer(br.readLine());
			StringTokenizer stY = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				islands[i][0] = Integer.parseInt(stX.nextToken());
				islands[i][1] = Integer.parseInt(stY.nextToken());
			}
			
			e = Double.parseDouble(br.readLine());
			
			makeTunnels();
			double cost = selectTunnels();
			sb.append("#" + t + " " + Math.round(cost) + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void makeTunnels() {
		
		queue.clear();
		for(int i = 0; i < n - 1; i++) {
			for(int j = i + 1; j < n; j++) {
				double weight = calculateWeight(i, j);
				queue.add(new Edge(i, j, weight));
			}
		}
	}
	
	private static double calculateWeight(int u, int v) {
		return Math.pow(islands[u][0] - islands[v][0], 2) + Math.pow(islands[u][1] - islands[v][1], 2);
	}
	
	private static double selectTunnels() {
		
		int cnt = 0;
		double cost = 0;
		
		while(!queue.isEmpty()) {
			
			Edge poll = queue.poll();
			if(union(poll.u, poll.v)) {
				cost += e * poll.weight;
				if(++cnt == n - 1) return cost;
			}
		}
		
		return -1;
	}
	
	private static boolean union(int a, int b) {
		
		int pa = findParent(a);
		int pb = findParent(b);
		
		if(pa == pb) return false;
		parent[pb] = pa;
		return true;
	}
	
	private static int findParent(int x) {
		
		if(parent[x] == x) return x;
		return parent[x] = findParent(parent[x]);
	}

}
