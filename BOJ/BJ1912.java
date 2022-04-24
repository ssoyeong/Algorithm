package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

// 연속합

public class BJ1912 {

	static int n;
	static int[] arr;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		dp = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0] = arr[0];
		for(int i = 1; i < n; i++) {
			dp[i] = Integer.max(dp[i-1] + arr[i], arr[i]);
		}
		
		Arrays.sort(dp);
		System.out.println(dp[dp.length - 1]);
	}
}
