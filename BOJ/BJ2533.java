import java.io.*;
import java.util.*;

// 사회망 서비스(SNS)

public class BJ2533 {

	static class Node {
		int v;
		Node next;
		
		Node(int v, Node next) {
			this.v = v;
			this.next = next;
		}
	}
	static int n;
	static Node[] adj;
	static int[][] dp;			// dp[x][0]: x가 얼리어덥터가 아닌 경우 x의 자식 트리까지의 최소 얼리 어덥터 수
								// dp[x][1]: x가 얼리어덥터인 경우 x의 자식 트리까지의 최소 얼리 어덥터 수
	static int[] visited;		// 트리의 depth를 알기 위해 int로 선언
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		adj = new Node[n + 1];
		dp = new int[n + 1][2];
		visited = new int[n + 1];
		
		StringTokenizer st;
		for(int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj[u] = new Node(v, adj[u]);
			adj[v] = new Node(u, adj[v]);
		}
		
		// 1번 노드부터 탐색 시작하기
		visited[1]++;
		dfs(1);
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}
	
	private static void dfs(int x) {
		
		boolean flag = false;
		for(Node node = adj[x]; node != null; node = node.next) {
			if(visited[node.v] == 0) {
				visited[node.v] = visited[x] + 1;
				dfs(node.v);
				flag = true;
			}
		}
		
		if(flag) {		// 리프 노드가 아닌 경우
			for(Node node = adj[x]; node != null; node = node.next) {
				if(visited[node.v] < visited[x]) continue;				// x와 연결된 노드 중 부모 노드는 패쓰
				
				dp[x][0] += dp[node.v][1];								// x가 얼리어덥터가 아니라면, 자식 노드들은 무조건 얼리어덥터야 함
				dp[x][1] += Math.min(dp[node.v][0], dp[node.v][1]);		// x가 얼리어덥터라면, 더 작은 값의 경우를 선택하면 됨
			}
			dp[x][1]++;													// x가 얼리어덥터가 됐으므로 하나 추가
		}
		else {			// 리프 노드인 경우
			dp[x][0] = 0;
			dp[x][1] = 1;
		}
	}
}
