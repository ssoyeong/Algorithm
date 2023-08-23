import java.io.*;
import java.util.*;

// [S/W 문제해결 기본] 10일차 - Contact

public class SWEA1238 {
	
	static final int SIZE = 100;
	static boolean[] visited = new boolean[SIZE + 1];
	static ArrayList<Integer>[] adj;
	static ArrayDeque<int[]> queue = new ArrayDeque<>();
	static PriorityQueue<int[]> answer = new PriorityQueue<>((o1, o2) -> o1[1] == o2[1] ? Integer.compare(o2[0], o1[0]) : Integer.compare(o2[1], o1[1]));
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		adj = new ArrayList[SIZE + 1];
		
		for(int tc = 1; tc <= 10; tc++) {
			Arrays.fill(visited, false);
			for(int i = 1; i <= SIZE; i++) {
				adj[i] = new ArrayList<>();
			}
			queue.clear();
			answer.clear();
			
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n / 2; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(!adj[a].contains(b)) adj[a].add(b);
			}
			
			bfs(start);
			sb.append("#" + tc + " " + answer.poll()[0] + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void bfs(int start) {
		
		visited[start] = true;
		queue.add(new int[] {start, 0});
		answer.add(new int[] {start, 0});
		
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			for(int x : adj[poll[0]]) {
				if(visited[x]) continue;
				visited[x] = true;
				queue.add(new int[] {x, poll[1] + 1});
				answer.add(new int[] {x, poll[1] + 1});
			}
		}
	}

}
