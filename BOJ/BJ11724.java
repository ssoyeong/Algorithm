package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 연결 요소의 개수

public class BJ11724 {
	
	static int n;
	static int m;
	static ArrayList<Integer>[] edges;
	static boolean[] visited;
	static int cnt;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		edges = new ArrayList[n+1];
		for(int i = 0; i <= n; i++) {
			edges[i] = new ArrayList<Integer>();
		}
		visited = new boolean[n+1];
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges[a].add(b);
			edges[b].add(a);
		}
		
		for(int i = 1; i <= n; i++) {
			if(visited[i]) continue;
			dfs(i);
			cnt++;
		}
		
		System.out.println(cnt);
	}
	
	private static void dfs(int x) {
		
		visited[x] = true;
		for(int i = 0; i < edges[x].size(); i++) {
			if(visited[edges[x].get(i)]) continue;
			dfs(edges[x].get(i));
		}
	}

}
