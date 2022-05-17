package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 2xn 타일링

public class BJ11726 {
	
	static int n;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dp = new int[n+1];
		
		dp[1] = 1;
		if(n > 1) {
			dp[2] = 2;
			for(int i = 3; i <= n; i++) {
				dp[i] = (dp[i-1] + dp[i-2]) % 10007;
			}
		}
		
		System.out.println(dp[n]);
		
	}

}
