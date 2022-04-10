package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 집합의 표현

public class BJ1717 {
	
	static int n;
	static int m;
	static int cmd;
	static int a;
	static int b;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parent = new int[n+1];
		
		for(int i = 0; i <= n; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			cmd = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			if(cmd == 0) union(a, b);
			else sb.append(findParent(a) == findParent(b) ? "YES" : "NO").append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void union(int a, int b) {
		
		if(a == b) return;
		
		a = findParent(a);
		b = findParent(b);
		if(a != b) {
			parent[b] = a;
		}
	}
	
	private static int findParent(int x) {
		
		if(x == parent[x]) return x;
		return parent[x] = findParent(parent[x]);		// 재귀 호출 과정에서 부모 노드를 루트 노드로 갱신
														// 압축 최적화
	}
}
