package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 파도반 수열

public class BJ9461 {
	
	static int t;
	static long[] dp;
	static int[] cases;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		cases = new int[t];
		
		int max = 0;
		for(int i = 0; i < t; i++) {
			cases[i] = Integer.parseInt(br.readLine());
			max = Integer.max(max, cases[i]);
		}
		
		dp = new long[max+1];
		
		if(max >= 1) dp[1] = 1;
		if(max >= 2) dp[2] = 1;
		if(max >= 3) dp[3] = 1;
		if(max >= 4) dp[4] = 2;
		if(max >= 5) dp[5] = 2;
		
		StringBuilder sb = new StringBuilder();
		if(max > 5) {
			for(int i = 6; i <= max; i++) {
				dp[i] = dp[i-1] + dp[i-5];
			}
		}
		
		for(int i = 0; i < cases.length; i++) {
			sb.append(dp[cases[i]]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
