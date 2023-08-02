import java.io.*;
import java.util.*;

// N과 M (6)

public class BJ15655 {
	
	static int n, m;
	static int[] arr;
	static int[] output;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		output = new int[m];
		visited = new boolean[n];
	
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		dfs(0, 0);
		System.out.println(sb.toString());
	}

	private static void dfs(int depth, int idx) {

		if(depth == m) {
			for(int i = 0; i < m; i++) {
				sb.append(output[i] + " ");
			}
			sb.append("\n");
			return;
		}

		for(int i = idx; i < n; i++) {
			if(visited[i]) continue;

			visited[i] = true;
			output[depth] = arr[i];

			dfs(depth + 1, i + 1);
			visited[i] = false;
		}
	}

}
