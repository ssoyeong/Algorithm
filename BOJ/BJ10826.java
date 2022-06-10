package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.io.IOException;

// 피보나치 수 4

public class BJ10826 {
	
	static int n;
	static BigInteger[] dp;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		dp = new BigInteger[n+1];
		
		if(n <= 1) sb.append(n).append("\n");
		else {
			dp[0] = BigInteger.ZERO;
			dp[1] = BigInteger.ONE;
			
			for(int i = 2; i <= n; i++) {
				dp[i] = dp[i-1].add(dp[i-2]);
			}
			
			sb.append(dp[n]).append("\n");
		}

		System.out.println(sb.toString());
	}

}
