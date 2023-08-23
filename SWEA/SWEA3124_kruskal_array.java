import java.io.*;
import java.util.*;

// 최소 스패닝 트리

public class SWEA3124_kruskal_array {

	static int tc;
	static int v, e;
	static int[] parent;
	static int[][] graph;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tc = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			graph = new int[e][3];
			parent = new int[v + 1];
			for(int i = 1; i <= v; i++) {
				parent[i] = i;
			}
			
			for(int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				graph[i] = new int[] {a, b, c};
			}
			
			sb.append("#" + t + " " + solution() + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static long solution() {
		
		Arrays.sort(graph, (o1, o2) -> Integer.compare(o1[2], o2[2]));
		
		long total = 0;
		int cnt = 0;
		
		for(int[] edge : graph) {
			if(union(edge[0], edge[1])) {
				total += edge[2];
				cnt++;
				if(cnt == v - 1) break;
			}
		}
		return total;
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
