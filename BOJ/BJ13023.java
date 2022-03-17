package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

// ABCDE 일직선 관계 찾기

public class BJ13023 {
	
	static int numV;
	static int numE;
	static ArrayList<Integer>[] adj;	// 2차원 배열 사용하면 시간초과 발생
	static boolean[] visited;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		numV = Integer.parseInt(st.nextToken());
		numE = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[numV];
		visited = new boolean[numV];
		for(int i = 0; i < numV; i++) {
			adj[i] = new ArrayList<Integer>();
		}

		for(int i = 0; i < numE; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adj[from].add(to);
			adj[to].add(from);
		}
		
		if(numE < 4) {
			System.out.println(0);
			System.exit(0);
		}
		
		for(int i = 0; i < numV; i++) {
			dfs(i, 0);
		}
		
		System.out.println(0);

	}
	
	private static void dfs(int start, int cnt) {
		
		visited[start] = true;
		if(cnt == 4) {
			System.out.println(1);
			System.exit(0);
		}
	
		for(int i = 0; i < adj[start].size(); i++) {
			int v = adj[start].get(i);
			if(!visited[v]) {
				dfs(v, cnt + 1);	// argument에 증감 연산자 사용 시 호출 순서에 의해 값이 달라짐
			}
		}
		
		visited[start] = false;		// 재탐색을 위해 방문한 노드는 false 처리
	}

}
