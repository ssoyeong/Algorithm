package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 로또

public class BJ2758 {
	
	static int t;
	static int n;
	static int m;
	static long[][] dp;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			dp = new long[n+1][m+1];
			
			solution();
			
		}
	}
	
	private static void solution() {
		
		for(int i = 1; i <= m; i++) {
			dp[1][i] = 1;
		}
		
		for(int i = 2; i <= n; i++) {
			for(int j = 2; j <= m; j++) {
				
				if(j%2 == 0) {
					dp[i][j] = dp[i][j-1] + dp[i-1][j/2];
				}
				else {
					dp[i][j] = dp[i][j-1];
				}
			}
		}
		
		long cnt = 0;
		int start = (int)Math.pow(2, n-1);		// n번째 고르는 수 중 가장 작은 값
		for(int i = start; i <= m; i++) {
			cnt += dp[n][i];
		}
		
		System.out.println(cnt);
	}
	
	
}
