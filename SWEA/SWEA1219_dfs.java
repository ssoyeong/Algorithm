import java.io.*;
import java.util.*;

// [S/W 문제해결 기본] 4일차 - 길찾기

public class SWEA1219_dfs {
	
	static class Node {
		int num;
		Node next;
		
		Node(int num, Node next) {
			this.num = num;
			this.next = next;
		}
	}
	static final int SIZE = 100;
	static int n;
	static boolean flag;
	static boolean[] visited = new boolean[SIZE];
	static Node[] adj = new Node[SIZE];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for(int t = 1; t <= 10; t++) {
			flag = false;
			Arrays.fill(visited, false);
			Arrays.fill(adj, null);
			
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			n = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				adj[u] = new Node(v, adj[u]);
			}
			
			visited[0] = true;
			dfs(0);
			int ans = flag ? 1 : 0;
			sb.append("#" + t + " " + ans + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void dfs(int x) {
		
		for(Node target = adj[x]; target != null; target = target.next) {
			if(!visited[target.num]) {
				if(target.num == 99) {
					flag = true;
					return;
				}
				visited[target.num] = true;
				dfs(target.num);
			}
		}
	}
}
