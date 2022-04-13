package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

// 친구 네트워크

public class BJ4195 {
	
	static int test;
	static int num;
	static int[] parent;
	static int[] level;
	static HashMap<String, Integer> friends = new HashMap<>();		// 이름과 parent를 담는 map
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		test = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int i = 0; i < test; i++) {
			
			num = Integer.parseInt(br.readLine());
			friends.clear();
			parent = new int[num * 2];
			level = new int[num * 2];
			Arrays.fill(level, 1);
			
			int idx = 0;
			for(int j = 0; j < num; j++) {
				
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				
				if(!friends.containsKey(a)) {
					parent[idx] = idx;
					friends.put(a, idx++);
				}
				if(!friends.containsKey(b)) {
					parent[idx] = idx;
					friends.put(b, idx++);
				}
				
				union(friends.get(a), friends.get(b));
				
				sb.append(level[findParent(friends.get(a))]).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	private static void union(int a, int b) {
		
		int pa = findParent(a);
		int pb = findParent(b);
		
		if(pa == pb) return;
		parent[pb] = pa;			// pb의 부모를 pa로 설정하였으므로
		level[pa] += level[pb];		// pb의 서브트리만큼 pa의 서브트리가 늘어났기에 더해준다.
	}
	
	private static int findParent(int x) {
		
		if(x == parent[x]) return x;
		return parent[x] = findParent(parent[x]);
	}
	
}
