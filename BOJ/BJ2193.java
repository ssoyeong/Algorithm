import java.io.*;

// 이친수

public class BJ2193 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[][] dp = new long[n][2];
		dp[0][0] = 0;
		dp[0][1] = 1;
		
		for(int i = 1; i < n; i++) {
			dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
			dp[i][1] = dp[i - 1][0];
		}
		
		System.out.println((dp[n - 1][0] + dp[n - 1][1]));
	}
}
