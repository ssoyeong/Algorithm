package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 가장 가까운 공통 조상

public class BJ3584 {

	static int numT;
	static int numN;
	static int[] parent;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		numT = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int i = 0; i < numT; i++) {
			numN = Integer.parseInt(br.readLine());
			parent = new int[numN + 1];
			visited = new boolean[numN + 1];
			for(int j = 1; j <= numN; j++) {
				parent[j] = j;
			}
			
			for(int k = 0; k < numN - 1; k++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
			
				parent[b] = a;
			}
			
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			setParents(a);
			sb.append(findCommon(b)).append('\n');
		}
		
		System.out.println(sb.toString());
	}
	
	private static int setParents(int x) {
		
		visited[x] = true;		// x의 부모들은 true로 설정
		
		if(parent[x] == x) return x;
		return setParents(parent[x]);
	}
	
	private static int findCommon(int b) {
		
		if(parent[b] == b) return b;
		else {
			if(visited[b]) return b;		// visited[b]가 true라면 가장 가까운 공통 부모이다.
			return findCommon(parent[b]);
		}
	}
}
