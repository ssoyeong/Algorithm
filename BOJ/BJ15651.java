package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// N과 M (3)

public class BJ15651 {
	
	static int m, n;
	static int[] arr;
	static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		arr = new int[n];
		
		backTracking(0, 0);
		
		System.out.println(sb.toString());
	}
	
	private static void backTracking(int level, int idx) {
		
		if(level == n) {
			for(int i = 0; i < n; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 1; i <= m; i++) {
			arr[idx] = i;
			backTracking(level+1, idx+1);
		}
	}

}
