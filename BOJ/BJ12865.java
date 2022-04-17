package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 평범한 배낭

public class BJ12865 {

	static int n;
	static int max;
	static int[] weight;
	static int[] value;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		max = Integer.parseInt(st.nextToken());
		weight = new int[n+1];
		value = new int[n+1];
		dp = new int[n+1][max+1];
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= n; i++) {
			
			int w = weight[i];
			int v = value[i];
			
			for(int j = 1; j <= max; j++) {
				
				dp[i][j] = dp[i-1][j];
				if(j - w >= 0) dp[i][j] = Math.max(dp[i-1][j], v + dp[i-1][j-w]);
			}
			
		}
		

		System.out.println(dp[n][max]);
	}
	
}
