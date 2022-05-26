package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 바이러스

public class BJ2606 {
	
	static int n;
	static int numAdj;
	static int cnt;
	static boolean[] visited;
	static ArrayList<Integer>[] adj;
	static Queue<Integer> queue = new LinkedList<>();
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		numAdj = Integer.parseInt(br.readLine());
		
		visited = new boolean[n+1];
		adj = new ArrayList[n+1];
		for(int i = 0; i < n+1; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		
		StringTokenizer st;
		for(int i = 0; i < numAdj; i++) {
			
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			adj[u].add(v);
			adj[v].add(u);
		}
		
		solution(1);
		
		System.out.println(cnt-1);		// 시작 컴퓨터 제외
	}
	
	static private void solution(int start) {
		
		queue.add(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			
			int poll = queue.poll();
			cnt++;
			
			for(int i = 0; i < adj[poll].size(); i++) {
				int x = adj[poll].get(i);
				if(!visited[x]) {
					queue.add(x);
					visited[x] = true;
				}
			}
		}
	}

}
