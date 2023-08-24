
import java.io.*;
import java.util.*;

// [S/W 문제해결 응용] 4일차 - 하나로

public class SWEA1251_prim {
	
	static int tc;
	static int n;
	static double e;
	static int[][] islands;
	static double[][] weights;
	static double[] minEdges;
	static boolean[] visited;
	
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
		
		int cnt = 0;
		double cost = 0;
		minEdges[0] = 0;
		
		for(int i = 0; i < n; i++) {
			
			int minVertex = -1;
			double min = Double.MAX_VALUE;
			
			for(int j = 0; j < n; j++) {
				if(!visited[j] && min > minEdges[j]) {
					minVertex = j;
					min = minEdges[j];
				}
			}
			
			visited[minVertex] = true;
			cost += min * e;
			if(cnt++ == n - 1) break;
			
			for(int j = 0; j < n; j++) {
				if(!visited[j] && weights[minVertex][j] != 0 && minEdges[j] > weights[minVertex][j]) {
					minEdges[j] = weights[minVertex][j];
				}
			}
		}
		
		return cost;
	}

}
