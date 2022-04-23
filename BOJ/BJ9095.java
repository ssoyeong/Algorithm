package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 1, 2, 3 더하기

public class BJ9095 {
	
	static int test;
	static int n;
	static int[] dp = new int[11];
	
	public static void main(String[] args) throws IOException {
		
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		
		for(int i = 4; i < 11; i++) {
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		test = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < test; i++) {
			n = Integer.parseInt(br.readLine());
			sb.append(dp[n]).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
