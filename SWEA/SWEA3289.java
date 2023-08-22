import java.io.*;
import java.util.*;

// 서로소 집합

public class SWEA3289 {

	static int tc;
	static int n, m;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		tc = Integer.parseInt(br.readLine());
		for(int t = 1; t <= tc; t++) {
			sb.append("#" + t + " ");
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			parent = new int[n + 1];
			for(int i = 1; i <= n; i++) parent[i] = i;
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int flag = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(flag == 0) union(a, b);
				else {
					if(findParent(a) == findParent(b)) sb.append("1");
					else sb.append("0");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static void union(int a, int b) {
		
		int pa = findParent(a);
		int pb = findParent(b);
		
		if(pa < pb) parent[pb] = pa;
		else if(pb < pa) parent[pa] = pb;
	}
	
	private static int findParent(int x) {
		if(parent[x] == x) return x;
		return parent[x] = findParent(parent[x]);
	}
}
