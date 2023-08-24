import java.io.*;
import java.util.*;

// [S/W 문제해결 응용] 4일차 - 하나로

public class SWEA1251_primPQ {
	
	static class Edge implements Comparable<Edge> {
		int v;
		double weight;
		
		Edge(int v, double weight) {
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
	static double[][] weights;
	static double[] minEdges;
	static boolean[] visited;
	static PriorityQueue<Edge> queue = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		tc = Integer.parseInt(br.readLine());
		for(int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine());
			islands = new int[n][2];
			weights = new double[n][n];
			minEdges = new double[n];
			visited = new boolean[n];
			
			StringTokenizer stX = new StringTokenizer(br.readLine());
			StringTokenizer stY = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				islands[i][0] = Integer.parseInt(stX.nextToken());
				islands[i][1] = Integer.parseInt(stY.nextToken());
				minEdges[i] = Double.MAX_VALUE;
			}
			
			e = Double.parseDouble(br.readLine());
			
			makeTunnels();
			double cost = selectTunnels();
			sb.append("#" + t + " " + Math.round(cost) + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void makeTunnels() {
		
		for(int i = 0; i < n - 1; i++) {
			for(int j = i + 1; j < n; j++) {
				weights[i][j] = calculateWeight(i, j);
				weights[j][i] = weights[i][j];
			}
		}
	}
	
	private static double calculateWeight(int u, int v) {
		return Math.pow(islands[u][0] - islands[v][0], 2) + Math.pow(islands[u][1] - islands[v][1], 2);
	}
	
	private static double selectTunnels() {
		
		queue.clear();
		int cnt = 0;
		double cost = 0;
		minEdges[0] = 0;
		queue.add(new Edge(0, minEdges[0]));
		
		while(!queue.isEmpty()) {
			
			Edge poll = queue.poll();
			int minVertex = poll.v;
			double min = poll.weight;
			if(visited[minVertex]) continue;
			
			visited[minVertex] = true;
			cost += min * e;
			if(cnt++ == n - 1) break;
			
			for(int j = 0; j < n; j++) {
				if(!visited[j] && weights[minVertex][j] != 0 && minEdges[j] > weights[minVertex][j]) {
					minEdges[j] = weights[minVertex][j];
					queue.add(new Edge(j, minEdges[j]));
				}
			}
		}
		
		return cost;
	}

}
