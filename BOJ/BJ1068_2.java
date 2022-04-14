package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 트리

public class BJ1068_2 {
	
	static int n;
	static int root;
	static int del;
	static boolean[] visited;		
	static ArrayList<Integer>[] list;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		visited = new boolean[n];
		list = new ArrayList[n];
		
		for(int i = 0; i < n; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			int x = Integer.parseInt(st.nextToken());
			
			if(x == -1) root = i;
			else list[x].add(i);
		}
		
		del = Integer.parseInt(br.readLine());
		visited[del] = true;
		
		System.out.println(dfs(root));
		
	}
	
	private static int dfs(int x) {
		
		// 방문했던 경우
		if(visited[x]) return 0;
		
		visited[x] = true;
		// leaf인 경우
		if(list[x].size() == 0 || (list[x].size() == 1 && list[x].get(0) == del)) return 1;		// del인 경우도 leaf로 여겨서 탐색하지 않도록. 즉 del 이하 subtree를 삭제한 것과 같은 효과
		
		// non-leaf인 경우
		int cnt = 0;
		for(int i = 0; i < list[x].size(); i++) {		// x의 각 leaf에 대해
 			cnt += dfs(list[x].get(i));
		}
		return cnt;
	}
	
	
}
