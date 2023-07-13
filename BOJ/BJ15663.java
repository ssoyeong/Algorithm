import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

// N과 M (9)

public class BJ15663 {
	
	static int n, m;
	static int[] arr;
	static int[] ans;
	static boolean[] visited;
	static HashSet<String> set = new HashSet<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		ans = new int[m];
		visited = new boolean[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		backTracking(0);
		System.out.println(sb.toString());
	}
	
	private static void backTracking(int depth) {
		
		if(depth == m) {
			
			StringBuilder temp = new StringBuilder();
			for(int i = 0; i < m; i++) {
				temp.append(ans[i] + " ");
			}
			
			if(!set.contains(temp.toString())) {
				set.add(temp.toString());
				sb.append(temp.toString() + "\n");
			}
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			ans[depth] = arr[i];
			backTracking(depth + 1);
			visited[i] = false;
		}
	}
}
