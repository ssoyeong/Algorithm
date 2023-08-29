import java.io.*;
import java.util.*;

// 1로 만들기

public class BJ1463_2 {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		dp[1] = 0;
		
		for(int i = 2; i <= n; i++) {
			if(i % 6 == 0) dp[i] = Integer.min(dp[i / 3], Integer.min(dp[i / 2], dp[i - 1])) + 1;
			else if(i % 3 == 0) dp[i] = Integer.min(dp[i / 3], dp[i - 1]) + 1;
			else if(i % 2 == 0) dp[i] = Integer.min(dp[i / 2], dp[i - 1]) + 1;
			else dp[i] = dp[i - 1] + 1;
		}
		System.out.println(dp[n]);
	}
}
