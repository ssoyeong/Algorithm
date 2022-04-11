package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 여행 가자

public class BJ1976 {
	
	static int num;
	static int numDest;
	static int[] dest;
	static int[] parent;
	static int root;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(br.readLine());
		numDest = Integer.parseInt(br.readLine());
		parent = new int[num+1];
		
		for(int i = 1; i <= num; i++) {
			parent[i] = i;
		}
		
		// union
		StringTokenizer st;
		for(int i = 1; i <= num; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= num; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) {
					if(i == j) continue;
					union(i, j);
				}
			}
		}
		
		// find
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < numDest; i++) {
			int city = Integer.parseInt(st.nextToken());
			if(i == 0) {
				root = findParent(city);
				continue;
			}
			if(root != findParent(city)) {
				System.out.println("NO");
				System.exit(0);
			}
		}
		System.out.println("YES");
	}
	
	private static void union(int a, int b) {
		
		a = findParent(a);
		b = findParent(b);
		
		if(a != b) {
			if(a < b) parent[b] = a;
			else parent[a] = b;
		}
	}
	
	private static int findParent(int x) {
		if(x == parent[x]) return x;
		return findParent(parent[x]);
	}

}
