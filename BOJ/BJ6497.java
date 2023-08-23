import java.io.*;
import java.util.*;

// 전력난

public class BJ6497 {
	
	static int m, n;
	static int[] parent;
	static PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			
			queue.clear();
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			if(m == 0 && n == 0) break;
			
			parent = new int[m];
			for(int i = 0; i < m; i++) {
				parent[i] = i;
			}
			
			int totalCost = 0;
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				queue.add(new int[] {x, y, z});
				totalCost += z;
			}
			
			long ans = totalCost - kruskal();
			sb.append(ans + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static long kruskal() {
		
		int cnt = 0;
		long total = 0;
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			if(union(poll[0], poll[1])) {
				total += poll[2];
				cnt++;
				if(cnt == m - 1) return total;
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