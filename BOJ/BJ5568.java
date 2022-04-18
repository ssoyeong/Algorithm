package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.io.IOException;

// 카드 놓기

public class BJ5568 {
	
	static int n;
	static int k;
	static String[] arr;
	static boolean[] visited;
	static HashSet<String> set = new HashSet<>();
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		arr = new String[n];
		visited = new boolean[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = br.readLine();
		}
		
		dfs(0, 0);
		System.out.println(set.size());
	
	}
	
	private static void dfs(int level, int idx) {
		
		if(level == k) {
			String number = sb.toString();
			if(!set.contains(number)) set.add(number);
			return;
		}
		
		if(level == 0) sb = new StringBuilder();
		
		for(int i = 0; i < n; i++) {
			if(level != 0 && i == idx) continue;
			if(!visited[i]) {
				visited[i] = true;
				sb.append(arr[i]);
				dfs(level + 1, i);
				visited[i] = false;
				
                int start = sb.length() - arr[i].length();
				sb.delete(start, sb.length());
			}
		}
	}

}
