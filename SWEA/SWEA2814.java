package SWEA;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 최장 경로 

public class SWEA2814 {

	static int t;
	static int n, m;
	static ArrayList<Integer> adj[];
	static boolean[] visited;
	static int max;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc <= t; tc++) {
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			adj = new ArrayList[n+1];
			for(int i = 1; i <= n; i++) {
				adj[i] = new ArrayList<>();
			}
			visited = new boolean[n+1];
			max = 0;
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				adj[x].add(y);
				adj[y].add(x);
			}
			
			for(int i = 1; i <= n; i++) {
				
				if(visited[i]) continue;
				
				visited[i] = true;
				dfs(i, 1);
				visited[i] = false;
			}
			
			sb.append(String.format("#%d %d\n", tc, max));
		}
		
		System.out.println(sb.toString());
	}
	
	private static void dfs(int start, int cnt) {
		
		for(int i = 0; i < adj[start].size(); i++) {
			
			if(visited[adj[start].get(i)]) continue;
			
			visited[adj[start].get(i)] = true;
			dfs(adj[start].get(i), cnt+1);
			visited[adj[start].get(i)]= false;
		}
		
		max = Integer.max(max, cnt);
	}
	 
}
