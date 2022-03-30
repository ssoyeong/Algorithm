package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 이분 그래프

public class BJ1707 {
	
	static int numOfCase;
	static int numV;
	static int numE;
	static Queue<Integer> queue;
	static ArrayList<Integer>[] adj;
	static int[] visited;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		numOfCase = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < numOfCase; i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			numV = Integer.parseInt(st.nextToken());
			numE = Integer.parseInt(st.nextToken());
			adj = new ArrayList[numV+1];
			visited = new int[numV+1];
			
			for(int j = 0; j < adj.length; j++) {
				adj[j] = new ArrayList<Integer>();
			}

			for(int j = 0; j < numE; j++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				adj[u].add(v);
				adj[v].add(u);
			
			}
			bfs();
		}
		
	}

	
	private static void bfs() {
		
		queue = new LinkedList<>();
		
		for(int i = 1; i <= numV; i++) {
			if(visited[i] == 0) {		// 0: 방문 전 / 1: Group A / 2: Group B
				queue.add(i);
				visited[i] = 1;
			}
			
			while(!queue.isEmpty()) {
				int poll = queue.poll();
				
				for(int j = 0; j < adj[poll].size(); j++) {
					if(visited[adj[poll].get(j)] == 0) {
						queue.add(adj[poll].get(j));
					}
					if(visited[adj[poll].get(j)] == visited[poll]) {
						System.out.println("NO");
						return;
					}
					
					if(visited[poll] == 1 && visited[adj[poll].get(j)] == 0) {
						visited[adj[poll].get(j)] = 2;
					}
					else if(visited[poll] == 2 && visited[adj[poll].get(j)] == 0) {
						visited[adj[poll].get(j)] = 1;
					}
				}
			}
		}
		
		System.out.println("YES");
		
		
	}

}
