import java.io.*;
import java.util.*;

// 최소 스패닝 트리

public class SWEA3124_kruskal_list {

	static int tc;
	static int v, e;
	static boolean[] visited;
	static int[] minEdge;
	static ArrayList<int[]>[] graph;
	static PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tc = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			visited = new boolean[v + 1];
			minEdge = new int[v + 1];
			graph = new ArrayList[v + 1];
			for(int i = 1; i <= v; i++) {
				graph[i] = new ArrayList<>();
				minEdge[i] = Integer.MAX_VALUE;
			}
			
			for(int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				graph[a].add(new int[] {b, c});
				graph[b].add(new int[] {a, c});
			}
			
			sb.append("#" + t + " " + solution() + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static long solution() {
		
		long total = 0;
		int cnt = 0;
		queue.clear();
		queue.add(new int[] {1, 0});
		
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			
			if(visited[poll[0]]) continue;
			visited[poll[0]] = true;
			total += poll[1];
			if(cnt++ == v - 1) break;
			
			for(int[] edge : graph[poll[0]]) {
				if(!visited[edge[0]] && minEdge[edge[0]] > edge[1]) {
					minEdge[edge[0]] = edge[1];
					queue.add(edge);
				}
			}
		}
		return total;
	}
}