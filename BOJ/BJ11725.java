package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 트리의 부모 찾기

public class BJ11725 {
	
	static int n;
	static ArrayList<Integer>[] adj;
	static boolean[] visited;
	static int[] parent;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[n+1];
		for(int i = 0; i <= n; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		visited =  new boolean[n+1];
		parent = new int[n+1];
		
		StringTokenizer st;
		for(int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}
		
		bfs();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 2; i <= n; i++) {
			sb.append(parent[i]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void bfs() {
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);
		visited[1] = true;
		
		while(!queue.isEmpty()) {
			
			int poll = queue.poll();
			
			for(int i = 0; i < adj[poll].size(); i++) {
				int x = adj[poll].get(i);
				if(!visited[x]) {
					queue.add(x);
					visited[x] = true;
					parent[x] = poll;
				}
			}
		}
	}

}
