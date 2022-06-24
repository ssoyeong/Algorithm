package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// N과 M (4)

public class BJ15652 {
	
	static int n, m;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		backTracking(0, 0, "");
		System.out.println(sb.toString());
	}
	
	private static void backTracking(int depth, int idx, String str) {
		
		if(depth == m) {
			sb.append(str).append("\n");
			return;
		}
		
		for(int i = idx; i <= n; i++) {
				
			if(depth == 0 && i == 0) continue;
			backTracking(depth + 1, i, str + i + " ");
		}
	}

}
