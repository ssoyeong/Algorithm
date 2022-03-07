package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 행렬 곱셈 순서

public class BJ11049 {
	
	static StringTokenizer st;
	static int n;
	static int[] arr;
	static int[][] dp;

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1];
		dp = new int[n][n];
	
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
		arr[n] = Integer.parseInt(st.nextToken());
		
		
		for(int i = 1; i < n; i++) {
			for(int j = 0; j < n - i; j++) {
				dp[j][j+i] = Integer.MAX_VALUE;
				for(int k = j+1; k <= j+i; k++) {
					dp[j][j+i] = Math.min(dp[j][j+i], 
							dp[j][k-1] + dp[k][j+i] + arr[j]*arr[k]*arr[j+i+1]);
				}
			}
		}
		
		System.out.println(dp[0][dp.length-1]);
		
	}

}
